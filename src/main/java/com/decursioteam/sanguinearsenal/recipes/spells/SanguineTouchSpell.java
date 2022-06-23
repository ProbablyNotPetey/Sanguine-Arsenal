package com.decursioteam.sanguinearsenal.recipes.spells;

import com.decursioteam.sanguinearsenal.core.init.ItemInit;
import elucent.eidolon.Registry;
import elucent.eidolon.capability.ReputationProvider;
import elucent.eidolon.deity.Deities;
import elucent.eidolon.network.MagicBurstEffectPacket;
import elucent.eidolon.network.Networking;
import elucent.eidolon.spell.Sign;
import elucent.eidolon.spell.Signs;
import elucent.eidolon.spell.StaticSpell;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;
public class SanguineTouchSpell extends StaticSpell {

    public SanguineTouchSpell(ResourceLocation name, Sign... signs) {
        super(name, signs);
    }

    public static Vector3d getVector(World world, PlayerEntity player) {
        RayTraceResult ray = world.clip(new RayTraceContext(player.getEyePosition(0), player.getEyePosition(0).add(player.getLookAngle().scale(4)), RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, player));
        return (ray.getType() == RayTraceResult.Type.BLOCK) ? ray.getLocation() : player.getEyePosition(0).add(player.getLookAngle().scale(4));
    }

    @Override
    public boolean canCast(World world, BlockPos pos, PlayerEntity player) {
        if (!world.getCapability(ReputationProvider.CAPABILITY).isPresent()) return false;
        if (world.getCapability(ReputationProvider.CAPABILITY).resolve().get().getReputation(player, Deities.DARK_DEITY.getId()) < 2.0)
            return false;

        Vector3d v = getVector(world, player);
        List<ItemEntity> items = world.getEntitiesOfClass(ItemEntity.class, new AxisAlignedBB(v.x - 1.5, v.y - 1.5, v.z - 1.5, v.x + 1.5, v.y + 1.5, v.z + 1.5));
        if (items.size() != 1) return false;
        ItemStack stack = items.get(0).getItem();
        return canTouch(stack);
    }

    @Override
    public void cast(World world, BlockPos pos, PlayerEntity player) {

        Vector3d v = getVector(world, player);
        List<ItemEntity> items = world.getEntitiesOfClass(ItemEntity.class, new AxisAlignedBB(v.x - 1.5, v.y - 1.5, v.z - 1.5, v.x + 1.5, v.y + 1.5, v.z + 1.5));
        if (items.size() == 1) {
            if (!world.isClientSide) {
                ItemStack stack = items.get(0).getItem();
                if (canTouch(stack)) {
                    items.get(0).setItem(touchResult(stack));
                    Vector3d p = items.get(0).position();
                    items.get(0).setDefaultPickUpDelay();
                    Networking.sendToTracking(world, items.get(0).blockPosition(), new MagicBurstEffectPacket(p.x, p.y, p.z, Signs.SOUL_SIGN.getColor(), Signs.MIND_SIGN.getColor()));
                }
            } else {
                world.playSound(player, player.blockPosition(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundCategory.NEUTRAL, 1.0F, 0.6F + world.random.nextFloat() * 0.2F);
            }
        }
    }

    boolean canTouch(ItemStack stack) {
        Item stackItem = stack.getItem();
        return stackItem == Registry.SHADOW_GEM.get();
    }

    ItemStack touchResult(ItemStack stack) { // assumes canTouch is true

        if (stack.getItem() == Registry.SHADOW_GEM.get())
            return new ItemStack(ItemInit.SIGIL_OF_THE_DARK_LORD.get());

        return stack;
    }
}
