package com.eidolonworld.world;

import com.eidolonworld.world.registry.*;
import net.fabricmc.api.ModInitializer; // Fabric
import net.minecraftforge.fml.common.Mod; // Forge/NeoForge
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point của Eidolon World.
 * Tập trung vào worldgen – biome, noise, surface rule.
 */
@Mod(EidolonWorld.MOD_ID)
public class EidolonWorld implements ModInitializer {
    public static final String MOD_ID = "eidolonworld";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[EidolonWorld] Initializing core worldgen systems...");

        EWBiomes.register();
        EWNoiseRouter.register();
        
        LOGGER.info("[EidolonWorld] World generation modules loaded successfully.");
    }
}
