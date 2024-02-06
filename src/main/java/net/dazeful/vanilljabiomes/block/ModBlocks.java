package net.dazeful.vanilljabiomes.block;

import net.dazeful.vanilljabiomes.Vanilljabiomes;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block RAW_ROSE_QUARTZ_BLOCK = registerBlock("raw_rose_quartz_block",
            new Block(FabricBlockSettings.create().strength(1.5f).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool()));
    public static final Block RAW_CARMINE_ROSE_QUARTZ_BLOCK = registerBlock("raw_carmine_rose_quartz_block",
            new Block(FabricBlockSettings.create().strength(1.5f).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool()));







    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Vanilljabiomes.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Vanilljabiomes.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }


    public static void registerModBlocks() {
        Vanilljabiomes.LOGGER.info("Registering ModBlocks for " + Vanilljabiomes.MOD_ID);
    }
}
