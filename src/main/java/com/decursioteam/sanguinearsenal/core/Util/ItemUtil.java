package com.decursioteam.sanguinearsenal.core.Util;

import com.decursioteam.sanguinearsenal.items.curios.bloodflask.BloodFlaskItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

public class ItemUtil {

    public static ItemStack bloodFlaskIdentifier(PlayerEntity playerEntity){
        ItemStack stack = ItemStack.EMPTY;
        if(CuriosApi.getCuriosHelper().findEquippedCurio(ItemUtil::isBloodFlaskItem, playerEntity).isPresent()) {
            stack = CuriosApi.getCuriosHelper().findEquippedCurio(ItemUtil::isBloodFlaskItem, playerEntity).get().getRight();
        }
        return stack;
    }
    public static boolean isBloodFlaskItem(ItemStack stack) {
        return stack.getItem() instanceof BloodFlaskItem;
    }
}
