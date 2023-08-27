package com.decursioteam.sanguinearsenal.core.mixins;

import com.decursioteam.sanguinearsenal.core.Util.LivingUtil;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEffect.class)
public abstract class SanguinePraetorEffects {

    @Inject(at = @At(value = "HEAD"), method = "applyEffectTick", cancellable = true)
    protected void applyEffectTick(LivingEntity entity, int amplifier, CallbackInfo info) {
        if(entity instanceof Player) {
            boolean isWithering = entity.hasEffect(MobEffects.WITHER);
            boolean hasFullSPSet = LivingUtil.entityhasFullSPSet(entity);
            if(isWithering && hasFullSPSet){
                entity.removeEffect(MobEffects.WITHER);
                info.cancel();
            }
        }
    }
}
