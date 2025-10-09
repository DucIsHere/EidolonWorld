package com.eidolonworld.mixin.world;

import com.eidolonworld.world.EidolonWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.world.level.biome.Climate;

/**
 * ClimateTargetRedirectMixin
 *
 * Redirect the call that builds the Climate.Target/Climate.target(x,y,z) to substitute scaled coords.
 *
 * IMPORTANT:
 * - The exact method/constructor call to redirect depends on MC internals and mapping.
 * - Commonly code does: Climate.target(int x, int y, int z) or new Climate.Target(...).
 * - Adjust the target string in @Redirect accordingly if your mapping differs.
 */
@Mixin(targets = "net.minecraft.world.level.biome.MultiNoiseBiomeSource")
public class ClimateTargetRedirectMixin {

    // Redirect static factory Climate.target(x,y,z) to inject scaled coords when available
    @Redirect(method = "getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/Climate;target(III)Lnet/minecraft/world/level/biome/Climate$Target;"))
    private Climate.Target eidolon$redirectClimateTarget(int x, int y, int z) {
        int[] scaled = BiomeSourceMixin.consumeScaledCoords();
        if (scaled != null) {
            EidolonWorld.LOGGER.debug("[Eidolon BiomeScale] substituting climate target {} {} {} -> {} {} {}", x, y, z, scaled[0], scaled[1], scaled[2]);
            return Climate.target(scaled[0], scaled[1], scaled[2]);
        }
        // no scaled coords present -> default behavior
        return Climate.target(x, y, z);
    }
}
