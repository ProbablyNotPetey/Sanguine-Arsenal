package com.decursioteam.sanguinearsenal.core.Util;

import com.decursioteam.sanguinearsenal.armor.sanguinepraetor.SPArmorItem;
import com.decursioteam.sanguinearsenal.core.SangArsConfig;
import com.decursioteam.sanguinearsenal.core.network.Network;
import com.decursioteam.sanguinearsenal.core.network.messages.InputMessage;
import elucent.eidolon.event.SpeedFactorEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.decursioteam.sanguinearsenal.core.Util.BloodUtil.addBlood;
import static com.decursioteam.sanguinearsenal.core.Util.KeyBinds.BLOOD_AURA_KB;
import static com.decursioteam.sanguinearsenal.core.Util.LivingUtil.hasFullSPSet;

public class Events {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    @OnlyIn(Dist.CLIENT)
    public void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        PlayerEntity playerEntity = minecraft.player;
        if (event.phase.equals(TickEvent.Phase.END) && playerEntity != null) {
            if (hasFullSPSet(playerEntity) && BLOOD_AURA_KB.isDown()) {
                Network.CHANNEL.sendToServer(new InputMessage());
            }
        }
    }

    @SubscribeEvent
    public void onDeath(LivingDropsEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (entity.getLastHurtByMob() instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) entity.getLastHurtByMob();
            if (hasFullSPSet(playerEntity)) {
                if (Math.floor(Math.random() * 100) + 1 <= 5) {
                    for (int i = 0; i < 4; i++) {
                        if (playerEntity.hasItemInSlot(EquipmentSlotType.byTypeAndIndex(EquipmentSlotType.Group.ARMOR, i))) {
                            int oldDamage = playerEntity.getItemBySlot(EquipmentSlotType.byTypeAndIndex(EquipmentSlotType.Group.ARMOR, i)).getDamageValue();
                            int newDamaged = Math.max(oldDamage + (-5), 0);
                            playerEntity.getItemBySlot(EquipmentSlotType.byTypeAndIndex(EquipmentSlotType.Group.ARMOR, i)).setDamageValue(newDamaged);
                        }
                    }
                }

                if (entity instanceof AnimalEntity) {
                    addBlood(playerEntity, SangArsConfig.COMMON.passiveEntitiesBloodValue.get());
                }
                if (entity instanceof MonsterEntity) {
                    addBlood(playerEntity, SangArsConfig.COMMON.aggressiveEntitiesBloodValue.get());
                }
                if (!(entity instanceof MonsterEntity) && !(entity instanceof AnimalEntity))
                    addBlood(playerEntity, SangArsConfig.COMMON.otherEntitiesBloodValue.get());
            }
        }
    }

    @SubscribeEvent
    public void onApplyPotion(PotionEvent.PotionApplicableEvent event) {
        if (event.getPotionEffect().getEffect() == Effects.MOVEMENT_SLOWDOWN && event.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).getItem() instanceof SPArmorItem) {
            event.setResult(Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {

        DamageSource source = event.getSource();

        if (!(source.getEntity() instanceof PlayerEntity)) {
            return;
        }

        PlayerEntity livingEntity = (PlayerEntity) source.getEntity();

        if (source == DamageSource.WITHER || source.isMagic()) {

            ItemStack stack = livingEntity.getItemBySlot(EquipmentSlotType.HEAD);
            ItemStack stack2 = livingEntity.getItemBySlot(EquipmentSlotType.LEGS);
            if (stack.getItem() instanceof SPArmorItem && stack2.getItem() instanceof SPArmorItem) {
                event.setAmount(event.getAmount() * 1.7F);
            }

            stack = event.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST);

            if (stack.getItem() instanceof SPArmorItem) {
                event.setAmount(event.getAmount() * 1.7F);

                if (source == DamageSource.WITHER) {
                    livingEntity.heal(event.getAmount() / 3.0F);
                }
            }

        } else if (source == DamageSource.FALL || source == DamageSource.ANVIL || source == DamageSource.HOT_FLOOR || source.isExplosion()) {
            ItemStack stack = livingEntity.getItemBySlot(EquipmentSlotType.LEGS);
            if (stack.getItem() instanceof SPArmorItem) {
                event.setAmount(event.getAmount() * 1.25F);
            }
        } else if (source == DamageSource.SWEET_BERRY_BUSH) {
            ItemStack stack = livingEntity.getItemBySlot(EquipmentSlotType.FEET);
            if (stack.getItem() instanceof SPArmorItem && event.isCancelable()) {
                event.setCanceled(true);
            }
        }

    }

    @SubscribeEvent
    public void onGetSpeedFactor(SpeedFactorEvent event) {
        if ((event.getSpeedFactor() < 1.0F) && event.getEntity() instanceof LivingEntity) {
            ItemStack stack = ((LivingEntity) event.getEntity()).getItemBySlot(EquipmentSlotType.FEET);
            if ((stack.getItem() instanceof SPArmorItem)) {
                float diff = 1.0F - event.getSpeedFactor();
                event.setSpeedFactor(1.0F - diff / 2.0F);
            }
        }

    }
}
