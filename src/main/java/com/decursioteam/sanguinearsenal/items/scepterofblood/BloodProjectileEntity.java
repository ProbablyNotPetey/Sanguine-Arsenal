package com.decursioteam.sanguinearsenal.items.scepterofblood;

import com.decursioteam.sanguinearsenal.core.SangArsConfig;
import elucent.eidolon.Registry;
import elucent.eidolon.entity.SpellProjectileEntity;
import elucent.eidolon.network.MagicBurstEffectPacket;
import elucent.eidolon.network.Networking;
import elucent.eidolon.particle.Particles;
import elucent.eidolon.util.ColorUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.UUID;

public class BloodProjectileEntity extends SpellProjectileEntity {
    UUID casterID;

    public BloodProjectileEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Entity shoot(double x, double y, double z, double vx, double vy, double vz, UUID caster) {
        casterID = caster;
        return super.shoot(x, y, z, vx, vy, vz, caster);
    }

    @Override
    public void tick() {
        super.tick();

        Vector3d motion = getDeltaMovement();
        Vector3d pos = position();
        Vector3d norm = motion.normalize().scale(0.025f);
        for (int i = 0; i < 8; i ++) {
            double lerpX = MathHelper.lerp(i / 8.0f, xo, pos.x);
            double lerpY = MathHelper.lerp(i / 8.0f, yo, pos.y);
            double lerpZ = MathHelper.lerp(i / 8.0f, zo, pos.z);
            Particles.create(Registry.SPARKLE_PARTICLE)
                    .addVelocity(-norm.x, -norm.y, -norm.z)
                    .setAlpha(0.375f, 0).setScale(0.375f, 0)
                    .setColor(1.0F, 0.0F, 0.23F, 1.0F, 0.0F, 0.23F)
                    .setLifetime(5)
                    .spawn(level, lerpX, lerpY, lerpZ);
        }
    }

    @Override
    protected void onImpact(RayTraceResult ray, Entity target) {
        target.hurt(DamageSource.indirectMagic(this, level.getPlayerByUUID(casterID)), SangArsConfig.COMMON.scepterOfBloodMagicDamage.get().floatValue());
        onImpact(ray);
    }

    @Override
    protected void onImpact(RayTraceResult ray) {
        onRemovedFromWorld();
        if (level.isClientSide()) {
            Vector3d pos = ray.getLocation();
            level.playSound(null, pos.x, pos.y, pos.z, Registry.SPLASH_SOULFIRE_EVENT.get(), SoundCategory.NEUTRAL, 0.6f, random.nextFloat() * 0.2f + 0.9f);
            Networking.sendToTracking(level, blockPosition(), new MagicBurstEffectPacket(pos.x, pos.y, pos.z, ColorUtil.packColor(255, 255, 0, 23), ColorUtil.packColor(255, 255, 0, 23)));
        }
    }
}
