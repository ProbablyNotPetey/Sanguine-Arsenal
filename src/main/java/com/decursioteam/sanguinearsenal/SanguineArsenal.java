package com.decursioteam.sanguinearsenal;

import com.decursioteam.sanguinearsenal.core.SangArsConfig;
import com.decursioteam.sanguinearsenal.core.SangArsSpells;
import com.decursioteam.sanguinearsenal.core.Util.Events;
import com.decursioteam.sanguinearsenal.core.codex.ArsEclesiaCodex;
import com.decursioteam.sanguinearsenal.core.init.*;
import com.decursioteam.sanguinearsenal.core.network.Network;
import com.decursioteam.sanguinearsenal.hud.BloodBar;
import com.decursioteam.sanguinearsenal.items.curios.renderers.CurioRenderers;
import com.decursioteam.sanguinearsenal.recipes.EidolonCrucible;
import com.decursioteam.sanguinearsenal.recipes.EidolonWorktable;
import com.decursioteam.sanguinearsenal.recipes.rituals.EidolonRituals;
import com.decursioteam.sanguinearsenal.renderers.FlyingScytheRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.Objects;

@Mod("sanguinearsenal")
@Mod.EventBusSubscriber(modid = SanguineArsenal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SanguineArsenal {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "sanguinearsenal";

    public SanguineArsenal() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SangArsConfig.COMMON_CONFIG);
        bus.addListener(this::setup);
        ParticleInit.ServerParticleRegistry.init(bus);
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        EntityInit.ENTITIES.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new Events());

        if (FMLEnvironment.dist == Dist.CLIENT) {

            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::sendImc);

            //todo : what is this for?
//            ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> Pair.of(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));


            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
//            if(ModList.get().isLoaded("classicbar")) EventHandler.register(new BloodBarClassicBars());
        }
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegisterEvent event) {

        BlockInit.BLOCKS.getEntries().stream().forEach(block -> {
            event.register(ForgeRegistries.Keys.ITEMS,
                    helper -> helper.register(new ResourceLocation(Objects.requireNonNull(block.getId().toString())), new BlockItem(block.get(), new Item.Properties().tab(SangArsItemGroup.SANGUINE_ARSENAL))));
        });

    }

    private void setup(final FMLCommonSetupEvent event) {
        Network.init();
        event.enqueueWork(() -> {
//            EidolonCrucible.init();
            EidolonRituals.init();
//            SangArsSpells.RegisterSpells();
            ArsEclesiaCodex.init();
            EidolonWorktable.init();
//            CurioRenderers.setupCurioRenderers();
        });
    }

    @OnlyIn(Dist.CLIENT)
    private void clientSetup(final FMLClientSetupEvent event) {
        EntityRenderers.register(EntityInit.BLOOD_PROJECTILE.get(), NoopRenderer::new);
        EntityRenderers.register(EntityInit.FLYING_SCYTHE.get(), FlyingScytheRenderer::new);

        event.enqueueWork(() -> {
            ModelInit.registerRenderers(event);
        });
    }

    public void sendImc(InterModEnqueueEvent evt) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BELT.getMessageBuilder().build());
    }
}
