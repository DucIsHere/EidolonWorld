package com.eidolonworld.density_function_type;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunction.FunctionContext;

public record AmplifyNoise(DensityFunction input, double factor) implements DensityFunction.SimpleFunction {

    public static final MapCodec<AmplifyNoise> CODEC = RecordCodecBuilder.mapCodec((instance) ->
        instance.group(
            DensityFunction.Holder.CODEC.fieldOf("input").forGetter(AmplifyNoise::input),
            com.mojang.serialization.codecs.PrimitiveCodec.DOUBLE.fieldOf("factor").forGetter(AmplifyNoise::factor)
        ).apply(instance, AmplifyNoise::new)
    );

    @Override
    public double compute(FunctionContext context) {
        double base = input.compute(context);
        double x = context.blockX();
        double z = context.blockZ();

        // 💥 amplify biến động theo khoảng cách + sin + sqrt
        double dist = Math.sqrt(x * x + z * z);
        double wave = Math.sin(dist / 300.0) + Math.cos(x / 200.0);
        double amp = 1.5 + (wave * 0.75) + (Math.sqrt(Math.abs(base)) * 0.5);

        return base * factor * amp;
    }

    @Override
    public void fillArray(double[] arr, ContextProvider ctx) {
        input.fillArray(arr, ctx);
        for (int i = 0; i < arr.length; i++) arr[i] = arr[i] * factor;
    }

    @Override
    public double minValue() { return input.minValue() * factor; }
    @Override
    public double maxValue() { return input.maxValue() * factor; }

    @Override
    public KeyDispatchDataCodec<? extends DensityFunction> codec() {
        return ModDensityFunctionTypes.AMPLIFY_NOISE.codec();
    }
}
