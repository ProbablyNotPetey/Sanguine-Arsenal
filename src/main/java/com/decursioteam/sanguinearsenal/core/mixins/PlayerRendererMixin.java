package com.decursioteam.sanguinearsenal.core.mixins;

import com.decursioteam.sanguinearsenal.core.init.ItemInit;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {

    public PlayerRendererMixin(EntityRendererManager entityRenderDispatcher, PlayerModel<AbstractClientPlayerEntity> entityModel, float f) {
        super(entityRenderDispatcher, entityModel, f);
    }

    @Inject(method = "setModelProperties", at = @At("RETURN"))
    public void setModelProperties(AbstractClientPlayerEntity abstractClientPlayer, CallbackInfo info) {
        PlayerModel<AbstractClientPlayerEntity> playerModel = this.getModel();
        if(ItemStack.isSameIgnoreDurability(abstractClientPlayer.getItemBySlot(EquipmentSlotType.CHEST), new ItemStack(ItemInit.SP_CHESTPLATE.get()))){
            playerModel.jacket.visible = false;
            playerModel.leftSleeve.visible = false;
            playerModel.rightSleeve.visible = false;
        }
        if(ItemStack.isSameIgnoreDurability(abstractClientPlayer.getItemBySlot(EquipmentSlotType.HEAD), new ItemStack(ItemInit.SP_HELMET.get()))){
            playerModel.hat.visible = false;
        }
        if(ItemStack.isSameIgnoreDurability(abstractClientPlayer.getItemBySlot(EquipmentSlotType.LEGS), new ItemStack(ItemInit.SP_LEGGINGS.get()))){
            playerModel.leftPants.visible = false;
            playerModel.rightPants.visible = false;
        }
        if(ItemStack.isSameIgnoreDurability(abstractClientPlayer.getItemBySlot(EquipmentSlotType.FEET), new ItemStack(ItemInit.SP_BOOTS.get()))){
            playerModel.leftPants.visible = false;
            playerModel.rightPants.visible = false;
        }
    }
}