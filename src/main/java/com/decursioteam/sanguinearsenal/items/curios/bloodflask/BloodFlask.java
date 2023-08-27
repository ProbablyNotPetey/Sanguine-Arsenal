package com.decursioteam.sanguinearsenal.items.curios.bloodflask;

import com.decursioteam.sanguinearsenal.core.init.SangArsItemGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class BloodFlask extends Item {

    public BloodFlask(Properties properties) {
        super(new Item.Properties().tab(SangArsItemGroup.SANGUINE_ARSENAL).stacksTo(1));
    }

    public BloodFlask() {
        this(new Properties());
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 0;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return getMaxDamage(ItemStack.EMPTY) > 0;
    }

}