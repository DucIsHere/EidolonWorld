package com.eidolonworld.structures;

import com.eidolonworld.structures.ModStructures;
import net.minecraftforge.common.world.BiomeModification;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "modid")
public class ForgeModInit {
    public static void onBiomeLoad(final BiomeLoadingEvent event) {
        event.getGeneration().addStructureStart(ModStructures.MY_STRUCTURE);
    }
}
