package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.StymphalianFeatherEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class StymphalianFeatherEntityRenderer extends ProjectileEntityRenderer<StymphalianFeatherEntity> {
    private static final Identifier TEXTURE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/stymphalianbird/feather.png");

    public StymphalianFeatherEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(StymphalianFeatherEntity entity) {
        return TEXTURE;
    }
}