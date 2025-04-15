package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.EntityAmphithereArrow;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class RenderAmphithereArrow extends ProjectileEntityRenderer<EntityAmphithereArrow> {
    private static final Identifier TEXTURE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/misc/amphithere_arrow.png");

    public RenderAmphithereArrow(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(EntityAmphithereArrow entity) {
        return TEXTURE;
    }
}