package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.DreadHorseEntity;
import com.iafenvoy.iceandfire.render.entity.feature.GenericGlowingFeatureRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.util.Identifier;

public class DreadHorseEntityRenderer extends MobEntityRenderer<DreadHorseEntity, HorseEntityModel<DreadHorseEntity>> {
    public static final Identifier TEXTURE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_knight_horse.png");
    public static final Identifier TEXTURE_EYES = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_knight_horse_eyes.png");

    public DreadHorseEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new HorseEntityModel<>(context.getPart(EntityModelLayers.HORSE)), 0.75F);
        this.addFeature(new GenericGlowingFeatureRenderer<>(this, TEXTURE_EYES));
    }

    @Override
    public Identifier getTexture(DreadHorseEntity entity) {
        return TEXTURE;
    }
}
