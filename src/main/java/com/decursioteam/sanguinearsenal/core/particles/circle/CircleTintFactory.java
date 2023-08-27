package com.decursioteam.sanguinearsenal.core.particles.circle;

import com.mojang.serialization.Codec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ParticleType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CircleTintFactory implements ParticleProvider<CircleTintData> {
    private final SpriteSet sprites;

    public CircleTintFactory(SpriteSet sprite) {
        this.sprites = sprite;
    }

    @Nullable
    @Override
    public Particle createParticle(CircleTintData circleTintData, @Nonnull ClientLevel world, double xPos, double yPos,
                                   double zPos, double xVelocity, double yVelocity, double zVelocity) {
        CircleTintParticle particle = new CircleTintParticle(world, xPos, yPos, zPos, xVelocity, yVelocity, zVelocity,
                circleTintData.getTint(), circleTintData.getDiameter(), circleTintData.getLifeTime(), circleTintData.getResizeSpeed(), circleTintData.shouldCollide(), sprites);
        particle.pickSprite(sprites);
        return particle;
    }

    public static class CircleTintType extends ParticleType<CircleTintData> {
        public CircleTintType() {
            super(false, CircleTintData.DESERIALIZER);
        }

        @Override
        public Codec<CircleTintData> codec() {
            return CircleTintData.CODEC;
        }
    }
}