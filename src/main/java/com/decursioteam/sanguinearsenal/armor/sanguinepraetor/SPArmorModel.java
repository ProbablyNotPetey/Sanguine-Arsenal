package com.decursioteam.sanguinearsenal.armor.sanguinepraetor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SPArmorModel extends BipedModel {
    public static final SPArmorModel HELMET = new SPArmorModel(EquipmentSlotType.HEAD, 128, 128);
    public static final SPArmorModel PANTS = new SPArmorModel(EquipmentSlotType.LEGS, 128, 128);
    public static final SPArmorModel BOOTS = new SPArmorModel(EquipmentSlotType.FEET, 128, 128);
    public static final SPArmorModel BODYARMOR = new SPArmorModel(EquipmentSlotType.CHEST, 128, 128);
    final EquipmentSlotType slot;

    public SPArmorModel(EquipmentSlotType slot, int texWidth, int texHeight) {
        super(0, 0, texWidth, texHeight);
        this.slot = slot;

        head = copyWithoutBoxes(head);
        body = copyWithoutBoxes(body);
        leftArm = copyWithoutBoxes(leftArm);
        leftLeg = copyWithoutBoxes(leftLeg);
        rightArm = copyWithoutBoxes(rightArm);
        rightLeg = copyWithoutBoxes(rightLeg);
    }

    public ModelRenderer copyWithoutBoxes(ModelRenderer box) {
        ModelRenderer newbox = new ModelRenderer(this);
        newbox.setPos(box.x, box.y, box.z);
        setRotationAngle(newbox, box.xRot, box.yRot, box.zRot);
        newbox.mirror = box.mirror;
        newbox.visible = box.visible;
        return newbox;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        hat.visible = false;
        body.visible = leftArm.visible = rightArm.visible =
                head.visible = leftLeg.visible = rightLeg.visible = false;

        if (slot == EquipmentSlotType.CHEST) {
            body.visible = true;
            leftArm.visible = true;
            rightArm.visible = true;
        }

        if (slot == EquipmentSlotType.HEAD) {
            head.visible = true;
        }

        if (slot == EquipmentSlotType.LEGS) {
            leftLeg.visible = true;
            rightLeg.visible = true;
        }

        if (slot == EquipmentSlotType.FEET) {
            leftLeg.visible = true;
            rightLeg.visible = true;
        }
        super.renderToBuffer(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}