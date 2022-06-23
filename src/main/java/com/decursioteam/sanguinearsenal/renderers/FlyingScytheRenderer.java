package com.decursioteam.sanguinearsenal.renderers;

import com.decursioteam.sanguinearsenal.core.particles.circle.CircleTintData;
import com.decursioteam.sanguinearsenal.entities.FlyingScytheEntity;
import com.decursioteam.sanguinearsenal.items.swords.PraetorScythe;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

import java.awt.*;

public class FlyingScytheRenderer extends EntityRenderer<FlyingScytheEntity> {

    public FlyingScytheRenderer(EntityRendererManager entityRendererManager)
    {
        super(entityRendererManager);
        this.shadowRadius = 2F;
        this.shadowStrength = 0.5F;
    }


    @Override
    public void render(FlyingScytheEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        ItemStack itemstack = entityIn.getItem();
        matrixStackIn.scale(1.5f, 1.5f, 1.5f);
        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90F));
        matrixStackIn.mulPose(Vector3f.YN.rotationDegrees(90F));
        matrixStackIn.mulPose(Vector3f.XP.rotation((entityIn.age + partialTicks) * 0.9f));
        Minecraft.getInstance().getItemRenderer().renderStatic(itemstack, itemstack.getItem() instanceof PraetorScythe ? ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND : ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
        entityIn.getCommandSenderWorld().addParticle(new CircleTintData(
                        new Color(255, 0, 23), 0.35F,
                        5, 0.95F, false),
                entityIn.getX(), entityIn.getY() + 0.5F, entityIn.getZ(), 0F, 0F, 0F);
        matrixStackIn.popPose();

        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(FlyingScytheEntity entity)
    {
        return AtlasTexture.LOCATION_BLOCKS;
    }
}
