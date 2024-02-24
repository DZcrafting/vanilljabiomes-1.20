package net.dazeful.vanilljabiomes;

import net.dazeful.vanilljabiomes.block.ModBlocks;
import net.dazeful.vanilljabiomes.entity.ModEntities;
import net.dazeful.vanilljabiomes.entity.client.RoseBettaModel;
import net.dazeful.vanilljabiomes.entity.client.RoseBettaRenderer;
import net.dazeful.vanilljabiomes.entity.layer.ModModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class VanilljabiomesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient()
    { BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROSE_QUARTZ_SPIRE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CARMINE_ROSE_QUARTZ_SPIRE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PALE_ROSE_QUARTZ_SPIRE, RenderLayer.getCutout());

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ROSE_BETTA, RoseBettaModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.ROSEBETTA, RoseBettaRenderer::new);





    }
}
