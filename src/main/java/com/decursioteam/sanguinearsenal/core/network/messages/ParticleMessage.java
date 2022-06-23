package com.decursioteam.sanguinearsenal.core.network.messages;

import com.decursioteam.sanguinearsenal.core.network.ClientPacketHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ParticleMessage {

    public ParticleMessage() {
    }

    public ParticleMessage(PacketBuffer buffer) {
    }

    public static void encode(ParticleMessage message, PacketBuffer buffer) {
    }

    public static void handle(ParticleMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.particleMessageHandler(message, contextSupplier));
        });
        context.setPacketHandled(true);
    }
}
