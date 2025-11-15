package com.eidolonworld.structures;

import com.eidolonworld.structures.ModStructure;
import com.eidolonworld.structures.ModTag;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModStructureFabric {
  public static void init() {
    BiomeModification.addFeature(
      BiomeSelector.tag(ModTags.MY_BIOME_TAG),
      GenerationStep.Decoration.SURFACE_STRUCTURES,
      ModStructures.MY_STRUCTURES
    );
  }
}
