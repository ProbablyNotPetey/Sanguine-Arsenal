package com.decursioteam.sanguinearsenal.core;

import net.minecraftforge.common.ForgeConfigSpec;

public class SangArsConfig {
    public static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

    public static final Config COMMON = new Config();
    public static final ForgeConfigSpec COMMON_CONFIG = COMMON_BUILDER.build();

    public static final class Config {
        // Blood system
        public final ForgeConfigSpec.IntValue aggressiveEntitiesBloodValue;
        public final ForgeConfigSpec.IntValue passiveEntitiesBloodValue;
        public final ForgeConfigSpec.IntValue otherEntitiesBloodValue;

        // Blood Aura
        public final ForgeConfigSpec.BooleanValue bloodAuraParticles;
        public final ForgeConfigSpec.BooleanValue bloodAuraEffects;
        public final ForgeConfigSpec.IntValue praetorEffectMultiplier;
        public final ForgeConfigSpec.IntValue bloodFlaskEffectMultiplier;
        public final ForgeConfigSpec.IntValue bloodAuraEffectMultiplier;
        public final ForgeConfigSpec.IntValue bloodDrainSpeed;
        public final ForgeConfigSpec.IntValue bloodUsageMultiplier;
        public final ForgeConfigSpec.IntValue bloodDrainAmount;

        // Sanguine Praetor Armor
        public final ForgeConfigSpec.BooleanValue praetorArmorRegen;
        public final ForgeConfigSpec.BooleanValue praetorEyes;

        // Scepter of Blood
        public final ForgeConfigSpec.IntValue scepterOfBloodMeleeDamage;
        public final ForgeConfigSpec.DoubleValue scepterOfBloodMagicDamage;
        public final ForgeConfigSpec.DoubleValue scepterOfBloodAttackSpeed;
        public final ForgeConfigSpec.IntValue scepterOfBloodUseValue;

        // Praetor's Scythe
        public final ForgeConfigSpec.IntValue praetorScytheMeleeDamage;
        public final ForgeConfigSpec.DoubleValue praetorScytheAttackSpeed;
        public final ForgeConfigSpec.IntValue praetorScytheThrowValue;

