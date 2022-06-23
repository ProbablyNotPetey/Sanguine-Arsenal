package com.decursioteam.sanguinearsenal.core.network.messages;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

import static com.decursioteam.sanguinearsenal.core.Util.BloodUtil.getBloodAmount;
import static com.decursioteam.sanguinearsenal.core.Util.BloodUtil.setBloodAura;
import static com.decursioteam.sanguinearsenal.core.Util.LivingUtil.hasFullSPSet;

public class InputMessage {

    public InputMessage() {
    }

    public InputMessage(PacketBuffer buffer) {
    }

    public static void encode(InputMessage message, PacketBuffer buffer) {
    }

    public static void handle(InputMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayerEntity playerEntity = context.getSender();
            assert playerEntity != null;
            if (hasFullSPSet(playerEntity) && getBloodAmount(playerEntity, false) >= 20) {
                setBloodAura(playerEntity, true);
            }
        });
        context.setPacketHandled(true);
    }
}
