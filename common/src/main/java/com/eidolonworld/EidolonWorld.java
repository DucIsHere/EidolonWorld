package com.eidolonworld;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Loader compatibility
// Fabric:
import net.fabricmc.api.ModInitializer;
// Forge / NeoForge:
import net.minecraftforge.fml.common.Mod;

// Main mod entry (Forge & NeoForge use @Mod, Fabric uses ModInitializer)
@Mod(EidolonWorld.MOD_ID)
public class EidolonWorld implements ModInitializer {
    public static final String MOD_ID = "eidolonworld";
    public static final Logger LOGGER = LogManager.getLogger("Eidolon World");

    public EidolonWorld() {
        // Forge / NeoForge constructor entry
        LOGGER.info("[Eidolon World] Initializing via Forge/NeoForge...");
        init();
    }

    @Override
    public void onInitialize() {
        // Fabric entry
        LOGGER.info("[Eidolon World] Initializing via Fabric...");
        init();
    }

    /**
     * Shared initialization for all loaders
     */
    private void init() {
        LOGGER.info("==========================================");
        LOGGER.info("        Eidolon World Initialization       ");
        LOGGER.info("          Where Echoes Become Real         ");
        LOGGER.info("==========================================");

        // Register biomes, noise, and worldgen data
        EWBiomes.register();
        LOGGER.info("✓ Biomes registered.");

        EWNoiseRouter.register();
        LOGGER.info("✓ Noise router initialized.");

        EWSurfaceRules.register();
        LOGGER.info("✓ Surface rules loaded.");

        LOGGER.info("[Eidolon World] Initialization complete.");
    }

    /**
     * Utility function for creating mod resource locations.
     */
    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    /**
     * Create biome keys for registry bootstrap.
     */
    public static ResourceKey<Biome> biomeKey(String name) {
        return ResourceKey.create(Registry.BIOME_REGISTRY, id(name));
    }
}
