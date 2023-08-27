package com.decursioteam.sanguinearsenal.core.init;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.decursioteam.sanguinearsenal.entities.FlyingScytheEntity;
import com.decursioteam.sanguinearsenal.entities.BloodProjectileEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SanguineArsenal.MOD_ID);

    public static final RegistryObject<EntityType<FlyingScytheEntity>> FLYING_SCYTHE = ENTITIES.register("flying_scythe", () -> addEntity("flying_scythe", 2.5F, 0.75F, 10, (e, w)-> new FlyingScytheEntity(w)));
    public static final RegistryObject<EntityType<BloodProjectileEntity>> BLOOD_PROJECTILE = ENTITIES.register("blood_projectile", () -> addEntity("blood_projectile", 0.4f, 0.4f, 64, BloodProjectileEntity::new));

    private static <T extends Entity> EntityType<T> addEntity(String name, float width, float height, int trackingRange, EntityType.EntityFactory<T> factory) {
        return EntityType.Builder.of(factory, MobCategory.MISC)
                .setTrackingRange(trackingRange)
                .setUpdateInterval(1)
                .sized(width, height)
                .build(SanguineArsenal.MOD_ID + ":" + name);

    }


}
