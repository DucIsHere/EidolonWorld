package com.eidolonworld.density_function_types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunction.FunctionContext;

public record AmplifyNoise(
        DensityFunction input,
        double multiplier,    // cường độ amplify, ví dụ 4.5
        double warpStrength,  // warp theo X/Z
        double maxHeight      // chiều cao max, ví dụ 15000
) implements DensityFunction {

    public static final Codec<HyperWarpAmplify> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    DensityFunction.HOLDER_HELPER_CODEC.fieldOf("input").forGetter(HyperWarpAmplify::input),
                    Codec.DOUBLE.fieldOf("multiplier").forGetter(HyperWarpAmplify::multiplier),
                    Codec.DOUBLE.fieldOf("warpStrength").forGetter(HyperWarpAmplify::warpStrength),
                    Codec.DOUBLE.fieldOf("maxHeight").forGetter(HyperWarpAmplify::maxHeight)
            ).apply(instance, HyperWarpAmplify::new)
    );

    @Override
    public double compute(FunctionContext ctx) {
        double x = ctx.blockX();
        double z = ctx.blockZ();

        // ---- Step 1: Domain warp X/Z ----
        double warpedX = x + FlatDomainWarp.computeX(x, z, warpStrength);
        double warpedZ = z + FlatDomainWarp.computeZ(x, z, warpStrength);

        // ---- Step 2: Base noise ----
        double base = input.compute(ctx);

        // ---- Step 3: Multi-layer sine/cos waves ----
        double wave1 = Math.sin(warpedX * 0.01) * Math.cos(warpedZ * 0.01);
        double wave2 = Math.sin(warpedX * 0.02 + warpedZ * 0.03);
        double wave3 = Math.cos(warpedX * 0.015 - warpedZ * 0.02);

        double waveCombined = wave1 * 0.3 + wave2 * 0.4 + wave3 * 0.3;

        // ---- Step 4: Sqrt/Division + Signum ----
        double amplitude = Sqrt.compute(base * base + 0.001) / Division.compute(base, 2.0);
        double sign = Signum.compute(amplitude);

        // ---- Step 5: Combine all ----
        double amplified = base * (1.0 + sign * amplitude * multiplier + waveCombined * multiplier);

        // ---- Step 6: Scale max height + clamp safe ----
        return Mth.clamp(amplified * maxHeight, -maxHeight, maxHeight);
    }

    @Override
    public void fillArray(double[] array, ContextProvider ctx) {
        input.fillArray(array, ctx);
        for (int i = 0; i < array.length; i++) {
            double x = ctx.blockX(i);
            double z = ctx.blockZ(i);

            double warpedX = x + FlatDomainWarp.computeX(x, z, warpStrength);
            double warpedZ = z + FlatDomainWarp.computeZ(x, z, warpStrength);

            double base = array[i];

            double wave1 = Math.sin(warpedX * 0.01) * Math.cos(warpedZ * 0.01);
            double wave2 = Math.sin(warpedX * 0.02 + warpedZ * 0.03);
            double wave3 = Math.cos(warpedX * 0.015 - warpedZ * 0.02);
            double waveCombined = wave1 * 0.3 + wave2 * 0.4 + wave3 * 0.3;

            double amplitude = Sqrt.compute(base * base + 0.001) / Division.compute(base, 2.0);
            double sign = Signum.compute(amplitude);

            double amplified = base * (1.0 + sign * amplitude * multiplier + waveCombined * multiplier);
            array[i] = Mth.clamp(amplified * maxHeight, -maxHeight, maxHeight);
        }
    }

    @Override
    public DensityFunction mapAll(Visitor visitor) {
        return visitor.apply(new HyperWarpAmplify(input.mapAll(visitor), multiplier, warpStrength, maxHeight));
    }

    @Override
    public double minValue() { return -maxHeight; }

    @Override
    public double maxValue() { return maxHeight; }

    @Override
    public DensityFunctionType<?> type() {
        return EidolonDensityFunctions.HYPER_WARP; // Đăng ký riêng
    }
}
