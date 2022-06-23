package com.decursioteam.sanguinearsenal.core.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class SangArsItemGroup extends ItemGroup {


    public static final SangArsItemGroup SANGUINE_ARSENAL = new SangArsItemGroup(ItemGroup.getGroupCountSafe(), "sanguinearsenal");

    public SangArsItemGroup(int p_i1853_1_, String p_i1853_2_) {
        super(p_i1853_1_, p_i1853_2_);
    }
    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get());
    }
}
