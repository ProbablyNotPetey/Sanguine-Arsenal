package com.decursioteam.sanguinearsenal.core.Util;

import com.decursioteam.sanguinearsenal.core.init.ItemInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class LivingUtil {
    public static boolean hasFullSPSet(PlayerEntity player)
    {
        return ItemStack.isSameIgnoreDurability(player.getItemBySlot(EquipmentSlotType.CHEST), new ItemStack(ItemInit.SP_CHESTPLATE.get())) &&
                ItemStack.isSameIgnoreDurability(player.getItemBySlot(EquipmentSlotType.HEAD), new ItemStack(ItemInit.SP_HELMET.get())) &&
                ItemStack.isSameIgnoreDurability(player.getItemBySlot(EquipmentSlotType.LEGS), new ItemStack(ItemInit.SP_LEGGINGS.get())) &&
                ItemStack.isSameIgnoreDurability(player.getItemBySlot(EquipmentSlotType.FEET), new ItemStack(ItemInit.SP_BOOTS.get()));
    }
    public static boolean entityhasFullSPSet(LivingEntity entity)
    {
        return ItemStack.isSameIgnoreDurability(entity.getItemBySlot(EquipmentSlotType.CHEST), new ItemStack(ItemInit.SP_CHESTPLATE.get())) &&
                ItemStack.isSameIgnoreDurability(entity.getItemBySlot(EquipmentSlotType.HEAD), new ItemStack(ItemInit.SP_HELMET.get())) &&
                ItemStack.isSameIgnoreDurability(entity.getItemBySlot(EquipmentSlotType.LEGS), new ItemStack(ItemInit.SP_LEGGINGS.get())) &&
                ItemStack.isSameIgnoreDurability(entity.getItemBySlot(EquipmentSlotType.FEET), new ItemStack(ItemInit.SP_BOOTS.get()));
    }
}
