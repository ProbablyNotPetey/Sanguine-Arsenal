package com.decursioteam.sanguinearsenal.hud;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import java.util.ArrayList;
import java.util.List;

import static com.decursioteam.sanguinearsenal.core.Util.BloodUtil.getBloodAmount;
import static com.decursioteam.sanguinearsenal.core.Util.LivingUtil.hasFullSPSet;
import static net.minecraft.client.gui.AbstractGui.GUI_ICONS_LOCATION;

public class BloodBar {
    private static final Minecraft mc = Minecraft.getInstance();
    static Index[] indexes;
    private final ResourceLocation TEXTURE = new ResourceLocation(SanguineArsenal.MOD_ID, "textures/gui/blood_bar.png");
    private final List<Integer> colors = new ArrayList<>();

    public static void blit(MatrixStack stack, int x, int y, float u, float v, int width, int height) {
        blit(stack, x, y, 0, u, v, width, height, 9, 27);
    }

    public static void blit(MatrixStack stack, int x, int y, int z, float u, float v, int width, int height, int textureX, int textureY) {
        AbstractGui.blit(stack, x, y, z, u, v, width, height, textureX, textureY);
    }

    @SubscribeEvent()
    @OnlyIn(Dist.CLIENT)
    public void onRenderBloodBarEvent(RenderGameOverlayEvent.Post event) {
        if(mc.getCurrentServer() == null && !mc.hasSingleplayerServer()) return;
        if(!ModList.get().isLoaded("classicbar")) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.FOOD) {
                if (mc.getCameraEntity() instanceof LivingEntity) {
                    LivingEntity viewEntity = (LivingEntity) mc.getCameraEntity();
                    if(!(viewEntity instanceof PlayerEntity)) return;
                    if (hasFullSPSet((PlayerEntity) viewEntity)) {

                        int storedBlood = getBloodAmount((PlayerEntity) viewEntity, true);

                        if (colors.isEmpty()) colors.add(0xffffff);
                        calculateIndex(storedBlood);
                        int layer = (int) Math.ceil(storedBlood / 20d) - 1;
                        int color = getColor(layer);

                        RenderSystem.enableBlend();
                        RenderSystem.pushMatrix();

                        int top = mc.getWindow().getGuiScaledHeight() - ForgeIngameGui.right_height;
                        int right = mc.getWindow().getGuiScaledWidth() / 2 + 82;
                        mc.getTextureManager().bind(TEXTURE);
                        for (int i = 0; i < 10; i++) {
                            Index index = indexes[i];
                            MatrixStack stack = event.getMatrixStack();

                            if (layer >= 0)
                                switch (index) {
                                    case empty:
                                        drawEmptyIcon(stack, color, i, right, top);
                                        break;
                                    case half:
                                        drawHalfIcon(stack, color, i, right, top);
                                        break;
                                    case full:
                                        drawFullIcon(stack, color, i, right, top);
                                        break;
                                }
                        }
                        ForgeIngameGui.right_height += 10;
                        RenderSystem.popMatrix();
                        mc.getTextureManager().bind(GUI_ICONS_LOCATION);
                        RenderSystem.disableBlend();
                    }
                }
            }
        }
    }

    void calculateIndex(int storedBlood) {
        indexes = new Index[]{Index.empty, Index.empty, Index.empty, Index.empty, Index.empty, Index.empty, Index.empty, Index.empty, Index.empty, Index.empty};
        int modulo = storedBlood % 20;
        if (modulo == 0) {
            indexes = new Index[]{Index.full, Index.full, Index.full, Index.full, Index.full, Index.full, Index.full, Index.full, Index.full, Index.full};
            return;
        }
        int fullicons = modulo / 2;
        boolean halficon = storedBlood % 2 == 1;
        for (int i = 0; i < fullicons; i++) {
            indexes[i] = Index.full;
        }
        if (halficon) {
            indexes[fullicons] = Index.half;
        }
    }

    private int getColor(int index) {
        return index < 0 ? 0xffffff : index >= colors.size() ? colors.get(colors.size() - 1) : colors.get(index);
    }

    private void drawEmptyIcon(MatrixStack stack, int color, int i, int guiLeft, int guiTop) {
        RenderSystem.color3f((color >> 16 & 0xff) / 256f, (color >> 8 & 0xff) / 256f, (color & 0xff) / 256f);
        blit(stack, guiLeft - i * 8, guiTop, 18, 27, 9, 9);
    }

    private void drawFullIcon(MatrixStack stack, int color, int i, int guiLeft, int guiTop) {
        RenderSystem.color3f((color >> 16 & 0xff) / 256f, (color >> 8 & 0xff) / 256f, (color & 0xff) / 256f);
        blit(stack, guiLeft - i * 8, guiTop, 9, 18, 9, 9);
    }

    private void drawHalfIcon(MatrixStack stack, int color, int i, int guiLeft, int guiTop) {
        RenderSystem.color3f((color >> 16 & 0xff) / 256f, (color >> 8 & 0xff) / 256f, (color & 0xff) / 256f);
        drawEmptyIcon(stack, color, i, guiLeft, guiTop);
        blit(stack, guiLeft - i * 8, guiTop, 0, 9, 9, 9);
    }

    enum Index {
        half, full, empty
    }
}