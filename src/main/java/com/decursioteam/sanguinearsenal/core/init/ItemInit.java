package com.decursioteam.sanguinearsenal.core.init;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.decursioteam.sanguinearsenal.armor.sanguinepraetor.SPArmorItem;
import com.decursioteam.sanguinearsenal.items.SigilOfTheDarkLord;
import com.decursioteam.sanguinearsenal.items.ToolTipItem;
import com.decursioteam.sanguinearsenal.items.curios.bloodflask.BloodFlaskItem;
import com.decursioteam.sanguinearsenal.items.scepterofblood.ScepterOfBloodItem;
import com.decursioteam.sanguinearsenal.items.swords.PraetorScythe;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SanguineArsenal.MOD_ID);
    public static final RegistryObject<Item> SP_BOOTS = ITEMS.register("sanguine_praetor_boots",
            () -> new SPArmorItem(EquipmentSlotType.FEET,
                    new Item.Properties().tab(SangArsItemGroup.SANGUINE_ARSENAL).rarity(Rarity.create("SANGUINE", TextFormatting.RED))));
    public static final RegistryObject<Item> SP_LEGGINGS = ITEMS.register("sanguine_praetor_leggings",
            () -> new SPArmorItem(EquipmentSlotType.LEGS,
                    new Item.Properties().tab(SangArsItemGroup.SANGUINE_ARSENAL).rarity(Rarity.create("SANGUINE", TextFormatting.RED))));
    public static final RegistryObject<Item> SP_CHESTPLATE = ITEMS.register("sanguine_praetor_tunic",
            () -> new SPArmorItem(EquipmentSlotType.CHEST,
                    new Item.Properties().tab(SangArsItemGroup.SANGUINE_ARSENAL).rarity(Rarity.create("SANGUINE", TextFormatting.RED))));
    public static final RegistryObject<Item> SP_HELMET = ITEMS.register("sanguine_praetor_hood",
            () -> new SPArmorItem(EquipmentSlotType.HEAD,
                    new Item.Properties().tab(SangArsItemGroup.SANGUINE_ARSENAL).rarity(Rarity.create("SANGUINE", TextFormatting.RED))));


    public static final RegistryObject<Item> CRIMSON_WEAVE = ITEMS.register("crimson_weave",
            () -> new Item(new Item.Properties().tab(SangArsItemGroup.SANGUINE_ARSENAL)));
    public static final RegistryObject<Item> SCEPTER_OF_BLOOD = ITEMS.register("scepter_of_blood",
            () -> new ScepterOfBloodItem(new Item.Properties().tab(SangArsItemGroup.SANGUINE_ARSENAL).
                    rarity(Rarity.UNCOMMON).stacksTo(1).setNoRepair()));
    public static final RegistryObject<Item> PRAETOR_SCYTHE = ITEMS.register("praetor_scythe",
            () -> new PraetorScythe(new Item.Properties().tab(SangArsItemGroup.SANGUINE_ARSENAL).
                    rarity(Rarity.create("SANGUINE", TextFormatting.RED)).stacksTo(1).setNoRepair()));

    public static final RegistryObject<Item> ZOMBIE_HEART_STEW = ITEMS.register("zombie_heart_stew", () -> new ToolTipItem(new Item.Properties().tab(SangArsItemGroup.SANGUINE_ARSENAL).stacksTo(16).food(new Food.Builder().effect(() -> new EffectInstance(Effects.REGENERATION, 2200, 2), 0.85F).alwaysEat().nutrition(20).meat().saturationMod(1.5F).build())));

    public static final RegistryObject<Item> SANGUINE_CRYSTAL = ITEMS.register("sanguine_crystal",
            () -> new SigilOfTheDarkLord(new Item.Properties().tab(SangArsItemGroup.SANGUINE_ARSENAL).stacksTo(1)));
    public static final RegistryObject<Item> SIGIL_OF_THE_DARK_LORD = ITEMS.register("sigil_of_the_dark_lord",
            () -> new SigilOfTheDarkLord(new Item.Properties().tab(SangArsItemGroup.SANGUINE_ARSENAL).rarity(Rarity.UNCOMMON).stacksTo(1)));
    public static final RegistryObject<Item> SHADOW_INFUSED_INGOT = ITEMS.register("shadow_ingot",
            () -> new Item(new Item.Properties().tab(SangArsItemGroup.SANGUINE_ARSENAL).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> SHADOW_INFUSED_NUGGET = ITEMS.register("shadow_nugget",
            () -> new Item(new Item.Properties().tab(SangArsItemGroup.SANGUINE_ARSENAL)));
    public static final RegistryObject<Item> BLOOD_FLASK = ITEMS.register("blood_flask", BloodFlaskItem::new);


}
