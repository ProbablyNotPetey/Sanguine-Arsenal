package com.decursioteam.sanguinearsenal.items.swords;

import com.decursioteam.sanguinearsenal.core.SangArsConfig;
import com.decursioteam.sanguinearsenal.core.Util.Keys;
import com.decursioteam.sanguinearsenal.entities.FlyingScytheEntity;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

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
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(Item.BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", SangArsConfig.COMMON.praetorScytheMeleeDamage.get(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_UUID, "Weapon modifier", SangArsConfig.COMMON.praetorScytheAttackSpeed.get(), AttributeModifier.Operation.ADDITION));
        Multimap<Attribute, AttributeModifier> defaultModifiers = builder.build();
        return slot == EquipmentSlot.MAINHAND ? defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player playerEntity, InteractionHand handIn) {
        ItemStack stack = playerEntity.getItemInHand(handIn);
        EquipmentSlot hand;
        if(!hasFullSPSet(playerEntity)) playerEntity.displayClientMessage(Keys.NO_PRAETOR_ARMOR, true);
        if (!world.isClientSide && hasFullSPSet(playerEntity)) {
            if(!playerEntity.isCreative() && getBloodAmount(playerEntity, false) < SangArsConfig.COMMON.praetorScytheThrowValue.get()) {
                playerEntity.displayClientMessage(Keys.NOT_ENOUGH_BLOOD, true);
                return InteractionResultHolder.fail(stack);
            }
            if(!playerEntity.isCreative()) removeBlood(playerEntity, SangArsConfig.COMMON.praetorScytheThrowValue.get());

            if(handIn == InteractionHand.OFF_HAND) hand = EquipmentSlot.OFFHAND;
            else hand = EquipmentSlot.MAINHAND;


            playerEntity.setItemSlot(hand, ItemStack.EMPTY);
            double baseDamage = playerEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
            float multiplier = 1.2f;
            double damage = 1.0F + baseDamage * multiplier;

            int slot = playerEntity.getInventory().selected;
            FlyingScytheEntity entity = new FlyingScytheEntity(world);
            entity.setPos(playerEntity.getX(), playerEntity.getY() + playerEntity.getBbHeight() / 2f, playerEntity.getZ());

            entity.setData((float) damage, playerEntity.getUUID(), slot, stack);
            entity.getEntityData().set(FlyingScytheEntity.SCYTHE, stack);

            entity.shootFromRotation(playerEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, 1.5F + 0 * 0.125f, 0F);
            world.addFreshEntity(entity);
        }
        playerEntity.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        return super.use(world, playerEntity, handIn);
    }


    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player attacker, Entity target) {
        if(target instanceof LivingEntity){
            float damage = (float)attacker.getAttributeValue(Attributes.ATTACK_DAMAGE);

            for(LivingEntity livingentity : attacker.level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(1.0D, 0.25D, 1.0D))) {
                if (livingentity != attacker && livingentity != target && !attacker.isAlliedTo(livingentity) && (!(livingentity instanceof ArmorStand) || !((ArmorStand)livingentity).isMarker()) && attacker.distanceToSqr(livingentity) < 9.0D) {
                    livingentity.knockback(0.4F, Mth.sin(attacker.getYRot() * ((float)Math.PI / 180F)), -Mth.cos(attacker.getYRot() * ((float)Math.PI / 180F)));
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
