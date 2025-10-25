package com.eidolonworld.density_function_types;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctionType;
import net.minecraft.resources.ResourceLocation;

public class ModDensityFunctionTypes {
    public static final DensityFunctionType<AmplifyNoise> AMPLIFY_NOISE =
        register("amplify_noise", new DensityFunctionType<>(AmplifyNoise.CODEC));

    private static <T extends DensityFunction> DensityFunctionType<T> register(String id, DensityFunctionType<T> type) {
        return Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE,
                new ResourceLocation("eidolonworld", id), type);
    }

    public static void init() {
        // gọi trong main mod init
    }
}
