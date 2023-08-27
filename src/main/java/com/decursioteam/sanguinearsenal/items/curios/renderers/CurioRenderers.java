package com.decursioteam.sanguinearsenal.items.curios.renderers;

import com.decursioteam.sanguinearsenal.core.init.ItemInit;
import com.decursioteam.sanguinearsenal.items.curios.models.BeltModel;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import java.util.HashMap;
import java.util.Map;

public class CurioRenderers {

    private static final Map<Item, ICurioRenderer> renderers = new HashMap<>();

    public static ICurioRenderer getRenderer(Item curio) {
        return renderers.get(curio);
    }

    @OnlyIn(Dist.CLIENT)
    public static void setupCurioRenderers() {
        //Items
//        renderers.put(ItemInit.BLOOD_FLASK.get(), new BeltRenderer("blood_flask", BeltModel.blood_flask()));
    }
}