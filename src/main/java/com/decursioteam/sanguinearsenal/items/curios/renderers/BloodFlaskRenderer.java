package com.decursioteam.sanguinearsenal.items.curios.renderers;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.decursioteam.sanguinearsenal.core.init.ModelInit;
import com.decursioteam.sanguinearsenal.items.curios.models.BloodFlaskModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class BloodFlaskRenderer implements ICurioRenderer {

    public static final ResourceLocation TEXTURE = new ResourceLocation(SanguineArsenal.MOD_ID, "textures/entity/blood_flask.png");
    private BloodFlaskModel model = null;

    public BloodFlaskRenderer() {
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity entity = slotContext.entity();
        if(model == null) {
            this.model = new BloodFlaskModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelInit.BLOOD_FLASK_LAYER));
        }

        ICurioRenderer.followBodyRotations(entity, this.model);
        this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.model.renderToBuffer(matrixStack, renderTypeBuffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE)), light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
