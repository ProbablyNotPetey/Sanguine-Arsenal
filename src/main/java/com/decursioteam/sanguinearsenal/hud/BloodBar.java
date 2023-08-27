package com.decursioteam.sanguinearsenal.hud;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import java.util.ArrayList;
import java.util.List;

import static com.decursioteam.sanguinearsenal.core.Util.BloodUtil.getBloodAmount;
import static com.decursioteam.sanguinearsenal.core.Util.LivingUtil.hasFullSPSet;
import static net.minecraft.client.gui.Gui.GUI_ICONS_LOCATION;

public class BloodBar {
    private static final Minecraft mc = Minecraft.getInstance();
    static Index[] indexes;
    private static final ResourceLocation TEXTURE = new ResourceLocation(SanguineArsenal.MOD_ID, "textures/gui/blood_bar.png");


    public static void blit(PoseStack stack, int x, int y, float u, float v, int width, int height) {
        blit(stack, x, y, u, v, width, height, 27, 9);
    }

    public static void blit(PoseStack stack, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
        GuiComponent.blit(stack, x, y, u, v, width, height, textureWidth, textureHeight);
    }

    public static final IGuiOverlay BLOOD_BAR = (new IGuiOverlay() {

        private final List<Integer> colors = new ArrayList<>();
        @Override
        public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
//        int x = screenWidth / 2;
//        int y = screenHeight;

            if (mc.getCurrentServer() == null && !mc.hasSingleplayerServer()) return;
            if (!ModList.get().isLoaded("classicbar")) {

                if (mc.getCameraEntity() instanceof LivingEntity) {
                    LivingEntity viewEntity = (LivingEntity) mc.getCameraEntity();
                    if (!(viewEntity instanceof Player)) return;
                    if (hasFullSPSet((Player) viewEntity)) {

                        int storedBlood = getBloodAmount((Player) viewEntity, true);

                        if (colors.isEmpty()) colors.add(0xffffff);
                        calculateIndex(storedBlood);
                        int layer = (int) Math.ceil(storedBlood / 20d) - 1;
                        int color = getColor(layer);

                        RenderSystem.enableBlend();
                        poseStack.pushPose();

//                        int top = mc.getWindow().getGuiScaledHeight() - 39;
                        int top = screenHeight - 49;

//                        int right = mc.getWindow().getGuiScaledWidth() / 2 + 82;
                        int right = screenWidth / 2 + 82;

                        RenderSystem.setShader(GameRenderer::getPositionTexShader);
                        RenderSystem.setShaderTexture(0, TEXTURE);
                        for (int i = 0; i < 10; i++) {
                            Index index = indexes[i];
                            if (layer >= 0)
                                switch (index) {
                                    case empty:
                                        drawEmptyIcon(poseStack, color, i, right, top);
                                        break;
                                    case half:
                                        drawHalfIcon(poseStack, color, i, right, top);
                                        break;
                                    case full:
                                        drawFullIcon(poseStack, color, i, right, top);
                                        break;
                                }
                        }
//                        ForgeIngameGui.right_height += 10;
                        poseStack.popPose();
//                        mc.getTextureManager().bindForSetup(GUI_ICONS_LOCATION);
                        RenderSystem.disableBlend();
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

        private void drawEmptyIcon(PoseStack stack, int color, int i, int guiLeft, int guiTop) {
            RenderSystem.setShaderColor((color >> 16 & 0xff) / 256f, (color >> 8 & 0xff) / 256f, (color & 0xff) / 256f, 1.0f);
            blit(stack, guiLeft - i * 8, guiTop, 18, 0, 9, 9);
        }

        private void drawFullIcon(PoseStack stack, int color, int i, int guiLeft, int guiTop) {
            RenderSystem.setShaderColor((color >> 16 & 0xff) / 256f, (color >> 8 & 0xff) / 256f, (color & 0xff) / 256f, 1.0f);
            blit(stack, guiLeft - i * 8, guiTop, 9, 0, 9, 9);
        }

        private void drawHalfIcon(PoseStack stack, int color, int i, int guiLeft, int guiTop) {
            RenderSystem.setShaderColor((color >> 16 & 0xff) / 256f, (color >> 8 & 0xff) / 256f, (color & 0xff) / 256f, 1.0f);
            drawEmptyIcon(stack, color, i, guiLeft, guiTop);
            blit(stack, guiLeft - i * 8, guiTop, 0, 0, 9, 9);
        }

    });






//    @SubscribeEvent()
//    @OnlyIn(Dist.CLIENT)
//    public void onRenderBloodBarEvent(RenderGuiOverlayEvent.Post event) {
//        if(mc.getCurrentServer() == null && !mc.hasSingleplayerServer()) return;
//        if(!ModList.get().isLoaded("classicbar")) {
//            PoseStack poseStack = event.getPoseStack();
//
//            if (event.getType() == RenderGuiOverlayEvent.ElementType.FOOD) {
//                if (mc.getCameraEntity() instanceof LivingEntity) {
//                    LivingEntity viewEntity = (LivingEntity) mc.getCameraEntity();
//                    if(!(viewEntity instanceof Player) ) return;
//                    if (hasFullSPSet((Player) viewEntity)) {
//
//                        int storedBlood = getBloodAmount((Player) viewEntity, true);
//
//                        if (colors.isEmpty()) colors.add(0xffffff);
//                        calculateIndex(storedBlood);
//                        int layer = (int) Math.ceil(storedBlood / 20d) - 1;
//                        int color = getColor(layer);
//
//                        RenderSystem.enableBlend();
//                        poseStack.pushPose();
//
//                        int top = mc.getWindow().getGuiScaledHeight() - ForgeIngameGui.right_height;
//                        int right = mc.getWindow().getGuiScaledWidth() / 2 + 82;
//                        mc.getTextureManager().bind(TEXTURE);
//                        for (int i = 0; i < 10; i++) {
//                            Index index = indexes[i];
//                            PoseStack stack = event.getPoseStack();
//
//                            if (layer >= 0)
//                                switch (index) {
//                                    case empty:
//                                        drawEmptyIcon(stack, color, i, right, top);
//                                        break;
//                                    case half:
//                                        drawHalfIcon(stack, color, i, right, top);
//                                        break;
//                                    case full:
//                                        drawFullIcon(stack, color, i, right, top);
//                                        break;
//                                }
//                        }
//                        ForgeIngameGui.right_height += 10;
//                        poseStack.popPose();
//                        mc.getTextureManager().bind(GUI_ICONS_LOCATION);
//                        RenderSystem.disableBlend();
//                    }
//                }
//            }
//        }
//    }



    enum Index {
        half, full, empty
    }
}