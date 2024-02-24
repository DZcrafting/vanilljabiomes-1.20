package net.dazeful.vanilljabiomes.entity.client;

import net.dazeful.vanilljabiomes.entity.animations.ModAnimations;
import net.dazeful.vanilljabiomes.entity.custom.RoseBettaEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class RoseBettaModel<T extends RoseBettaEntity> extends SinglePartEntityModel<T> {
    private final ModelPart rosebetta;
    public RoseBettaModel(ModelPart root) {
        this.rosebetta = root.getChild("rosebetta");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData rosebetta = modelPartData.addChild("rosebetta", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData Body = rosebetta.addChild("Body", ModelPartBuilder.create().uv(0, 22).cuboid(-1.0F, -2.0F, -6.0F, 2.0F, 3.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

        ModelPartData Fin_L = Body.addChild("Fin_L", ModelPartBuilder.create().uv(11, 25).mirrored().cuboid(-2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-1.0F, 0.0F, -4.0F));

        ModelPartData Fin_R = Body.addChild("Fin_R", ModelPartBuilder.create().uv(11, 27).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 0.0F, -4.0F));

        ModelPartData Tail = Body.addChild("Tail", ModelPartBuilder.create().uv(18, 12).cuboid(0.0F, -8.0F, 0.0F, 0.0F, 14.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

        ModelPartData Fin_top = Body.addChild("Fin_top", ModelPartBuilder.create().uv(0, 14).cuboid(0.0F, -4.0F, -1.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, -2.0F));

        ModelPartData Fin_bottom = Body.addChild("Fin_bottom", ModelPartBuilder.create().uv(8, 12).cuboid(0.0F, 0.0F, -1.0F, 0.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, -3.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Override
    public void setAngles(RoseBettaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(ModAnimations.ROSEBETTA_SWIM, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.swimAnimationState, ModAnimations.ROSEBETTA_SWIM, ageInTicks, 1f);

    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        rosebetta.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return rosebetta;
    }
}