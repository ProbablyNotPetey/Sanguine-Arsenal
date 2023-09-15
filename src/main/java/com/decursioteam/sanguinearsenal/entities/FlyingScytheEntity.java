package com.decursioteam.sanguinearsenal.entities;

import com.decursioteam.sanguinearsenal.core.init.EntityInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.network.NetworkHooks;

import java.util.UUID;

public class FlyingScytheEntity extends ThrowableItemProjectile {
    public static final EntityDataAccessor<ItemStack> SCYTHE = SynchedEntityData.defineId(FlyingScytheEntity.class, EntityDataSerializers.ITEM_STACK);
    public ItemStack scythe;
    public UUID ownerUUID;
    public Player owner;
    public Vec3 shooterPos;

    public int slot;
    public float damage;
    public int age;
    public int returnAge=15;
    public boolean returning;

    public FlyingScytheEntity(Level level) {
        super(EntityInit.FLYING_SCYTHE.get(), level);
        noPhysics = false;
    }
    public Player owner() {
        if (owner == null && !level.isClientSide) {
            owner = (Player) ((ServerLevel) level).getEntity(ownerUUID);
        }
        return owner;
    }

    public void setData(float damage, UUID ownerUUID, int slot, ItemStack scythe) {
        this.damage = damage;
        this.ownerUUID = ownerUUID;
        this.slot = slot;
        this.scythe = scythe;
    }
    public void shootFromRotation(Entity shooter, float rotationPitch, float rotationYaw, float pitchOffset, float velocity, float innacuracy) {
        float f = -Mth.sin(rotationYaw * ((float)Math.PI / 180F)) * Mth.cos(rotationPitch * ((float)Math.PI / 180F));
        float f1 = -Mth.sin((rotationPitch + pitchOffset) * ((float)Math.PI / 180F));
        float f2 = Mth.cos(rotationYaw * ((float)Math.PI / 180F)) * Mth.cos(rotationPitch * ((float)Math.PI / 180F));
        this.shoot(f, f1, f2, velocity, innacuracy);
        this.shooterPos = shooter.position();
        Vec3 vec3 = shooter.getDeltaMovement();
        this.setDeltaMovement(this.getDeltaMovement().add(vec3.x, 0, vec3.z));
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        returning = true;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        DamageSource source = DamageSource.indirectMobAttack(this, owner());
        Entity entity = result.getEntity();
        if (level.isClientSide) return;
        if (age > 100) return;
        if (entity.equals(owner)) return;
        boolean success = entity.hurt(source, damage);
        if (success) {
            if (entity instanceof LivingEntity) {
                scythe.hurtAndBreak(1, owner(), (e) -> remove(RemovalReason.DISCARDED));
            }
            returning = true;
            entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.CROSSBOW_SHOOT, entity.getSoundSource(), 1.0F, 0.9f + entity.level.random.nextFloat() * 0.2f);
        }
        super.onHitEntity(result);
    }


    @Override
    public void tick() {
        super.tick();
        age++;

        if (level.isClientSide) return;
        Player playerEntity = owner();
        //if player doesn't exist or is dead, drop
        if (playerEntity == null || !playerEntity.isAlive()) {
            ItemEntity entityitem = new ItemEntity(level, getX(), getY() + 0.5, getZ(), scythe);
            entityitem.setPickUpDelay(40);
            entityitem.setDeltaMovement(entityitem.getDeltaMovement().multiply(0, 1, 0));
            level.addFreshEntity(entityitem);
            remove(RemovalReason.DISCARDED);
            return;
        }
        //rotate
        if (this.getXRot() == 0.0F && this.getYRot() == 0.0F) {
            Vec3 vector3d = getDeltaMovement();
            setYBodyRot((float) (Mth.atan2(vector3d.x, vector3d.z) * (double) (180F / (float) Math.PI)));
            this.yRotO = this.getYRot();
            this.xRotO = this.getXRot();
        }
        if (shooterPos != null && shooterPos.distanceTo(this.position()) > 15) returning = true;
        else if (age > returnAge) returning = true;
        if (age < 10) noPhysics = true;
        //home in on enemies
        if(age >= 1) {
            LivingEntity nearestEntity = level.getNearestEntity(LivingEntity.class, TargetingConditions.forNonCombat(), playerEntity, getX(), getY(), getZ(), getBoundingBox().inflate(12.0D, 2.0D, 12.0D));

            if(nearestEntity != null && nearestEntity != playerEntity && !(nearestEntity instanceof Animal) && !(nearestEntity instanceof ArmorStand)) {
                Vec3 entityPos = nearestEntity.position().add(0, 1, 0);
                Vec3 motion = entityPos.subtract(position());
                setDeltaMovement(motion.normalize().scale(0.75f));
            }
        }
        //return to player
        if (returning) {
            noPhysics = true;
            Vec3 ownerPos = playerEntity.position().add(0, 1, 0);
            Vec3 motion = ownerPos.subtract(position());
            setDeltaMovement(motion.normalize().scale(0.75f));
        }
        //discard, enter player inventory
        float distance = distanceTo(playerEntity);
        if (age > 8 && distance < 3f && isAlive()) {
            if(playerEntity.getInventory().getFreeSlot() == -1) return;
            else ItemHandlerHelper.giveItemToPlayer(playerEntity, scythe, slot);
            if (!playerEntity.getAbilities().instabuild) {
                int cooldown = 20;
                playerEntity.getCooldowns().addCooldown(scythe.getItem(), cooldown);
            }
            remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.put("scythe", scythe.serializeNBT());
        if (ownerUUID != null) {
            compound.putUUID("ownerUUID", ownerUUID);
        }
        compound.putInt("slot", slot);
        compound.putFloat("damage", damage);
        compound.putInt("age", age);
        compound.putBoolean("returning", returning);
        compound.putInt("returnAge", returnAge);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("scythe")) {
            scythe = ItemStack.of(compound.getCompound("scythe"));
        }
        entityData.set(SCYTHE, scythe);

        if (compound.contains("ownerUUID")) {
            ownerUUID = compound.getUUID("ownerUUID");
            owner = owner();
        }
        slot = compound.getInt("slot");
        damage = compound.getFloat("damage");
        age = compound.getInt("age");
        returning = compound.getBoolean("returning");
        returnAge = compound.getInt("returnAge");
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(SCYTHE, ItemStack.EMPTY);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    protected Item getDefaultItem() {
        if (scythe == null) {
            scythe = entityData.get(SCYTHE);
        }
        return scythe.getItem();
    }

    @Override
    public ItemStack getItem() {
        if (scythe == null) {
            scythe = entityData.get(SCYTHE);
        }
        return scythe;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean ignoreExplosion()
    {
        return true;
    }
}

