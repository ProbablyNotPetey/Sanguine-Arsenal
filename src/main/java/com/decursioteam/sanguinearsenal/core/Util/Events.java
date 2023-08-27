package com.decursioteam.sanguinearsenal.core.Util;

import com.decursioteam.sanguinearsenal.armor.sanguinepraetor.SPArmorItem;
import com.decursioteam.sanguinearsenal.core.SangArsConfig;
import com.decursioteam.sanguinearsenal.core.network.Network;
import com.decursioteam.sanguinearsenal.core.network.messages.InputMessage;
import elucent.eidolon.event.StuckInBlockEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
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
        Player playerEntity = minecraft.player;
        if (event.phase.equals(TickEvent.Phase.END) && playerEntity != null) {
            if (hasFullSPSet(playerEntity) && BLOOD_AURA_KB.isDown()) {
                Network.CHANNEL.sendToServer(new InputMessage());
            }
        }
    }

    @SubscribeEvent
    public void onDeath(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getLastHurtByMob() instanceof Player) {
            Player playerEntity = (Player) entity.getLastHurtByMob();
            if (hasFullSPSet(playerEntity)) {
                if (Math.floor(Math.random() * 100) + 1 <= 5) {
                    for (int i = 0; i < 4; i++) {
                        if (playerEntity.hasItemInSlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i))) {
                            int oldDamage = playerEntity.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i)).getDamageValue();
                            int newDamaged = Math.max(oldDamage + (-5), 0);
                            playerEntity.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i)).setDamageValue(newDamaged);
                        }
                    }
                }

                if (entity instanceof Animal) {
                    addBlood(playerEntity, SangArsConfig.COMMON.passiveEntitiesBloodValue.get());
                }
                if (entity instanceof Monster) {
                    addBlood(playerEntity, SangArsConfig.COMMON.aggressiveEntitiesBloodValue.get());
                }
                if (!(entity instanceof Monster) && !(entity instanceof Animal))
                    addBlood(playerEntity, SangArsConfig.COMMON.otherEntitiesBloodValue.get());
            }
        }
    }

    @SubscribeEvent
    public void onApplyPotion(MobEffectEvent.Applicable event) {
        if (event.getEffectInstance().getEffect() == MobEffects.MOVEMENT_SLOWDOWN && event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem() instanceof SPArmorItem) {
            event.setResult(Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {

        DamageSource source = event.getSource();

        if (!(source.getEntity() instanceof Player)) {
            return;
        }

        Player livingEntity = (Player) source.getEntity();

        if (source == DamageSource.WITHER || source.isMagic()) {

            ItemStack stack = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
            ItemStack stack2 = livingEntity.getItemBySlot(EquipmentSlot.LEGS);
            if (stack.getItem() instanceof SPArmorItem && stack2.getItem() instanceof SPArmorItem) {
                event.setAmount(event.getAmount() * 1.7F);
            }

            stack = event.getEntity().getItemBySlot(EquipmentSlot.CHEST);

            if (stack.getItem() instanceof SPArmorItem) {
                event.setAmount(event.getAmount() * 1.7F);

                if (source == DamageSource.WITHER) {
                    livingEntity.heal(event.getAmount() / 3.0F);
                }
            }

        } else if (source == DamageSource.FALL || source == DamageSource.ANVIL || source == DamageSource.HOT_FLOOR || source.isExplosion()) {
            ItemStack stack = livingEntity.getItemBySlot(EquipmentSlot.LEGS);
            if (stack.getItem() instanceof SPArmorItem) {
                event.setAmount(event.getAmount() * 1.25F);
            }
        } else if (source == DamageSource.SWEET_BERRY_BUSH) {
            ItemStack stack = livingEntity.getItemBySlot(EquipmentSlot.FEET);
            if (stack.getItem() instanceof SPArmorItem && event.isCancelable()) {
                event.setCanceled(true);
            }
        }

    }

    @SubscribeEvent
    public void onGetSpeedFactor(StuckInBlockEvent event) {
        if ((event.getStuckMultiplier().length() < 1.0F) && event.getEntity() instanceof LivingEntity) {
            ItemStack stack = ((LivingEntity) event.getEntity()).getItemBySlot(EquipmentSlot.FEET);
            if ((stack.getItem() instanceof SPArmorItem)) {
                Vec3 diff = (new Vec3(1.0D, 1.0D, 1.0D)).subtract(event.getStuckMultiplier()).scale(0.5D);
                event.setStuckMultiplier((new Vec3(1.0D, 1.0D, 1.0D)).subtract(diff));
            }
        }

    }
}
