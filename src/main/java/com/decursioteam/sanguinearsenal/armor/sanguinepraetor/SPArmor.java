package com.decursioteam.sanguinearsenal.armor.sanguinepraetor;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SPArmor extends SPArmorModel {
    private ModelRenderer skirt_front;
    private ModelRenderer skirt_back;
    private ModelRenderer skirt_left;
    private ModelRenderer skirt_right;
    private ModelRenderer ciucuri;
    private ModelRenderer cape;

    public SPArmor(EquipmentSlotType slot) {
        super(slot, 128, 128);

        if (slot == EquipmentSlotType.FEET) {
            ModelRenderer armorLeftBoot = new ModelRenderer(this);
            armorLeftBoot.setPos(-4.0F, 0.0F, 0.0F);
            leftLeg.addChild(armorLeftBoot);
            armorLeftBoot.texOffs(35, 86).addBox(2.0F, 7.25F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
            armorLeftBoot.texOffs(79, 39).addBox(1.75F, 8.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.0F, false);

            ModelRenderer armorRightBoot = new ModelRenderer(this);
            armorRightBoot.setPos(4.0F, 0.0F, 0.0F);
            rightLeg.addChild(armorRightBoot);
            armorRightBoot.texOffs(48, 23).addBox(-6.0F, 7.25F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
            armorRightBoot.texOffs(35, 79).addBox(-6.75F, 8.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.0F, false);
        }

        if (slot == EquipmentSlotType.CHEST) {
            ModelRenderer armorBody = new ModelRenderer(this);
            armorBody.setPos(0.0F, 0.0F, 0.0F);
            body.addChild(armorBody);
            armorBody.texOffs(56, 35).addBox(-4.5F, 10.1F, -2.5F, 9.0F, 2.0F, 5.0F, 0.0F, false);
            armorBody.texOffs(0, 18).addBox(-5.0F, 0.2F, -3.0F, 10.0F, 10.0F, 6.0F, 0.0F, false);
            armorBody.texOffs(87, 6).addBox(-2.0F, 0.6F, -4.05F, 4.0F, 10.0F, 2.0F, 0.0F, false);
            armorBody.texOffs(94, 93).addBox(1.15F, 0.5F, -4.0F, 3.0F, 8.0F, 1.0F, 0.0F, false);
            armorBody.texOffs(0, 46).addBox(-4.15F, 0.5F, -4.0F, 3.0F, 8.0F, 1.0F, 0.0F, false);
            armorBody.texOffs(26, 18).addBox(-3.9F, 2.5F, -5.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
            armorBody.texOffs(35, 95).addBox(-2.0F, 2.5F, -5.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);
            armorBody.texOffs(62, 3).addBox(1.9F, 2.5F, -5.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
            armorBody.texOffs(42, 42).addBox(-4.0F, -0.25F, -3.5F, 8.0F, 1.0F, 7.0F, 0.0F, false);
            armorBody.texOffs(35, 0).addBox(1.0F, 1.0F, 3.0F, 3.0F, 8.0F, 1.0F, 0.0F, false);
            armorBody.texOffs(0, 34).addBox(-4.0F, 1.0F, 3.0F, 3.0F, 8.0F, 1.0F, 0.0F, false);
            armorBody.texOffs(8, 97).addBox(-1.5F, 1.0F, 2.5F, 3.0F, 7.0F, 1.0F, 0.0F, false);

            ModelRenderer cube_r12 = new ModelRenderer(this);
            cube_r12.setPos(0.0F, 8.6939F, 2.6193F);
            armorBody.addChild(cube_r12);
            setRotationAngle(cube_r12, -1.0036F, 0.0F, 0.0F);
            cube_r12.texOffs(16, 69).addBox(1.0F, -1.0F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);
            cube_r12.texOffs(71, 29).addBox(-4.0F, -1.0F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

            ModelRenderer cube_r13 = new ModelRenderer(this);
            cube_r13.setPos(-4.0F, 0.0F, 0.0F);
            armorBody.addChild(cube_r13);
            setRotationAngle(cube_r13, 0.0F, 0.0F, 0.4363F);
            cube_r13.texOffs(32, 18).addBox(-1.75F, -0.25F, -3.5F, 2.0F, 1.0F, 7.0F, 0.0F, false);

            ModelRenderer cube_r14 = new ModelRenderer(this);
            cube_r14.setPos(4.0F, 0.0F, 0.0F);
            armorBody.addChild(cube_r14);
            setRotationAngle(cube_r14, 0.0F, 0.0F, -0.4363F);
            cube_r14.texOffs(57, 50).addBox(-0.25F, -0.25F, -3.5F, 2.0F, 1.0F, 7.0F, 0.0F, false);

            ModelRenderer cube_r15 = new ModelRenderer(this);
            cube_r15.setPos(0.5F, 0.0F, 1.0F);
            armorBody.addChild(cube_r15);
            setRotationAngle(cube_r15, -0.5236F, 0.0F, 0.0F);
            cube_r15.texOffs(16, 66).addBox(-5.0F, -1.25F, 2.0F, 9.0F, 1.0F, 2.0F, 0.0F, false);

            ModelRenderer cube_r16 = new ModelRenderer(this);
            cube_r16.setPos(-0.65F, -0.2909F, -6.4904F);
            armorBody.addChild(cube_r16);
            setRotationAngle(cube_r16, -0.3927F, 0.0F, 0.0F);
            cube_r16.texOffs(33, 23).addBox(2.5F, 1.5F, 1.55F, 2.0F, 1.0F, 1.0F, 0.0F, false);
            cube_r16.texOffs(32, 18).addBox(-3.2F, 1.5F, 1.55F, 2.0F, 1.0F, 1.0F, 0.0F, false);

            ModelRenderer cube_r17 = new ModelRenderer(this);
            cube_r17.setPos(0.65F, 0.5F, -9.0F);
            armorBody.addChild(cube_r17);
            setRotationAngle(cube_r17, 0.4363F, 0.0F, 0.0F);
            cube_r17.texOffs(20, 84).addBox(-4.5F, 2.5F, 2.55F, 2.0F, 1.0F, 2.0F, 0.0F, false);
            cube_r17.texOffs(87, 81).addBox(1.2F, 2.5F, 2.55F, 2.0F, 1.0F, 2.0F, 0.0F, false);

            ModelRenderer cube_r18 = new ModelRenderer(this);
            cube_r18.setPos(-0.1F, 10.3633F, -3.72F);
            armorBody.addChild(cube_r18);
            setRotationAngle(cube_r18, -0.829F, 0.0F, 0.0F);
            cube_r18.texOffs(57, 50).addBox(1.9F, -6.0F, -7.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
            cube_r18.texOffs(42, 42).addBox(-3.7F, -6.0F, -7.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

            ModelRenderer cube_r19 = new ModelRenderer(this);
            cube_r19.setPos(0.0F, 10.3633F, -3.22F);
            armorBody.addChild(cube_r19);
            setRotationAngle(cube_r19, -0.829F, 0.0F, 0.0F);
            cube_r19.texOffs(0, 63).addBox(-2.0F, -6.0F, -7.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);

            ModelRenderer cube_r20 = new ModelRenderer(this);
            cube_r20.setPos(1.85F, 8.8672F, 3.8016F);
            armorBody.addChild(cube_r20);
            setRotationAngle(cube_r20, 0.829F, 0.0F, 0.0F);
            cube_r20.texOffs(30, 86).addBox(-5.9F, -6.0F, -5.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);
            cube_r20.texOffs(87, 25).addBox(-0.8F, -6.0F, -5.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

            ModelRenderer cube_r21 = new ModelRenderer(this);
            cube_r21.setPos(-2.1F, 10.5535F, -1.0763F);
            armorBody.addChild(cube_r21);
            setRotationAngle(cube_r21, 0.829F, 0.0F, 0.0F);
            cube_r21.texOffs(57, 53).addBox(3.9F, -6.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
            cube_r21.texOffs(42, 45).addBox(-1.7F, -6.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

            ModelRenderer cube_r22 = new ModelRenderer(this);
            cube_r22.setPos(0.0F, 9.1756F, -4.2627F);
            armorBody.addChild(cube_r22);
            setRotationAngle(cube_r22, 0.829F, 0.0F, 0.0F);
            cube_r22.texOffs(49, 64).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);

            skirt_front = new ModelRenderer(this);
            skirt_front.setPos(-4.35F, 12.3664F, -2.3793F);
            armorBody.addChild(skirt_front);
            skirt_front.texOffs(0, 105).addBox(0.85F, 2.5336F, -2.9207F, 7.0F, 1.0F, 1.0F, 0.0F, false);
            skirt_front.texOffs(0, 105).addBox(0.85F, 1.5336F, -2.6707F, 7.0F, 1.0F, 1.0F, 0.0F, false);
            skirt_front.texOffs(0, 105).addBox(0.85F, 0.5336F, -2.4207F, 7.0F, 1.0F, 1.0F, 0.0F, false);
            skirt_front.texOffs(0, 105).addBox(0.85F, -0.4664F, -2.1207F, 7.0F, 1.0F, 1.0F, 0.0F, false);
            skirt_front.texOffs(0, 105).addBox(0.85F, -1.4664F, -1.6207F, 7.0F, 1.0F, 1.0F, 0.0F, false);
            skirt_front.texOffs(0, 105).addBox(0.85F, -2.4664F, -1.1207F, 7.0F, 1.0F, 1.0F, 0.0F, false);

            ModelRenderer cube_r23 = new ModelRenderer(this);
            cube_r23.setPos(0.0F, 0.0F, 0.0F);
            skirt_front.addChild(cube_r23);
            setRotationAngle(cube_r23, -0.8727F, 0.0F, 0.0F);
            cube_r23.texOffs(89, 68).addBox(0.3F, -1.5359F, -1.1243F, 8.0F, 2.0F, 1.0F, 0.0F, false);

            ModelRenderer skirt_front_up_r1 = new ModelRenderer(this);
            skirt_front_up_r1.setPos(-0.15F, -0.3107F, -0.6072F);
            skirt_front.addChild(skirt_front_up_r1);
            setRotationAngle(skirt_front_up_r1, -0.6283F, 0.0F, 0.0F);
            skirt_front_up_r1.texOffs(30, 54).addBox(0.45F, 7.888F, 3.0449F, 8.0F, 3.0F, 1.0F, 0.0F, false);

            ModelRenderer skirt_front_down_r1 = new ModelRenderer(this);
            skirt_front_down_r1.setPos(0.05F, -0.1007F, -0.3447F);
            skirt_front.addChild(skirt_front_down_r1);
            setRotationAngle(skirt_front_down_r1, -0.2094F, 0.0F, 0.0F);
            skirt_front_down_r1.texOffs(71, 68).addBox(0.25F, -0.2996F, -0.8136F, 8.0F, 9.0F, 1.0F, 0.0F, false);

            skirt_back = new ModelRenderer(this);
            skirt_back.setPos(-4.35F, 12.3664F, 2.3793F);
            armorBody.addChild(skirt_back);
            skirt_back.texOffs(0, 105).addBox(0.85F, 2.5336F, 1.9207F, 7.0F, 1.0F, 1.0F, 0.0F, false);
            skirt_back.texOffs(0, 105).addBox(0.85F, 1.5336F, 1.6707F, 7.0F, 1.0F, 1.0F, 0.0F, false);
            skirt_back.texOffs(0, 105).addBox(0.85F, -2.4664F, 0.1207F, 7.0F, 1.0F, 1.0F, 0.0F, false);
            skirt_back.texOffs(0, 105).addBox(0.85F, -1.4664F, 0.6207F, 7.0F, 1.0F, 1.0F, 0.0F, false);
            skirt_back.texOffs(0, 105).addBox(0.85F, -0.4664F, 1.1207F, 7.0F, 1.0F, 1.0F, 0.0F, false);
            skirt_back.texOffs(0, 105).addBox(0.85F, 0.5336F, 1.4207F, 7.0F, 1.0F, 1.0F, 0.0F, false);

            ModelRenderer cube_r24 = new ModelRenderer(this);
            cube_r24.setPos(0.5F, 0.0F, 0.2F);
            skirt_back.addChild(cube_r24);
            setRotationAngle(cube_r24, 0.8727F, 0.0F, 0.0F);
            cube_r24.texOffs(86, 46).addBox(-0.2F, -1.6891F, -0.0043F, 8.0F, 2.0F, 1.0F, 0.0F, false);

            ModelRenderer skirt_back_up_r1 = new ModelRenderer(this);
            skirt_back_up_r1.setPos(0.05F, -0.1007F, 0.3447F);
            skirt_back.addChild(skirt_back_up_r1);
            setRotationAngle(skirt_back_up_r1, 0.2094F, 0.0F, 0.0F);
            skirt_back_up_r1.texOffs(22, 74).addBox(0.25F, -0.2996F, -0.1864F, 8.0F, 9.0F, 1.0F, 0.0F, false);

            ModelRenderer skirt_back_down_r1 = new ModelRenderer(this);
            skirt_back_down_r1.setPos(0.55F, -0.0107F, 0.3073F);
            skirt_back.addChild(skirt_back_down_r1);
            setRotationAngle(skirt_back_down_r1, 0.6283F, 0.0F, 0.0F);
            skirt_back_down_r1.texOffs(82, 64).addBox(-0.25F, 7.8216F, -3.6259F, 8.0F, 3.0F, 1.0F, 0.0F, false);

            skirt_left = new ModelRenderer(this);
            skirt_left.setPos(4.5279F, 12.1468F, -2.5F);
            armorBody.addChild(skirt_left);
            setRotationAngle(skirt_left, 0.0F, 0.0F, -0.0436F);
            skirt_left.texOffs(0, 107).addBox(-0.0279F, -1.2468F, 0.5F, 1.0F, 1.0F, 4.0F, 0.0F, false);
            skirt_left.texOffs(0, 107).addBox(0.4721F, -0.2468F, 0.5F, 1.0F, 1.0F, 4.0F, 0.0F, false);
            skirt_left.texOffs(0, 107).addBox(0.7721F, 0.6532F, 0.5F, 1.0F, 1.0F, 4.0F, 0.0F, false);
            skirt_left.texOffs(0, 107).addBox(1.0221F, 1.6532F, 0.5F, 1.0F, 1.0F, 4.0F, 0.0F, false);
            skirt_left.texOffs(0, 107).addBox(1.2721F, 2.6532F, 0.5F, 1.0F, 1.0F, 4.0F, 0.0F, false);

            ModelRenderer skirt_right_up_r1 = new ModelRenderer(this);
            skirt_right_up_r1.setPos(0.0F, 0.0F, 0.0F);
            skirt_left.addChild(skirt_right_up_r1);
            setRotationAngle(skirt_right_up_r1, 0.0F, 0.0F, -0.3927F);
            skirt_right_up_r1.texOffs(55, 94).addBox(-1.9176F, 8.6403F, 0.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);

            ModelRenderer skirt_left_up_r1 = new ModelRenderer(this);
            skirt_left_up_r1.setPos(0.2672F, 0.2272F, 0.0F);
            skirt_left.addChild(skirt_left_up_r1);
            setRotationAngle(skirt_left_up_r1, 0.0F, 0.0F, -0.1745F);
            skirt_left_up_r1.texOffs(23, 84).addBox(-0.2258F, -0.4196F, 0.0F, 1.0F, 9.0F, 5.0F, 0.0F, false);

            ModelRenderer cube_r25 = new ModelRenderer(this);
            cube_r25.setPos(-0.0279F, 5.4196F, -3.1207F);
            skirt_left.addChild(cube_r25);
            setRotationAngle(cube_r25, 0.8727F, 1.5708F, 0.0F);
            cube_r25.texOffs(49, 67).addBox(-7.6F, -4.9F, 3.95F, 4.0F, 2.0F, 1.0F, 0.0F, false);

            skirt_right = new ModelRenderer(this);
            skirt_right.setPos(-4.5279F, 12.1468F, -2.5F);
            armorBody.addChild(skirt_right);
            skirt_right.texOffs(0, 107).addBox(-2.2721F, 2.6532F, 0.5F, 1.0F, 1.0F, 4.0F, 0.0F, true);
            skirt_right.texOffs(0, 107).addBox(-2.0221F, 1.6532F, 0.5F, 1.0F, 1.0F, 4.0F, 0.0F, true);
            skirt_right.texOffs(0, 107).addBox(-1.7721F, 0.6532F, 0.5F, 1.0F, 1.0F, 4.0F, 0.0F, true);
            skirt_right.texOffs(0, 107).addBox(-1.4721F, -0.2468F, 0.5F, 1.0F, 1.0F, 4.0F, 0.0F, true);
            skirt_right.texOffs(0, 107).addBox(-0.9721F, -1.2468F, 0.5F, 1.0F, 1.0F, 4.0F, 0.0F, true);

            ModelRenderer skirt_right_down_r1 = new ModelRenderer(this);
            skirt_right_down_r1.setPos(0.0F, 0.0F, 0.0F);
            skirt_right.addChild(skirt_right_down_r1);
            setRotationAngle(skirt_right_down_r1, 0.0F, 0.0F, 0.3927F);
            skirt_right_down_r1.texOffs(94, 34).addBox(0.9176F, 8.6403F, 0.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);

            ModelRenderer skirt_right_up_r2 = new ModelRenderer(this);
            skirt_right_up_r2.setPos(-0.2672F, 0.2272F, 0.0F);
            skirt_right.addChild(skirt_right_up_r2);
            setRotationAngle(skirt_right_up_r2, 0.0F, 0.0F, 0.1745F);
            skirt_right_up_r2.texOffs(11, 83).addBox(-0.7742F, -0.4196F, 0.0F, 1.0F, 9.0F, 5.0F, 0.0F, false);

            ModelRenderer cube_r26 = new ModelRenderer(this);
            cube_r26.setPos(0.0279F, 5.4196F, -3.1207F);
            skirt_right.addChild(cube_r26);
            setRotationAngle(cube_r26, 0.8727F, -1.5708F, 0.0F);
            cube_r26.texOffs(65, 42).addBox(3.6F, -4.9F, 3.95F, 4.0F, 2.0F, 1.0F, 0.0F, false);

            ciucuri = new ModelRenderer(this);
            ciucuri.setPos(-3.75F, 2.6715F, -5.9811F);
            armorBody.addChild(ciucuri);
            ciucuri.texOffs(18, 76).addBox(5.6F, -0.0595F, -0.0339F, 2.0F, 10.0F, 0.0F, 0.0F, false);
            ciucuri.texOffs(50, 74).addBox(-0.1F, -0.0595F, -0.0339F, 2.0F, 10.0F, 0.0F, 0.0F, false);

            ModelRenderer ciucure_left_down_r1 = new ModelRenderer(this);
            ciucure_left_down_r1.setPos(0.0F, 0.0F, 0.0F);
            ciucuri.addChild(ciucure_left_down_r1);
            setRotationAngle(ciucure_left_down_r1, -0.2007F, 0.0F, 0.0F);
            ciucure_left_down_r1.texOffs(12, 0).addBox(5.6F, 9.7477F, 1.9486F, 2.0F, 4.0F, 0.0F, 0.0F, false);
            ciucure_left_down_r1.texOffs(0, 0).addBox(-0.1F, 9.7477F, 1.9486F, 2.0F, 4.0F, 0.0F, 0.0F, false);

            cape = new ModelRenderer(this);
            cape.setPos(0.5F, 1.2415F, 3.8318F);
            armorBody.addChild(cape);


            ModelRenderer cape_top_r1 = new ModelRenderer(this);
            cape_top_r1.setPos(0.0F, 0.0F, 0.0F);
            cape.addChild(cape_top_r1);
            setRotationAngle(cape_top_r1, -1.4399F, 0.0F, 0.0F);
            cape_top_r1.texOffs(0, 0).addBox(-5.0F, -1.2889F, -0.1571F, 9.0F, 1.0F, 17.0F, 0.0F, false);

            ModelRenderer cape_middle_r1 = new ModelRenderer(this);
            cape_middle_r1.setPos(0.0F, 0.0484F, 0.0505F);
            cape.addChild(cape_middle_r1);
            setRotationAngle(cape_middle_r1, -0.829F, 0.0F, 0.0F);
            cape_middle_r1.texOffs(62, 0).addBox(-5.0F, 8.4286F, 13.8928F, 9.0F, 1.0F, 2.0F, 0.0F, false);

            ModelRenderer cape_tip_r1 = new ModelRenderer(this);
            cape_tip_r1.setPos(0.0F, 0.0868F, 0.2164F);
            cape.addChild(cape_tip_r1);
            setRotationAngle(cape_tip_r1, 0.3927F, 0.0F, 0.0F);
            cape_tip_r1.texOffs(0, 60).addBox(-5.0F, 17.0602F, -3.5629F, 9.0F, 1.0F, 2.0F, 0.0F, false);

            ModelRenderer armorRightArm = new ModelRenderer(this);
            armorRightArm.setPos(9.0F, 0.0F, 0.0F);
            rightArm.addChild(armorRightArm);
            armorRightArm.texOffs(84, 73).addBox(-12.15F, 7.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F, false);
            armorRightArm.texOffs(33, 64).addBox(-12.5F, 4.0F, -3.0F, 5.0F, 4.0F, 6.0F, 0.0F, false);
            armorRightArm.texOffs(76, 55).addBox(-12.4F, -1.2F, -2.5F, 4.0F, 4.0F, 5.0F, 0.0F, false);
            armorRightArm.texOffs(60, 58).addBox(-12.9F, -2.2F, -3.0F, 5.0F, 4.0F, 6.0F, 0.0F, false);
            armorRightArm.texOffs(15, 51).addBox(-12.9F, 5.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
            armorRightArm.texOffs(92, 25).addBox(-13.4F, -2.7F, -2.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);
            armorRightArm.texOffs(55, 68).addBox(-12.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

            ModelRenderer armorbipedLeftArm = new ModelRenderer(this);
            armorbipedLeftArm.setPos(-1.0F, 0.0F, 0.0F);
            leftArm.addChild(armorbipedLeftArm);
            armorbipedLeftArm.texOffs(0, 0).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
            armorbipedLeftArm.texOffs(82, 85).addBox(1.15F, 7.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F, false);
            armorbipedLeftArm.texOffs(0, 66).addBox(-0.5F, 4.0F, -3.0F, 5.0F, 4.0F, 6.0F, 0.0F, false);
            armorbipedLeftArm.texOffs(65, 5).addBox(-0.1F, -2.2F, -3.0F, 5.0F, 4.0F, 6.0F, 0.0F, false);
            armorbipedLeftArm.texOffs(79, 30).addBox(0.4F, -1.2F, -2.5F, 4.0F, 4.0F, 5.0F, 0.0F, false);
            armorbipedLeftArm.texOffs(93, 81).addBox(2.4F, -2.7F, -2.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);
            armorbipedLeftArm.texOffs(62, 15).addBox(2.9F, 5.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        }
        if (slot == EquipmentSlotType.LEGS) {
            ModelRenderer armorbipedRightLeg = new ModelRenderer(this);
            armorbipedRightLeg.setPos(0.0F, 0.0F, 0.0F);
            rightLeg.addChild(armorbipedRightLeg);
            armorbipedRightLeg.texOffs(0, 76).addBox(-2.0F, -0.7F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);


            ModelRenderer armorbipedLeftLeg = new ModelRenderer(this);
            armorbipedLeftLeg.setPos(-4.0F, 0.0F, 0.0F);
            leftLeg.addChild(armorbipedLeftLeg);
            armorbipedLeftLeg.texOffs(71, 78).addBox(2.0F, -0.7F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
            armorbipedLeftLeg.texOffs(81, 0).addBox(1.95F, 5.0F, -2.5F, 5.0F, 1.0F, 5.0F, 0.0F, false);
            armorbipedLeftLeg.texOffs(0, 18).addBox(6.0F, 4.0F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        }
        if (slot == EquipmentSlotType.HEAD) {

            ModelRenderer armorHead = new ModelRenderer(this);
            armorHead.setPos(0.0F, 0.0F, 0.0F);
            head.addChild(armorHead);
            armorHead.texOffs(0, 34).addBox(-3.5F, -2.4F, -4.5F, 7.0F, 3.0F, 9.0F, 0.0F, false);
            armorHead.texOffs(15, 46).addBox(-3.5F, -6.4F, -4.2F, 7.0F, 4.0F, 1.0F, 0.0F, false);
            armorHead.texOffs(35, 11).addBox(-3.5F, -6.5F, 3.1F, 7.0F, 4.0F, 1.0F, 0.0F, false);
            armorHead.texOffs(35, 0).addBox(-4.5F, -8.4F, -4.4F, 9.0F, 2.0F, 9.0F, 0.0F, false);

            ModelRenderer cube_r1 = new ModelRenderer(this);
            cube_r1.setPos(0.7741F, -9.8356F, 5.5F);
            armorHead.addChild(cube_r1);
            setRotationAngle(cube_r1, -0.4954F, -0.2648F, -0.4529F);
            cube_r1.texOffs(87, 18).addBox(-4.65F, -0.4F, -0.85F, 4.0F, 3.0F, 4.0F, 0.0F, false);

            ModelRenderer cube_r2 = new ModelRenderer(this);
            cube_r2.setPos(-0.7741F, -9.8356F, 5.5F);
            armorHead.addChild(cube_r2);
            setRotationAngle(cube_r2, -0.4954F, 0.2648F, 0.4529F);
            cube_r2.texOffs(89, 51).addBox(0.65F, -0.4F, -0.85F, 4.0F, 3.0F, 4.0F, 0.0F, false);

            ModelRenderer cube_r3 = new ModelRenderer(this);
            cube_r3.setPos(0.75F, 0.0154F, 8.1315F);
            armorHead.addChild(cube_r3);
            setRotationAngle(cube_r3, -1.0908F, 0.0F, 0.0F);
            cube_r3.texOffs(30, 58).addBox(-2.25F, -2.9F, -2.25F, 3.0F, 3.0F, 2.0F, 0.0F, false);

            ModelRenderer cube_r4 = new ModelRenderer(this);
            cube_r4.setPos(0.75F, 0.0154F, 8.1315F);
            armorHead.addChild(cube_r4);
            setRotationAngle(cube_r4, -0.829F, 0.0F, 0.0F);
            cube_r4.texOffs(51, 84).addBox(-3.25F, -3.75F, -5.5F, 5.0F, 4.0F, 4.0F, 0.0F, false);

            ModelRenderer cube_r5 = new ModelRenderer(this);
            cube_r5.setPos(0.75F, 0.0154F, 8.1315F);
            armorHead.addChild(cube_r5);
            setRotationAngle(cube_r5, -0.5672F, 0.0F, 0.0F);
            cube_r5.texOffs(68, 46).addBox(-4.25F, -5.2F, -7.5F, 7.0F, 5.0F, 4.0F, 0.0F, false);

            ModelRenderer cube_r6 = new ModelRenderer(this);
            cube_r6.setPos(-0.5891F, -9.6058F, -0.15F);
            armorHead.addChild(cube_r6);
            setRotationAngle(cube_r6, 0.0F, 0.0F, -0.5672F);
            cube_r6.texOffs(23, 42).addBox(-3.95F, -0.7F, -4.0F, 5.0F, 3.0F, 9.0F, 0.0F, false);

            ModelRenderer cube_r7 = new ModelRenderer(this);
            cube_r7.setPos(0.5891F, -9.6058F, -0.15F);
            armorHead.addChild(cube_r7);
            setRotationAngle(cube_r7, 0.0F, 0.0F, 0.5672F);
            cube_r7.texOffs(43, 11).addBox(-1.05F, -0.7F, -3.9F, 5.0F, 3.0F, 9.0F, 0.0F, false);

            ModelRenderer cube_r8 = new ModelRenderer(this);
            cube_r8.setPos(0.6681F, -0.2942F, 0.0F);
            armorHead.addChild(cube_r8);
            setRotationAngle(cube_r8, 0.0F, 0.0F, 0.2967F);
            cube_r8.texOffs(0, 46).addBox(-7.4F, -5.5F, -4.5F, 3.0F, 5.0F, 9.0F, 0.0F, false);

            ModelRenderer cube_r9 = new ModelRenderer(this);
            cube_r9.setPos(0.4416F, -0.2974F, 0.0F);
            armorHead.addChild(cube_r9);
            setRotationAngle(cube_r9, 0.0F, 0.0F, 0.4712F);
            cube_r9.texOffs(15, 54).addBox(-6.15F, -0.6F, -4.4F, 3.0F, 3.0F, 9.0F, 0.0F, false);

            ModelRenderer cube_r10 = new ModelRenderer(this);
            cube_r10.setPos(-0.4416F, -0.2974F, 0.0F);
            armorHead.addChild(cube_r10);
            setRotationAngle(cube_r10, 0.0F, 0.0F, -0.4712F);
            cube_r10.texOffs(56, 23).addBox(3.15F, -0.6F, -4.4F, 3.0F, 3.0F, 9.0F, 0.0F, false);

            ModelRenderer cube_r11 = new ModelRenderer(this);
            cube_r11.setPos(-0.6681F, -0.2942F, 0.0F);
            armorHead.addChild(cube_r11);
            setRotationAngle(cube_r11, 0.0F, 0.0F, -0.2967F);
            cube_r11.texOffs(42, 50).addBox(4.4F, -5.5F, -4.5F, 3.0F, 5.0F, 9.0F, 0.0F, false);
        }
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        float f = 1.0F;
        if (entity.getFallFlyingTicks() > 4) {
            f = (float)entity.getDeltaMovement().lengthSqr();
            f = f / 0.2F;
            f = f * f * f;
        }
        if (f < 1.0F) {
            f = 1.0F;
        }
        if (slot == EquipmentSlotType.CHEST) {
            cape.xRot = MathHelper.abs(MathHelper.cos(limbSwing * 0.6662f) * 0.7f * limbSwingAmount / f);
            ciucuri.xRot = -MathHelper.abs(MathHelper.cos(limbSwing * 0.6662f) * 0.7f * limbSwingAmount / f) * 1.4f;
            skirt_back.xRot = MathHelper.abs(MathHelper.cos(limbSwing * 0.6662f) * 0.7f * limbSwingAmount / f) * 2.2f;
            skirt_front.xRot = -MathHelper.abs(MathHelper.cos(limbSwing * 0.6662f) * 0.7f * limbSwingAmount / f) * 2.2f;
            skirt_left.xRot = -MathHelper.abs(MathHelper.cos(limbSwing * 0.6662f) * 0.7f * limbSwingAmount / f) * 0.3f;
            skirt_right.xRot = MathHelper.abs(MathHelper.cos(limbSwing * 0.6662f) * 0.7f * limbSwingAmount / f) * 0.3f;
        }
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}