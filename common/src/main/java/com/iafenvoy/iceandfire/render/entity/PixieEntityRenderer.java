package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.PixieEntity;
import com.iafenvoy.iceandfire.render.entity.layer.PixieGlowFeatureRenderer;
import com.iafenvoy.iceandfire.render.entity.layer.PixieItemFeatureRenderer;
import com.iafenvoy.iceandfire.render.model.PixieModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class PixieEntityRenderer extends MobEntityRenderer<PixieEntity, PixieModel> {
    public static final Identifier TEXTURE_0 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/pixie/pixie_0.png");
    public static final Identifier TEXTURE_1 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/pixie/pixie_1.png");
    public static final Identifier TEXTURE_2 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/pixie/pixie_2.png");
    public static final Identifier TEXTURE_3 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/pixie/pixie_3.png");
    public static final Identifier TEXTURE_4 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/pixie/pixie_4.png");
    public static final Identifier TEXTURE_5 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/pixie/pixie_5.png");

    public PixieEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new PixieModel(), 0.2F);
        this.features.add(new PixieItemFeatureRenderer(this));
        this.features.add(new PixieGlowFeatureRenderer(this));
    }

    @Override
    public void scale(PixieEntity LivingEntityIn, MatrixStack stack, float partialTickTime) {
        stack.scale(0.55F, 0.55F, 0.55F);
        if (LivingEntityIn.isSitting()) {
            stack.translate(0F, 0.5F, 0F);

        }
    }

    @Override
    public Identifier getTexture(PixieEntity pixie) {
        return switch (pixie.getColor()) {
            case 1 -> TEXTURE_1;
            case 2 -> TEXTURE_2;
            case 3 -> TEXTURE_3;
            case 4 -> TEXTURE_4;
            case 5 -> TEXTURE_5;
            default -> TEXTURE_0;
        };
    }
}
