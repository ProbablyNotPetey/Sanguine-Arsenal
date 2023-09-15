package com.decursioteam.sanguinearsenal.core.codex;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.decursioteam.sanguinearsenal.core.init.BlockInit;
import com.decursioteam.sanguinearsenal.core.init.ItemInit;
import com.decursioteam.sanguinearsenal.recipes.rituals.EidolonRituals;
import elucent.eidolon.Registry;
import elucent.eidolon.codex.*;
import elucent.eidolon.spell.Signs;
import elucent.eidolon.util.ColorUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

import static net.minecraftforge.fml.util.ObfuscationReflectionHelper.getPrivateValue;

public class ArsEclesiaCodex {

        static final String prefix = SanguineArsenal.MOD_ID + ".codex.";
        static Category MAIN;
        static Chapter SHADOW_INFUSED_INGOT;
        static Chapter SANGUINE_PRAETOR_ARMOR;
        static Chapter SANGUINE_APPARATUS;
        static Chapter SANGUINE_FEAST;
        static Chapter MAIN_INDEX;
        static Chapter BLESSING_OF_DARKNESS;

        public static String makePageKey(String path) {
        return prefix + "page." + path;
    }

        public static String makeChapterKey(String path) {
            return prefix + "chapter." + path;
        }


        public static void init() {
        {
            // todo: fix when spells are added
//            BLESSING_OF_DARKNESS = new Chapter(makeChapterKey("blessing_of_darkness"),
//                    new ChantPage(makePageKey("blessing_of_darkness.0"), Signs.MIND_SIGN, Signs.SOUL_SIGN, Signs.BLOOD_SIGN, Signs.SOUL_SIGN, Signs.MIND_SIGN),
//                    new TextPage(makePageKey("blessing_of_darkness.1"))
//            );
            BLESSING_OF_DARKNESS = new Chapter(makeChapterKey("blessing_of_darkness"),
                    new TitlePage(makePageKey("blessing_of_darkness.0")),
//                    new TextPage(makePageKey("blessing_of_darkness.1")),
                    new RitualPage(EidolonRituals.SIGIL_OF_THE_DARK_LORD, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.HARMING),
                            new RitualPage.RitualIngredient(new ItemStack(Registry.ZOMBIE_HEART.get()), false),
                            new RitualPage.RitualIngredient(new ItemStack(ItemInit.SHADOW_INFUSED_NUGGET.get()), false),
                            new RitualPage.RitualIngredient(new ItemStack(ItemInit.SHADOW_INFUSED_NUGGET.get()), false),
                            new RitualPage.RitualIngredient(new ItemStack(Registry.UNHOLY_SYMBOL.get()), true),
                            new RitualPage.RitualIngredient(new ItemStack(Registry.CRIMSON_ESSENCE.get()), false),
                            new RitualPage.RitualIngredient(new ItemStack(Registry.CRIMSON_ESSENCE.get()), false),
                            new RitualPage.RitualIngredient(new ItemStack(Registry.LESSER_SOUL_GEM.get()), false)
            ));

            SHADOW_INFUSED_INGOT = new Chapter(makeChapterKey("shadow_infused_ingot"),
                    new TitlePage(makePageKey("shadow_infused_ingot.0")),
                    new TextPage(makePageKey("shadow_infused_ingot.1")),
                    new CruciblePage(new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get(), 1),
                        new CruciblePage.CrucibleStep(new ItemStack(Registry.PEWTER_INGOT.get())),
                        new CruciblePage.CrucibleStep(2, new ItemStack(Registry.ENDER_CALX.get())),
                        new CruciblePage.CrucibleStep(new ItemStack(Registry.ENCHANTED_ASH.get())),
                        new CruciblePage.CrucibleStep(new ItemStack(Registry.ARCANE_GOLD_INGOT.get()), new ItemStack(Registry.LESSER_SOUL_GEM.get()))
            ));

