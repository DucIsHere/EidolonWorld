package com.eidolonworld.mixin.world;

import com.eidolonworld.world.EidolonWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.biome.Climate;

/**
 * BiomeSourceMixin
 *
 * Strategy:
 *  - On entry to getNoiseBiome(x,y,z,sampler), compute scaledX/Z and push to a ThreadLocal.
 *  - Another mixin (ClimateTargetRedirectMixin) will read ThreadLocal and substitute coordinates
 *    when Climate.target(...) is built inside the biome selection flow.
 *
 * NOTE:
 *  - This is intentionally conservative: it avoids direct recursion or deep reflection.
 *  - You must include ClimateTargetRedirectMixin (below) to complete the coordinate substitution.
 */
@Mixin(MultiNoiseBiomeSource.class)
public class BiomeSourceMixin {

    // scale factor (biome horizontal scale)
    private static final double BIOME_SCALE = 15000.0D;

    // ThreadLocal to carry scaled coordinates to the internal climate target call
    private static final ThreadLocal<int[]> SCALED_COORDS = new ThreadLocal<>();

    @Inject(method = "getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;",
            at = @At("HEAD"))
    private void eidolon$onGetNoiseBiome(int x, int y, int z, Climate.Sampler sampler, CallbackInfoReturnable<Holder<Biome>> cir) {
        int scaledX = (int) Math.floor(x / BIOME_SCALE);
        int scaledZ = (int) Math.floor(z / BIOME_SCALE);
        // store scaled coords for the redirected Climate target
        SCALED_COORDS.set(new int[]{ scaledX, y, scaledZ });
        EidolonWorld.LOGGER.debug("[Eidolon BiomeScale] pushed scaled coords {} {} -> {} {}", x, y, z, scaledX, scaledZ);
        // note: we do not cancel; ClimateTargetRedirectMixin will read ThreadLocal and substitute.
    }

    // helper for ClimateTargetRedirectMixin to read & clear
    public static int[] consumeScaledCoords() {
        int[] v = SCALED_COORDS.get();
        SCALED_COORDS.remove();
        return v;
    }
}
