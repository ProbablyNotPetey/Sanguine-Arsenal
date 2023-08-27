package com.decursioteam.sanguinearsenal.items.curios;

import com.decursioteam.sanguinearsenal.core.init.SangArsItemGroup;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class CuriosItem extends Item {

    public CuriosItem(Properties properties) {
        super(properties.stacksTo(1).tab(SangArsItemGroup.SANGUINE_ARSENAL).rarity(Rarity.RARE).fireResistant());
    }

    public CuriosItem() {
        this(new Properties());
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 20;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return getMaxDamage(ItemStack.EMPTY) > 0;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags) {
        tooltip.add(Component.translatable(getDescriptionId() + ".tooltip").withStyle(ChatFormatting.GRAY));
    }
}