            {
                SANGUINE_PRAETOR_ARMOR = new Chapter(makeChapterKey("sp_armor"),
                    new TitlePage(makePageKey("crimson_weave.0")),
                    new TextPage(makePageKey("crimson_weave.1")),
                    new WorktablePage(new ItemStack(ItemInit.CRIMSON_WEAVE.get(), 2),
                            ItemStack.EMPTY, new ItemStack(Registry.WICKED_WEAVE.get()), ItemStack.EMPTY,
                            new ItemStack(Registry.WICKED_WEAVE.get()), new ItemStack(Registry.SHADOW_GEM.get()), new ItemStack(Registry.WICKED_WEAVE.get()),
                            ItemStack.EMPTY, new ItemStack(Registry.WICKED_WEAVE.get()), ItemStack.EMPTY,

                            new ItemStack(Registry.CRIMSON_ESSENCE.get()), new ItemStack(Registry.CRIMSON_ESSENCE.get()), new ItemStack(Registry.CRIMSON_ESSENCE.get()), new ItemStack(Registry.CRIMSON_ESSENCE.get())
                    ),


//                    new CraftingPage(new ItemStack(ItemInit.CRIMSON_WEAVE.get()),
//                            new ItemStack(Registry.CRIMSON_ESSENCE.get()), new ItemStack(Registry.WICKED_WEAVE.get()), new ItemStack(Registry.CRIMSON_ESSENCE.get()),
//                            new ItemStack(Registry.WICKED_WEAVE.get()), new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get()), new ItemStack(Registry.WICKED_WEAVE.get()),
//                            new ItemStack(Registry.CRIMSON_ESSENCE.get()),new ItemStack(Registry.WICKED_WEAVE.get()),new ItemStack(Registry.CRIMSON_ESSENCE.get())),
                    /*new WorktablePage(new ItemStack(ItemInit.CRIMSON_WEAVE.get()),
                        new ItemStack(Items.WHITE_WOOL), new ItemStack(Registry.WICKED_WEAVE.get()), new ItemStack(Items.WHITE_WOOL),
                        new ItemStack(Registry.WICKED_WEAVE.get()), new ItemStack(ItemInit.SHADOW_INFUSED_NUGGET.get()), new ItemStack(Registry.WICKED_WEAVE.get()),
                        new ItemStack(Items.WHITE_WOOL), new ItemStack(Registry.WICKED_WEAVE.get()), new ItemStack(Items.WHITE_WOOL),
                        new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get()), new ItemStack(Items.RED_DYE), new ItemStack(Registry.LESSER_SOUL_GEM.get()), new ItemStack(Items.RED_DYE)
                    ),*/
                    new TitlePage(makePageKey("sp_hood")),
                    new WorktablePage(new ItemStack(ItemInit.SP_HELMET.get()),
                            new ItemStack(ItemInit.CRIMSON_WEAVE.get()), new ItemStack(ItemInit.CRIMSON_WEAVE.get()), new ItemStack(ItemInit.CRIMSON_WEAVE.get()),
                            new ItemStack(ItemInit.CRIMSON_WEAVE.get()), ItemStack.EMPTY, new ItemStack(ItemInit.CRIMSON_WEAVE.get()),
                            new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), ItemStack.EMPTY, new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()),

                            new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get()), ItemStack.EMPTY, new ItemStack(ItemInit.SANGUINE_CRYSTAL.get()), ItemStack.EMPTY
                    ),

