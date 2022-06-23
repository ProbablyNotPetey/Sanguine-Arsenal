package com.decursioteam.sanguinearsenal.core.particles.circle;

import com.mojang.serialization.Codec;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.ParticleType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CircleTintFactory implements IParticleFactory<CircleTintData> {
    private final IAnimatedSprite sprites;

    public CircleTintFactory(IAnimatedSprite sprite) {
        this.sprites = sprite;
    }

    @Nullable
    @Override
    public Particle createParticle(CircleTintData circleTintData, @Nonnull ClientWorld world, double xPos, double yPos,
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