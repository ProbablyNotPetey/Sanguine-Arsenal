package com.decursioteam.sanguinearsenal.items.curios.renderers;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.decursioteam.sanguinearsenal.core.init.ModelInit;
import com.decursioteam.sanguinearsenal.items.curios.models.BeltModel;
import com.decursioteam.sanguinearsenal.items.curios.models.BloodFlaskModel;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

public class BloodFlaskRenderer extends BeltRenderer{


    public BloodFlaskRenderer(ResourceLocation texture, BeltModel model) {
        super(texture, model);
    }

    public BloodFlaskRenderer() {
        this(new ResourceLocation(SanguineArsenal.MOD_ID, "texture"), ModelInit.BLOOD_FLASK_MODEL);
        //        this.model = new CharmOfKeepingModel(Minecraft.getInstance().getEntityModels().bakeLayer(TFModelLayers.CHARM_OF_KEEPING));

    }
}
