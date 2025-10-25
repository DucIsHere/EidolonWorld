package com.eidolonworld.mixin.accessor;

import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.NoiseChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NoiseChunk.class)
public interface NoiseChunkAccessor {
    @Accessor("router")
    NoiseRouter getRouter();

    @Accessor("router")
    void setRouter(NoiseRouter router);
}
