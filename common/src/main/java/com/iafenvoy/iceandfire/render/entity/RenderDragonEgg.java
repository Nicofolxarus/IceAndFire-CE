package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.entity.EntityDragonEgg;
import com.iafenvoy.iceandfire.render.model.ModelDragonEgg;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

public class RenderDragonEgg extends LivingEntityRenderer<EntityDragonEgg, ModelDragonEgg<EntityDragonEgg>> {
    //TODO: Unused
//    public static final Identifier EGG_RED = Identifier.of(IceAndFire.MOD_ID, "textures/entity/firedragon/egg_red.png");
//    public static final Identifier EGG_GREEN = Identifier.of(IceAndFire.MOD_ID, "textures/entity/firedragon/egg_green.png");
//    public static final Identifier EGG_BRONZE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/firedragon/egg_bronze.png");
//    public static final Identifier EGG_GREY = Identifier.of(IceAndFire.MOD_ID, "textures/entity/firedragon/egg_gray.png");
//    public static final Identifier EGG_BLUE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/icedragon/egg_blue.png");
//    public static final Identifier EGG_WHITE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/icedragon/egg_white.png");
//    public static final Identifier EGG_SAPPHIRE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/icedragon/egg_sapphire.png");
//    public static final Identifier EGG_SILVER = Identifier.of(IceAndFire.MOD_ID, "textures/entity/icedragon/egg_silver.png");
//    public static final Identifier EGG_ELECTRIC = Identifier.of(IceAndFire.MOD_ID, "textures/entity/lightningdragon/egg_electric.png");
//    public static final Identifier EGG_amethyst = Identifier.of(IceAndFire.MOD_ID, "textures/entity/lightningdragon/egg_amethyst.png");
//    public static final Identifier EGG_BLACK = Identifier.of(IceAndFire.MOD_ID, "textures/entity/lightningdragon/egg_black.png");
//    public static final Identifier EGG_COPPER = Identifier.of(IceAndFire.MOD_ID, "textures/entity/lightningdragon/egg_copper.png");

    public RenderDragonEgg(EntityRendererFactory.Context context) {
        super(context, new ModelDragonEgg<>(), 0.3F);
    }

    @Override
    public Identifier getTexture(EntityDragonEgg entity) {
        return entity.getEggType().getEggTexture();
    }
}