//                    new CraftingPage(new ItemStack(ItemInit.SP_HELMET.get()),
//                            new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), new ItemStack(ItemInit.CRIMSON_WEAVE.get()), new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()),
//                            new ItemStack(ItemInit.CRIMSON_WEAVE.get()), new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get()), new ItemStack(ItemInit.CRIMSON_WEAVE.get()),
//                            new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()),new ItemStack(ItemInit.CRIMSON_WEAVE.get()),new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get())),
                    /*new TitledRitualPage(makePageKey("sp_hood"), EidolonRituals.SANGUINE_PRAETOR_HOOD, new ItemStack(Registry.SHADOW_GEM.get()),
                        new RitualPage.RitualIngredient(new ItemStack(ItemInit.CRIMSON_WEAVE.get()), false),
                        new RitualPage.RitualIngredient(new ItemStack(Registry.GOLD_INLAY.get()), false),
                        new RitualPage.RitualIngredient(new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), false),
                        new RitualPage.RitualIngredient(new ItemStack(Registry.WARLOCK_HAT.get()), true),
                        new RitualPage.RitualIngredient(new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), false),
                        new RitualPage.RitualIngredient(new ItemStack(Registry.GOLD_INLAY.get()), false),
                        new RitualPage.RitualIngredient(new ItemStack(ItemInit.CRIMSON_WEAVE.get()), false)
                    ),*/
                    new TitlePage(makePageKey("sp_tunic")),
                    new WorktablePage(new ItemStack(ItemInit.SP_CHESTPLATE.get()),
                            new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), ItemStack.EMPTY, new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()),
                            new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()),
                            new ItemStack(ItemInit.CRIMSON_WEAVE.get()), new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), new ItemStack(ItemInit.CRIMSON_WEAVE.get()),

                            new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get()), new ItemStack(Registry.GOLD_INLAY.get()), new ItemStack(ItemInit.SANGUINE_CRYSTAL.get()), new ItemStack(Registry.GOLD_INLAY.get())
                    ),



//                    new CraftingPage(new ItemStack(ItemInit.SP_CHESTPLATE.get()),
//                            new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get()), new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()),
//                            new ItemStack(ItemInit.CRIMSON_WEAVE.get()), new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), new ItemStack(ItemInit.CRIMSON_WEAVE.get()),
//                            new ItemStack(Registry.GOLD_INLAY.get()),new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()),new ItemStack(Registry.GOLD_INLAY.get())),

                    new TitlePage(makePageKey("sp_leggings")),
                    new WorktablePage(new ItemStack(ItemInit.SP_LEGGINGS.get()),
                            new ItemStack(ItemInit.CRIMSON_WEAVE.get()), new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), new ItemStack(ItemInit.CRIMSON_WEAVE.get()),
                            new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), new ItemStack(ItemInit.CRIMSON_WEAVE.get()), new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()),
                            new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), ItemStack.EMPTY, new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()),

                            new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get()), new ItemStack(Registry.GOLD_INLAY.get()), new ItemStack(ItemInit.SANGUINE_CRYSTAL.get()), new ItemStack(Registry.GOLD_INLAY.get())
                    ),


//                    new CraftingPage(new ItemStack(ItemInit.SP_LEGGINGS.get()),
//                            new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()),
//                            new ItemStack(Registry.GOLD_INLAY.get()), new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get()), new ItemStack(Registry.GOLD_INLAY.get()),
//                            new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), ItemStack.EMPTY, new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get())),
                    /*new TitledRitualPage(makePageKey("sp_tunic_and_leggings"),EidolonRituals.SANGUINE_PRAETOR_TUNIC, new ItemStack(Registry.SHADOW_GEM.get()),
                            new RitualPage.RitualIngredient(new ItemStack(ItemInit.CRIMSON_WEAVE.get()), false),
                            new RitualPage.RitualIngredient(new ItemStack(Registry.GOLD_INLAY.get()), false),
                            new RitualPage.RitualIngredient(new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), false),
                            new RitualPage.RitualIngredient(new ItemStack(Registry.WARLOCK_CLOAK.get()), true),
                            new RitualPage.RitualIngredient(new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), false),
                            new RitualPage.RitualIngredient(new ItemStack(Registry.GOLD_INLAY.get()), false),
                            new RitualPage.RitualIngredient(new ItemStack(ItemInit.CRIMSON_WEAVE.get()), false)
                    ),*/
                    new TitlePage(makePageKey("sp_boots")),
                    new WorktablePage(new ItemStack(ItemInit.SP_BOOTS.get()),
                            ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,
                            new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), ItemStack.EMPTY, new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()),
                            new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), new ItemStack(ItemInit.CRIMSON_WEAVE.get()), new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()),

                            new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get()), ItemStack.EMPTY, new ItemStack(ItemInit.SANGUINE_CRYSTAL.get()), ItemStack.EMPTY
                    ),


