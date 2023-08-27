package com.decursioteam.sanguinearsenal.items.curios.renderers;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.decursioteam.sanguinearsenal.items.curios.models.BeltModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import top.theillusivec4.curios.api.type.capability.ICurio;

/*
public class BeltRenderer implements ICurioRenderer {

    protected final ResourceLocation texture;
    protected final BeltModel model;

    public BeltRenderer(String texturePath, BeltModel model) {
        this(new ResourceLocation(SanguineArsenal.MOD_ID, String.format("textures/entity/%s.png", texturePath)), model);
    }

    public BeltRenderer(ResourceLocation texture, BeltModel model) {
        this.texture = texture;
        this.model = model;

    }

    protected ResourceLocation getTexture() {
        return texture;
    }

    protected BeltModel getModel() {
        return model;
    }


    protected void render(PoseStack matrixStack, MultiBufferSource buffer, int light, boolean hasFoil) {
        RenderType renderType = model.renderType(getTexture());
        VertexConsumer vertexBuilder = ItemRenderer.getFoilBuffer(buffer, renderType, false, hasFoil);
        model.renderToBuffer(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource buffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ticks, float headYaw, float headPitch) {
        BeltModel model = getModel();
        LivingEntity entity = slotContext.entity();
        model.setupAnim(entity, limbSwing, limbSwingAmount, ticks, headYaw, headPitch);
        model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
//        model.setCharmPosition(index);
        ICurioRenderer.followBodyRotations(entity, model);
        render(matrixStack, buffer, light, stack.hasFoil());
    }
}
 */
