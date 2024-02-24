package net.dazeful.vanilljabiomes.util;

import net.dazeful.vanilljabiomes.block.ModBlocks;
import net.dazeful.vanilljabiomes.entity.ModEntities;
import net.dazeful.vanilljabiomes.entity.custom.RoseBettaEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class ModRegistries {
    public static void registerModStuffs() {
        registerStrippables();
        registerAttributes();

    }

    private static void registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.CHARRED_LOG, ModBlocks.STRIPPED_CHARRED_LOG);
        StrippableBlockRegistry.register(ModBlocks.CHARRED_WOOD, ModBlocks.STRIPPED_CHARRED_WOOD);
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.ROSEBETTA, RoseBettaEntity.createRoseBettaAttributes());
    }

}
