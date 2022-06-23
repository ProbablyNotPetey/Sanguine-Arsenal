package com.decursioteam.sanguinearsenal.entities;

import com.decursioteam.sanguinearsenal.core.init.EntityInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.UUID;

public class FlyingScytheEntity extends ProjectileItemEntity {
    public static final DataParameter<ItemStack> SCYTHE = EntityDataManager.defineId(FlyingScytheEntity.class, DataSerializers.ITEM_STACK);
    public ItemStack scythe;
    public UUID ownerUUID;
    public PlayerEntity owner;
    public Vector3d shooterPos;

    public int slot;
    public float damage;
    public int age;
    public int returnAge=15;
    public boolean returning;

    public FlyingScytheEntity(World level)
    {
        super(EntityInit.FLYING_SCYTHE.get(), level);
        noPhysics = false;
    }
    public PlayerEntity owner()
    {
        if (owner == null)
        {
            if (!level.isClientSide())
            {
                owner = (PlayerEntity) ((ServerWorld) level).getEntity(ownerUUID);
            }
        }
        return owner;
    }

    public void setData(float damage, UUID ownerUUID, int slot, ItemStack scythe)
    {
        this.damage = damage;
        this.ownerUUID = ownerUUID;
        this.slot = slot;
        this.scythe = scythe;
    }
    public void shootFromRotation(Entity shooter, float rotationPitch, float rotationYaw, float pitchOffset, float velocity, float innacuracy)
    {
        float f = -MathHelper.sin(rotationYaw * ((float)Math.PI / 180F)) * MathHelper.cos(rotationPitch * ((float)Math.PI / 180F));
        float f1 = -MathHelper.sin((rotationPitch + pitchOffset) * ((float)Math.PI / 180F));
        float f2 = MathHelper.cos(rotationYaw * ((float)Math.PI / 180F)) * MathHelper.cos(rotationPitch * ((float)Math.PI / 180F));
        this.shoot(f, f1, f2, velocity, innacuracy);
        this.shooterPos = shooter.position();
        Vector3d vec3 = shooter.getDeltaMovement();
        this.setDeltaMovement(this.getDeltaMovement().add(vec3.x, 0, vec3.z));
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult result) {
        super.onHitBlock(result);
        returning = true;
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        DamageSource source = DamageSource.indirectMobAttack(this, owner());
        Entity entity = result.getEntity();
        if (level.isClientSide()) return;
        if (age > 100) return;
        if (entity.equals(owner)) return;
        boolean success = entity.hurt(source, damage);
        if (success)
        {
            if (!level.isClientSide())
            {
                if (entity instanceof LivingEntity)
                {
                    scythe.hurtAndBreak(1, owner(), (e) -> remove());
                }
            }
            returning = true;
            entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.CROSSBOW_SHOOT, entity.getSoundSource(), 1.0F, 0.9f + entity.level.random.nextFloat() * 0.2f);
        }
        super.onHitEntity(result);
    }


    @Override
    public void tick()
    {
        super.tick();
        age++;

        if (!level.isClientSide())
        {
            PlayerEntity playerEntity = owner();
            if (playerEntity == null || !playerEntity.isAlive())
            {
                ItemEntity entityitem = new ItemEntity(level, getX(), getY() + 0.5, getZ(), scythe);
                entityitem.setPickUpDelay(40);
                entityitem.setDeltaMovement(entityitem.getDeltaMovement().multiply(0, 1, 0));
                level.addFreshEntity(entityitem);
                remove();
                return;
            }
            if (this.xRot == 0.0F && this.yRot == 0.0F)
            {
                Vector3d vector3d = getDeltaMovement();
                setYBodyRot((float) (MathHelper.atan2(vector3d.x, vector3d.z) * (double) (180F / (float) Math.PI)));
                this.yRotO = this.yRot;
                this.xRotO = this.xRot;
            }
            if (shooterPos != null && shooterPos.distanceTo(this.position()) > 15) {
                returning = true;
            }
            else if (age > returnAge) returning = true;
            if (age < 10) noPhysics = true;
            if(age >= 1){
                EntityPredicate PREDICATE = (new EntityPredicate()).range(12.0D);
                LivingEntity nearestEntity = level.getNearestEntity(LivingEntity.class, PREDICATE, playerEntity, getX(), getY(), getZ(), getBoundingBox().inflate(12.0D, 2.0D, 12.0D));

                if(nearestEntity != null && nearestEntity != playerEntity && !(nearestEntity instanceof AnimalEntity) && !(nearestEntity instanceof ArmorStandEntity)) {
                    Vector3d entityPos = nearestEntity.position().add(0, 1, 0);
                    Vector3d motion = entityPos.subtract(position());
                    setDeltaMovement(motion.normalize().scale(0.75f));
                }
            }
            if (returning)
            {
                noPhysics = true;
                Vector3d ownerPos = playerEntity.position().add(0, 1, 0);
                Vector3d motion = ownerPos.subtract(position());
                setDeltaMovement(motion.normalize().scale(0.75f));
            }
            float distance = distanceTo(playerEntity);
            if (age > 8)
            {
                if (distance < 3f)
                {
                    if (isAlive())
                    {
                        if(playerEntity.inventory.getFreeSlot() == -1) return;
                        else ItemHandlerHelper.giveItemToPlayer(playerEntity, scythe, slot);
                        if (!playerEntity.abilities.instabuild)
                        {
                            int cooldown = 20;
                            playerEntity.getCooldowns().addCooldown(scythe.getItem(), cooldown);
                        }
                        remove();
                    }
                }
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.put("scythe", scythe.serializeNBT());
        if (ownerUUID != null)
        {
            compound.putUUID("ownerUUID", ownerUUID);
        }
        compound.putInt("slot", slot);
        compound.putFloat("damage", damage);
        compound.putInt("age", age);
        compound.putBoolean("returning", returning);
        compound.putInt("returnAge", returnAge);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("scythe"))
        {
            scythe = ItemStack.of(compound.getCompound("scythe"));
        }
        entityData.set(SCYTHE, scythe);

        if (compound.contains("ownerUUID"))
        {
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
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(SCYTHE, ItemStack.EMPTY);
    }


    @Override
    protected Item getDefaultItem()
    {
        if (scythe == null)
        {
            scythe = entityData.get(SCYTHE);
        }
        return scythe.getItem();
    }

    @Override
    public ItemStack getItem()
    {
        if (scythe == null)
        {
            scythe = entityData.get(SCYTHE);
        }
        return scythe;
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

