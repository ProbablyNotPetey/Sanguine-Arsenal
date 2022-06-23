package com.decursioteam.sanguinearsenal.core.codex;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import elucent.eidolon.codex.CodexGui;
import elucent.eidolon.codex.Page;
import elucent.eidolon.codex.RitualPage;
import elucent.eidolon.ritual.Ritual;
import elucent.eidolon.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CustomRitualPage extends Page {
    public static final ResourceLocation BACKGROUND = new ResourceLocation(SanguineArsenal.MOD_ID, "textures/gui/custom_codex_ritual_page.png");
    final Ritual ritual;
    final ItemStack leftcenter;
    final ItemStack rightcenter;
    final RitualPage.RitualIngredient[] inputs;

    public CustomRitualPage(Ritual ritual, ItemStack leftcenter, ItemStack rightcenter, RitualPage.RitualIngredient... inputs) {
        super(BACKGROUND);
        this.ritual = ritual;
        this.leftcenter = leftcenter;
        this.rightcenter = rightcenter;
        this.inputs = inputs;
    }

    @OnlyIn(Dist.CLIENT)
    public void render(CodexGui gui, MatrixStack mStack, int x, int y, int mouseX, int mouseY) {
        float angleStep = (float)Math.min(30, 180 / this.inputs.length);
        double rootAngle = 90.0F - (float)(this.inputs.length - 1) * angleStep / 2.0F;

        for(int i = 0; i < this.inputs.length; ++i) {
            double a = Math.toRadians(rootAngle + (double)(angleStep * (float)i));
            int dx = (int)(64.0D + 48.0D * Math.cos(a));
            int dy = (int)(88.0D + 48.0D * Math.sin(a));
            if (this.inputs[i].isFocus) {
                gui.blit(mStack, x + dx - 13, y + dy - 13, 128, 0, 26, 24);
            } else {
                gui.blit(mStack, x + dx - 8, y + dy - 8, 154, 0, 16, 16);
            }
        }

        RenderSystem.enableBlend();
        RenderSystem.shadeModel(7425);
        RenderSystem.alphaFunc(518, 0.00390625F);
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        Tessellator tess = Tessellator.getInstance();
        RenderSystem.disableTexture();
        RenderSystem.depthMask(false);
        RenderUtil.dragon(mStack, IRenderTypeBuffer.immediate(tess.getBuilder()), x + 64, y + 48, 20.0D, 20.0F, this.ritual.getRed(), this.ritual.getGreen(), this.ritual.getBlue());
        tess.end();
        RenderSystem.enableTexture();
        Minecraft.getInstance().getTextureManager().bind(AtlasTexture.LOCATION_BLOCKS);

        for(int j = 0; j < 2; ++j) {
            RenderUtil.litQuad(mStack, IRenderTypeBuffer.immediate(tess.getBuilder()), x + 52, y + 36, 24.0D, 24.0D, this.ritual.getRed(), this.ritual.getGreen(), this.ritual.getBlue(), Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS).apply(this.ritual.getSymbol()));
            tess.end();
        }

        RenderSystem.defaultAlphaFunc();
        RenderSystem.disableBlend();
        RenderSystem.shadeModel(7424);
        RenderSystem.depthMask(true);
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    }

    @OnlyIn(Dist.CLIENT)
    public void renderIngredients(CodexGui gui, MatrixStack mStack, int x, int y, int mouseX, int mouseY) {
        float angleStep = (float)Math.min(30, 180 / this.inputs.length);
        double rootAngle = 90.0F - (float)(this.inputs.length - 1) * angleStep / 2.0F;

        for(int i = 0; i < this.inputs.length; ++i) {
            double a = Math.toRadians(rootAngle + (double)(angleStep * (float)i));
            int dx = (int)(64.0D + 48.0D * Math.cos(a));
            int dy = (int)(88.0D + 48.0D * Math.sin(a));
            drawItem(gui, mStack, this.inputs[i].stack, x + dx - 8, y + dy - 8, mouseX, mouseY);
        }

        drawItem(gui, mStack, this.leftcenter, x + 43, y + 80, mouseX, mouseY);
        drawItem(gui, mStack, this.rightcenter, x + 69, y + 80, mouseX, mouseY);
    }

    public static class RitualIngredient {
        public final ItemStack stack;
        public final boolean isFocus;

        public RitualIngredient(ItemStack stack, boolean isFocus) {
            this.stack = stack;
            this.isFocus = isFocus;
        }
    }
}
