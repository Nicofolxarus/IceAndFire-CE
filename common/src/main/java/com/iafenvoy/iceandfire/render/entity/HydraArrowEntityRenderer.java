package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.HydraArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class HydraArrowEntityRenderer extends ProjectileEntityRenderer<HydraArrowEntity> {
    private static final Identifier TEXTURES = Identifier.of(IceAndFire.MOD_ID, "textures/entity/misc/hydra_arrow.png");

    public HydraArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(HydraArrowEntity entity) {
        return TEXTURES;
    }
}