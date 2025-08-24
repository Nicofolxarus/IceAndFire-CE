package com.iafenvoy.iceandfire.render.entity.feature;

import com.iafenvoy.iceandfire.entity.util.IHasArmorVariant;
import com.iafenvoy.iceandfire.render.model.BipedBaseModel;
import com.iafenvoy.uranus.animation.IAnimatedEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class BipedArmorFeatureRendererMultiple<R extends MobEntityRenderer<T, M> & IHasArmorVariantResource, T extends MobEntity & IHasArmorVariant & IAnimatedEntity, M extends BipedBaseModel<T>, A extends BipedBaseModel<T>> extends BipedArmorFeatureRenderer<T, M, A> {
    final R mobRenderer;

    public BipedArmorFeatureRendererMultiple(R mobRenderer, A modelLeggings, A modelArmor, Identifier defaultArmor, Identifier defaultLegArmor) {
        super(mobRenderer, modelLeggings, modelArmor, defaultArmor, defaultLegArmor);
        this.mobRenderer = mobRenderer;
    }

    @Override
    public Identifier getArmorResource(T entity, ItemStack stack, EquipmentSlot slot, String type) {
        return this.mobRenderer.getArmorResource(entity.getBodyArmorVariant(), slot);
    }
}
