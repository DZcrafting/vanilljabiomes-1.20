package net.dazeful.vanilljabiomes.datagen;

import net.dazeful.vanilljabiomes.block.ModBlocks;
import net.dazeful.vanilljabiomes.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_ROSE_QUARTZ_BLOCK, ModItems.RAW_ROSE_QUARTZ);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_CARMINE_ROSE_QUARTZ_BLOCK, ModItems.RAW_CARMINE_ROSE_QUARTZ);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_PALE_ROSE_QUARTZ_BLOCK, ModItems.RAW_PALE_ROSE_QUARTZ);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_ROSE_QUARTZ_BLOCK, ModItems.POLISHED_ROSE_QUARTZ);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_CARMINE_ROSE_QUARTZ_BLOCK, ModItems.POLISHED_CARMINE_ROSE_QUARTZ);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PALE_ROSE_QUARTZ_BLOCK, ModItems.POLISHED_PALE_ROSE_QUARTZ);
        createStairsRecipe(ModBlocks.POLISHED_ROSE_QUARTZ_STAIRS, Ingredient.ofItems(ModBlocks.POLISHED_ROSE_QUARTZ_BLOCK));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_ROSE_QUARTZ_SLAB, Ingredient.ofItems(ModBlocks.POLISHED_ROSE_QUARTZ_BLOCK));
        createStairsRecipe(ModBlocks.POLISHED_CARMINE_ROSE_QUARTZ_STAIRS, Ingredient.ofItems(ModBlocks.POLISHED_CARMINE_ROSE_QUARTZ_BLOCK));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_CARMINE_ROSE_QUARTZ_SLAB, Ingredient.ofItems(ModBlocks.POLISHED_CARMINE_ROSE_QUARTZ_BLOCK));
        createStairsRecipe(ModBlocks.POLISHED_PALE_ROSE_QUARTZ_STAIRS, Ingredient.ofItems(ModBlocks.POLISHED_PALE_ROSE_QUARTZ_BLOCK));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PALE_ROSE_QUARTZ_SLAB, Ingredient.ofItems(ModBlocks.POLISHED_PALE_ROSE_QUARTZ_BLOCK));

        offerSmelting(exporter, List.of(ModItems.RAW_ROSE_QUARTZ), RecipeCategory.MISC, ModItems.POLISHED_ROSE_QUARTZ, 0.7f, 200, "polishedrosequartz_result");
        offerSmelting(exporter, List.of(ModItems.RAW_CARMINE_ROSE_QUARTZ), RecipeCategory.MISC, ModItems.POLISHED_CARMINE_ROSE_QUARTZ, 0.7f, 200, "polishedrosequartz_result");
        offerSmelting(exporter, List.of(ModItems.RAW_PALE_ROSE_QUARTZ), RecipeCategory.MISC, ModItems.POLISHED_PALE_ROSE_QUARTZ, 0.7f, 200, "polishedrosequartz_result");

        offerBlasting(exporter, List.of(ModItems.RAW_ROSE_QUARTZ), RecipeCategory.MISC, ModItems.POLISHED_ROSE_QUARTZ, 0.7f, 100, "polishedrosequartz_result");
        offerBlasting(exporter, List.of(ModItems.RAW_CARMINE_ROSE_QUARTZ), RecipeCategory.MISC, ModItems.POLISHED_CARMINE_ROSE_QUARTZ, 0.7f, 100, "polishedrosequartz_result");
        offerBlasting(exporter, List.of(ModItems.RAW_PALE_ROSE_QUARTZ), RecipeCategory.MISC, ModItems.POLISHED_PALE_ROSE_QUARTZ, 0.7f, 100, "polishedrosequartz_result");

    }
}
