package com.decursioteam.sanguinearsenal.recipes.rituals;

import elucent.eidolon.ritual.Ritual;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SanguineRitual extends Ritual {

    public static final ResourceLocation SYMBOL = new ResourceLocation("eidolon", "particle/warding_sign");
    ItemStack result;

    SanguineRitual(ItemStack result, int color) {
        this(SYMBOL, color);
        this.result = result;
    }

    public SanguineRitual(ResourceLocation symbol, int color) {
        super(symbol, color);
    }

    public RitualResult start(World world, BlockPos pos) {
        if (!world.isClientSide()) {
            world.addFreshEntity(new ItemEntity(world, (double) pos.getX() + 0.5D, (double) pos.getY() + 2.5D, (double) pos.getZ() + 0.5D, this.result.copy()));
        }
        return RitualResult.TERMINATE;
    }

}
