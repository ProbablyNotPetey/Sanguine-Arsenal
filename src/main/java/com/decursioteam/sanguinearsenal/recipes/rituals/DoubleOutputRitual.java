package com.decursioteam.sanguinearsenal.recipes.rituals;

import elucent.eidolon.ritual.Ritual;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DoubleOutputRitual extends Ritual {
    public static final ResourceLocation SYMBOL = new ResourceLocation("eidolon", "particle/energy_sign");
    final ItemStack result;
    final ItemStack result2;

    public DoubleOutputRitual(ItemStack result, ItemStack result2, int color) {
        super(SYMBOL, color);
        this.result = result;
        this.result2 = result2;
    }

    public RitualResult start(Level world, BlockPos pos) {
        if (!world.isClientSide()) {
            world.addFreshEntity(new ItemEntity(world, (double) pos.getX() + 0.3D, (double) pos.getY() + 2.5D, (double) pos.getZ() + 0.3D, this.result.copy()));
            world.addFreshEntity(new ItemEntity(world, (double) pos.getX() + 0.6D, (double) pos.getY() + 2.5D, (double) pos.getZ() + 0.6D, this.result2.copy()));
        }

        return RitualResult.TERMINATE;
    }
}

