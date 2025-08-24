package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.StymphalianBirdEntity;
import com.iafenvoy.iceandfire.render.model.StymphalianBirdModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class StymphalianBirdEntityRenderer extends MobEntityRenderer<StymphalianBirdEntity, StymphalianBirdModel> {
    public static final Identifier TEXTURE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/stymphalianbird/stymphalian_bird.png");

    public StymphalianBirdEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new StymphalianBirdModel(), 0.6F);
    }

    @Override
    public void scale(StymphalianBirdEntity LivingEntityIn, MatrixStack stack, float partialTickTime) {
        stack.scale(0.75F, 0.75F, 0.75F);
    }

    @Override
    public Identifier getTexture(StymphalianBirdEntity cyclops) {
        return TEXTURE;
    }
}
