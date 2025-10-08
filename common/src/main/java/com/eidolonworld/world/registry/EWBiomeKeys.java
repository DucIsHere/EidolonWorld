package com.eidolonworld.world.registry;

import com.eidolonworld.world.EidolonWorld;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

/**
 * Centralized biome ResourceKey list.
 * Keep keys here so both Java logic and JSON names match.
 */
public final class EWBiomeKeys {

    private EWBiomeKeys() {}

    public static final ResourceKey<Biome> EIDOLON_HIGHLANDS = create("eidolon_highlands");
    public static final ResourceKey<Biome> EIDOLON_VALLEY = create("eidolon_valley");
    public static final ResourceKey<Biome> EIDOLON_FOREST = create("eidolon_forest");
    public static final ResourceKey<Biome> EIDOLON_CRYSTAL = create("eidolon_crystal");

    private static ResourceKey<Biome> create(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(EidolonWorld.MOD_ID, name));
    }
}
