package com.eidolonworld.world.registry;

import com.eidolonworld.world.EidolonWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * EWBiomes.java
 * - Defines biome keys (in EWBiomeKeys)
 * - Minimal Java bootstrap: we DO NOT register full Biome instances here if using JSON
 *   (recommended): let data/eidolonworld/worldgen/biome/*.json provide the biome definitions.
 *
 * Use this class to perform Java-driven registration of features or to fall back
 * to programmatic biome creation if desired.
 */
public class EWBiomes {
    private static final Logger LOGGER = LogManager.getLogger("EWBiomes");

    public static void register() {
        LOGGER.info("[EWBiomes] Preparing biome registry keys for EidolonWorld...");

        // NOTE:
        // We expect JSON biome files under:
        // data/eidolonworld/worldgen/biome/eidolon_highlands.json, etc.
        //
        // If you prefer programmatic biomes, implement creation and Registry.register(...) here.
        //
        // For now, we only ensure the keys/constants exist (EWBiomeKeys) and log startup.
        LOGGER.info(" - Biome keys ready: " +
                EidolonWorld.id("eidolon_highlands") + ", " +
                EidolonWorld.id("eidolon_valley") + ", " +
                EidolonWorld.id("eidolon_forest") + ", " +
                EidolonWorld.id("eidolon_crystal"));

        // Example: if you want to register programmatically (not recommended for heavy noise),
        // uncomment and implement registration here.
    }
}
