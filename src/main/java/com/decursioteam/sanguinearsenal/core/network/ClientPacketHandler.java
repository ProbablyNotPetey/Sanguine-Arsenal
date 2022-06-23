package com.decursioteam.sanguinearsenal.core.network;

import com.decursioteam.sanguinearsenal.core.network.messages.BloodAuraMessage;
import com.decursioteam.sanguinearsenal.core.network.messages.BloodMessage;
import com.decursioteam.sanguinearsenal.core.network.messages.ParticleMessage;
import com.decursioteam.sanguinearsenal.core.particles.circle.CircleTintData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.network.NetworkEvent;

import java.awt.*;
import java.util.function.Supplier;

import static com.decursioteam.sanguinearsenal.core.Util.BloodUtil.*;

public class ClientPacketHandler {

    public static Minecraft mc = Minecraft.getInstance();

    public static void bloodMessageHandler(BloodMessage message, Supplier<NetworkEvent.Context> contextSupplier, int bloodAmount) {
        assert mc.player != null;
        mc.player.getPersistentData().putInt(bloodTAG, bloodAmount);
    }

    public static void bloodAuraMessageHandler(BloodAuraMessage message, Supplier<NetworkEvent.Context> contextSupplier, boolean bloodAura) {
        assert mc.player != null;
        mc.player.getPersistentData().putBoolean(bloodAuraTAG, bloodAura);
    }

    public static void particleMessageHandler(ParticleMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        assert mc.player != null;
        PlayerEntity playerEntity = mc.player;
        assert mc.level != null;
        if(getBloodAura(playerEntity)){
            for (int i = 0; i < 2; i++) {
                float angle = (-0.02F * (playerEntity.tickCount * 3 + i * 160));
                double extraX = (double) (5 * 0.5F * MathHelper.sin((float) (Math.PI + angle))) + playerEntity.position().x;
                double extraZ = (double) (5 * 0.5F * MathHelper.cos(angle)) + playerEntity.position().z;
                double extraY = playerEntity.position().y + 0.5F;
                mc.level.addParticle(new CircleTintData(
                                new Color(255, 0, 23), 0.5F,
                                40, 0.95F, false),
                        extraX, extraY, extraZ, 0F, 0F, 0F);
            }
        }
    }
}
