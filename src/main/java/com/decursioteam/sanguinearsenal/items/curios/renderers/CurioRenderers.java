package com.decursioteam.sanguinearsenal.items.curios.renderers;

import com.decursioteam.sanguinearsenal.core.init.ItemInit;
import com.decursioteam.sanguinearsenal.items.curios.models.BeltModel;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.Map;

public class CurioRenderers {

    private static final Map<Item, CurioRenderer> renderers = new HashMap<>();

    public static CurioRenderer getRenderer(Item curio) {
        return renderers.get(curio);
    }

    @OnlyIn(Dist.CLIENT)
    public static void setupCurioRenderers() {
        //Items
        renderers.put(ItemInit.BLOOD_FLASK.get(), new BeltRenderer("blood_flask", BeltModel.blood_flask()));
    }
}