//                    new CraftingPage(new ItemStack(ItemInit.SP_BOOTS.get()),
//                        new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get()), new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()),
//                        new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), ItemStack.EMPTY, new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()),
//                        ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY),
                    /*new TitledRitualPage(makePageKey("sp_boots"), EidolonRituals.SANGUINE_PRAETOR_BOOTS, new ItemStack(Registry.SHADOW_GEM.get()),
                        new RitualPage.RitualIngredient(new ItemStack(ItemInit.CRIMSON_WEAVE.get()), false),
                        new RitualPage.RitualIngredient(new ItemStack(Registry.GOLD_INLAY.get()), false),
                        new RitualPage.RitualIngredient(new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), false),
                        new RitualPage.RitualIngredient(new ItemStack(Registry.WARLOCK_BOOTS.get()), true),
                        new RitualPage.RitualIngredient(new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), false),
                        new RitualPage.RitualIngredient(new ItemStack(Registry.GOLD_INLAY.get()), false),
                        new RitualPage.RitualIngredient(new ItemStack(ItemInit.CRIMSON_WEAVE.get()), false)
                    ),*/
                    new TitlePage(makePageKey("full_set.0")),
                    new TextPage(makePageKey("full_set.1")),
                    new TextPage(makePageKey("full_set.2")),
                    new TextPage(makePageKey("full_set.3")),
                    new TitlePage(makePageKey("full_set.overworld")),
                    new TitlePage(makePageKey("full_set.nether")),
                    new TitlePage(makePageKey("full_set.end")),
                    new TitlePage(makePageKey("full_set.twilight_forest")),
                    new TitlePage(makePageKey("full_set.undergarden")),
                    new TitlePage(makePageKey("full_set.everbright")),
                    new TitlePage(makePageKey("full_set.everdawn")),
                    new TitlePage(makePageKey("full_set.bumblezone"))
