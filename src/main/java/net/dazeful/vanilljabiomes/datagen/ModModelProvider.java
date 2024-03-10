package net.dazeful.vanilljabiomes.datagen;

import net.dazeful.vanilljabiomes.block.ModBlocks;
import net.dazeful.vanilljabiomes.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.enums.Thickness;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;

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
        BlockStateModelGenerator.BlockTexturePool polished_rosequartz_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_ROSE_QUARTZ_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ROSE_QUARTZ_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_CARMINE_ROSE_QUARTZ_BLOCK);
        BlockStateModelGenerator.BlockTexturePool polished_carminerosequartz_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_CARMINE_ROSE_QUARTZ_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CARMINE_ROSE_QUARTZ_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_PALE_ROSE_QUARTZ_BLOCK);
        BlockStateModelGenerator.BlockTexturePool polished_palerosequartz_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_PALE_ROSE_QUARTZ_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PALE_ROSE_QUARTZ_BRICKS);
        registerCustomPointedDripstone(blockStateModelGenerator, ModBlocks.ROSE_QUARTZ_SPIRE);
        registerCustomPointedDripstone(blockStateModelGenerator, ModBlocks.CARMINE_ROSE_QUARTZ_SPIRE);
        registerCustomPointedDripstone(blockStateModelGenerator, ModBlocks.PALE_ROSE_QUARTZ_SPIRE);
        polished_rosequartz_pool.stairs(ModBlocks.POLISHED_ROSE_QUARTZ_STAIRS);
        polished_rosequartz_pool.slab(ModBlocks.POLISHED_ROSE_QUARTZ_SLAB);
        polished_carminerosequartz_pool.stairs(ModBlocks.POLISHED_CARMINE_ROSE_QUARTZ_STAIRS);
        polished_carminerosequartz_pool.slab(ModBlocks.POLISHED_CARMINE_ROSE_QUARTZ_SLAB);
        polished_palerosequartz_pool.stairs(ModBlocks.POLISHED_PALE_ROSE_QUARTZ_STAIRS);
        polished_palerosequartz_pool.slab(ModBlocks.POLISHED_PALE_ROSE_QUARTZ_SLAB);


        blockStateModelGenerator.registerSingleton(ModBlocks.TRAVERTINE, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.QUARTZITE);
        blockStateModelGenerator.registerSingleton(ModBlocks.SCHIST, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SMOOTH_SCHIST);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SULFUR_BLOCK);


        blockStateModelGenerator.registerLog(ModBlocks.CHARRED_LOG).log(ModBlocks.CHARRED_LOG).wood(ModBlocks.CHARRED_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_CHARRED_WOOD).log(ModBlocks.STRIPPED_CHARRED_LOG).wood(ModBlocks.STRIPPED_CHARRED_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHARRED_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHARRED_LEAVES);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.CHARRED_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);




    }




    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RAW_ROSE_QUARTZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_CARMINE_ROSE_QUARTZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PALE_ROSE_QUARTZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.POLISHED_ROSE_QUARTZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.POLISHED_CARMINE_ROSE_QUARTZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.POLISHED_PALE_ROSE_QUARTZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROSE_BETTA_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROSE_BETTA_SPAWN_EGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));
    }
}
