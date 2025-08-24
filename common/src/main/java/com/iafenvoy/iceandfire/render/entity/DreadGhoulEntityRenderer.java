package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.DreadGhoulEntity;
import com.iafenvoy.iceandfire.render.entity.layer.GenericGlowingFeatureRenderer;
import com.iafenvoy.iceandfire.render.model.DreadGhoulModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DreadGhoulEntityRenderer extends MobEntityRenderer<DreadGhoulEntity, DreadGhoulModel> {
    public static final Identifier TEXTURE_EYES = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_ghoul_eyes.png");

    public static final Identifier TEXTURE_0 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_ghoul_closed_1.png");
    public static final Identifier TEXTURE_1 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_ghoul_closed_2.png");
    public static final Identifier TEXTURE_2 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_ghoul_closed_3.png");
    public static final Identifier TEXTURE_0_MID = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_ghoul_mid_1.png");
    public static final Identifier TEXTURE_1_MID = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_ghoul_mid_2.png");
    public static final Identifier TEXTURE_2_MID = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_ghoul_mid_3.png");
    public static final Identifier TEXTURE_0_OPEN = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_ghoul_open_1.png");
    public static final Identifier TEXTURE_1_OPEN = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_ghoul_open_2.png");
    public static final Identifier TEXTURE_2_OPEN = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_ghoul_open_3.png");

    public DreadGhoulEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DreadGhoulModel(0.0F), 0.5F);
        this.addFeature(new GenericGlowingFeatureRenderer<>(this, TEXTURE_EYES));
    }

    @Override
    protected void scale(DreadGhoulEntity entity, MatrixStack matrixStackIn, float partialTickTime) {
        float scale = entity.getSize() < 0.01F ? 1F : entity.getSize();
        matrixStackIn.scale(scale, scale, scale);
    }

    @Override
    public Identifier getTexture(DreadGhoulEntity ghoul) {
        return switch (ghoul.getScreamStage()) {
            case 2 -> switch (ghoul.getVariant()) {
                case 1 -> TEXTURE_1_OPEN;
                case 2 -> TEXTURE_2_OPEN;
                default -> TEXTURE_0_OPEN;
            };
            case 1 -> switch (ghoul.getVariant()) {
                case 1 -> TEXTURE_1_MID;
                case 2 -> TEXTURE_2_MID;
                default -> TEXTURE_0_MID;
            };
            default -> switch (ghoul.getVariant()) {
                case 1 -> TEXTURE_1;
                case 2 -> TEXTURE_2;
                default -> TEXTURE_0;
            };
        };
    }
}
