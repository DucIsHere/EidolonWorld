package com.eidolonworld.world.registry;

import com.eidolonworld.world.EidolonWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * EWNoiseRouter.java
 *
 * NOTE:
 * - We strongly recommend defining the full terrain noise (ridges/erosion/rivers/slopes/depth)
 *   inside JSON under data/eidolonworld/worldgen/noise_settings/*.json and corresponding
 *   data/eidolonworld/worldgen/noise/*.json files (this keeps iteration fast and loader-compatible).
 *
 * - This Java class is a place for optional runtime hooks, dynamic registration,
 *   or later Mixin-based injection into the vanilla NoiseRouter if you need dynamic behavior.
 */
public class EWNoiseRouter {
    private static final Logger LOGGER = LogManager.getLogger("EWNoiseRouter");

    /**
     * Call during mod init. If you only use JSON noise, this can remain minimal.
     * If you later need to inject DensityFunction via mixin, use this class to centralize helpers.
     */
    public static void register() {
        LOGGER.info("[EWNoiseRouter] Registering noise router helpers for EidolonWorld...");

        // If you want purely JSON-defined noise, leave this method minimal.
        // Example tasks you might add here later:
        // - register custom noise sampler IDs (for documentation)
        // - prepare constants used by mixins
        // - load optional runtime config that controls noise parameters
        //
        // For now we only log; actual noise lives in:
        // data/eidolonworld/worldgen/noise_settings/eidolon_overworld.json
        LOGGER.info(" - Noise recommended in data/eidolonworld/worldgen/noise_settings/* (JSON).");
    }
}
