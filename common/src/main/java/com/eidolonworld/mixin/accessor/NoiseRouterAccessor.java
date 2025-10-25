package com.eidolonworld.mixin.accessor;

import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseRouter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NoiseRouter.class)
public interface NoiseRouterAccessor {
    @Accessor("finalDensity")
    DensityFunction getFinalDensity();

    @Accessor("finalDensity")
    void setFinalDensity(DensityFunction density);

    @Accessor("initialDensityWithoutJaggedness")
    DensityFunction getInitialDensityWithoutJaggedness();

    @Accessor("initialDensityWithoutJaggedness")
    void setInitialDensityWithoutJaggedness(DensityFunction density);

    @Accessor("ridges")
    void setRidges(DensityFunction density);

    @Accessor("erosion")
    void setErosion(DensityFunction density);

    @Accessor("depth")
    void setDepth(DensityFunction density);

    @Accessor("continents")
    void setContinents(DensityFunction density);
}
