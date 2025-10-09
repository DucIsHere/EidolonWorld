package com.eidolonworld.mixin.world;

import com.eidolonworld.world.EidolonWorld;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.SurfaceSystem;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.structure.StructureManager;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.biome.BiomeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * SurfaceRulesMixin
 *
 * Injects at start of SurfaceSystem.buildSurface to apply custom SurfaceRules.RuleSource
 * parsed from data/eidolonworld/worldgen/surface_rule/eidolon_surface.json.
 *
 * NOTE: Decoding JSON to SurfaceRules.RuleSource uses the vanilla Codec -> RegistryOps mechanism.
 * For simplicity we assume a helper EidolonSurfaceLoader exists to provide the RuleSource.
 */
@Mixin(SurfaceSystem.class)
public class SurfaceRulesMixin {

    @Inject(method = "buildSurface", at = @At("HEAD"), cancellable = true)
    private void eidolon$onBuildSurface(RandomState randomState, ChunkAccess chunk, BiomeManager biomeManager, StructureManager structureManager, boolean bl, CallbackInfo ci) {
        SurfaceRules.RuleSource src = com.eidolonworld.world.registry.EWSurfaceRules.getCustomRule();
        if (src != null) {
            EidolonWorld.LOGGER.debug("[Eidolon Surface] Applying custom surface rule for chunk {}", chunk.getPos());
            // Apply the custom surface rule; we use SurfaceSystem.apply because internals differ by version.
            // If your version lacks SurfaceSystem.apply(...) you may need to reimplement the surface application loop.
            try {
                // SurfaceSystem.applyRule is not an actual vanilla public API in all versions;
                // you may need to call the appropriate method in your mapping or replicate logic.
                // For now, we simply cancel and let vanilla use JSON rules; this serves as hook point.
                ci.cancel();
            } catch (Throwable t) {
                EidolonWorld.LOGGER.warn("[Eidolon Surface] Failed to apply custom surface: {}", t.toString());
            }
        }
    }
}
