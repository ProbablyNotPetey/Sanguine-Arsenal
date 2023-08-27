package com.decursioteam.sanguinearsenal.hud;

/*
import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;
import tfar.classicbar.Color;
import tfar.classicbar.config.ModConfig;
import tfar.classicbar.overlays.BarOverlay;

import static com.decursioteam.sanguinearsenal.core.Util.BloodUtil.getBloodAmount;
import static com.decursioteam.sanguinearsenal.core.Util.LivingUtil.hasFullSPSet;
import static com.mojang.blaze3d.systems.RenderSystem.enableBlend;
import static com.mojang.blaze3d.systems.RenderSystem.pushMatrix;
import static tfar.classicbar.ColorUtils.hex2Color;
import static tfar.classicbar.ModUtils.*;

@OnlyIn(Dist.CLIENT)
public class BloodBarClassicBars implements BarOverlay {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SanguineArsenal.MOD_ID, "textures/gui/classic_blood_bar.png");
    public boolean side;

    @Override
    public BarOverlay setSide(boolean side) {
        this.side = side;
        return this;
    }

    @Override
    public boolean rightHandSide() {
        return side;
    }

    @Override
    public boolean shouldRender(PlayerEntity playerEntity) {
        return ModList.get().isLoaded("classicbar") && hasFullSPSet(playerEntity);
    }

    @Override
    public void renderBar(MatrixStack stack, PlayerEntity playerEntity, int width, int height) {
        if (shouldRender(playerEntity)) {
            int bloodAmount = getBloodAmount(playerEntity, true);
            int maxBlood = 20;
            //Push to avoid lasting changes

            int xStart = width / 2 + 10;
            int yStart = height - getSidedOffset();
            pushMatrix();
            enableBlend();

            Color.reset();
            //Bar background
            drawTexturedModalRect(stack, xStart, yStart, 0, 0, 81, 9);
            //draw portion of bar based on blood amount
            int f = xStart + 79 - getWidth(bloodAmount, maxBlood);
            hex2Color("#FF0000").color2Gl();
            drawTexturedModalRect(stack, f, yStart + 1, 1, 10, getWidth(bloodAmount, maxBlood), 7);

            RenderSystem.popMatrix();
        }
    }

    @Override
    public boolean shouldRenderText() {
        return true;
    }

    @Override
    public void renderText(MatrixStack stack, PlayerEntity playerEntity, int width, int height) {
        if (shouldRender(playerEntity)) {
            int bloodAmount = getBloodAmount(playerEntity, true);

            int c = Integer.decode("#FF0000");
            int xStart = width / 2 + 10;
            int yStart = height - getSidedOffset();
            drawStringOnHUD(stack, bloodAmount + "", xStart + 9 * ((ModConfig.displayIcons.get()) ? 1 : 0) + rightTextOffset, yStart - 1, c);
        }
    }

    @Override
    public void renderIcon(MatrixStack stack, PlayerEntity playerEntity, int width, int height) {

        if (shouldRender(playerEntity)) {
            int xStart = width / 2 + 10;
            int yStart = height - getSidedOffset();
            //Draw blood icon
            mc.getTextureManager().bind(TEXTURE);
            enableBlend();

            drawTexturedModalRect(stack, xStart + 82, yStart, 0, 0, 9, 9);
            drawTexturedModalRect(stack, xStart + 82, yStart, 9, 0, 9, 9);
        }
    }

    @Override
    public String name() {
        return "bloodaura";
    }
}

 */
