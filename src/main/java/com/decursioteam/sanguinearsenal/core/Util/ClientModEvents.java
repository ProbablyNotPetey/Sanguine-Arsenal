package com.decursioteam.sanguinearsenal.core.Util;


import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.decursioteam.sanguinearsenal.core.Util.KeyBinds;
import com.decursioteam.sanguinearsenal.core.init.ModelInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(
        modid = SanguineArsenal.MOD_ID,
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class ClientModEvents {


    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ModelInit.registerLayerDefinitions(event);
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.AddLayers event) {
        ModelInit.registerLayers(event);
    }

    @SubscribeEvent
    public static void registerKeyBindings(final RegisterKeyMappingsEvent event) {
        event.register(KeyBinds.BLOOD_AURA_KB);
    }
}
