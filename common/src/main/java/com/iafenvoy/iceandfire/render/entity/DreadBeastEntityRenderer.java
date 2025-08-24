package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.DreadBeastEntity;
import com.iafenvoy.iceandfire.render.entity.feature.GenericGlowingFeatureRenderer;
import com.iafenvoy.iceandfire.render.model.DreadBeastModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DreadBeastEntityRenderer extends MobEntityRenderer<DreadBeastEntity, DreadBeastModel> {
    public static final Identifier TEXTURE_EYES = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_beast_eyes.png");
    public static final Identifier TEXTURE_0 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_beast_1.png");
    public static final Identifier TEXTURE_1 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_beast_2.png");

    public DreadBeastEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DreadBeastModel(), 0.5F);
        this.addFeature(new GenericGlowingFeatureRenderer<>(this, TEXTURE_EYES));
    }

    @Override
    protected void scale(DreadBeastEntity entity, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(entity.getSize(), entity.getSize(), entity.getSize());
    }

    @Override
    public Identifier getTexture(DreadBeastEntity beast) {
        return beast.getVariant() == 1 ? TEXTURE_1 : TEXTURE_0;
    }
}
