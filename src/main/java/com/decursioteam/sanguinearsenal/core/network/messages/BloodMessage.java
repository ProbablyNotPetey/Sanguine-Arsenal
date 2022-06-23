package com.decursioteam.sanguinearsenal.core.network.messages;

import com.decursioteam.sanguinearsenal.core.network.ClientPacketHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class BloodMessage {

    int bloodAmount = 0;

    public BloodMessage(int bloodAmount) {
        this.bloodAmount = bloodAmount;
    }

    public BloodMessage(PacketBuffer buffer) {
        bloodAmount = buffer.readInt();
    }

    public static void encode(BloodMessage message, PacketBuffer buffer) {
        buffer.writeInt(message.bloodAmount);
    }

    public static void handle(BloodMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.bloodMessageHandler(message, contextSupplier, message.bloodAmount));
        });
        context.setPacketHandled(true);
    }
}
