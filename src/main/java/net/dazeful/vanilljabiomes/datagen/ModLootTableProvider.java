package net.dazeful.vanilljabiomes.datagen;

import net.dazeful.vanilljabiomes.block.ModBlocks;
import net.dazeful.vanilljabiomes.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.RAW_ROSE_QUARTZ_BLOCK);
        addDrop(ModBlocks.POLISHED_ROSE_QUARTZ_BLOCK);
        addDrop(ModBlocks.RAW_CARMINE_ROSE_QUARTZ_BLOCK);
        addDrop(ModBlocks.TRAVERTINE);
        addDrop(ModBlocks.ROSE_QUARTZ_SPIRE);

    }
}
