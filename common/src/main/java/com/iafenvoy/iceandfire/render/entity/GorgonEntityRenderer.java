package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.GorgonEntity;
import com.iafenvoy.iceandfire.render.entity.feature.GorgonEyesFeatureRenderer;
import com.iafenvoy.iceandfire.render.model.GorgonModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GorgonEntityRenderer extends MobEntityRenderer<GorgonEntity, GorgonModel> {
    public static final Identifier PASSIVE_TEXTURE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/gorgon/gorgon_passive.png");
    public static final Identifier AGRESSIVE_TEXTURE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/gorgon/gorgon_active.png");
    public static final Identifier DEAD_TEXTURE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/gorgon/gorgon_decapitated.png");

    public GorgonEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GorgonModel(), 0.4F);
        this.features.add(new GorgonEyesFeatureRenderer(this));
    }

    @Override
    public void scale(GorgonEntity LivingEntityIn, MatrixStack stack, float partialTickTime) {
        stack.scale(0.85F, 0.85F, 0.85F);
    }

    @Override
    public Identifier getTexture(GorgonEntity gorgon) {
        if (gorgon.getAnimation() == GorgonEntity.ANIMATION_SCARE) return AGRESSIVE_TEXTURE;
        else if (gorgon.deathTime > 0) return DEAD_TEXTURE;
        else return PASSIVE_TEXTURE;
    }
}
