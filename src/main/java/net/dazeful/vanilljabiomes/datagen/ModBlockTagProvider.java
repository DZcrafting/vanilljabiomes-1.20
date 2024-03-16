package net.dazeful.vanilljabiomes.datagen;

import net.dazeful.vanilljabiomes.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.RAW_ROSE_QUARTZ_BLOCK)
                .add(ModBlocks.POLISHED_ROSE_QUARTZ_BLOCK)
                .add(ModBlocks.ROSE_QUARTZ_BRICKS)
                .add(ModBlocks.RAW_CARMINE_ROSE_QUARTZ_BLOCK)
                .add(ModBlocks.POLISHED_CARMINE_ROSE_QUARTZ_BLOCK)
                .add(ModBlocks.CARMINE_ROSE_QUARTZ_BRICKS)
                .add(ModBlocks.RAW_PALE_ROSE_QUARTZ_BLOCK)
                .add(ModBlocks.POLISHED_PALE_ROSE_QUARTZ_BLOCK)
                .add(ModBlocks.PALE_ROSE_QUARTZ_BRICKS)
                .add(ModBlocks.ROSE_QUARTZ_SPIRE)
                .add(ModBlocks.CARMINE_ROSE_QUARTZ_SPIRE)
                .add(ModBlocks.PALE_ROSE_QUARTZ_SPIRE)
                .add(ModBlocks.TRAVERTINE)
                .add(ModBlocks.QUARTZITE)
                .add(ModBlocks.SCHIST)
                .add(ModBlocks.SMOOTH_SCHIST)
                .add(ModBlocks.POLISHED_SCHIST)
                .add(ModBlocks.SCHIST_BRICKS)
                .add(ModBlocks.SMALL_SCHIST_BRICKS)
                .add(ModBlocks.SULFUR_VENT)
                .add(ModBlocks.SULFUR_BLOCK);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.CINDER_SOIL);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL);

        getOrCreateTagBuilder(BlockTags.LOGS)
                .add(ModBlocks.CHARRED_LOG)
                .add(ModBlocks.STRIPPED_CHARRED_LOG)
                .add(ModBlocks.CHARRED_WOOD)
                .add(ModBlocks.STRIPPED_CHARRED_WOOD);


    }
}
