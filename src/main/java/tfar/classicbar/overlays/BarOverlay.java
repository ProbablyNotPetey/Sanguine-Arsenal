package tfar.classicbar.overlays;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.gui.ForgeIngameGui;

public interface BarOverlay {
    boolean shouldRender(PlayerEntity var1);

    boolean rightHandSide();

    BarOverlay setSide(boolean var1);

    void renderBar(MatrixStack var1, PlayerEntity var2, int var3, int var4);

    boolean shouldRenderText();

    void renderText(MatrixStack var1, PlayerEntity var2, int var3, int var4);

    void renderIcon(MatrixStack var1, PlayerEntity var2, int var3, int var4);

    default int getSidedOffset() {
        return this.rightHandSide() ? ForgeIngameGui.right_height : ForgeIngameGui.left_height;
    }

    String name();
}