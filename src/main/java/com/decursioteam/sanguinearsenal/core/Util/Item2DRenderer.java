package com.decursioteam.sanguinearsenal.core.Util;


import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
/*
@OnlyIn(Dist.CLIENT)
public class Item2DRenderer {


    @SubscribeEvent
    public static void onModelBakeEvent(ModelEvent.BakingCompleted event) {


        Map<ResourceLocation, BakedModel> map = event.getModels();
        for (String item : HAND_MODEL_ITEMS) {
            ResourceLocation modelInventory = new ModelResourceLocation(new ResourceLocation(SanguineArsenal.MOD_ID, item), "inventory");
            ResourceLocation modelHand = new ModelResourceLocation(new ResourceLocation(SanguineArsenal.MOD_ID, item + "_in_hand"), "inventory");

            BakedModel bakedModelDefault = map.get(modelInventory);
            BakedModel bakedModelHand = map.get(modelHand);
            BakedModel modelWrapper = new BakedModel() {
                @Override
                public List<BakedQuad> getQuads(BlockState state, Direction side, RandomSource rand) {
                    return bakedModelDefault.getQuads(state, side, rand);
                }

                @Override
                public boolean useAmbientOcclusion() {
                    return bakedModelDefault.useAmbientOcclusion();
                }

                @Override
                public boolean isGui3d() {
                    return bakedModelDefault.isGui3d();
                }

                @Override
                public boolean usesBlockLight() {
                    return false;
                }

                @Override
                public boolean isCustomRenderer() {
                    return bakedModelDefault.isCustomRenderer();
                }

                @Override
                public TextureAtlasSprite getParticleIcon() {
                    return bakedModelDefault.getParticleIcon();
                }

                @Override
                public ItemOverrides getOverrides() {
                    return bakedModelDefault.getOverrides();
                }

//                @Override
//                public BakedModel handlePerspective(ItemTransforms.TransformType cameraTransformType, PoseStack mat) {
//                    BakedModel modelToUse = bakedModelDefault;
//                    if (cameraTransformType == ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND || cameraTransformType == ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND
//                            || cameraTransformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND || cameraTransformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND) {
//                        modelToUse = bakedModelHand;
//                    }
//                    return ForgeHooksClient.handlePerspective(modelToUse, cameraTransformType, mat);
//                }
            };
            map.put(modelInventory, modelWrapper);


        }


    }


    public static BakedModel getModel(ItemModelShaper itemModelShaper, ItemStack stack, @Nullable Level pLevel, @Nullable LivingEntity p_174267_, int p_174268_) {

        BakedModel bakedmodel = itemModelShaper.getModelManager().getModel(new ModelResourceLocation( ForgeRegistries.ITEMS.getKey(stack.getItem()) + "_in_hand#inventory"));

        ClientLevel clientlevel = pLevel instanceof ClientLevel ? (ClientLevel)pLevel : null;
        BakedModel bakedmodel1 = bakedmodel.getOverrides().resolve(bakedmodel, stack, clientlevel, p_174267_, p_174268_);
        return bakedmodel1 == null ? itemModelShaper.getModelManager().getMissingModel() : bakedmodel1;

    }

}
*/
