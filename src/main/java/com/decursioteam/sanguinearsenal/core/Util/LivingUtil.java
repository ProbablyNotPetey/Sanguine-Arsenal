package com.decursioteam.sanguinearsenal.core.Util;

import com.decursioteam.sanguinearsenal.core.init.ItemInit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class LivingUtil {
    public static boolean hasFullSPSet(Player player)
    {
        return ItemStack.isSameIgnoreDurability(player.getItemBySlot(EquipmentSlot.CHEST), new ItemStack(ItemInit.SP_CHESTPLATE.get())) &&
                ItemStack.isSameIgnoreDurability(player.getItemBySlot(EquipmentSlot.HEAD), new ItemStack(ItemInit.SP_HELMET.get())) &&
                ItemStack.isSameIgnoreDurability(player.getItemBySlot(EquipmentSlot.LEGS), new ItemStack(ItemInit.SP_LEGGINGS.get())) &&
                ItemStack.isSameIgnoreDurability(player.getItemBySlot(EquipmentSlot.FEET), new ItemStack(ItemInit.SP_BOOTS.get()));
    }
    public static boolean entityhasFullSPSet(LivingEntity entity)
    {
        return ItemStack.isSameIgnoreDurability(entity.getItemBySlot(EquipmentSlot.CHEST), new ItemStack(ItemInit.SP_CHESTPLATE.get())) &&
                ItemStack.isSameIgnoreDurability(entity.getItemBySlot(EquipmentSlot.HEAD), new ItemStack(ItemInit.SP_HELMET.get())) &&
                ItemStack.isSameIgnoreDurability(entity.getItemBySlot(EquipmentSlot.LEGS), new ItemStack(ItemInit.SP_LEGGINGS.get())) &&
                ItemStack.isSameIgnoreDurability(entity.getItemBySlot(EquipmentSlot.FEET), new ItemStack(ItemInit.SP_BOOTS.get()));
    }
}
