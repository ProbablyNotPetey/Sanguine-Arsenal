package com.decursioteam.sanguinearsenal.core.mixins;

import com.decursioteam.sanguinearsenal.core.init.ItemInit;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    public PlayerRendererMixin(EntityRendererProvider.Context context, PlayerModel<AbstractClientPlayer> entityModel, float f) {
        super(context, entityModel, f);
    }

    @Inject(method = "setModelProperties", at = @At("RETURN"))
    public void setModelProperties(AbstractClientPlayer abstractClientPlayer, CallbackInfo info) {
        PlayerModel<AbstractClientPlayer> playerModel = this.getModel();
        if(ItemStack.isSameIgnoreDurability(abstractClientPlayer.getItemBySlot(EquipmentSlot.CHEST), new ItemStack(ItemInit.SP_CHESTPLATE.get()))){
            playerModel.jacket.visible = false;
            playerModel.leftSleeve.visible = false;
            playerModel.rightSleeve.visible = false;
        }
        if(ItemStack.isSameIgnoreDurability(abstractClientPlayer.getItemBySlot(EquipmentSlot.HEAD), new ItemStack(ItemInit.SP_HELMET.get()))){
            playerModel.hat.visible = false;
        }
        if(ItemStack.isSameIgnoreDurability(abstractClientPlayer.getItemBySlot(EquipmentSlot.LEGS), new ItemStack(ItemInit.SP_LEGGINGS.get()))){
            playerModel.leftPants.visible = false;
            playerModel.rightPants.visible = false;
        }
        if(ItemStack.isSameIgnoreDurability(abstractClientPlayer.getItemBySlot(EquipmentSlot.FEET), new ItemStack(ItemInit.SP_BOOTS.get()))){
            playerModel.leftPants.visible = false;
            playerModel.rightPants.visible = false;
        }
    }
}