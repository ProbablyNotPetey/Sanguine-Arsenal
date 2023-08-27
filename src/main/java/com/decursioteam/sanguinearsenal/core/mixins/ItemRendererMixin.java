package com.decursioteam.sanguinearsenal.core.mixins;


import com.decursioteam.sanguinearsenal.core.Util.Item2DRenderer;
import com.decursioteam.sanguinearsenal.core.Util.Item3D;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    @Shadow @Final
    private ItemModelShaper itemModelShaper;


    @ModifyVariable(method = "render", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public BakedModel sp_renderMixinModel(BakedModel pModel, ItemStack pItemStack, ItemTransforms.TransformType pTransformType, boolean pLeftHand, PoseStack pPoseStack, MultiBufferSource pBuffer, int pCombinedLight, int pCombinedOverlay) {
        if(!pItemStack.isEmpty()) {
            boolean flag = pTransformType == ItemTransforms.TransformType.GUI || pTransformType == ItemTransforms.TransformType.GROUND || pTransformType == ItemTransforms.TransformType.FIXED;
            if (flag) {
                if (pItemStack.getItem() instanceof Item3D) {

                    return this.itemModelShaper.getModelManager().getModel(new ModelResourceLocation(ForgeRegistries.ITEMS.getKey(pItemStack.getItem()) + "#inventory"));
                }
            }
        }
        return pModel;
    }


    //todo: use ModifyVariable instead?
    @Inject(method = "getModel", at = @At("HEAD"), cancellable = true)
    public void sp_getModelMixin(ItemStack stack, @Nullable Level pLevel, @Nullable LivingEntity p_174267_, int p_174268_, CallbackInfoReturnable<BakedModel> cir) {
        if(stack.getItem() instanceof Item3D) {
            BakedModel bM = itemModelShaper.getModelManager().getModel(new ModelResourceLocation( ForgeRegistries.ITEMS.getKey(stack.getItem()) + "_in_hand#inventory"));

            ClientLevel clientlevel = pLevel instanceof ClientLevel ? (ClientLevel)pLevel : null;
            BakedModel bm1 = bM.getOverrides().resolve(bM, stack, clientlevel, p_174267_, p_174268_);
            cir.setReturnValue(bm1 == null ? itemModelShaper.getModelManager().getMissingModel() : bm1);

        }
    }

}
