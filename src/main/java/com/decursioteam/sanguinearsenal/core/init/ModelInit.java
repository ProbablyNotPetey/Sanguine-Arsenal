package com.decursioteam.sanguinearsenal.core.init;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.decursioteam.sanguinearsenal.armor.sanguinepraetor.SPArmor;
import com.decursioteam.sanguinearsenal.armor.sanguinepraetor.SPArmorModel;
import com.decursioteam.sanguinearsenal.items.curios.models.BloodFlaskModel;
import com.decursioteam.sanguinearsenal.items.curios.renderers.BloodFlaskRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

public class ModelInit {


    public static final String[] HAND_MODEL_ITEMS = new String[]{"scepter_of_blood", "praetor_scythe"};

    public static final ModelLayerLocation
            SP_ARMOR_LAYER = new ModelLayerLocation(new ResourceLocation(SanguineArsenal.MOD_ID, "sp_armor"), "main"),
            BLOOD_FLASK_LAYER = new ModelLayerLocation(new ResourceLocation(SanguineArsenal.MOD_ID, "blood_flask"), "main");

    public static SPArmor SP_ARMOR_MODEL = null;
    public static BloodFlaskModel BLOOD_FLASK_MODEL = null;

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SP_ARMOR_LAYER, SPArmor::createBodyLayer);
        event.registerLayerDefinition(BLOOD_FLASK_LAYER, BloodFlaskModel::createBodyLayer);
    }

    public static void registerLayers(EntityRenderersEvent.AddLayers event) {
        SP_ARMOR_MODEL = new SPArmor(event.getEntityModels().bakeLayer(SP_ARMOR_LAYER));
        BLOOD_FLASK_MODEL = new BloodFlaskModel(event.getEntityModels().bakeLayer(BLOOD_FLASK_LAYER));
    }

    public static void registerRenderers(FMLClientSetupEvent event) {
        CuriosRendererRegistry.register(ItemInit.BLOOD_FLASK.get(), BloodFlaskRenderer::new);
    }
}
