package net.dazeful.vanilljabiomes.entity;

import net.dazeful.vanilljabiomes.Vanilljabiomes;
import net.dazeful.vanilljabiomes.entity.custom.RoseBettaEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<RoseBettaEntity> ROSEBETTA = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Vanilljabiomes.MOD_ID, "rose_betta"),
            FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, RoseBettaEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());

    public static void registerModEntities() {
        Vanilljabiomes.LOGGER.info("Registering Mod Entities for " + Vanilljabiomes.MOD_ID);
    }
}
