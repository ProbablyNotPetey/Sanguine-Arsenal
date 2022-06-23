package com.decursioteam.sanguinearsenal.core.Util;

import com.decursioteam.sanguinearsenal.core.network.Network;
import com.decursioteam.sanguinearsenal.core.network.messages.BloodAuraMessage;
import com.decursioteam.sanguinearsenal.core.network.messages.BloodMessage;
import com.teammetallurgy.atum.Atum;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.network.NetworkDirection;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Objects;

import static twilightforest.world.TFGenerationSettings.isStrictlyTwilightForest;

public class BloodUtil {

    public static final String bloodTAG = "blood_amount";
    public static final String bloodAuraTAG = "blood_aura";

    public static void setBloodAmount(PlayerEntity playerEntity, int amount) {
        playerEntity.getPersistentData().putInt(bloodTAG, amount);
        World world = playerEntity.getCommandSenderWorld();
        if(world.getServer() != null && !world.isClientSide()){
            ServerPlayerEntity serverPlayerEntity = world.getServer().getPlayerList().getPlayer(playerEntity.getUUID());
            assert serverPlayerEntity != null;
            Network.CHANNEL.sendTo(new BloodMessage(amount), serverPlayerEntity.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    public static int getBloodAmount(PlayerEntity playerEntity, boolean clientSide) {
        assert Minecraft.getInstance().player != null;
        if(clientSide) return Minecraft.getInstance().player.getPersistentData().getInt(bloodTAG);
        return playerEntity.getPersistentData().getInt(bloodTAG);
    }

    public static void setBloodAura(PlayerEntity playerEntity, boolean value) {
        playerEntity.getPersistentData().putBoolean(bloodAuraTAG, value);
        Network.CHANNEL.sendTo(new BloodAuraMessage(value), Objects.requireNonNull(Objects.requireNonNull(playerEntity.getServer()).getPlayerList().getPlayer(playerEntity.getUUID())).connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }

    public static boolean getBloodAura(PlayerEntity playerEntity) {
        return playerEntity.getPersistentData().getBoolean(bloodAuraTAG);
    }

    public static void addEffects(World world, PlayerEntity playerEntity, int amplifier, int duration, boolean regen) {
        if(regen && !playerEntity.hasEffect(Effects.REGENERATION)) playerEntity.addEffect(new EffectInstance(Effects.REGENERATION, duration, amplifier, true, true));
        if (world.dimension() == World.OVERWORLD && !playerEntity.hasEffect(Effects.DAMAGE_RESISTANCE)) {
            playerEntity.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, duration, amplifier, true, true));
        }
        if (world.dimension() == World.NETHER && !playerEntity.hasEffect(Effects.DAMAGE_BOOST)) {
            playerEntity.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, duration, amplifier, true, true));
        }
        if (world.dimension() == World.END && !playerEntity.hasEffect(Effects.SLOW_FALLING)) {
            playerEntity.addEffect(new EffectInstance(Effects.SLOW_FALLING, duration, amplifier, true, true));
        }
        if (ModList.get().isLoaded("atum") && world.dimension() == Atum.ATUM && !playerEntity.hasEffect(Effects.DIG_SPEED)) {
            playerEntity.addEffect(new EffectInstance(Effects.DIG_SPEED, duration, amplifier, true, true));
        }
        if (ModList.get().isLoaded("twilightforest") && isStrictlyTwilightForest(world) && !playerEntity.hasEffect(Effects.MOVEMENT_SPEED)) {
            playerEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, duration, amplifier, true, true));
        }
    }

    public static void addBlood(PlayerEntity playerEntity, int amount) {
        int prevAmount = getBloodAmount(playerEntity, false);
        int newAmount = Math.min(prevAmount + amount, 20);
        setBloodAmount(playerEntity, newAmount);

    }

    public static void removeBlood(PlayerEntity playerEntity, int amount) {
        int prevAmount = getBloodAmount(playerEntity, false);
        int newAmount = Math.max(prevAmount - amount, 0);
        setBloodAmount(playerEntity, newAmount);
    }

    public static boolean hasBloodFlask(PlayerEntity playerEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(ItemUtil::isBloodFlaskItem, playerEntity).isPresent();
    }
}
