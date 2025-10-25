package com.eidolonworld.mixin.accessor;

import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.RandomState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RandomState.class)
public interface RandomStateAccessor {
    @Accessor("router")
    NoiseRouter getRouter();

    @Accessor("router")
    void setRouter(NoiseRouter router);
}
