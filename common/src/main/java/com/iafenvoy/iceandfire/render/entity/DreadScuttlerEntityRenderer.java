package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.DreadScuttlerEntity;
import com.iafenvoy.iceandfire.render.entity.feature.GenericGlowingFeatureRenderer;
import com.iafenvoy.iceandfire.render.model.DreadScuttlerModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DreadScuttlerEntityRenderer extends MobEntityRenderer<DreadScuttlerEntity, DreadScuttlerModel> {
    public static final Identifier TEXTURE_EYES = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_scuttler_eyes.png");
    public static final Identifier TEXTURE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_scuttler.png");

    public DreadScuttlerEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DreadScuttlerModel(), 0.75F);
        this.addFeature(new GenericGlowingFeatureRenderer<>(this, TEXTURE_EYES));
    }

    @Override
    public void scale(DreadScuttlerEntity LivingEntityIn, MatrixStack stack, float partialTickTime) {
        stack.scale(LivingEntityIn.getSize(), LivingEntityIn.getSize(), LivingEntityIn.getSize());
    }

    @Override
    public Identifier getTexture(DreadScuttlerEntity beast) {
        return TEXTURE;
    }
}
