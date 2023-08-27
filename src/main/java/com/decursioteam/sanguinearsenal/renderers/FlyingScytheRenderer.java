package com.decursioteam.sanguinearsenal.renderers;

import com.decursioteam.sanguinearsenal.core.particles.circle.CircleTintData;
import com.decursioteam.sanguinearsenal.entities.FlyingScytheEntity;
import com.decursioteam.sanguinearsenal.items.swords.PraetorScythe;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Vector3f;
import net.minecraft.world.item.ItemStack;

import java.awt.*;

public class FlyingScytheRenderer extends EntityRenderer<FlyingScytheEntity> {

    public FlyingScytheRenderer(EntityRendererProvider.Context entityRendererManager)
    {
        super(entityRendererManager);
        this.shadowRadius = 2F;
        this.shadowStrength = 0.5F;
    }


    @Override
    public void render(FlyingScytheEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        ItemStack itemstack = entityIn.getItem();
        matrixStackIn.scale(1.5f, 1.5f, 1.5f);
        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90F));
        matrixStackIn.mulPose(Vector3f.YN.rotationDegrees(90F));
        matrixStackIn.mulPose(Vector3f.XP.rotation((entityIn.age + partialTicks) * 0.9f));
        Minecraft.getInstance().getItemRenderer().renderStatic(itemstack, itemstack.getItem() instanceof PraetorScythe ? ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND : ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn, 0);
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
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
