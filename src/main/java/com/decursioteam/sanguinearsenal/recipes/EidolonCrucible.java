package com.decursioteam.sanguinearsenal.recipes;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.decursioteam.sanguinearsenal.core.init.ItemInit;
import elucent.eidolon.Registry;
import elucent.eidolon.recipe.CrucibleRecipe;
import elucent.eidolon.recipe.CrucibleRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;

public class EidolonCrucible {

    public static void init() {
        CrucibleRegistry.register(new CrucibleRecipe(new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get())).setRegistryName(SanguineArsenal.MOD_ID, "shadow_infused_ingot")
                .addStep(Registry.PEWTER_INGOT.get())
                .addStirringStep(2, Registry.ENDER_CALX.get())
                .addStep(Registry.ENCHANTED_ASH.get())
                .addStep(Registry.ARCANE_GOLD_INGOT.get(), Registry.LESSER_SOUL_GEM.get()));
        CrucibleRegistry.register(new CrucibleRecipe(new ItemStack(ItemInit.ZOMBIE_HEART_STEW.get())).setRegistryName(SanguineArsenal.MOD_ID, "zombie_heart_stew")
                .addStep(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.REGENERATION))
                .addStirringStep(1, Registry.ZOMBIE_HEART.get())
                .addStirringStep(1, Items.SUGAR)
                .addStep(Items.BOWL));
        CrucibleRegistry.register(new CrucibleRecipe(new ItemStack(ItemInit.SANGUINE_CRYSTAL.get())).setRegistryName(SanguineArsenal.MOD_ID, "sanguine_crystal")
                .addStep(Blocks.REDSTONE_BLOCK.asItem())
                .addStirringStep(1, Items.GHAST_TEAR, Registry.CRIMSON_ESSENCE.get())
                .addStirringStep(1, Registry.LESSER_SOUL_GEM.get(), Registry.CRIMSON_ESSENCE.get())
                .addStep(Items.DIAMOND));
    }
}
