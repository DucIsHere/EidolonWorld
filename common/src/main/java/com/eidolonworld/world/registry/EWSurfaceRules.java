package com.eidolonworld.world.registry;

import com.eidolonworld.world.EidolonWorld;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.block.Blocks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * EWSurfaceRules.java
 *
 * Provides a Java-side SurfaceRules.RuleSource that can be used by Java biome builders
 * or by future mixins. Preferred approach is to define surface rules in JSON, but this
 * helper is convenient for programmatic generation or testing.
 */
public class EWSurfaceRules {
    private static final Logger LOGGER = LogManager.getLogger("EWSurfaceRules");

    /**
     * Prepare any runtime surface-rule helpers or register processors.
     * Returns a simple default rule if you need it programmatically.
     */
    public static SurfaceRules.RuleSource prepare() {
        LOGGER.info("[EWSurfaceRules] Preparing default surface rules...");

        // Simple example: top = GRASS_BLOCK, below = DIRT
        SurfaceRules.RuleSource topGrass = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.state(Blocks.GRASS_BLOCK.defaultBlockState())
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.UNDER_FLOOR,
                        SurfaceRules.state(Blocks.DIRT.defaultBlockState())
                )
        );

        LOGGER.info(" - Default surface rule (grass/dirt) prepared. Use JSON for full surface control.");
        return topGrass;
    }
}
