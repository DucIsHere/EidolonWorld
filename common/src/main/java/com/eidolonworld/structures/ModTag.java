package com.eidolonworld.structures;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biomes;

public class ModTag {
    public static final TagKey<Biomes> SHADOWED_GROVE =
    TagKey.create(Biomes.REGISTRY, new ResourceLocation("eidolonworld", "shadowed_grove"));
}