//                    new TitlePage(makePageKey("full_set.atum"))
                );
            }
            {
                SANGUINE_APPARATUS = new Chapter(makeChapterKey("sanguine_apparatus"),
                        new TitlePage(makePageKey("sanguine_crystal")),
                        new CruciblePage(new ItemStack(ItemInit.SANGUINE_CRYSTAL.get(), 1),
                                new CruciblePage.CrucibleStep(new ItemStack(Blocks.REDSTONE_BLOCK.asItem())),
                                new CruciblePage.CrucibleStep(1, new ItemStack(Items.GHAST_TEAR), new ItemStack(Registry.CRIMSON_ESSENCE.get())),
                                new CruciblePage.CrucibleStep(1, new ItemStack(Registry.LESSER_SOUL_GEM.get(), 2), new ItemStack(Registry.CRIMSON_ESSENCE.get())),
                                new CruciblePage.CrucibleStep(new ItemStack(Registry.SHADOW_GEM.get()))
                        ),
                        new TitlePage(makePageKey("blood_flask.0")),
                        new TextPage(makePageKey("blood_flask.1")),
                        new WorktablePage(new ItemStack(ItemInit.BLOOD_FLASK.get()),
                                ItemStack.EMPTY, new ItemStack(Registry.GOLD_INLAY.get()), ItemStack.EMPTY,
                                new ItemStack(Items.GLASS), new ItemStack(ItemInit.ZOMBIE_HEART_STEW.get()), new ItemStack(Items.GLASS),
                                new ItemStack(Registry.SHADOW_GEM.get()), new ItemStack(Items.GLASS), new ItemStack(Registry.SHADOW_GEM.get()),

                                new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get()), new ItemStack(Registry.CRIMSON_ESSENCE.get()), new ItemStack(ItemInit.SANGUINE_CRYSTAL.get()), new ItemStack(Registry.CRIMSON_ESSENCE.get())
                        ),

//                        new CraftingPage(new ItemStack(ItemInit.BLOOD_FLASK.get()),
//                                new ItemStack(Registry.CRIMSON_ESSENCE.get()), new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get()), new ItemStack(Registry.CRIMSON_ESSENCE.get()),
//                                new ItemStack(Items.GLASS), new ItemStack(ItemInit.ZOMBIE_HEART_STEW.get()), new ItemStack(Items.GLASS),
//                                new ItemStack(Registry.CRIMSON_ESSENCE.get()),new ItemStack(Registry.SHADOW_GEM.get()),new ItemStack(Registry.CRIMSON_ESSENCE.get())),
                        /*new TitledRitualPage(makePageKey("blood_flask.0"), EidolonRituals.BLOOD_FLASK, PotionUtils.setPotion(new ItemStack(Items.POTION),
                                Potions.REGENERATION),
                                new RitualPage.RitualIngredient(new ItemStack(Items.GLASS), false),
                                new RitualPage.RitualIngredient(new ItemStack(Registry.CRIMSON_ESSENCE.get()), false),
                                new RitualPage.RitualIngredient(new ItemStack(Registry.SHADOW_GEM.get()), false),
                                new RitualPage.RitualIngredient(PotionUtils.setPotion(new ItemStack(Items.POTION),
                                        Potions.WATER), true),
                                new RitualPage.RitualIngredient(new ItemStack(Registry.LESSER_SOUL_GEM.get()), false),
                                new RitualPage.RitualIngredient(new ItemStack(Registry.CRIMSON_ESSENCE.get()), false),
                                new RitualPage.RitualIngredient(new ItemStack(Items.GLASS), false)
                        ),*/
                        new TitlePage(makePageKey("scepter_of_blood.0")),
                        new TextPage(makePageKey("scepter_of_blood.1")),
                        /*new TitledRitualPage(makePageKey("scepter_of_blood.0"),EidolonRituals.BLOOD_SCEPTER, new ItemStack(ItemInit.SANGUINE_CRYSTAL.get()),
                                new RitualPage.RitualIngredient(new ItemStack(Registry.POLISHED_PLANKS.getBlock().asItem()), false),
                                new RitualPage.RitualIngredient(new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), false),
                                new RitualPage.RitualIngredient(new ItemStack(Registry.SOULFIRE_WAND.get()), true),
                                new RitualPage.RitualIngredient(new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), false),
                                new RitualPage.RitualIngredient(new ItemStack(Registry.POLISHED_PLANKS.getBlock().asItem()), false)
                        ),*/
                        new WorktablePage(new ItemStack(ItemInit.SCEPTER_OF_BLOOD.get()),
                                ItemStack.EMPTY, new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), new ItemStack(ItemInit.SANGUINE_CRYSTAL.get()),
                                ItemStack.EMPTY, new ItemStack(Registry.SOULFIRE_WAND.get()), new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()),
                                new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), ItemStack.EMPTY, ItemStack.EMPTY,

                                new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get()), new ItemStack(Registry.CRIMSON_ESSENCE.get()), ItemStack.EMPTY, new ItemStack(Registry.CRIMSON_ESSENCE.get())
                        ),

//                        new CraftingPage(new ItemStack(ItemInit.SCEPTER_OF_BLOOD.get()),
//                                new ItemStack(ItemInit.SANGUINE_CRYSTAL.get()), ItemStack.EMPTY, ItemStack.EMPTY,
//                                ItemStack.EMPTY, new ItemStack(Registry.SOULFIRE_WAND.get()), ItemStack.EMPTY,
//                                ItemStack.EMPTY, ItemStack.EMPTY, new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get())),
                        new TitlePage(makePageKey("praetor_scythe.0")),
                        new TextPage(makePageKey("praetor_scythe.1")),
                        new WorktablePage(new ItemStack(ItemInit.PRAETOR_SCYTHE.get()),
                                ItemStack.EMPTY, new ItemStack(ItemInit.SANGUINE_CRYSTAL.get()), new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()),
                                ItemStack.EMPTY, new ItemStack(Registry.REAPER_SCYTHE.get()), new ItemStack(ItemInit.SANGUINE_CRYSTAL.get()),
                                new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()), ItemStack.EMPTY, ItemStack.EMPTY,

                                new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get()), new ItemStack(Registry.GOLD_INLAY.get()), new ItemStack(Registry.CRIMSON_ESSENCE.get()), new ItemStack(Registry.GOLD_INLAY.get())
                        )


