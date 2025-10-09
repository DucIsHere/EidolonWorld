package com.eidolonworld.mixin.world;

import com.eidolonworld.world.EidolonWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import net.minecraft.world.level.levelgen.NoiseChunkGenerator;

/**
 * NoiseChunkGeneratorMixin
 *
 * - Thay hằng số min_y và height (vanilla -64/384) bằng -5000/15000.
 * - Tùy mapping/version, method "fillNoiseColumn" hay constants có thể khác; nếu IDE báo lỗi:
 *    - search trong deobf jar nơi có -64 / 384 constants in methods related to noise height
 *    - thay tên method/constant target cho phù hợp
 *
 * THIS MIXIN IS A TEMPLATE. Adjust if your mappings differ.
 */
@Mixin(NoiseChunkGenerator.class)
public class NoiseChunkGeneratorMixin {

    // Replace vanilla minimum Y constant (-64) => -5000
    @ModifyConstant(method = "fillNoiseColumn", constant = @org.spongepowered.asm.mixin.injection.constant.Constant(intValue = -64))
    private int eidolon$replaceMinY(int original) {
        // debug log once
        EidolonWorld.LOGGER.info("[Eidolon Mixin] Replacing minY constant {} -> -5000", original);
        return -5000;
    }

    // Replace vanilla height constant (384) => 15000
    @ModifyConstant(method = "fillNoiseColumn", constant = @org.spongepowered.asm.mixin.injection.constant.Constant(intValue = 384))
    private int eidolon$replaceHeight(int original) {
        EidolonWorld.LOGGER.info("[Eidolon Mixin] Replacing height constant {} -> 15000", original);
        return 10000;
    }

    /*
     * NOTE:
     * If your mappings or MC version use different method names or constants,
     * change "fillNoiseColumn" to the correct method or add more ModifyConstant
     * targets. This mixin only modifies constants — it is safer than overwriting logic.
     */
}
