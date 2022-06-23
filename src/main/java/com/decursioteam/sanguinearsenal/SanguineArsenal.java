package com.decursioteam.sanguinearsenal;

import com.decursioteam.sanguinearsenal.core.SangArsConfig;
import com.decursioteam.sanguinearsenal.core.SangArsSpells;
import com.decursioteam.sanguinearsenal.core.Util.Events;
import com.decursioteam.sanguinearsenal.core.Util.Item2DRenderer;
import com.decursioteam.sanguinearsenal.core.codex.ArsEclesiaCodex;
import com.decursioteam.sanguinearsenal.core.init.BlockInit;
import com.decursioteam.sanguinearsenal.core.init.EntityInit;
import com.decursioteam.sanguinearsenal.core.init.ItemInit;
import com.decursioteam.sanguinearsenal.core.init.SangArsItemGroup;
import com.decursioteam.sanguinearsenal.core.network.Network;
import com.decursioteam.sanguinearsenal.hud.BloodBar;
import com.decursioteam.sanguinearsenal.hud.BloodBarClassicBars;
import com.decursioteam.sanguinearsenal.items.curios.renderers.CurioRenderers;
import com.decursioteam.sanguinearsenal.recipes.EidolonCrucible;
import com.decursioteam.sanguinearsenal.recipes.EidolonWorktable;
import com.decursioteam.sanguinearsenal.recipes.rituals.EidolonRituals;
import com.decursioteam.sanguinearsenal.renderers.FlyingScytheRenderer;
import elucent.eidolon.entity.EmptyRenderer;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tfar.classicbar.EventHandler;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.Objects;

import static com.decursioteam.sanguinearsenal.core.Util.KeyBinds.BLOOD_AURA_KB;

@Mod("sanguinearsenal")
@Mod.EventBusSubscriber(modid = SanguineArsenal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SanguineArsenal {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "sanguinearsenal";

    public SanguineArsenal() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SangArsConfig.COMMON_CONFIG);
        bus.addListener(this::setup);
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new Events());
        EntityInit.init();
        if (FMLEnvironment.dist == Dist.CLIENT) {

            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::sendImc);
            ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));

            bus.register(Item2DRenderer.class);
            bus.addListener(this::init);

            MinecraftForge.EVENT_BUS.register(new BloodBar());

            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
            if(ModList.get().isLoaded("classicbar")) EventHandler.register(new BloodBarClassicBars());
        }
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> event.getRegistry().register(new BlockItem(block, new Item.Properties().tab(SangArsItemGroup.SANGUINE_ARSENAL))
                .setRegistryName(Objects.requireNonNull(block.getRegistryName()))));
    }

    private void setup(final FMLCommonSetupEvent event) {
        Network.init();
        event.enqueueWork(() -> {
            EidolonCrucible.init();
            EidolonRituals.init();
            SangArsSpells.RegisterSpells();
            ArsEclesiaCodex.init();
            EidolonWorktable.init();
            CurioRenderers.setupCurioRenderers();
        });
    }

    @OnlyIn(Dist.CLIENT)
    private void clientSetup(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.BLOOD_PROJECTILE.get(), EmptyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.FLYING_SCYTHE.get(), FlyingScytheRenderer::new);

        ClientRegistry.registerKeyBinding(BLOOD_AURA_KB);
    }

    public void sendImc(InterModEnqueueEvent evt) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BELT.getMessageBuilder().build());
    }

    private void init(ModelRegistryEvent modelRegistryEvent) {
        for (String item : Item2DRenderer.HAND_MODEL_ITEMS) {
            ModelLoader.addSpecialModel(new ModelResourceLocation(SanguineArsenal.MOD_ID + ":" + item + "_in_hand", "inventory"));
        }
    }
}
