package net.dazeful.vanilljabiomes.datagen;

import net.dazeful.vanilljabiomes.block.ModBlocks;
import net.dazeful.vanilljabiomes.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_ROSE_QUARTZ_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_CARMINE_ROSE_QUARTZ_BLOCK);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RAW_ROSE_QUARTZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_CARMINE_ROSE_QUARTZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.POLISHED_ROSE_QUARTZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.POLISHED_CARMINE_ROSE_QUARTZ, Models.GENERATED);
    }
}
