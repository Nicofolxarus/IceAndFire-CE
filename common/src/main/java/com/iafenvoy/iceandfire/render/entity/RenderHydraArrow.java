package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.EntityHydraArrow;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class RenderHydraArrow extends ProjectileEntityRenderer<EntityHydraArrow> {
    private static final Identifier TEXTURES = Identifier.of(IceAndFire.MOD_ID, "textures/entity/misc/hydra_arrow.png");

    public RenderHydraArrow(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(EntityHydraArrow entity) {
        return TEXTURES;
    }
}