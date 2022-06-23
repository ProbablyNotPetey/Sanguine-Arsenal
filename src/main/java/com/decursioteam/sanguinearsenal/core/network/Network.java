package com.decursioteam.sanguinearsenal.core.network;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.decursioteam.sanguinearsenal.core.network.messages.BloodAuraMessage;
import com.decursioteam.sanguinearsenal.core.network.messages.BloodMessage;
import com.decursioteam.sanguinearsenal.core.network.messages.InputMessage;
import com.decursioteam.sanguinearsenal.core.network.messages.ParticleMessage;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Network {

    private static final String NETWORK_VERSION = "0.1.0";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(SanguineArsenal.MOD_ID, "main"),
            () -> NETWORK_VERSION,
            NETWORK_VERSION::equals,
            NETWORK_VERSION::equals
    );

    public static void init() {
        CHANNEL.registerMessage(0, InputMessage.class, InputMessage::encode, InputMessage::new, InputMessage::handle);
        CHANNEL.registerMessage(1, BloodMessage.class, BloodMessage::encode, BloodMessage::new, BloodMessage::handle);
        CHANNEL.registerMessage(2, BloodAuraMessage.class, BloodAuraMessage::encode, BloodAuraMessage::new, BloodAuraMessage::handle);
        CHANNEL.registerMessage(3, ParticleMessage.class, ParticleMessage::encode, ParticleMessage::new, ParticleMessage::handle);
    }

}
