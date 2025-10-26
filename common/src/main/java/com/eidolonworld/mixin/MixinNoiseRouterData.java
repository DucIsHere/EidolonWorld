package com.eidolonworld.mixin;

import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.eidolonworld.density_function_types.AmplifyNoise;

@Mixin(NoiseRouterData.class)
public abstract class MixinNoiseRouterData {

    @Inject(method = "overworld", at = @At("RETURN"), cancellable = true)
    private static void injectAmplify(HolderGetter<DensityFunction> densityGetter, HolderGetter<NormalNoise.NoiseParameters> noiseGetter, CallbackInfoReturnable<NoiseRouter> cir) {
        DensityFuction base = DensityFunctions.OVERWORLD_FINAL_DENSITY;
        AmplifyNoise amplify = new AmplifyNoise(base, 4.5, 0.002, 15000);
        NoiseRouter router = ((RandomStateAccessor) randomState).getRouter();
        ((NoiseRouterAccessor) router).setFinalDensity(amplify);
        NoiseRouter original = cir.getReturnValue();
        DensityFunction amplified = new AmplifyNoise(original.finalDensity(), 4.0);
        NoiseRouter newRouter = new NoiseRouter(
                original.barrierNoise(),
                original.fluidLevelFloodednessNoise(),
                original.fluidLevelSpreadNoise(),
                original.lavaNoise(),
                amplified,
                original.veinToggle(),
                original.veinRidged(),
                original.veinGap()
        );
        cir.setReturnValue(newRouter);
    }
}