//                        new CraftingPage(new ItemStack(ItemInit.PRAETOR_SCYTHE.get()),
//                                new ItemStack(Registry.GOLD_INLAY.get()),new ItemStack(ItemInit.SANGUINE_CRYSTAL.get()), ItemStack.EMPTY,
//                                new ItemStack(ItemInit.SANGUINE_CRYSTAL.get()), new ItemStack(Registry.REAPER_SCYTHE.get()), ItemStack.EMPTY,
//                                ItemStack.EMPTY, ItemStack.EMPTY, new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()))
                        /*new TitledRitualPage(makePageKey("praetor_scythe.0"),EidolonRituals.PRAETOR_SCYTHE, new ItemStack(Registry.REAPER_SCYTHE.get()),
                                new RitualPage.RitualIngredient(new ItemStack(Items.CRYING_OBSIDIAN), false),
                                new RitualPage.RitualIngredient(new ItemStack(Registry.GOLD_INLAY.get()), false),
                                new RitualPage.RitualIngredient(new ItemStack(ItemInit.SANGUINE_CRYSTAL.get()), false),
                                new RitualPage.RitualIngredient(new ItemStack(ItemInit.SANGUINE_CRYSTAL.get()), false),
                                new RitualPage.RitualIngredient(new ItemStack(Registry.GOLD_INLAY.get()), false),
                                new RitualPage.RitualIngredient(new ItemStack(Items.CRYING_OBSIDIAN), false)
                        )*/
                );
            }
            SANGUINE_FEAST = new Chapter(makeChapterKey("sanguine_feast"),
                    new TitlePage(makePageKey("zombie_heart_stew")),
                    new CruciblePage(new ItemStack(ItemInit.ZOMBIE_HEART_STEW.get(), 1),
                            new CruciblePage.CrucibleStep(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.REGENERATION)),
                            new CruciblePage.CrucibleStep(1, new ItemStack(Registry.ZOMBIE_HEART.get())),
                            new CruciblePage.CrucibleStep(1, new ItemStack(Items.SUGAR)),
                            new CruciblePage.CrucibleStep(new ItemStack(Items.BOWL)))
                    );

        }

        makeIndexes();
    }


        static void makeIndexes() {
        MAIN_INDEX = new Chapter(makeChapterKey("sanguinearsenal_index"),
                new TitledIndexPage("sanguinearsenal.index",
                        new IndexPage.IndexEntry(BLESSING_OF_DARKNESS, new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get())),
                        new IndexPage.IndexEntry(SHADOW_INFUSED_INGOT, new ItemStack(BlockInit.SHADOW_INFUSED_BLOCK.get().asItem())),
                        new IndexPage.IndexEntry(SANGUINE_PRAETOR_ARMOR, new ItemStack(ItemInit.SP_HELMET.get())),
                        new IndexPage.IndexEntry(SANGUINE_APPARATUS, new ItemStack(ItemInit.BLOOD_FLASK.get())),
                        new IndexPage.IndexEntry(SANGUINE_FEAST, new ItemStack(ItemInit.ZOMBIE_HEART_STEW.get()))
                )
        );

        CodexChapters CChapters = new CodexChapters();
        List<Category> CCategories = getPrivateValue(CodexChapters.class, CChapters, "categories");

        if (CCategories != null) {
            CCategories.add(MAIN = new Category(SanguineArsenal.MOD_ID, new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get()), ColorUtil.packColor(220, 240, 22, 22), MAIN_INDEX));
        }
    }
}
