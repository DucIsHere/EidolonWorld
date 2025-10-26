package com.eidolonworld.density_function_types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunction.FunctionContext;

public record AmplifyFunction(DensityFunction input, double multiplier, double waveScale) implements DensityFunction {

    public static final Codec<AmplifyFunction> CODEC = RecordCodecBuilder.create(instance ->
        instance.group(
            DensityFunction.HOLDER_HELPER_CODEC.fieldOf("input").forGetter(AmplifyFunction::input),
            Codec.DOUBLE.fieldOf("multiplier").forGetter(AmplifyFunction::multiplier),
            Codec.DOUBLE.fieldOf("waveScale").forGetter(AmplifyFunction::waveScale)
        ).apply(instance, AmplifyFunction::new)
    );

    @Override
    public double compute(FunctionContext ctx) {
        double x = ctx.blockX();
        double z = ctx.blockZ();
        double base = input.compute(ctx);

        // √(x² + z²) để tạo biến động khoảng cách
        double distance = Math.sqrt(x * x + z * z) * waveScale;

        // Sine + cos kết hợp warp noise
        double wave = Math.sin(distance * 2.0) * Math.cos(distance * 1.5);

        // Combine base + wave với multiplier
        double amplified = base * (1.0 + wave * multiplier);

        // Clamp an toàn -1 → 1
        return Mth.clamp(amplified, -1.0, 1.0);
    }

    @Override
    public void fillArray(double[] array, ContextProvider ctx) {
        input.fillArray(array, ctx);
        for (int i = 0; i < array.length; i++) {
            double x = ctx.blockX(i);
            double z = ctx.blockZ(i);
            double distance = Math.sqrt(x * x + z * z) * waveScale;
            double wave = Math.sin(distance * 2.0) * Math.cos(distance * 1.5);
            array[i] = Mth.clamp(array[i] * (4.5 + wave * multiplier), -1.0, 1.0);
        }
    }

    @Override
    public DensityFunction mapAll(Visitor visitor) {
        return visitor.apply(new AmplifyFunction(input.mapAll(visitor), multiplier, waveScale));
    }

    @Override
    public double minValue() {
        return input.minValue() * (1.0 - multiplier);
    }

    @Override
    public double maxValue() {
        return input.maxValue() * (1.0 + multiplier);
    }

    @Override
    public DensityFunctionType<?> type() {
        return EidolonDensityFunctions.AMPLIFY; // Đăng ký riêng trong EidolonDensityFunctions
    }
}
