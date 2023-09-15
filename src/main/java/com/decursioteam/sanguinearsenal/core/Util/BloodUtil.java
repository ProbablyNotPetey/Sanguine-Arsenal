package com.decursioteam.sanguinearsenal.core.Util;

import com.decursioteam.sanguinearsenal.core.network.Network;
import com.decursioteam.sanguinearsenal.core.network.messages.BloodAuraMessage;
import com.decursioteam.sanguinearsenal.core.network.messages.BloodMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkDirection;
import top.theillusivec4.curios.api.CuriosApi;
import twilightforest.world.registration.TFGenerationSettings;

import java.util.Objects;


public class BloodUtil {

    public static final String bloodTAG = "blood_amount";
    public static final String bloodAuraTAG = "blood_aura";

    public static void setBloodAmount(Player playerEntity, int amount) {
        playerEntity.getPersistentData().putInt(bloodTAG, amount);
        Level world = playerEntity.getCommandSenderWorld();
        if(world.getServer() != null && !world.isClientSide()){
            ServerPlayer serverPlayer = world.getServer().getPlayerList().getPlayer(playerEntity.getUUID());
            assert serverPlayer != null;
            Network.CHANNEL.sendTo(new BloodMessage(amount), serverPlayer.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    public static int getBloodAmount(Player playerEntity, boolean clientSide) {
        assert Minecraft.getInstance().player != null;
        if(clientSide) return Minecraft.getInstance().player.getPersistentData().getInt(bloodTAG);
        return playerEntity.getPersistentData().getInt(bloodTAG);
    }

    public static void setBloodAura(Player playerEntity, boolean value) {
        playerEntity.getPersistentData().putBoolean(bloodAuraTAG, value);
        Network.CHANNEL.sendTo(new BloodAuraMessage(value), Objects.requireNonNull(Objects.requireNonNull(playerEntity.getServer()).getPlayerList().getPlayer(playerEntity.getUUID())).connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }

    public static boolean getBloodAura(Player playerEntity) {
        return playerEntity.getPersistentData().getBoolean(bloodAuraTAG);
    }

    public static void addDimensionEffects(Level world, Player playerEntity, int amplifier, int duration) {
        MobEffect effect = null;
        if(world.dimension().equals(Level.OVERWORLD)) effect = MobEffects.DAMAGE_RESISTANCE;
        if(world.dimension().equals(Level.NETHER)) effect = MobEffects.DAMAGE_BOOST;
        if(world.dimension().equals(Level.END)) effect = MobEffects.SLOW_FALLING;
        if(ModList.get().isLoaded("twilightforest") && world.dimension().equals(TFGenerationSettings.DIMENSION_KEY)) effect = MobEffects.MOVEMENT_SPEED;

        if(effect == null) return;
        if(!playerEntity.hasEffect(effect)) {
            playerEntity.addEffect(new MobEffectInstance(effect, duration, amplifier, true, true));
        }
    }
    public static void addRegen(Player playerEntity, int amplifier, int duration) {
        if(!playerEntity.hasEffect(MobEffects.REGENERATION)) playerEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duration, amplifier, true, true));
    }

    public static void addBlood(Player playerEntity, int amount) {
        int prevAmount = getBloodAmount(playerEntity, false);
        int newAmount = Math.min(prevAmount + amount, 20);
        setBloodAmount(playerEntity, newAmount);

    }

    public static void removeBlood(Player playerEntity, int amount) {
        int prevAmount = getBloodAmount(playerEntity, false);
        int newAmount = Math.max(prevAmount - amount, 0);
        setBloodAmount(playerEntity, newAmount);
    }

    public static boolean hasBloodFlask(Player playerEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(ItemUtil::isBloodFlaskItem, playerEntity).isPresent();
    }
}
