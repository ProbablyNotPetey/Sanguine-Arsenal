package com.decursioteam.sanguinearsenal.core.init;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.decursioteam.sanguinearsenal.core.particles.circle.CircleTintData;
import com.decursioteam.sanguinearsenal.core.particles.circle.CircleTintFactory;
import com.decursioteam.sanguinearsenal.core.particles.circle.CircleTintType;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

public class ParticleInit {



    @Mod.EventBusSubscriber(modid = SanguineArsenal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientParticleRegistry {
        @SubscribeEvent
        public static void onParticleFactoryRegistration(RegisterParticleProvidersEvent event) {
            event.register(ServerParticleRegistry.CIRCLE_TINT.get(), CircleTintFactory::new);
        }
    }

//    @Mod.EventBusSubscriber(modid = SanguineArsenal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ServerParticleRegistry {
//        @SubscribeEvent
//        public static void onParticleTypeRegistration(final RegisterEvent event) {
//            CIRCLE_TINT = new CircleTintType();
//            event.register(ForgeRegistries.Keys.PARTICLE_TYPES,
//                    helper -> helper.register(new ResourceLocation(SanguineArsenal.MOD_ID, "circle_tint"), CIRCLE_TINT));
//        }

        public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SanguineArsenal.MOD_ID);

        public static final RegistryObject<ParticleType<CircleTintData>> CIRCLE_TINT = PARTICLES.register("circle_tint", () -> new CircleTintType());

        public static void init(IEventBus bus) {
            PARTICLES.register(bus);
        }
    }
}