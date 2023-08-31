package com.decursioteam.sanguinearsenal.items.scepterofblood;

import com.decursioteam.sanguinearsenal.core.SangArsConfig;
import com.decursioteam.sanguinearsenal.core.Util.Keys;
import com.decursioteam.sanguinearsenal.core.init.EntityInit;
import com.decursioteam.sanguinearsenal.entities.BloodProjectileEntity;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import elucent.eidolon.item.IRechargeableWand;
import elucent.eidolon.registries.Sounds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static com.decursioteam.sanguinearsenal.core.Util.BloodUtil.*;
import static com.decursioteam.sanguinearsenal.core.Util.LivingUtil.hasFullSPSet;

public class ScepterOfBloodItem extends Item implements IRechargeableWand {

    public ScepterOfBloodItem(Item.Properties props) {
        super(props);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 640;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(Item.BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", SangArsConfig.COMMON.scepterOfBloodMeleeDamage.get(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_UUID, "Weapon modifier", SangArsConfig.COMMON.scepterOfBloodAttackSpeed.get(), AttributeModifier.Operation.ADDITION));
        Multimap<Attribute, AttributeModifier> defaultModifiers = builder.build();
        return slot == EquipmentSlot.MAINHAND ? defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        RandomSource random = world.random;
        ItemStack stack = entity.getItemInHand(hand);
        if (!entity.swinging) {
            if (entity.isCreative()) {
                Vec3 pos = entity.position().add(entity.getLookAngle().scale(0.5)).add(0.5 * Math.sin(Math.toRadians(225 - entity.yHeadRot)), entity.getBbHeight() * 2 / 3, 0.5 * Math.cos(Math.toRadians(225 - entity.yHeadRotO)));
                Vec3 vel = entity.getEyePosition(0).add(entity.getLookAngle().scale(40)).subtract(pos).scale(1.0 / 20);
                world.addFreshEntity(new BloodProjectileEntity(EntityInit.BLOOD_PROJECTILE.get(), world).shoot(
                        pos.x, pos.y, pos.z, vel.x, vel.y, vel.z, entity.getUUID()
                ));
                world.playSound(null, pos.x, pos.y, pos.z, Sounds.CAST_SOULFIRE_EVENT.get(), SoundSource.NEUTRAL, 0.75f, random.nextFloat() * 0.2f + 0.9f);
                try {
                    StructureTemplate t = ((ServerLevel) world).getStructureManager().get(new ResourceLocation("eidolon", "corridor")).get();
                    assert t != null;
                    BlockPos d = new BlockPos(t.getSize());
                    Rotation r = Rotation.values()[entity.getDirection().get2DDataValue()];
                    BlockPos o = new BlockPos(-d.getX() / 2, -d.getY() / 2, -d.getZ() / 2);
                    BlockPos s = new BlockPos(Math.max(o.getX(), o.getZ()), o.getY(), Math.max(o.getX(), o.getZ()));
                    BlockPos p = entity.blockPosition().below(8).offset(o.rotate(r)).subtract(s);
                    t.placeInWorld((ServerLevel) world, p, p, new StructurePlaceSettings().setRotation(r), random, 2);
                } catch (Exception e) {
                    //
                }
                entity.swing(hand, true);
                return InteractionResultHolder.success(stack);
            }
            if (!hasFullSPSet(entity)) {
                entity.displayClientMessage(Keys.NO_PRAETOR_ARMOR, true);
                entity.swing(hand, false);
                return InteractionResultHolder.fail(stack);
            }
            ItemStack itemStack = entity.getItemBySlot(EquipmentSlot.CHEST);
            double bloodAmount = getBloodAmount(entity, false);
            if (world.isClientSide() && bloodAmount < SangArsConfig.COMMON.scepterOfBloodUseValue.get()) {
                entity.displayClientMessage(Keys.NOT_ENOUGH_BLOOD, true);
                entity.swing(hand, false);
                return InteractionResultHolder.fail(stack);
            }

            if (!world.isClientSide() && hasFullSPSet(entity) && bloodAmount >= SangArsConfig.COMMON.scepterOfBloodUseValue.get()) {
                if (hasBloodFlask(entity)) removeBlood(entity, SangArsConfig.COMMON.scepterOfBloodUseValue.get());
                else removeBlood(entity, SangArsConfig.COMMON.scepterOfBloodUseValue.get() * SangArsConfig.COMMON.bloodUsageMultiplier.get());
                Vec3 pos = entity.position().add(entity.getLookAngle().scale(0.5)).add(0.5 * Math.sin(Math.toRadians(225 - entity.yHeadRot)), entity.getBbHeight() * 2 / 3, 0.5 * Math.cos(Math.toRadians(225 - entity.yHeadRotO)));
                Vec3 vel = entity.getEyePosition(0).add(entity.getLookAngle().scale(40)).subtract(pos).scale(1.0 / 20);
                world.addFreshEntity(new BloodProjectileEntity(EntityInit.BLOOD_PROJECTILE.get(), world).shoot(
                        pos.x, pos.y, pos.z, vel.x, vel.y, vel.z, entity.getUUID()
                ));
                world.playSound(null, pos.x, pos.y, pos.z, Sounds.CAST_SOULFIRE_EVENT.get(), SoundSource.NEUTRAL, 0.75f, random.nextFloat() * 0.2f + 0.9f);
                stack.setDamageValue(stack.getDamageValue() + 1);

                try {
                    StructureTemplate t = ((ServerLevel) world).getStructureManager().get(new ResourceLocation("eidolon", "corridor")).get();
                    assert t != null;
                    BlockPos d = new BlockPos(t.getSize());
                    Rotation r = Rotation.values()[entity.getDirection().get2DDataValue()];
                    BlockPos o = new BlockPos(-d.getX() / 2, -d.getY() / 2, -d.getZ() / 2);
                    BlockPos s = new BlockPos(Math.max(o.getX(), o.getZ()), o.getY(), Math.max(o.getX(), o.getZ()));
                    BlockPos p = entity.blockPosition().below(8).offset(o.rotate(r)).subtract(s);
                    t.placeInWorld((ServerLevel) world, p, p, new StructurePlaceSettings().setRotation(r), random, 2);
                } catch (Exception e) {
                    //
                }
                entity.swing(hand, true);
                return InteractionResultHolder.success(stack);
            }
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public ItemStack recharge(ItemStack stack) {
        stack.setDamageValue(0);
        return stack;
    }
}
