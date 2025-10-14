package com.eidolonworld.mixin.world;

import com.eidolonworld.world.EidolonWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import net.minecraft.world.level.levelgen.NoiseChunkGenerator;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;

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
    @Inject(method = "createFluidPicker", at = @At("HEAD"), cancellable = true)
  private static void createFluidPicker(NoiseGeneratorSettings settings,
      CallbackInfoReturnable<Aquifer.FluidPicker> ci) {
    // This mixin modifies the aquifer generator so that the "bedrock lava
    // level" (in vanilla hardcoded to -54) is instead determined by the world's
    // minimum Y level, allowing for deeper caves without them being flooded.
    // Ideally the lava sea level would be a field in the noise settings
    // like normal sea level, but this is easier to implement
    var lavaSeaLevel = settings.noiseSettings().minY() + 10;
    Aquifer.FluidStatus lavaFluidStatus = new Aquifer.FluidStatus(lavaSeaLevel, Blocks.LAVA.defaultBlockState());
    int i = settings.seaLevel();
    Aquifer.FluidStatus defaultFluidStatus = new Aquifer.FluidStatus(i, settings.defaultFluid());
    // AquiferSampler.FluidLevel fluidLevel3 = new
    // AquiferSampler.FluidLevel(DimensionType.MIN_HEIGHT * 2,
    // Blocks.AIR.getDefaultState());
    ci.setReturnValue((x, y, z) -> y < Math.min(lavaSeaLevel, i) ? lavaFluidStatus : defaultFluidStatus);
  }

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
