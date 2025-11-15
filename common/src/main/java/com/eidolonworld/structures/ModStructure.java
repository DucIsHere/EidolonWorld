package com.eidolonworld.structures;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;

public class ModStructure {
  public static final ResourceKey<Structure> MY_STRUCTURE = 
  ResourceKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation("eidolonworld", "brown_mushroom_1"));

  public static void register() {
    // Architectury: common registration
  }
}
