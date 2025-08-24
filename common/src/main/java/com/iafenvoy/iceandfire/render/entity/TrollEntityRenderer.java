package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.entity.TrollEntity;
import com.iafenvoy.iceandfire.render.entity.layer.TrollEyesFeatureRenderer;
import com.iafenvoy.iceandfire.render.entity.layer.TrollWeaponFeatureRenderer;
import com.iafenvoy.iceandfire.render.model.TrollModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class TrollEntityRenderer extends MobEntityRenderer<TrollEntity, TrollModel> {
    public TrollEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new TrollModel(), 0.9F);
        this.features.add(new TrollWeaponFeatureRenderer(this));
        this.features.add(new TrollEyesFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(TrollEntity troll) {
        return troll.getTrollType().getTexture();
    }
}
