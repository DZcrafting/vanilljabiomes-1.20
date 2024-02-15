package net.dazeful.vanilljabiomes;

import net.dazeful.vanilljabiomes.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class VanilljabiomesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient()
    { BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROSE_QUARTZ_SPIRE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CARMINE_ROSE_QUARTZ_SPIRE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PALE_ROSE_QUARTZ_SPIRE, RenderLayer.getCutout());





    }
}
