package com.decursioteam.sanguinearsenal.items.curios.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class BloodFlaskModel extends BeltModel{
//    private final ModelPart flask;
//    private final ModelPart neck;


//    protected BloodFlaskModel(Function<ResourceLocation, RenderType> renderType, float xOffset, float zOffset, float rotation) {
//        super(renderType, xOffset, zOffset, rotation);
//        flask = new ModelPart(this);
//        flask.setPos(6F, 10.0F, -3.0F);
//        flask.texOffs(0, 0).addBox(-4.0F, 2.9F, -3.0F, 7.0F, 4.0F, 7.0F, 0.0F, false);
//        flask.texOffs(21, 12).addBox(-3.0F, 6.9F, -2.0F, 5.0F, 1.0F, 5.0F, 0.0F, false);
//        flask.texOffs(0, 20).addBox(-2.0F, -1.35F, -1.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
//        flask.texOffs(0, 12).addBox(-3.0F, 0.0F, -2.0F, 5.0F, 2.0F, 5.0F, 0.0F, false);
//
//        neck = new ModelPart(this);
//        neck.setPos(0.0F, 0.0F, 0.0F);
//        flask.addChild(neck);
//        neck.texOffs(22, 20).addBox(-1.25F, 1.5F, -0.25F, 2.0F, 2.0F, 2.0F, 0.0F, false);
//        neck.texOffs(13, 20).addBox(-1.75F, 1.5F, -0.75F, 2.0F, 2.0F, 2.0F, 0.0F, false);
//        neck.texOffs(5, 26).addBox(-0.25F, 1.5F, -0.75F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//        neck.texOffs(0, 26).addBox(-1.75F, 1.5F, 0.75F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//        setRotationAngle(flask, 0, 7.0F, 0);
//    }

    //offset: 0, -0.5F
    public BloodFlaskModel(ModelPart root, Function<ResourceLocation, RenderType> renderType, float xOffset, float zOffset, float rotation) {
        super(root, renderType, xOffset, zOffset, rotation);
    }

    public BloodFlaskModel(ModelPart root) {
        this(root, RenderType::entityCutoutNoCull, 0F, -0.5F, 0.0F);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0.0F), 0.0F);
        PartDefinition root = createHumanoidAlias(mesh);

        PartDefinition body = root.getChild("body");
//        PartDefinition right_foot = root.getChild("right_foot");
//        PartDefinition left_foot = root.getChild("left_foot");
        PartDefinition right_leg = root.getChild("right_legging");
        PartDefinition left_leg = root.getChild("left_legging");
        PartDefinition left_arm = root.getChild("left_arm");
        PartDefinition right_arm = root.getChild("right_arm");
        PartDefinition head = root.getChild("head");

        PartDefinition flask = root.addOrReplaceChild("flask", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4.0F, 2.9F, -3.0F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(21, 12).addBox(-3.0F, 6.9F, -2.0F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-2.0F, -1.35F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 12).addBox(-3.0F, 0.0F, -2.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                , PartPose.offset(6F, 10.0F, -3.0F));

        flask.addOrReplaceChild("neck", CubeListBuilder.create()
                .texOffs(22, 20).addBox(-1.25F, 1.5F, -0.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(13, 20).addBox(-1.75F, 1.5F, -0.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(5, 26).addBox(-0.25F, 1.5F, -0.75F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 26).addBox(-1.75F, 1.5F, 0.75F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                , PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 7.0F, 0.0F));

        //maybe 192, 192
        return LayerDefinition.create(mesh, 48, 48);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        matrixStack.pushPose();
        assert Minecraft.getInstance().player != null;
        boolean isCrouching = Minecraft.getInstance().player.isCrouching();
        matrixStack.scale(0.75F, 0.75F, 0.75F);
        if(isCrouching) matrixStack.translate(0.0F, +0.1F, +0.1F);
        root.getChild("flask").render(matrixStack, buffer, packedLight, packedOverlay);
        root.getChild("flask").getChild("neck").render(matrixStack, buffer, packedLight, packedOverlay);
        matrixStack.popPose();
    }

//    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
//        modelRenderer.xRot = x;
//        modelRenderer.yRot = y;
//        modelRenderer.zRot = z;
//    }


}
