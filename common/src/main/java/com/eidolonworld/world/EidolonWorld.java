package com.eidolonworld.world;

import com.eidolonworld.world.registry.EWBiomes;
import com.eidolonworld.world.registry.EWNoiseRouter;
import com.eidolonworld.world.registry.EWSurfaceRules;
import com.eidolonworld.world.registry.EWTiers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Fabric
import net.fabricmc.api.ModInitializer;
// Forge / NeoForge
import net.minecraftforge.fml.common.Mod;

/**
 * Multi-loader entrypoint.
 * - Forge/NeoForge: uses @Mod constructor
 * - Fabric: uses ModInitializer.onInitialize()
 *
 * Package placed under com.eidolonworld.world as requested.
 */
@Mod(EidolonWorld.MOD_ID)
public class EidolonWorld implements ModInitializer {
    public static final String MOD_ID = "eidolonworld";
    public static final Logger LOGGER = LogManager.getLogger("EidolonWorld");

    public EidolonWorld() {
        // Forge/NeoForge entry
        LOGGER.info("[EidolonWorld] Construct (Forge/NeoForge) - init common");
        initCommon();
    }

    @Override
    public void onInitialize() {
        // Fabric entry
        LOGGER.info("[EidolonWorld] onInitialize (Fabric) - init common");
        initCommon();
    }

    /**
     * Common initialization for all loaders.
     * Register keys, tiers, and prepare noise surface stubs.
     */
    private void initCommon() {
        LOGGER.info("=== EidolonWorld: initializing worldgen core ===");

        // Register/prepare biome keys (we keep biome data in JSON; keys for Java usage)
        EWBiomes.register();
        LOGGER.info("✓ EWBiomes.register() called");

        // Register tier mapping
        EWTiers.registerDefaults();
        LOGGER.info("✓ EWTiers.registerDefaults() called");

        // Noise router (note: heavy noise should be defined by JSON under data/.../noise_settings)
        EWNoiseRouter.register();
        LOGGER.info("✓ EWNoiseRouter.register() called");

        // Surface rules helper (Java-side rule source if needed, JSON preferred)
        EWSurfaceRules.prepare();
        LOGGER.info("✓ EWSurfaceRules.prepare() called");

        LOGGER.info("=== EidolonWorld initialization complete ===");
    }

    /**
     * Utility: build resource path for mod assets (Java-side).
     */
    public static String id(String path) {
        return MOD_ID + ":" + path;
    }
}
