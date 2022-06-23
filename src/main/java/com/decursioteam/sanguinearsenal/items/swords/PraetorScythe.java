package com.decursioteam.sanguinearsenal.items.swords;

import com.decursioteam.sanguinearsenal.core.SangArsConfig;
import com.decursioteam.sanguinearsenal.core.Util.Keys;
import com.decursioteam.sanguinearsenal.entities.FlyingScytheEntity;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import static com.decursioteam.sanguinearsenal.core.Util.BloodUtil.getBloodAmount;
import static com.decursioteam.sanguinearsenal.core.Util.BloodUtil.removeBlood;
import static com.decursioteam.sanguinearsenal.core.Util.LivingUtil.hasFullSPSet;

public class PraetorScythe extends Item {

    public PraetorScythe(Item.Properties props) {
        super(props);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 1080;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(Item.BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", SangArsConfig.COMMON.praetorScytheMeleeDamage.get(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_UUID, "Weapon modifier", SangArsConfig.COMMON.praetorScytheAttackSpeed.get(), AttributeModifier.Operation.ADDITION));
        Multimap<Attribute, AttributeModifier> defaultModifiers = builder.build();
        return slot == EquipmentSlotType.MAINHAND ? defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand handIn) {
        ItemStack stack = playerEntity.getItemInHand(handIn);
        EquipmentSlotType hand;
        if(!hasFullSPSet(playerEntity)) playerEntity.displayClientMessage(Keys.NO_PRAETOR_ARMOR, true);
        if (!world.isClientSide() && hasFullSPSet(playerEntity)) {
            if(!playerEntity.isCreative() && getBloodAmount(playerEntity, false) < SangArsConfig.COMMON.praetorScytheThrowValue.get()) {
                playerEntity.displayClientMessage(Keys.NOT_ENOUGH_BLOOD, true);
                return ActionResult.fail(stack);
            }
            if(!playerEntity.isCreative()) removeBlood(playerEntity, SangArsConfig.COMMON.praetorScytheThrowValue.get());

            if(handIn == Hand.OFF_HAND) hand = EquipmentSlotType.OFFHAND;
            else hand = EquipmentSlotType.MAINHAND;


            playerEntity.setItemSlot(hand, ItemStack.EMPTY);
            double baseDamage = playerEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
            float multiplier = 1.2f;
            double damage = 1.0F + baseDamage * multiplier;

            int slot = playerEntity.inventory.selected;
            FlyingScytheEntity entity = new FlyingScytheEntity(world);
            entity.setPos(playerEntity.getX(), playerEntity.getY() + playerEntity.getBbHeight() / 2f, playerEntity.getZ());

            entity.setData((float) damage, playerEntity.getUUID(), slot, stack);
            entity.getEntityData().set(FlyingScytheEntity.SCYTHE, stack);

            entity.shootFromRotation(playerEntity, playerEntity.xRot, playerEntity.yRot, 0.0F, 1.5F + 0 * 0.125f, 0F);
            world.addFreshEntity(entity);
        }
        playerEntity.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        return super.use(world, playerEntity, handIn);
    }


    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity attacker, Entity target) {
        if(target instanceof LivingEntity){
            float damage = (float)attacker.getAttributeValue(Attributes.ATTACK_DAMAGE);

            for(LivingEntity livingentity : attacker.level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(1.0D, 0.25D, 1.0D))) {
                if (livingentity != attacker && livingentity != target && !attacker.isAlliedTo(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity)livingentity).isMarker()) && attacker.distanceToSqr(livingentity) < 9.0D) {
                    livingentity.knockback(0.4F, MathHelper.sin(attacker.yRot * ((float)Math.PI / 180F)), -MathHelper.cos(attacker.yRot * ((float)Math.PI / 180F)));
                    livingentity.hurt(DamageSource.playerAttack(attacker), damage);
                }
            }

            attacker.level.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, attacker.getSoundSource(), 1.0F, 1.0F);
            attacker.sweepAttack();
        }
        stack.hurtAndBreak(1, attacker, (e) -> attacker.awardStat(Stats.ITEM_USED.get(stack.getItem())));
        return super.onLeftClickEntity(stack, attacker, target);
    }
}
