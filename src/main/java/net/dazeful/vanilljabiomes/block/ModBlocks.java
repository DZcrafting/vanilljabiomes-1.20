package net.dazeful.vanilljabiomes.block;

import net.dazeful.vanilljabiomes.Vanilljabiomes;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    //Rose Quartz Blocks
    public static final Block RAW_ROSE_QUARTZ_BLOCK = registerBlock("raw_rose_quartz_block",
            new Block(FabricBlockSettings.create().strength(1.5f).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().mapColor(MapColor.DULL_PINK).luminance(state -> 3)));
    public static final Block POLISHED_ROSE_QUARTZ_BLOCK = registerBlock("polished_rose_quartz_block",
            new Block(FabricBlockSettings.create().strength(1.5f).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().mapColor(MapColor.DULL_PINK)));
    public static final Block POLISHED_ROSE_QUARTZ_STAIRS = registerBlock("polished_rose_quartz_stairs",
            new StairsBlock(ModBlocks.POLISHED_ROSE_QUARTZ_BLOCK.getDefaultState(), FabricBlockSettings.create().strength(1.5f).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().mapColor(MapColor.DULL_PINK)));
    public static final Block POLISHED_ROSE_QUARTZ_SLAB = registerBlock("polished_rose_quartz_slab",
            new SlabBlock(FabricBlockSettings.create().strength(1.5f).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().mapColor(MapColor.DULL_PINK)));
    public static final Block ROSE_QUARTZ_BRICKS = registerBlock("rose_quartz_bricks",
            new Block(FabricBlockSettings.create().strength(1.5f).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().mapColor(MapColor.DULL_PINK)));
    public static final Block ROSE_QUARTZ_SPIRE = registerBlock("rose_quartz_spire",
                new CrystalSpireBlock((AbstractBlock.Settings.create().mapColor(MapColor.DULL_PINK).solid().instrument(Instrument.BASEDRUM)
                        .nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK).ticksRandomly().strength(1.5f, 1.0f).dynamicBounds().offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never).luminance(state -> 6))));

    //Carmine Rose Quartz Blocks
    public static final Block RAW_CARMINE_ROSE_QUARTZ_BLOCK = registerBlock("raw_carmine_rose_quartz_block",
            new Block(FabricBlockSettings.copyOf(ModBlocks.RAW_ROSE_QUARTZ_BLOCK).mapColor(MapColor.RED).luminance(state -> 2)));
    public static final Block POLISHED_CARMINE_ROSE_QUARTZ_BLOCK = registerBlock("polished_carmine_rose_quartz_block",
            new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_ROSE_QUARTZ_BLOCK).mapColor(MapColor.RED)));
    public static final Block POLISHED_CARMINE_ROSE_QUARTZ_STAIRS = registerBlock("polished_carmine_rose_quartz_stairs",
            new StairsBlock(ModBlocks.POLISHED_CARMINE_ROSE_QUARTZ_BLOCK.getDefaultState(), FabricBlockSettings.copyOf(ModBlocks.POLISHED_ROSE_QUARTZ_STAIRS).mapColor(MapColor.RED)));
    public static final Block POLISHED_CARMINE_ROSE_QUARTZ_SLAB = registerBlock("polished_carmine_rose_quartz_slab",
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.POLISHED_ROSE_QUARTZ_SLAB).mapColor(MapColor.RED)));
    public static final Block CARMINE_ROSE_QUARTZ_BRICKS = registerBlock("carmine_rose_quartz_bricks",
            new Block(FabricBlockSettings.copyOf(ModBlocks.ROSE_QUARTZ_BRICKS).mapColor(MapColor.RED)));
    public static final Block CARMINE_ROSE_QUARTZ_SPIRE = registerBlock("carmine_rose_quartz_spire",
            new CrystalSpireBlock((AbstractBlock.Settings.copy(ModBlocks.ROSE_QUARTZ_SPIRE).mapColor(MapColor.RED).luminance(state -> 5))));

    //Pale Rose Quartz Blocks
    public static final Block RAW_PALE_ROSE_QUARTZ_BLOCK = registerBlock("raw_pale_rose_quartz_block",
            new Block(FabricBlockSettings.copyOf(ModBlocks.RAW_ROSE_QUARTZ_BLOCK).mapColor(MapColor.PINK).luminance(state -> 4)));
    public static final Block POLISHED_PALE_ROSE_QUARTZ_BLOCK = registerBlock("polished_pale_rose_quartz_block",
            new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_ROSE_QUARTZ_BLOCK).mapColor(MapColor.PINK)));
    public static final Block POLISHED_PALE_ROSE_QUARTZ_STAIRS = registerBlock("polished_pale_rose_quartz_stairs",
            new StairsBlock(ModBlocks.POLISHED_PALE_ROSE_QUARTZ_BLOCK.getDefaultState(), FabricBlockSettings.copyOf(ModBlocks.POLISHED_ROSE_QUARTZ_STAIRS).mapColor(MapColor.PINK)));
    public static final Block POLISHED_PALE_ROSE_QUARTZ_SLAB = registerBlock("polished_pale_rose_quartz_slab",
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.POLISHED_ROSE_QUARTZ_SLAB).mapColor(MapColor.PINK)));
    public static final Block PALE_ROSE_QUARTZ_BRICKS = registerBlock("pale_rose_quartz_bricks",
            new Block(FabricBlockSettings.copyOf(ModBlocks.ROSE_QUARTZ_BRICKS).mapColor(MapColor.PINK)));
    public static final Block PALE_ROSE_QUARTZ_SPIRE = registerBlock("pale_rose_quartz_spire",
            new CrystalSpireBlock((AbstractBlock.Settings.copy(ModBlocks.ROSE_QUARTZ_SPIRE).mapColor(MapColor.PINK).luminance(state -> 7))));

    //New Enviorment Blocks
    public static final Block TRAVERTINE = registerBlock("travertine",
            new Block(FabricBlockSettings.create().strength(1.0f, 4).sounds(BlockSoundGroup.STONE).requiresTool().mapColor(MapColor.PALE_YELLOW)));
    public static final Block QUARTZITE = registerBlock("quartzite",
            new Block(FabricBlockSettings.create().strength(3.0f, 6).sounds(BlockSoundGroup.STONE).requiresTool().mapColor(MapColor.DULL_RED)));








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
