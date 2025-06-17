package org.yolka.mechanical_botania.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.yolka.mechanical_botania.Mechanical_botania;
import org.yolka.mechanical_botania.blockentity.RotationalManaPoolBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Mechanical_botania.MODID);

    public static final RegistryObject<BlockEntityType<RotationalManaPoolBlockEntity>> ROTATIONAL_MANA_POOL =
            BLOCK_ENTITIES.register("rotational_mana_pool",
                    () -> BlockEntityType.Builder.of(RotationalManaPoolBlockEntity::new,
                            Mechanical_botania.ROTATIONAL_MANA_POOL.get()).build(null));
}
