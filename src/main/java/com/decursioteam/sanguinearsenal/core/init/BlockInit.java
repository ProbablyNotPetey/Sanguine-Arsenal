package com.decursioteam.sanguinearsenal.core.init;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            SanguineArsenal.MOD_ID);

    public static final RegistryObject<Block> SHADOW_INFUSED_BLOCK = BLOCKS.register("shadow_block",
            () -> new Block(Block.Properties.of(Material.METAL, MaterialColor.COLOR_RED).strength(5f, 6f)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)));
}
