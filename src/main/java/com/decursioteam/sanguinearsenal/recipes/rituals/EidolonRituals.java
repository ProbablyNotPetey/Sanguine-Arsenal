package com.decursioteam.sanguinearsenal.recipes.rituals;

import elucent.eidolon.ritual.Ritual;

public class EidolonRituals {

    public static Ritual SANGUINE_PRAETOR_TUNIC;
    public static Ritual SANGUINE_PRAETOR_BOOTS;
    public static Ritual SANGUINE_PRAETOR_HOOD;
    public static Ritual PRAETOR_SCYTHE;
    public static Ritual BLOOD_FLASK;
    public static Ritual BLOOD_SCEPTER;

    public static void init() {

        /*int fireColor = ColorUtil.packColor(255, 230, 30, 40);
        int purpleColor = ColorUtil.packColor(255, 128, 20, 135);

        SANGUINE_PRAETOR_TUNIC = RitualRegistry.register(new MultiItemSacrifice(Registry.SHADOW_GEM.get(), Registry.WARLOCK_CLOAK.get()),
                (new DoubleOutputRitual(new ItemStack(ItemInit.SP_LEGGINGS.get()), new ItemStack(ItemInit.SP_CHESTPLATE.get()), purpleColor)
                        .setRegistryName(SanguineArsenal.MOD_ID, "sanguine_praetor_tunic")
                        .addRequirement(new ItemRequirement(ItemInit.SHADOW_INFUSED_INGOT.get()))
                        .addRequirement(new ItemRequirement(ItemInit.CRIMSON_WEAVE.get()))
                        .addRequirement(new ItemRequirement(Registry.GOLD_INLAY.get()))
                        .addRequirement(new ItemRequirement(Registry.WARLOCK_CLOAK.get()))
                        .addRequirement(new ItemRequirement(Registry.GOLD_INLAY.get()))
                        .addRequirement(new ItemRequirement(ItemInit.CRIMSON_WEAVE.get()))
                        .addRequirement(new ItemRequirement(ItemInit.SHADOW_INFUSED_INGOT.get()))
                        .addRequirement(new HealthRequirement(20))
        ));
        SANGUINE_PRAETOR_HOOD = RitualRegistry.register(new MultiItemSacrifice(Registry.SHADOW_GEM.get(), Registry.WARLOCK_HAT.get()),
                (new ForcefulRitual(new ItemStack(ItemInit.SP_HELMET.get()), purpleColor)
                        .setRegistryName(SanguineArsenal.MOD_ID, "sanguine_praetor_hood")
                        .addRequirement(new ItemRequirement(ItemInit.SHADOW_INFUSED_INGOT.get()))
                        .addRequirement(new ItemRequirement(ItemInit.CRIMSON_WEAVE.get()))
                        .addRequirement(new ItemRequirement(Registry.GOLD_INLAY.get()))
                        .addRequirement(new ItemRequirement(Registry.WARLOCK_HAT.get()))
                        .addRequirement(new ItemRequirement(Registry.GOLD_INLAY.get()))
                        .addRequirement(new ItemRequirement(ItemInit.CRIMSON_WEAVE.get()))
                        .addRequirement(new ItemRequirement(ItemInit.SHADOW_INFUSED_INGOT.get()))
                        .addRequirement(new HealthRequirement(10))
        ));
        SANGUINE_PRAETOR_BOOTS = RitualRegistry.register(new MultiItemSacrifice(Registry.SHADOW_GEM.get(), Registry.WARLOCK_BOOTS.get()),
                (new ForcefulRitual(new ItemStack(ItemInit.SP_BOOTS.get()), purpleColor)
                        .setRegistryName(SanguineArsenal.MOD_ID, "sanguine_praetor_boots")
                        .addRequirement(new ItemRequirement(ItemInit.SHADOW_INFUSED_INGOT.get()))
                        .addRequirement(new ItemRequirement(ItemInit.CRIMSON_WEAVE.get()))
                        .addRequirement(new ItemRequirement(Registry.GOLD_INLAY.get()))
                        .addRequirement(new ItemRequirement(Registry.WARLOCK_BOOTS.get()))
                        .addRequirement(new ItemRequirement(Registry.GOLD_INLAY.get()))
                        .addRequirement(new ItemRequirement(ItemInit.CRIMSON_WEAVE.get()))
                        .addRequirement(new ItemRequirement(ItemInit.SHADOW_INFUSED_INGOT.get()))
                        .addRequirement(new HealthRequirement(10))
                ));
        BLOOD_FLASK = RitualRegistry.register(new MultiItemSacrifice(PotionUtils.setPotion(new ItemStack(Items.POTION),
                        Potions.REGENERATION), PotionUtils.setPotion(new ItemStack(Items.POTION),
                Potions.WATER)),
                (new ForcefulRitual(new ItemStack(ItemInit.BLOOD_FLASK.get()), fireColor)
                        .setRegistryName(SanguineArsenal.MOD_ID, "blood_flask_ritual")
                        .addRequirement(new ItemRequirement(Tags.Items.GLASS))
                        .addRequirement(new ItemRequirement(Registry.CRIMSON_ESSENCE.get()))
                        .addRequirement(new ItemRequirement(Registry.SHADOW_GEM.get()))
                        .addRequirement(new ItemRequirement(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)))
                        .addRequirement(new ItemRequirement(Registry.LESSER_SOUL_GEM.get()))
                        .addRequirement(new ItemRequirement(Registry.CRIMSON_ESSENCE.get()))
                        .addRequirement(new ItemRequirement(Tags.Items.GLASS))
                        .addRequirement(new HealthRequirement(20))
                ));
        BLOOD_SCEPTER = RitualRegistry.register(new MultiItemSacrifice(ItemInit.SANGUINE_CRYSTAL.get(), Registry.SOULFIRE_WAND.get()),
                (new ForcefulRitual(new ItemStack(ItemInit.SCEPTER_OF_BLOOD.get()), fireColor)
                        .setRegistryName(SanguineArsenal.MOD_ID, "blood_scepter_ritual")
                        .addRequirement(new ItemRequirement(Registry.POLISHED_PLANKS.getBlock().asItem()))
                        .addRequirement(new ItemRequirement(ItemInit.SHADOW_INFUSED_INGOT.get()))
                        .addRequirement(new ItemRequirement(Registry.SOULFIRE_WAND.get()))
                        .addRequirement(new ItemRequirement(ItemInit.SHADOW_INFUSED_INGOT.get()))
                        .addRequirement(new ItemRequirement(Registry.POLISHED_PLANKS.getBlock().asItem()))
                        .addRequirement(new HealthRequirement(25))
                ));
        PRAETOR_SCYTHE = RitualRegistry.register(Registry.REAPER_SCYTHE.get(),
                (new SanguineRitual(new ItemStack(ItemInit.PRAETOR_SCYTHE.get()), fireColor)
                        .setRegistryName(SanguineArsenal.MOD_ID, "praetor_scythe_ritual")
                        .addRequirement(new ItemRequirement(Items.CRYING_OBSIDIAN))
                        .addRequirement(new ItemRequirement(Registry.GOLD_INLAY.get()))
                        .addRequirement(new ItemRequirement(ItemInit.SANGUINE_CRYSTAL.get()))
                        .addRequirement(new ItemRequirement(ItemInit.SANGUINE_CRYSTAL.get()))
                        .addRequirement(new ItemRequirement(Registry.GOLD_INLAY.get()))
                        .addRequirement(new ItemRequirement(Items.CRYING_OBSIDIAN))
                        .addRequirement(new HealthRequirement(40))
                ));*/
    }
}
