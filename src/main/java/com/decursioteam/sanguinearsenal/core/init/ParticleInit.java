package com.decursioteam.sanguinearsenal.core.init;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.decursioteam.sanguinearsenal.core.particles.circle.CircleTintData;
import com.decursioteam.sanguinearsenal.core.particles.circle.CircleTintFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public class ParticleInit {

    public static ParticleType<CircleTintData> CIRCLE_TINT;

    @Mod.EventBusSubscriber(modid = SanguineArsenal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientParticleRegistry {
        @SubscribeEvent
        public static void onParticleFactoryRegistration(RegisterParticleProvidersEvent event) {
            event.register(CIRCLE_TINT, CircleTintFactory::new);
        }
    }

    @Mod.EventBusSubscriber(modid = SanguineArsenal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ServerParticleRegistry {
        @SubscribeEvent
        public static void onParticleTypeRegistration(final RegisterEvent event) {
            CIRCLE_TINT = new CircleTintFactory.CircleTintType();
            event.register(ForgeRegistries.Keys.PARTICLE_TYPES,
                    helper -> helper.register(new ResourceLocation(SanguineArsenal.MOD_ID, "circle_tint"), CIRCLE_TINT));
        }
    }
}