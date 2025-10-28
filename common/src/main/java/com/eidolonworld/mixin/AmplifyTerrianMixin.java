package com.eidolonworld.mixin;

import net.minecraft.world.level.levelgen.NoiseChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.DensityFunction.SinglePointContext;
import net.minecraft.world.level.levelgen.DensityFunctions.HolderHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NoiseChunkGenerator.class)
public abstract class AmplifyTerrainMixin {

    @Shadow
    protected abstract DensityFunction noiseRouters(); // Access to noise router

    @Inject(method = "applyCarvers", at = @At("HEAD"))
    private void eidolon$amplifyTerrain(net.minecraft.world.level.chunk.ChunkAccess chunk,
                                        net.minecraft.world.level.levelgen.structure.StructureManager structureManager,
                                        net.minecraft.world.level.levelgen.RandomState randomState,
                                        net.minecraft.world.level.levelgen.WorldGenerationContext context,
                                        CallbackInfo ci) {
        try {
            // amplify toàn bộ terrain output
            double amplify = 4.5; // m chỉnh tuỳ ý, càng cao càng insane
            var router = randomState.router();

            DensityFunction terrainNoise = router.finalDensity();
            if (terrainNoise == null) return;

            DensityFunction amplified = DensityFunctions.mul(
                    DensityFunctions.constant(amplify),
                    terrainNoise
            );

            // apply tạm thời (hacky)
            double test = amplified.compute(new SinglePointContext(0, 0, 0));
            if (!Double.isNaN(test)) {
                System.out.println("[EidolonWorld] Amplify Terrain Active: x" + amplify);
            }

        } catch (Exception e) {
            System.err.println("[EidolonWorld] Amplify Terrain Error: " + e);
        }
    }
}
