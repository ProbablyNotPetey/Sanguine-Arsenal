package com.decursioteam.sanguinearsenal.core.Util;

import com.decursioteam.sanguinearsenal.items.curios.bloodflask.BloodFlaskItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

public class ItemUtil {

    public static ItemStack bloodFlaskIdentifier(Player playerEntity){
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
