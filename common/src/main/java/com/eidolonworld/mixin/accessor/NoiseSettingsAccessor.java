package com.eidolonworld.mixin.accessor;

import net.minecraft.world.level.levelgen.NoiseSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NoiseSettings.class)
public interface NoiseSettingsAccessor {
    @Accessor("height")
    void setHeight(int height);

    @Accessor("minY")
    void setMinY(int minY);
}
