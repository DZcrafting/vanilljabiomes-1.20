package net.dazeful.vanilljabiomes.datagen;

import net.dazeful.vanilljabiomes.block.ModBlocks;
import net.dazeful.vanilljabiomes.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.enums.Thickness;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class ModModelProvider extends FabricModelProvider {

    private static void registerCustomPointedDripstone(BlockStateModelGenerator blockStateModelGenerator, Block customPointedDripstone) {
        blockStateModelGenerator.excludeFromSimpleItemModelGeneration(customPointedDripstone);
        BlockStateVariantMap.DoubleProperty<Direction, Thickness> directionAndThickness = BlockStateVariantMap.create(Properties.VERTICAL_DIRECTION, Properties.THICKNESS);
        for (Thickness thickness : Thickness.values()) {
            directionAndThickness.register(Direction.UP, thickness, getCustomDripstoneVariant(blockStateModelGenerator, customPointedDripstone, Direction.UP, thickness));
        }
        for (Thickness thickness : Thickness.values()) {
            directionAndThickness.register(Direction.DOWN, thickness, getCustomDripstoneVariant(blockStateModelGenerator, customPointedDripstone, Direction.DOWN, thickness));
        }
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(customPointedDripstone).coordinate(directionAndThickness));
    }

    public static BlockStateVariant getCustomDripstoneVariant(BlockStateModelGenerator blockStateModelGenerator, Block customPointedDripstone, Direction direction, Thickness thickness) {
        String blockModelSuffix = "_" + direction + "_" + thickness;
        TextureMap textureMap = TextureMap.cross(ModelIds.getBlockSubModelId(customPointedDripstone, blockModelSuffix));
        return BlockStateVariant.create()
                .put(VariantSettings.MODEL, Models.POINTED_DRIPSTONE.upload(customPointedDripstone, blockModelSuffix, textureMap, blockStateModelGenerator.modelCollector));
    }
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_ROSE_QUARTZ_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_ROSE_QUARTZ_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_CARMINE_ROSE_QUARTZ_BLOCK);
        blockStateModelGenerator.registerSingleton(ModBlocks.TRAVERTINE, TexturedModel.CUBE_BOTTOM_TOP);
        registerCustomPointedDripstone(blockStateModelGenerator, ModBlocks.ROSE_QUARTZ_SPIRE);


    }




    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RAW_ROSE_QUARTZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_CARMINE_ROSE_QUARTZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.POLISHED_ROSE_QUARTZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.POLISHED_CARMINE_ROSE_QUARTZ, Models.GENERATED);
    }
}
