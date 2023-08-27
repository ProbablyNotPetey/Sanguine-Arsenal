package com.decursioteam.sanguinearsenal.core.particles.circle;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;

public class CircleTintType extends ParticleType<CircleTintData> {
    public CircleTintType() {
        super(false, CircleTintData.DESERIALIZER);
    }

    @Override
    public Codec<CircleTintData> codec() {
        return CircleTintData.CODEC;
    }
}
