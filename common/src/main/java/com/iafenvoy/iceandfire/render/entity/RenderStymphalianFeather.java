package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.EntityStymphalianFeather;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class RenderStymphalianFeather extends ProjectileEntityRenderer<EntityStymphalianFeather> {
    private static final Identifier TEXTURE = Identifier.of(IceAndFire.MOD_ID, "textures/models/stymphalianbird/feather.png");

    public RenderStymphalianFeather(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(EntityStymphalianFeather entity) {
        return TEXTURE;
    }
}