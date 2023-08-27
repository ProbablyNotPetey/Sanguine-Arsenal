package com.decursioteam.sanguinearsenal.core.particles.circle;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Objects;

public class CircleTintParticle extends TextureSheetParticle {
    private static final ParticleRenderType RENDERER = new ParticleRenderType() {
        @Override
        public void begin(BufferBuilder bufferBuilder, TextureManager textureManager) {
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
//            RenderSystem.alphaFunc(GL11.GL_GREATER, 0.001F);
//            RenderSystem.disableLighting();

            textureManager.bindForSetup(TextureAtlas.LOCATION_PARTICLES);
            Objects.requireNonNull(textureManager.getTexture(TextureAtlas.LOCATION_PARTICLES)).setBlurMipmap(true, false);
            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
        }

        @Override
        public void end(Tesselator tessellator) {
            tessellator.end();
            RenderSystem.enableDepthTest();
            Objects.requireNonNull(Minecraft.getInstance().textureManager.getTexture(TextureAtlas.LOCATION_PARTICLES)).restoreLastBlurMipmap();
//            RenderSystem.alphaFunc(GL11.GL_GREATER, 0.1F);
            RenderSystem.disableBlend();
            RenderSystem.depthMask(true);
        }

        @Override
        public String toString() {
            return SanguineArsenal.MOD_ID + ":" + "circle_tint";
        }
    };
    final float resizeSpeed;

    public CircleTintParticle(ClientLevel world, double x, double y, double z, double velocityX,
                              double velocityY, double velocityZ, Color spark, float diameter,
                              int lifeTime, float resizeSpeed, boolean shouldCollide, SpriteSet sprites) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);
        this.resizeSpeed = resizeSpeed;
        setColor(spark.getRed() / 255.0F, spark.getGreen() / 255.0F, spark.getBlue() / 255.0F);
        setSize(diameter, diameter);

        lifetime = lifeTime;

        quadSize = diameter;
        this.alpha = 1.0F;

        xd = velocityX;
        yd = velocityY;
        zd = velocityZ;

        this.hasPhysics = shouldCollide;
    }

    @Override
    public float getQuadSize(float scaleFactor) {
        return this.quadSize;
    }

    @Override
    protected int getLightColor(float partialTick) {
        return LightTexture.pack(15, 15);
    }

    @Nonnull
    @Override
    public ParticleRenderType getRenderType() {
        return RENDERER;
    }

    @Override
    public void tick() {
        this.quadSize *= this.resizeSpeed;

        xo = x;
        yo = y;
        zo = z;

        move(xd, yd, zd);

        if (onGround) {
            this.remove();
        }

        if (yo == y && yd > 0) {
            this.remove();
        }

        if (this.age++ >= this.lifetime) {
            this.remove();
        }
    }
}