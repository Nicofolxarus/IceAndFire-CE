package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.EntitySeaSerpentArrow;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class RenderSeaSerpentArrow extends ProjectileEntityRenderer<EntitySeaSerpentArrow> {
    private static final Identifier TEXTURE = Identifier.of(IceAndFire.MOD_ID, "textures/models/misc/sea_serpent_arrow.png");

    public RenderSeaSerpentArrow(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(EntitySeaSerpentArrow entity) {
        return TEXTURE;
    }
}