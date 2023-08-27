package com.decursioteam.sanguinearsenal.armor.sanguinepraetor;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Mostly based on {@link elucent.eidolon.item.model.ArmorModel} bc idk how models work lol
 */
@OnlyIn(Dist.CLIENT)
public class SPArmorModel extends HumanoidModel {
//    public static final SPArmorModel HELMET = new SPArmorModel(EquipmentSlot.HEAD, 128, 128);
//    public static final SPArmorModel PANTS = new SPArmorModel(EquipmentSlot.LEGS, 128, 128);
//    public static final SPArmorModel BOOTS = new SPArmorModel(EquipmentSlot.FEET, 128, 128);
//    public static final SPArmorModel BODYARMOR = new SPArmorModel(EquipmentSlot.CHEST, 128, 128);
public EquipmentSlot slot;
    public final ModelPart root;
    public final ModelPart head;
    public final ModelPart body;
    public final ModelPart leftArm;
    public final ModelPart rightArm;
    public final ModelPart pelvis;
    public final ModelPart leftLegging;
    public final ModelPart rightLegging;
    public final ModelPart leftFoot;
    public final ModelPart rightFoot;

    public SPArmorModel(ModelPart root) {
        super(root);
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.pelvis = root.getChild("pelvis");
        this.leftArm = root.getChild("left_arm");
        this.rightArm = root.getChild("right_arm");
        this.leftLegging = root.getChild("left_legging");
        this.rightLegging = root.getChild("right_legging");
        this.leftFoot = root.getChild("left_foot");
        this.rightFoot = root.getChild("right_foot");
    }

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

    protected Iterable<ModelPart> headParts() {
        return this.slot == EquipmentSlot.HEAD ? ImmutableList.of(this.head) : ImmutableList.of();
    }

    protected Iterable<ModelPart> bodyParts() {
        if (this.slot == EquipmentSlot.CHEST) {
            return ImmutableList.of(this.body, this.leftArm, this.rightArm);
        } else if (this.slot == EquipmentSlot.LEGS) {
            return ImmutableList.of(this.leftLegging, this.rightLegging, this.pelvis);
        } else {
            return this.slot == EquipmentSlot.FEET ? ImmutableList.of(this.leftFoot, this.rightFoot) : ImmutableList.of();
        }
    }

    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void copyFromDefault(HumanoidModel model) {
        this.body.copyFrom(model.body);
        this.pelvis.copyFrom(this.body);
        this.leftLegging.copyFrom(this.leftLeg);
        this.rightLegging.copyFrom(this.rightLeg);
        this.leftFoot.copyFrom(this.leftLeg);
        this.rightFoot.copyFrom(this.rightLeg);
    }




//    public SPArmorModel(EquipmentSlot slot, int texWidth, int texHeight) {
//        super(0, 0, texWidth, texHeight);
//        this.slot = slot;
//
//        head = copyWithoutBoxes(head);
//        body = copyWithoutBoxes(body);
//        leftArm = copyWithoutBoxes(leftArm);
//        leftLeg = copyWithoutBoxes(leftLeg);
//        rightArm = copyWithoutBoxes(rightArm);
//        rightLeg = copyWithoutBoxes(rightLeg);
//    }
//
//    public ModelPart copyWithoutBoxes(ModelPart box) {
//        ModelPart newbox = new ModelPart(this);
//        newbox.setPos(box.x, box.y, box.z);
//        setRotationAngle(newbox, box.xRot, box.yRot, box.zRot);
//        newbox.mirror = box.mirror;
//        newbox.visible = box.visible;
//        return newbox;
//    }

//    @Override
//    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
//        hat.visible = false;
//        body.visible = leftArm.visible = rightArm.visible =
//                head.visible = leftLeg.visible = rightLeg.visible = false;
//
//        if (slot == EquipmentSlot.CHEST) {
//            body.visible = true;
//            leftArm.visible = true;
//            rightArm.visible = true;
//        }
//
//        if (slot == EquipmentSlot.HEAD) {
//            head.visible = true;
//        }
//
//        if (slot == EquipmentSlot.LEGS) {
//            leftLeg.visible = true;
//            rightLeg.visible = true;
//        }
//
//        if (slot == EquipmentSlot.FEET) {
//            leftLeg.visible = true;
//            rightLeg.visible = true;
//        }
//        super.renderToBuffer(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}