package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.entity.EntityMyrmexBase;
import com.iafenvoy.iceandfire.render.entity.layer.LayerMyrmexItem;
import com.iafenvoy.iceandfire.render.model.ModelMyrmexPupa;
import com.iafenvoy.uranus.client.model.AdvancedEntityModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;


public class RenderMyrmexBase<T extends EntityMyrmexBase> extends MobEntityRenderer<T, AdvancedEntityModel<T>> {
    private final AdvancedEntityModel<T> larvaModel = new ModelMyrmexPupa<>();
    private final AdvancedEntityModel<T> pupaModel = new ModelMyrmexPupa<>();
    private final AdvancedEntityModel<T> adultModel;

    public RenderMyrmexBase(EntityRendererFactory.Context context, AdvancedEntityModel<T> model, float shadowSize) {
        super(context, model, shadowSize);
        this.addFeature(new LayerMyrmexItem<>(this));
        this.adultModel = model;
    }

    @Override
    public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn) {
        switch (entityIn.getGrowthStage()) {
            case 0 -> this.model = this.larvaModel;
            case 1 -> this.model = this.pupaModel;
            default -> this.model = this.adultModel;
        }
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    protected void scale(EntityMyrmexBase myrmex, MatrixStack matrixStackIn, float partialTickTime) {
        float scale = myrmex.getModelScale();
        if (myrmex.getGrowthStage() == 0)
            scale /= 2;
        if (myrmex.getGrowthStage() == 1)
            scale /= 1.5F;
        matrixStackIn.scale(scale, scale, scale);
        if (myrmex.hasVehicle() && myrmex.getGrowthStage() < 2)
            matrixStackIn.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));
    }

    @Override
    public Identifier getTexture(EntityMyrmexBase myrmex) {
        return myrmex.getTexture();
    }
}
