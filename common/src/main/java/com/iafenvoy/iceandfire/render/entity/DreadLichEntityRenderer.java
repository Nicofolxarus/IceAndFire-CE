package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.DreadLichEntity;
import com.iafenvoy.iceandfire.render.entity.layer.GenericGlowingFeatureRenderer;
import com.iafenvoy.iceandfire.render.model.DreadLichModel;
import com.iafenvoy.uranus.client.model.util.HideableLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DreadLichEntityRenderer extends MobEntityRenderer<DreadLichEntity, DreadLichModel> {
    public static final Identifier TEXTURE_EYES = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_lich_eyes.png");
    public static final Identifier TEXTURE_0 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_lich_0.png");
    public static final Identifier TEXTURE_1 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_lich_1.png");
    public static final Identifier TEXTURE_2 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_lich_2.png");
    public static final Identifier TEXTURE_3 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_lich_3.png");
    public static final Identifier TEXTURE_4 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_lich_4.png");
    public final HideableLayer<DreadLichEntity, DreadLichModel, HeldItemFeatureRenderer<DreadLichEntity, DreadLichModel>> itemLayer;

    public DreadLichEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DreadLichModel(0.0F), 0.6F);
        this.addFeature(new GenericGlowingFeatureRenderer<>(this, TEXTURE_EYES));
        this.itemLayer = new HideableLayer<>(new HeldItemFeatureRenderer<>(this, context.getHeldItemRenderer()), this);
        this.addFeature(this.itemLayer);
    }

    @Override
    protected void scale(DreadLichEntity entity, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(0.95F, 0.95F, 0.95F);
        if (entity.getAnimation() == this.getModel().getSpawnAnimation()) {
            this.itemLayer.hidden = entity.getAnimationTick() <= this.getModel().getSpawnAnimation().getDuration() - 10;
            return;
        }
        this.itemLayer.hidden = false;
    }

    @Override
    public Identifier getTexture(DreadLichEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> TEXTURE_1;
            case 2 -> TEXTURE_2;
            case 3 -> TEXTURE_3;
            case 4 -> TEXTURE_4;
            default -> TEXTURE_0;
        };
    }
}
