package net.dazeful.vanilljabiomes.entity.client;

import net.dazeful.vanilljabiomes.Vanilljabiomes;
import net.dazeful.vanilljabiomes.entity.custom.RoseBettaEntity;
import net.dazeful.vanilljabiomes.entity.layer.ModModelLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class RoseBettaRenderer extends MobEntityRenderer<RoseBettaEntity, RoseBettaModel<RoseBettaEntity>> {
    private static final Identifier TEXTURE = new Identifier(Vanilljabiomes.MOD_ID, "textures/entity/rose_betta.png");
    public RoseBettaRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new RoseBettaModel<>(ctx.getPart(ModModelLayers.ROSE_BETTA)), 0.2f);
    }

    @Override
    public Identifier getTexture(RoseBettaEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(RoseBettaEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if (livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }


        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
