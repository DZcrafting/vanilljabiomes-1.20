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
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block RAW_ROSE_QUARTZ_BLOCK = registerBlock("raw_rose_quartz_block",
            new Block(FabricBlockSettings.create().strength(1.5f).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().mapColor(DyeColor.PINK)));
    public static final Block POLISHED_ROSE_QUARTZ_BLOCK = registerBlock("polished_rose_quartz_block",
            new Block(FabricBlockSettings.create().strength(1.5f).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().mapColor(DyeColor.PINK)));
    public static final Block ROSE_QUARTZ_SPIRE = registerBlock("rose_quartz_spire",
                new CrystalSpireBlock((AbstractBlock.Settings.create().mapColor(MapColor.PINK).solid().instrument(Instrument.BASEDRUM)
                        .nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK).ticksRandomly().strength(1.5f, 1.0f).dynamicBounds().offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never))));

    public static final Block RAW_CARMINE_ROSE_QUARTZ_BLOCK = registerBlock("raw_carmine_rose_quartz_block",
            new Block(FabricBlockSettings.create().strength(1.5f).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().mapColor(MapColor.DARK_RED)));
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
