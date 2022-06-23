package com.decursioteam.sanguinearsenal.items;

import elucent.eidolon.item.ItemBase;
import net.minecraft.item.ItemStack;

public class SigilOfTheDarkLord extends ItemBase {
    public SigilOfTheDarkLord(Properties builderIn) {
        super(builderIn);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        return stack.copy();
    }
}
