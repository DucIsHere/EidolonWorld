package com.eidolonworld.world;

import com.eidolonworld.world.registry.*;
import net.fabricmc.api.ModInitializer; // Fabric
import net.minecraftforge.fml.common.Mod; // Forge/NeoForge
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.com.eidolonworld.density_function_type.ModDensityFunctionType;
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

        MaterialRuleContextExtension.registries();
        SomewhatSteepMaterialCondition.registries();
        SomewhatSteepSlopePredicate.registries();
        

    public static final DensityFunctionType<AmplifyFunction> AMPLIFY =
        DensityFuctionType.register("amplify", AmplifyFunction.CODEC);
        
        
        LOGGER.info("[EidolonWorld] World generation modules loaded successfully.");
    }
}
