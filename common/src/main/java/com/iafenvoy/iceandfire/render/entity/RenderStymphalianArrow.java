package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.EntityStymphalianArrow;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class RenderStymphalianArrow extends ProjectileEntityRenderer<EntityStymphalianArrow> {
    private static final Identifier TEXTURE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/misc/stymphalian_arrow.png");

    public RenderStymphalianArrow(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(EntityStymphalianArrow entity) {
        return TEXTURE;
    }
}