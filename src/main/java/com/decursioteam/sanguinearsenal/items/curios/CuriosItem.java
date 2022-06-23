package com.decursioteam.sanguinearsenal.items.curios;

import com.decursioteam.sanguinearsenal.core.init.SangArsItemGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
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
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flags) {
        tooltip.add(new TranslationTextComponent(getDescriptionId() + ".tooltip").withStyle(TextFormatting.GRAY));
    }
}