        private Config() {
            COMMON_BUILDER.comment(
                    "I recommend changing the values from this file while the game isn't launched, you can still change some of these while the game is launched but it could cause issues!"
                    , "If you see a 'sanguinearsenal-common-<number>.toml.bak' file it means that something went wrong. " + "If you think that what you did was right and it was supposed to work, contact me."
            );
            COMMON_BUILDER.push("Blood System");
            aggressiveEntitiesBloodValue = COMMON_BUILDER
                    .comment("Set the amount of blood that the player receives when they kill an aggressive entity.")
                    .defineInRange("aggressiveEntitiesBloodValue", 5, 0, 20);
            passiveEntitiesBloodValue = COMMON_BUILDER
                    .comment("Set the amount of blood that the player receives when they kill a passive entity.")
                    .defineInRange("passiveEntitiesBloodValue", 5, 0, 20);
            otherEntitiesBloodValue = COMMON_BUILDER
                    .comment("Set the amount of blood that the player receives when they kill an entity that doesn't have compatibily with the mod.")
                    .defineInRange("otherEntitiesBloodValue", 5, 0, 20);
            COMMON_BUILDER.pop();
            COMMON_BUILDER.push("Blood Aura");
            bloodAuraParticles = COMMON_BUILDER
                    .comment("If this value is 'true', when a player activates 'Blood Aura' they will be followed by 2 particles moving in a circle around him.")
                    .define("bloodAuraParticles", true);
            bloodAuraEffects = COMMON_BUILDER
                    .comment("If this value is 'true', when a player activates 'Blood Aura' effects will be applied to him depending of the dimension he's in.")
                    .define("bloodAuraEffects", true);
            praetorEffectMultiplier = COMMON_BUILDER
                    .comment("Set the multiplier of the effects that a player receives when he's wearing the full set of Praetor Armor. ")
                    .defineInRange("praetorEffectMultiplier", 0, 0, 100000);
            bloodFlaskEffectMultiplier = COMMON_BUILDER
                    .comment("Set the multiplier of the effects that a player receives when he's wearing the full set of Praetor Armor and a Blood Flask.")
                    .defineInRange("bloodFlaskEffectMultiplier", 1, 0, 100000);
            bloodAuraEffectMultiplier = COMMON_BUILDER
                    .comment("Set the multiplier of the effects that a player receives when they have activated 'Blood Aura'.")
                    .defineInRange("bloodAuraEffectMultiplier", 3, 0, 100000);
            bloodDrainSpeed = COMMON_BUILDER
                    .comment("Set how quickly the blood amount that the player has in their Sanguine Praetor Armor while wearing a Blood Flask goes down when they activate 'Blood Aura'."
                            , "This value represents ticks, 20 ticks = 1 second.")
                    .defineInRange("bloodDrainSpeed", 20, 20, 100000000);
            bloodUsageMultiplier = COMMON_BUILDER
                    .comment("Set by how much should the blood consumption of the player should by multiplied when they doesn't wear a Blood Flask.")
                    .defineInRange("bloodUsageMultiplier", 2, 0, 20);
            bloodDrainAmount = COMMON_BUILDER
                    .comment("Set how much blood will be removed from the player's Sanguine Praetor Armor  when they have 'Blood Aura' activated, and a Blood Flask equipped."
                            , "This amount will be removed from the player's Blood Flask every 'bloodDrainSpeed'.")
                    .defineInRange("bloodDrainAmount", 1, 1, 20);
            COMMON_BUILDER.pop();
            COMMON_BUILDER.push("Praetor's Armor");
            praetorArmorRegen = COMMON_BUILDER
                    .comment("If this value is 'true', when a player has blood in their Praetor Armor they will receive the regen effect.")
                    .define("praetorArmorRegen", true);
            praetorEyes = COMMON_BUILDER
                    .comment("If this value is 'false', the Sanguine Praetor's will have a different texture that will get rid of the red eyes.")
                    .define("praetorEyes", true);
            COMMON_BUILDER.pop();
            COMMON_BUILDER.push("Scepter of Blood");
            scepterOfBloodMeleeDamage = COMMON_BUILDER
                    .comment("Set how much melee damage the Scepter of Blood has.")
                    .defineInRange("scepterOfBloodMeleeDamage", 7, 0, 100);
            scepterOfBloodMagicDamage = COMMON_BUILDER
                    .comment("Set how much magic damage the Scepter of Blood has.")
                    .defineInRange("scepterOfBloodMagicDamage", 6.0, 0.0, 100);
            scepterOfBloodAttackSpeed = COMMON_BUILDER
                    .comment("Note that the melee damage and attack speed values don't represent vanilla tools values, for example to get attack speed 1.3 vanilla you would need to set the attack speed to -2.4.", "Set the attack speed of the Scepter of Blood.")
                    .defineInRange("scepterOfBloodAttackSpeed", -2.7, -3.5, 15);
            scepterOfBloodUseValue = COMMON_BUILDER
                    .comment("Set the amount of blood that the player needs to use the Scepter of Blood ability.")
                    .defineInRange("scepterOfBloodUseValue", 2, 0, 20);
            COMMON_BUILDER.pop();
            COMMON_BUILDER.push("Sanguine Scythe");
            praetorScytheMeleeDamage = COMMON_BUILDER
                    .comment("Set how much melee damage the Praetor's Scythe has.")
                    .defineInRange("praetorScytheMeleeDamage", 9, 0, 100);
            praetorScytheAttackSpeed = COMMON_BUILDER
                    .comment("Note that the melee damage and attack speed values don't represent vanilla tools values, for example to get attack speed 1.3 vanilla you would need to set the attack speed to -2.4.", "Set the attack speed of the Praetor's Scythe.")
                    .defineInRange("praetorScytheAttackSpeed", -2.9, -3.5, 15);
            praetorScytheThrowValue = COMMON_BUILDER
                    .comment("Set the amount of blood that the player needs to throw the Praetor's Scythe using right click..")
                    .defineInRange("praetorScytheThrowValue", 5, 0, 20);
            COMMON_BUILDER.pop();
        }
    }
}
