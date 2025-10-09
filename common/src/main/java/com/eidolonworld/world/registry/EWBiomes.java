package com.eidolonworld.world.registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Music;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.world.level.biome.*;

public class EWBiomes {

    public static Biome createHighlands(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(placedFeatures, carvers);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generation);
        BiomeDefaultFeatures.addDefaultOres(generation);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);
        
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .skyColor(12000000)
                .fogColor(8000000)
                .waterColor(4000000)
                .waterFogColor(2000000)
                .backgroundMusic(Music.createGameMusic(null))
                .build();

        return new Biome.BiomeBuilder()
                .temperature(0.2F)
                .downfall(0.1F)
                .precipitation(Biome.Precipitation.SNOW)
                .generationSettings(generation.build())
                .mobSpawnSettings(spawns.build())
                .specialEffects(effects)
                .build();
    }

    public static Biome createValley(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(placedFeatures, carvers);
        BiomeDefaultFeatures.addDefaultOres(generation);
        BiomeDefaultFeatures.addDefaultGrass(generation);
        BiomeDefaultFeatures.addDefaultFlowers(generation);

        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .skyColor(15000000)
                .fogColor(10000000)
                .waterColor(7000000)
                .waterFogColor(5000000)
                .build();

        return new Biome.BiomeBuilder()
                .temperature(0.8F)
                .downfall(0.6F)
                .precipitation(Biome.Precipitation.RAIN)
                .generationSettings(generation.build())
                .mobSpawnSettings(spawns.build())
                .specialEffects(effects)
                .build();
    }

    public static Biome createMythicOcean(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(placedFeatures, carvers);
        BiomeDefaultFeatures.addOceanCarvers(generation);
        BiomeDefaultFeatures.addDefaultOres(generation);

        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .skyColor(13000000)
                .fogColor(9000000)
                .waterColor(3000000)
                .waterFogColor(2000000)
                .build();

        return new Biome.BiomeBuilder()
                .temperature(0.5F)
                .downfall(0.8F)
                .precipitation(Biome.Precipitation.RAIN)
                .generationSettings(generation.build())
                .mobSpawnSettings(spawns.build())
                .specialEffects(effects)
                .build();
    }
}
