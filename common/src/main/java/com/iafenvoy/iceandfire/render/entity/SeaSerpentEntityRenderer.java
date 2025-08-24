package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.data.SeaSerpent;
import com.iafenvoy.iceandfire.entity.SeaSerpentEntity;
import com.iafenvoy.iceandfire.registry.IafRenderers;
import com.iafenvoy.iceandfire.render.entity.feature.SeaSerpentAncientFeatureRenderer;
import com.iafenvoy.iceandfire.render.model.animator.SeaSerpentTabulaModelAnimator;
import com.iafenvoy.uranus.client.model.AdvancedEntityModel;
import com.iafenvoy.uranus.client.model.util.TabulaModelHandlerHelper;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SeaSerpentEntityRenderer extends MobEntityRenderer<SeaSerpentEntity, AdvancedEntityModel<SeaSerpentEntity>> {
    public SeaSerpentEntityRenderer(EntityRendererFactory.Context context) {
        super(context, TabulaModelHandlerHelper.getModel(IafRenderers.SEA_SERPENT, SeaSerpentTabulaModelAnimator::new), 1.6F);
        this.features.add(new SeaSerpentAncientFeatureRenderer(this));
    }

    @Override
    protected void scale(SeaSerpentEntity entity, MatrixStack matrixStackIn, float partialTickTime) {
        this.shadowRadius = entity.getSeaSerpentScale();
        matrixStackIn.scale(this.shadowRadius, this.shadowRadius, this.shadowRadius);
    }

    @Override
    public Identifier getTexture(SeaSerpentEntity serpent) {
        return SeaSerpent.getByName(serpent.getVariant()).getTexture(serpent.isBlinking());
    }
}
