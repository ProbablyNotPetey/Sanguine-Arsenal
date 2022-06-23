package com.decursioteam.sanguinearsenal.core.init;

import com.decursioteam.sanguinearsenal.SanguineArsenal;
import com.decursioteam.sanguinearsenal.entities.FlyingScytheEntity;
import com.decursioteam.sanguinearsenal.items.scepterofblood.BloodProjectileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, SanguineArsenal.MOD_ID);

    public static final RegistryObject<EntityType<FlyingScytheEntity>> FLYING_SCYTHE = addEntity("flying_scythe", 2.5F, 0.75F, 10, (e, w)-> new FlyingScytheEntity(w));
    public static final RegistryObject<EntityType<BloodProjectileEntity>> BLOOD_PROJECTILE = addEntity("blood_projectile", 0.4f, 0.4f, 64, BloodProjectileEntity::new);

    static <T extends Entity> RegistryObject<EntityType<T>> addEntity(String name, float width, float height, int trackingRange, EntityType.IFactory<T> factory) {
        EntityType<T> type = EntityType.Builder.of(factory, EntityClassification.MISC)
                .setTrackingRange(trackingRange)
                .setUpdateInterval(1)
                .sized(width, height)
                .build(SanguineArsenal.MOD_ID + ":" + name);
        return ENTITIES.register(name, () -> type);
    }

    public static void init() {
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
