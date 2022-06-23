package com.decursioteam.sanguinearsenal.core.mixins;

import com.decursioteam.sanguinearsenal.core.Util.LivingUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Effect.class)
public abstract class SanguinePraetorEffects {

    @Inject(at = @At(value = "HEAD"), method = "applyEffectTick", cancellable = true)
    protected void applyEffectTick(LivingEntity entity, int amplifier, CallbackInfo info) {
        if(entity instanceof PlayerEntity) {
            boolean isWithering = entity.hasEffect(Effects.WITHER);
            boolean hasFullSPSet = LivingUtil.entityhasFullSPSet(entity);
            if(isWithering && hasFullSPSet){
                entity.removeEffect(Effects.WITHER);
                info.cancel();
            }
        }
    }
}
