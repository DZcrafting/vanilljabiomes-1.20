package net.dazeful.vanilljabiomes.block;

import net.dazeful.vanilljabiomes.Vanilljabiomes;
import net.dazeful.vanilljabiomes.world.tree.CharredSaplingGenerator;
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



    //Charred Wood Blocks
    public static final Block CHARRED_LOG = registerBlock("charred_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.MANGROVE_LOG).strength(2f).mapColor(MapColor.BLACK).sounds(BlockSoundGroup.CHERRY_WOOD)));
    public static final Block CHARRED_WOOD = registerBlock("charred_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.MANGROVE_WOOD).strength(2f).mapColor(MapColor.BLACK).sounds(BlockSoundGroup.CHERRY_WOOD)));
    public static final Block STRIPPED_CHARRED_LOG = registerBlock("stripped_charred_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_MANGROVE_LOG).strength(2f).mapColor(MapColor.BLACK).sounds(BlockSoundGroup.CHERRY_WOOD)));
    public static final Block STRIPPED_CHARRED_WOOD = registerBlock("stripped_charred_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_MANGROVE_WOOD).strength(2f).mapColor(MapColor.BLACK).sounds(BlockSoundGroup.CHERRY_WOOD)));

    public static final Block CHARRED_PLANKS = registerBlock("charred_planks",
            new Block(FabricBlockSettings.copyOf(Blocks.MANGROVE_PLANKS).strength(2f).mapColor(MapColor.BLACK).sounds(BlockSoundGroup.CHERRY_WOOD)));
    public static final Block CHARRED_LEAVES = registerBlock("charred_leaves",
            new LeavesBlock(FabricBlockSettings.copyOf(Blocks.MANGROVE_LEAVES).strength(0.5f).mapColor(MapColor.BLACK).sounds(BlockSoundGroup.CHERRY_LEAVES).luminance(state -> 9)));
    public static final Block CHARRED_SAPLING = registerBlock("charred_sapling",
            new SaplingBlock(new CharredSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.CHERRY_SAPLING).strength(1f).mapColor(MapColor.BLACK).sounds(BlockSoundGroup.CHERRY_SAPLING)));






    //New Enviorment Blocks
    public static final Block TRAVERTINE = registerBlock("travertine",
            new Block(FabricBlockSettings.create().strength(1.0f, 4).sounds(BlockSoundGroup.STONE).requiresTool().mapColor(MapColor.PALE_YELLOW)));
    public static final Block QUARTZITE = registerBlock("quartzite",
            new Block(FabricBlockSettings.create().strength(2.0f, 4).sounds(BlockSoundGroup.STONE).requiresTool().mapColor(MapColor.DULL_RED)));
    public static final Block CINDER_SOIL = registerBlock("cinder_soil",
            new Block(FabricBlockSettings.copyOf(Blocks.COARSE_DIRT).mapColor(MapColor.BLACK)));
    public static final Block SCHIST = registerBlock("schist",
            new Block(FabricBlockSettings.create().strength(2.5f, 5).sounds(BlockSoundGroup.BASALT).requiresTool().mapColor(MapColor.DEEPSLATE_GRAY)));
    public static final Block SMOOTH_SCHIST = registerBlock("smooth_schist",
            new Block(FabricBlockSettings.copyOf(ModBlocks.SCHIST)));
    public static final Block POLISHED_SCHIST = registerBlock("polished_schist",
            new Block(FabricBlockSettings.copyOf(ModBlocks.SCHIST)));
    public static final Block SCHIST_BRICKS = registerBlock("schist_bricks",
            new Block(FabricBlockSettings.copyOf(ModBlocks.SCHIST)));
    public static final Block SMALL_SCHIST_BRICKS = registerBlock("small_schist_bricks",
            new Block(FabricBlockSettings.copyOf(ModBlocks.SCHIST)));
    public static final Block SULFUR_BLOCK = registerBlock("sulfur_block",
            new Block(FabricBlockSettings.create().strength(0.5f).sounds(BlockSoundGroup.AMETHYST_BLOCK).mapColor(MapColor.YELLOW).luminance(state -> 5)));
    public static final Block SULFUR_VENT = registerBlock("sulfur_vent",
            new SulfurVentBlock((AbstractBlock.Settings.create().mapColor(MapColor.DEEPSLATE_GRAY).solid().instrument(Instrument.BASEDRUM)
                    .nonOpaque().sounds(BlockSoundGroup.BASALT).ticksRandomly().strength(2.5f, 5).dynamicBounds().offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never))));









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
