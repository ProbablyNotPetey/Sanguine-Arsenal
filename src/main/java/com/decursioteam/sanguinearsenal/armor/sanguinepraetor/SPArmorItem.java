package com.decursioteam.sanguinearsenal.armor.sanguinepraetor;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.decursioteam.sanguinearsenal.core.SangArsConfig;
import com.decursioteam.sanguinearsenal.core.Util.Keys;
import com.decursioteam.sanguinearsenal.core.init.ItemInit;
import com.decursioteam.sanguinearsenal.core.init.ModelInit;
import com.decursioteam.sanguinearsenal.core.network.Network;
import com.decursioteam.sanguinearsenal.core.network.messages.ParticleMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.network.NetworkDirection;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.decursioteam.sanguinearsenal.core.Util.BloodUtil.*;
import static com.decursioteam.sanguinearsenal.core.Util.LivingUtil.hasFullSPSet;

@SuppressWarnings("unchecked")
public class SPArmorItem extends ArmorItem {

    SPArmor model = null;

    public SPArmorItem(EquipmentSlot slot, Properties builderIn) {
        super(ArmorMaterial.SHADOW_INFUSED, slot, builderIn);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player playerEntity) {
        if (stack.getItem() != ItemInit.SP_CHESTPLATE.get()) return;

        if (world.isClientSide && getBloodAmount(playerEntity, false) >= 20 && !playerEntity.isCreative()) {
            playerEntity.displayClientMessage(Keys.BLOOD_AURA_AVAILABLE, true);
        } else if (!world.isClientSide && getBloodAmount(playerEntity, false) <= 0) {
            setBloodAura(playerEntity, false);
        }

        if (!hasFullSPSet(playerEntity)) return;

        if (getBloodAura(playerEntity) && getBloodAmount(playerEntity, false) >= 1) {
            if (SangArsConfig.COMMON.bloodAuraEffects.get() && world.getGameTime() % SangArsConfig.COMMON.bloodDrainSpeed.get() == 0) {
                int drain = SangArsConfig.COMMON.bloodDrainAmount.get(), amplifier = SangArsConfig.COMMON.bloodAuraEffectMultiplier.get();
                if(!hasBloodFlask(playerEntity)) {
                    drain *= SangArsConfig.COMMON.bloodUsageMultiplier.get();
                    amplifier--;
                }
                removeBlood(playerEntity, drain);
                addRegen(playerEntity, amplifier, 81);
            }
            if(!world.isClientSide && SangArsConfig.COMMON.bloodAuraParticles.get()){
                ServerPlayer serverPlayer = (ServerPlayer) playerEntity;
                Network.CHANNEL.sendTo(new ParticleMessage(), serverPlayer.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
            }
        }
        addDimensionEffects(world, playerEntity, SangArsConfig.COMMON.praetorEffectMultiplier.get(), 81);
        super.onArmorTick(stack, world, playerEntity);
    }

//    @OnlyIn(Dist.CLIENT)
//    @Override
//    public SPArmor getArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
//        if (model == null) model = new SPArmor(slot);
//        float pticks = Minecraft.getInstance().getFrameTime();
//        float f = Mth.rotLerp(pticks, entity.yRotO, entity.getYRot());
//        float f1 = Mth.rotLerp(pticks, entity.yHeadRotO, entity.yHeadRot);
//        float netHeadYaw = f1 - f;
//        float netHeadPitch = Mth.lerp(pticks, entity.xRotO, entity.xRotO);
//        model.setupAnim(entity, entity.animationPosition, entity.animationSpeed, entity.tickCount + pticks, netHeadYaw, netHeadPitch);
//        return model;
//    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if(SangArsConfig.COMMON.praetorEyes.get()) return SanguineArsenal.MOD_ID + ":textures/entity/sanguine_praetor.png";
        else return SanguineArsenal.MOD_ID + ":textures/entity/sanguine_praetor_no_eyes.png";
    }

    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            public SPArmorModel getHumanoidArmorModel(LivingEntity entity, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel _default) {
                float pticks = Minecraft.getInstance().getFrameTime();
                float f = Mth.rotLerp(pticks, entity.yBodyRotO, entity.yBodyRot);
                float f1 = Mth.rotLerp(pticks, entity.yHeadRotO, entity.yHeadRot);
                float netHeadYaw = f1 - f;
                float netHeadPitch = Mth.lerp(pticks, entity.xRotO, entity.getXRot());
                ModelInit.SP_ARMOR_MODEL.slot = SPArmorItem.this.slot;
                ModelInit.SP_ARMOR_MODEL.copyFromDefault(_default);
                ModelInit.SP_ARMOR_MODEL.setupAnim(entity, entity.animationPosition, entity.animationSpeed, (float)entity.tickCount + pticks, netHeadYaw, netHeadPitch);
                return ModelInit.SP_ARMOR_MODEL;
            }
        });
    }



    public enum ArmorMaterial implements net.minecraft.world.item.ArmorMaterial {
        SHADOW_INFUSED(new int[]{2, 5, 6, 2}, () -> {
            return Ingredient.of(new ItemStack(ItemInit.SHADOW_INFUSED_INGOT.get()));
        });

        private static final int[] HEALTH_PER_SLOT = new int[]{26, 30, 32, 22};
        private final String name;
        private final int durabilityMultiplier;
        private final int[] slotProtections;
        private final int enchantmentValue;
        private final SoundEvent sound;
        private final float toughness;
        private final float knockbackResistance;
        private final Supplier<Ingredient> repairIngredient;

        ArmorMaterial(int[] p_i231593_5_, Supplier<Ingredient> repairIngredient) {
            this.name = "sanguine_praetor";
            this.durabilityMultiplier = 14;
            this.slotProtections = p_i231593_5_;
            this.enchantmentValue = 15;
            this.sound = SoundEvents.ARMOR_EQUIP_NETHERITE;
            this.toughness = (float) 1.0;
            this.knockbackResistance = (float) 0.0;
            this.repairIngredient = repairIngredient;
        }

        public int getDurabilityForSlot(EquipmentSlot p_200896_1_) {
            return HEALTH_PER_SLOT[p_200896_1_.getIndex()] * this.durabilityMultiplier;
        }

        public int getDefenseForSlot(EquipmentSlot p_200902_1_) {
            return this.slotProtections[p_200902_1_.getIndex()];
        }

        public int getEnchantmentValue() {
            return this.enchantmentValue;
        }

        public SoundEvent getEquipSound() {
            return this.sound;
        }

        public Ingredient getRepairIngredient() {
            return this.repairIngredient.get();
        }

        @OnlyIn(Dist.CLIENT)
        public String getName() {
            return this.name;
        }

        public float getToughness() {
            return this.toughness;
        }

        public float getKnockbackResistance() {
            return this.knockbackResistance;
        }
    }
}
