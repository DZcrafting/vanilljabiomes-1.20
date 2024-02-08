package net.dazeful.vanilljabiomes.datagen;

import net.dazeful.vanilljabiomes.block.ModBlocks;
import net.dazeful.vanilljabiomes.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> SMELTRESULT_POLISHEDROSEQUARTZ = List.of(ModItems.RAW_ROSE_QUARTZ);
    private static final List<ItemConvertible> SMELTRESULT_POLISHEDCARMINEROSEQUARTZ = List.of(ModItems.RAW_CARMINE_ROSE_QUARTZ);
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_ROSE_QUARTZ_BLOCK, ModItems.RAW_ROSE_QUARTZ);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_ROSE_QUARTZ_BLOCK, ModItems.POLISHED_ROSE_QUARTZ);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_CARMINE_ROSE_QUARTZ_BLOCK, ModItems.RAW_CARMINE_ROSE_QUARTZ);

        offerSmelting(exporter, SMELTRESULT_POLISHEDROSEQUARTZ, RecipeCategory.MISC, ModItems.POLISHED_ROSE_QUARTZ,
                0.7f, 200, "polishedrosequartz_result");
        offerSmelting(exporter, SMELTRESULT_POLISHEDCARMINEROSEQUARTZ, RecipeCategory.MISC, ModItems.POLISHED_CARMINE_ROSE_QUARTZ,
                0.7f, 200, "polishedrosequartz_result");

    }
}
