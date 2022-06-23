package com.decursioteam.sanguinearsenal.items.curios.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class BeltModel extends BipedModel<LivingEntity> {

    protected final ModelRenderer charm = new ModelRenderer(this);

    private final float xOffset;
    private final float zOffset;
    private final float rotation;

    protected BeltModel(Function<ResourceLocation, RenderType> renderType, float xOffset, float zOffset, float rotation) {
        super(renderType, 0.5F, 0, 48, 48);
        this.xOffset = xOffset;
        this.zOffset = zOffset;
        this.rotation = rotation;

        setAllVisible(false);

        body = new ModelRenderer(this);

        // belt
        body.texOffs(0, 0);
        body.addBox(-4, 0, -2, 8, 12, 4, 0.5F);

        // charm
        body.addChild(charm);
    }

    private static BeltModel belt(float xOffset, float zOffset, float rotation) {
        return belt(RenderType::entityCutoutNoCull, xOffset, zOffset, rotation);
    }

    private static BeltModel belt(Function<ResourceLocation, RenderType> renderType, float xOffset, float zOffset, float rotation) {
        return new BeltModel(renderType, xOffset, zOffset, rotation);
    }

    public static BeltModel blood_flask() {
        return new BeltModel(RenderType::entityCutoutNoCull, 0, 0, -0.5F) {
            private final ModelRenderer flask;
            private final ModelRenderer neck;

            {
                flask = new ModelRenderer(this);
                flask.setPos(6F, 10.0F, -3.0F);
                flask.texOffs(0, 0).addBox(-4.0F, 2.9F, -3.0F, 7.0F, 4.0F, 7.0F, 0.0F, false);
                flask.texOffs(21, 12).addBox(-3.0F, 6.9F, -2.0F, 5.0F, 1.0F, 5.0F, 0.0F, false);
                flask.texOffs(0, 20).addBox(-2.0F, -1.35F, -1.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
                flask.texOffs(0, 12).addBox(-3.0F, 0.0F, -2.0F, 5.0F, 2.0F, 5.0F, 0.0F, false);

                neck = new ModelRenderer(this);
                neck.setPos(0.0F, 0.0F, 0.0F);
                flask.addChild(neck);
                neck.texOffs(22, 20).addBox(-1.25F, 1.5F, -0.25F, 2.0F, 2.0F, 2.0F, 0.0F, false);
                neck.texOffs(13, 20).addBox(-1.75F, 1.5F, -0.75F, 2.0F, 2.0F, 2.0F, 0.0F, false);
                neck.texOffs(5, 26).addBox(-0.25F, 1.5F, -0.75F, 1.0F, 2.0F, 1.0F, 0.0F, false);
                neck.texOffs(0, 26).addBox(-1.75F, 1.5F, 0.75F, 1.0F, 2.0F, 1.0F, 0.0F, false);
                setRotationAngle(flask, 0, 7.0F, 0);
            }

            @Override
            public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
                matrixStack.pushPose();
                assert Minecraft.getInstance().player != null;
                boolean isCrouching = Minecraft.getInstance().player.isCrouching();
                matrixStack.scale(0.75F, 0.75F, 0.75F);
                if(isCrouching) matrixStack.translate(0.0F, +0.1F, +0.1F);
                flask.render(matrixStack, buffer, packedLight, packedOverlay);
                neck.render(matrixStack, buffer, packedLight, packedOverlay);
                matrixStack.popPose();
            }

            public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
                modelRenderer.xRot = x;
                modelRenderer.yRot = y;
                modelRenderer.zRot = z;
            }
        };
    }

    public void setCharmPosition(int slot) {
        float xOffset = slot % 2 == 0 ? this.xOffset : -this.xOffset;
        float zOffset = slot % 4 < 2 ? this.zOffset : -this.zOffset;
        charm.setPos(xOffset, 9, zOffset);

        float rotation = slot % 4 < 2 ? 0 : (float) -Math.PI;
        rotation += slot % 2 == 0 ^ slot % 4 >= 2 ? this.rotation : -this.rotation;
        charm.yRot = rotation;
    }
}
