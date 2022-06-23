package com.decursioteam.sanguinearsenal.items.scepterofblood;

import com.decursioteam.sanguinearsenal.core.SangArsConfig;
import com.decursioteam.sanguinearsenal.core.Util.Keys;
import com.decursioteam.sanguinearsenal.core.init.EntityInit;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import elucent.eidolon.Registry;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

import static com.decursioteam.sanguinearsenal.core.Util.BloodUtil.*;
import static com.decursioteam.sanguinearsenal.core.Util.LivingUtil.hasFullSPSet;

public class ScepterOfBloodItem extends Item {

    public ScepterOfBloodItem(Properties props) {
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
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(Item.BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", SangArsConfig.COMMON.scepterOfBloodMeleeDamage.get(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_UUID, "Weapon modifier", SangArsConfig.COMMON.scepterOfBloodAttackSpeed.get(), AttributeModifier.Operation.ADDITION));
        Multimap<Attribute, AttributeModifier> defaultModifiers = builder.build();
        return slot == EquipmentSlotType.MAINHAND ? defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        ItemStack stack = entity.getItemInHand(hand);
        if (!entity.swinging) {
            if (entity.isCreative()) {
                Vector3d pos = entity.position().add(entity.getLookAngle().scale(0.5)).add(0.5 * Math.sin(Math.toRadians(225 - entity.yHeadRot)), entity.getBbHeight() * 2 / 3, 0.5 * Math.cos(Math.toRadians(225 - entity.yHeadRotO)));
                Vector3d vel = entity.getEyePosition(0).add(entity.getLookAngle().scale(40)).subtract(pos).scale(1.0 / 20);
                world.addFreshEntity(new BloodProjectileEntity(EntityInit.BLOOD_PROJECTILE.get(), world).shoot(
                        pos.x, pos.y, pos.z, vel.x, vel.y, vel.z, entity.getUUID()
                ));
                world.playSound(null, pos.x, pos.y, pos.z, Registry.CAST_SOULFIRE_EVENT.get(), SoundCategory.NEUTRAL, 0.75f, random.nextFloat() * 0.2f + 0.9f);
                try {
                    Template t = ((ServerWorld) world).getStructureManager().get(new ResourceLocation("eidolon", "corridor"));
                    assert t != null;
                    BlockPos d = t.getSize();
                    Rotation r = Rotation.values()[entity.getDirection().get2DDataValue()];
                    BlockPos o = new BlockPos(-d.getX() / 2, -d.getY() / 2, -d.getZ() / 2);
                    BlockPos s = new BlockPos(Math.max(o.getX(), o.getZ()), o.getY(), Math.max(o.getX(), o.getZ()));
                    t.placeInWorld((ServerWorld) world, entity.blockPosition().below(8).offset(o.rotate(r)).subtract(s), new PlacementSettings().setRotation(r), random);
                } catch (Exception e) {
                    //
                }
                entity.swing(hand, true);
                return ActionResult.success(stack);
            }
            if (!hasFullSPSet(entity)) {
                entity.displayClientMessage(Keys.NO_PRAETOR_ARMOR, true);
                entity.swing(hand, false);
                return ActionResult.fail(stack);
            }
            ItemStack itemStack = entity.getItemBySlot(EquipmentSlotType.CHEST);
            double bloodAmount = getBloodAmount(entity, false);
            if (world.isClientSide() && bloodAmount < SangArsConfig.COMMON.scepterOfBloodUseValue.get()) {
                entity.displayClientMessage(Keys.NOT_ENOUGH_BLOOD, true);
                entity.swing(hand, false);
                return ActionResult.fail(stack);
            }

            if (!world.isClientSide() && hasFullSPSet(entity) && bloodAmount >= SangArsConfig.COMMON.scepterOfBloodUseValue.get()) {
                if (hasBloodFlask(entity)) removeBlood(entity, SangArsConfig.COMMON.scepterOfBloodUseValue.get());
                else removeBlood(entity, SangArsConfig.COMMON.scepterOfBloodUseValue.get() * SangArsConfig.COMMON.bloodUsageMultiplier.get());
                Vector3d pos = entity.position().add(entity.getLookAngle().scale(0.5)).add(0.5 * Math.sin(Math.toRadians(225 - entity.yHeadRot)), entity.getBbHeight() * 2 / 3, 0.5 * Math.cos(Math.toRadians(225 - entity.yHeadRotO)));
                Vector3d vel = entity.getEyePosition(0).add(entity.getLookAngle().scale(40)).subtract(pos).scale(1.0 / 20);
                world.addFreshEntity(new BloodProjectileEntity(EntityInit.BLOOD_PROJECTILE.get(), world).shoot(
                        pos.x, pos.y, pos.z, vel.x, vel.y, vel.z, entity.getUUID()
                ));
                world.playSound(null, pos.x, pos.y, pos.z, Registry.CAST_SOULFIRE_EVENT.get(), SoundCategory.NEUTRAL, 0.75f, random.nextFloat() * 0.2f + 0.9f);
                stack.setDamageValue(stack.getDamageValue() + 1);

                try {
                    Template t = ((ServerWorld) world).getStructureManager().get(new ResourceLocation("eidolon", "corridor"));
                    assert t != null;
                    BlockPos d = t.getSize();
                    Rotation r = Rotation.values()[entity.getDirection().get2DDataValue()];
                    BlockPos o = new BlockPos(-d.getX() / 2, -d.getY() / 2, -d.getZ() / 2);
                    BlockPos s = new BlockPos(Math.max(o.getX(), o.getZ()), o.getY(), Math.max(o.getX(), o.getZ()));
                    t.placeInWorld((ServerWorld) world, entity.blockPosition().below(8).offset(o.rotate(r)).subtract(s), new PlacementSettings().setRotation(r), random);
                } catch (Exception e) {
                    //
                }
                entity.swing(hand, true);
                return ActionResult.success(stack);
            }
        }
        return ActionResult.pass(stack);
    }
}
