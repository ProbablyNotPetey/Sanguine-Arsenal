package com.decursioteam.sanguinearsenal.core.network.messages;

import com.decursioteam.sanguinearsenal.core.network.ClientPacketHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class BloodAuraMessage {

    boolean bloodAura = false;

    public BloodAuraMessage(boolean bloodAmount) {
        this.bloodAura = bloodAmount;
    }

    public BloodAuraMessage(PacketBuffer buffer) {
        bloodAura = buffer.readBoolean();
    }

    public static void encode(BloodAuraMessage message, PacketBuffer buffer) {
        buffer.writeBoolean(message.bloodAura);
    }

    public static void handle(BloodAuraMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.bloodAuraMessageHandler(message, contextSupplier, message.bloodAura));
        });
        context.setPacketHandled(true);
    }
}
