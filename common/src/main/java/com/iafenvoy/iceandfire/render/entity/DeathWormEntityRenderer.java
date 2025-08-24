package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.DeathWormEntity;
import com.iafenvoy.iceandfire.render.model.DeathWormModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class DeathWormEntityRenderer extends MobEntityRenderer<DeathWormEntity, DeathWormModel> {
    public static final Identifier TEXTURE_RED = Identifier.of(IceAndFire.MOD_ID, "textures/entity/deathworm/deathworm_red.png");
    public static final Identifier TEXTURE_WHITE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/deathworm/deathworm_white.png");
    public static final Identifier TEXTURE_YELLOW = Identifier.of(IceAndFire.MOD_ID, "textures/entity/deathworm/deathworm_yellow.png");

    public DeathWormEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DeathWormModel(), 0);
    }

    @Override
    protected void scale(DeathWormEntity entity, MatrixStack matrixStackIn, float partialTickTime) {
        this.shadowRadius = entity.getScaleFactor() / 3;
        matrixStackIn.scale(entity.getScaleFactor(), entity.getScaleFactor(), entity.getScaleFactor());
    }

    @Override
    protected int getBlockLight(DeathWormEntity entityIn, BlockPos partialTicks) {
        return entityIn.isOnFire() ? 15 : entityIn.getWormBrightness(false);
    }

    @Override
    protected int getSkyLight(DeathWormEntity entity, BlockPos pos) {
        return entity.getWormBrightness(true);
    }

    @Override
    public Identifier getTexture(DeathWormEntity entity) {
        return entity.getVariant() == 2 ? TEXTURE_WHITE : entity.getVariant() == 1 ? TEXTURE_RED : TEXTURE_YELLOW;
    }
}
