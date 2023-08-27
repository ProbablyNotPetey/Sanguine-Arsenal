package com.decursioteam.sanguinearsenal.items.curios.models;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Function;

public class BeltModel extends HumanoidModel<LivingEntity> {

//    protected final ModelPart charm = new ModelPart(this);
    public final ModelPart root;

    private final float xOffset;
    private final float zOffset;
    private final float rotation;

//    protected BeltModel(Function<ResourceLocation, RenderType> renderType, ) {
//        super(renderType, 0.5F, 0, 48, 48);

//
//        setAllVisible(false);
//
//        body = new ModelPart(this);
//
//        // belt
//        body.texOffs(0, 0);
//        body.addBox(-4, 0, -2, 8, 12, 4, 0.5F);
//
//        // charm
//        body.addChild(charm);
//    }

    public BeltModel(ModelPart root, Function<ResourceLocation, RenderType> renderType, float xOffset, float zOffset, float rotation) {
        super(root, renderType);
        this.root = root;
        this.xOffset = xOffset;
        this.zOffset = zOffset;
        this.rotation = rotation;

    }

//    private static BeltModel belt(float xOffset, float zOffset, float rotation) {
//        return belt(RenderType::entityCutoutNoCull, xOffset, zOffset, rotation);
//    }
//
//    private static BeltModel belt(Function<ResourceLocation, RenderType> renderType, float xOffset, float zOffset, float rotation) {
//        return new BeltModel(renderType, xOffset, zOffset, rotation);
//    }

//    public static BeltModel blood_flask() {
//        return new BloodFlaskModel(, 0, 0, -0.5F);
//    }

//    public void setCharmPosition(int slot) {
//        float xOffset = slot % 2 == 0 ? this.xOffset : -this.xOffset;
//        float zOffset = slot % 4 < 2 ? this.zOffset : -this.zOffset;
//        charm.setPos(xOffset, 9, zOffset);
//
//        float rotation = slot % 4 < 2 ? 0 : (float) -Math.PI;
//        rotation += slot % 2 == 0 ^ slot % 4 >= 2 ? this.rotation : -this.rotation;
//        charm.yRot = rotation;
//    }

    public static PartDefinition createHumanoidAlias(MeshDefinition mesh) {
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("pelvis", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("head", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("left_legging", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("left_foot", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("right_legging", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("right_foot", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("left_arm", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("right_arm", new CubeListBuilder(), PartPose.ZERO);
        return root;
    }
}
