package com.decursioteam.sanguinearsenal.armor.sanguinepraetor;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.decursioteam.sanguinearsenal.core.SangArsConfig;
import com.decursioteam.sanguinearsenal.core.Util.Keys;
import com.decursioteam.sanguinearsenal.core.init.ItemInit;
import com.decursioteam.sanguinearsenal.core.network.Network;
import com.decursioteam.sanguinearsenal.core.network.messages.ParticleMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;

import java.util.function.Supplier;

import static com.decursioteam.sanguinearsenal.core.Util.BloodUtil.*;
import static com.decursioteam.sanguinearsenal.core.Util.LivingUtil.hasFullSPSet;

@SuppressWarnings("unchecked")
public class SPArmorItem extends ArmorItem {

    SPArmor model = null;

    public SPArmorItem(EquipmentSlotType slot, Properties builderIn) {
        super(ArmorMaterial.SHADOW_INFUSED, slot, builderIn);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity playerEntity) {
        if(stack.getItem() == ItemInit.SP_CHESTPLATE.get()) {
            if (world.isClientSide() && getBloodAmount(playerEntity, false) >= 20 && !playerEntity.isCreative()) {
                playerEntity.displayClientMessage(Keys.BLOOD_AURA_AVAILABLE, true);
            } else if (!world.isClientSide() && getBloodAmount(playerEntity, false) <= 0) {
                setBloodAura(playerEntity, false);
            }
            if (hasFullSPSet(playerEntity)) {
                if (hasBloodFlask(playerEntity)) {
                    addEffects(world, playerEntity, SangArsConfig.COMMON.bloodFlaskEffectMultiplier.get(), 61, SangArsConfig.COMMON.praetorArmorRegen.get());
                }
                if (getBloodAura(playerEntity) && getBloodAmount(playerEntity, false) >= 1) {
                    if (SangArsConfig.COMMON.bloodAuraEffects.get()) {
                        if (world.getGameTime() % SangArsConfig.COMMON.bloodDrainSpeed.get() == 0) {
                            if (hasBloodFlask(playerEntity)) {
                                removeBlood(playerEntity, SangArsConfig.COMMON.bloodDrainAmount.get());
                                addEffects(world, playerEntity, SangArsConfig.COMMON.bloodAuraEffectMultiplier.get(), 61, true);
                            } else {
                                removeBlood(playerEntity, (SangArsConfig.COMMON.bloodDrainAmount.get() * SangArsConfig.COMMON.bloodUsageMultiplier.get()));
                                addEffects(world, playerEntity, SangArsConfig.COMMON.bloodAuraEffectMultiplier.get() - 1, 61, true);
                            }
                        }
                    }
                    if(world.getServer() != null && !world.isClientSide() && SangArsConfig.COMMON.bloodAuraParticles.get()){
                        ServerPlayerEntity serverPlayerEntity = world.getServer().getPlayerList().getPlayer(playerEntity.getUUID());
                        assert serverPlayerEntity != null;
                        Network.CHANNEL.sendTo(new ParticleMessage(), serverPlayerEntity.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
                    }
                }
                addEffects(world, playerEntity, SangArsConfig.COMMON.praetorEffectMultiplier.get(), 61, SangArsConfig.COMMON.praetorArmorRegen.get());
            }
        }
        super.onArmorTick(stack, world, playerEntity);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public SPArmor getArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
        if (model == null) model = new SPArmor(slot);
        float pticks = Minecraft.getInstance().getFrameTime();
        float f = MathHelper.rotLerp(pticks, entity.yRotO, entity.yRot);
        float f1 = MathHelper.rotLerp(pticks, entity.yHeadRotO, entity.yHeadRot);
        float netHeadYaw = f1 - f;
        float netHeadPitch = MathHelper.lerp(pticks, entity.xRotO, entity.xRotO);
        model.setupAnim(entity, entity.animationPosition, entity.animationSpeed, entity.tickCount + pticks, netHeadYaw, netHeadPitch);
        return model;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if(SangArsConfig.COMMON.praetorEyes.get()) return SanguineArsenal.MOD_ID + ":textures/entity/sanguine_praetor.png";
        else return SanguineArsenal.MOD_ID + ":textures/entity/sanguine_praetor_no_eyes.png";
    }
    public enum ArmorMaterial implements IArmorMaterial {
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
        private final LazyValue<Ingredient> repairIngredient;

        ArmorMaterial(int[] p_i231593_5_, Supplier<Ingredient> p_i231593_10_) {
            this.name = "sanguine_praetor";
            this.durabilityMultiplier = 14;
            this.slotProtections = p_i231593_5_;
            this.enchantmentValue = 15;
            this.sound = SoundEvents.ARMOR_EQUIP_NETHERITE;
            this.toughness = (float) 1.0;
            this.knockbackResistance = (float) 0.0;
            this.repairIngredient = new LazyValue<>(p_i231593_10_);
        }

        public int getDurabilityForSlot(EquipmentSlotType p_200896_1_) {
            return HEALTH_PER_SLOT[p_200896_1_.getIndex()] * this.durabilityMultiplier;
        }

        public int getDefenseForSlot(EquipmentSlotType p_200902_1_) {
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
