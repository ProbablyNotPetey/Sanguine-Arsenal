package com.decursioteam.sanguinearsenal.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class ToolTipItem extends Item {
    public ToolTipItem(Item.Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> component, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, component, isAdvanced);
        component.add(Component.translatable("item.sanguinearsenal." + Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(stack.getItem())).getPath() + ".tooltip").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC));
    }
}
