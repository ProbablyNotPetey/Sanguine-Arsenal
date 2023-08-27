package com.decursioteam.sanguinearsenal.entities;

import com.decursioteam.sanguinearsenal.core.SangArsConfig;
import elucent.eidolon.Registry;
import elucent.eidolon.entity.SpellProjectileEntity;
import elucent.eidolon.network.MagicBurstEffectPacket;
import elucent.eidolon.network.Networking;
import elucent.eidolon.particle.Particles;
import elucent.eidolon.registries.Sounds;
import elucent.eidolon.util.ColorUtil;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class BloodProjectileEntity extends SpellProjectileEntity {
    UUID casterID;

    public BloodProjectileEntity(EntityType<?> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Entity shoot(double x, double y, double z, double vx, double vy, double vz, UUID caster) {
        casterID = caster;
        return super.shoot(x, y, z, vx, vy, vz, caster);
    }

    @Override
    public void tick() {
        super.tick();

        Vec3 motion = getDeltaMovement();
        Vec3 pos = position();
        Vec3 norm = motion.normalize().scale(0.025f);
        for (int i = 0; i < 8; i ++) {
            double lerpX = Mth.lerp(i / 8.0f, xo, pos.x);
            double lerpY = Mth.lerp(i / 8.0f, yo, pos.y);
            double lerpZ = Mth.lerp(i / 8.0f, zo, pos.z);
            Particles.create(elucent.eidolon.registries.Particles.SPARKLE_PARTICLE)
                    .addVelocity(-norm.x, -norm.y, -norm.z)
                    .setAlpha(0.375f, 0).setScale(0.375f, 0)
                    .setColor(1.0F, 0.0F, 0.23F, 1.0F, 0.0F, 0.23F)
                    .setLifetime(5)
                    .spawn(level, lerpX, lerpY, lerpZ);
        }
    }

    @Override
    protected void onImpact(HitResult ray, Entity target) {
        target.hurt(DamageSource.indirectMagic(this, level.getPlayerByUUID(casterID)), SangArsConfig.COMMON.scepterOfBloodMagicDamage.get().floatValue());
        onImpact(ray);
    }

    @Override
    protected void onImpact(HitResult ray) {
        onRemovedFromWorld();
        if (!level.isClientSide()) {
            Vec3 pos = ray.getLocation();
            level.playSound(null, pos.x, pos.y, pos.z, Sounds.SPLASH_SOULFIRE_EVENT.get(), SoundSource.NEUTRAL, 0.6f, random.nextFloat() * 0.2f + 0.9f);
            Networking.sendToTracking(level, blockPosition(), new MagicBurstEffectPacket(pos.x, pos.y, pos.z, ColorUtil.packColor(255, 255, 0, 23), ColorUtil.packColor(255, 255, 0, 23)));
            this.discard();
        }
    }
}
