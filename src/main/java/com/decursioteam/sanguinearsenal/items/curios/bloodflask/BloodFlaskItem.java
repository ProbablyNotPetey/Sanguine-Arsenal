package com.decursioteam.sanguinearsenal.items.curios.bloodflask;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class BloodFlaskItem extends BloodFlaskCurio {

    public BloodFlaskItem() {
        super();
    }
    @Override
    public ICurio.SoundInfo getEquipSound(SlotContext slotContext, ItemStack stack) {
        return new ICurio.SoundInfo(SoundEvents.ARMOR_EQUIP_LEATHER, 1, 1);
    }
}