package net.dazeful.vanilljabiomes.util;

import net.dazeful.vanilljabiomes.block.ModBlocks;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class ModRegistries {
    public static void registerModStuffs() {
        registerStrippables();

    }

    private static void registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.CHARRED_LOG, ModBlocks.STRIPPED_CHARRED_LOG);
        StrippableBlockRegistry.register(ModBlocks.CHARRED_WOOD, ModBlocks.STRIPPED_CHARRED_WOOD);
    }

}
