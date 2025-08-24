package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.DreadThrallEntity;
import com.iafenvoy.iceandfire.render.entity.layer.IHasArmorVariantResource;
import com.iafenvoy.iceandfire.render.entity.layer.BipedArmorFeatureRendererMultiple;
import com.iafenvoy.iceandfire.render.entity.layer.GenericGlowingFeatureRenderer;
import com.iafenvoy.iceandfire.render.model.DreadThrallModel;
import com.iafenvoy.uranus.client.model.util.HideableLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;

public class DreadThrallEntityRenderer extends MobEntityRenderer<DreadThrallEntity, DreadThrallModel> implements IHasArmorVariantResource {
    public static final Identifier TEXTURE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_thrall.png");
    public static final Identifier TEXTURE_EYES = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/dread_thrall_eyes.png");
    public static final Identifier TEXTURE_LEG_ARMOR = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/thrall_legs.png");
    public static final Identifier TEXTURE_ARMOR_0 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/thrall_chest_1.png");
    public static final Identifier TEXTURE_ARMOR_1 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/thrall_chest_2.png");
    public static final Identifier TEXTURE_ARMOR_2 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/thrall_chest_3.png");
    public static final Identifier TEXTURE_ARMOR_3 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/thrall_chest_4.png");
    public static final Identifier TEXTURE_ARMOR_4 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/thrall_chest_5.png");
    public static final Identifier TEXTURE_ARMOR_5 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/thrall_chest_6.png");
    public static final Identifier TEXTURE_ARMOR_6 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/thrall_chest_7.png");
    public static final Identifier TEXTURE_ARMOR_7 = Identifier.of(IceAndFire.MOD_ID, "textures/entity/dread/thrall_chest_8.png");
    public final HideableLayer<DreadThrallEntity, DreadThrallModel, HeldItemFeatureRenderer<DreadThrallEntity, DreadThrallModel>> itemLayer;

    public DreadThrallEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DreadThrallModel(0.0F, false), 0.6F);
        this.addFeature(new GenericGlowingFeatureRenderer<>(this, TEXTURE_EYES));
        this.itemLayer = new HideableLayer<>(new HeldItemFeatureRenderer<>(this, context.getHeldItemRenderer()), this);
        this.addFeature(this.itemLayer);
        this.addFeature(new BipedArmorFeatureRendererMultiple<>(this, new DreadThrallModel(0.5F, true), new DreadThrallModel(1.0F, true), TEXTURE_ARMOR_0, TEXTURE_LEG_ARMOR));
    }

    @Override
    public Identifier getArmorResource(int variant, EquipmentSlot equipmentSlotType) {
        if (equipmentSlotType == EquipmentSlot.LEGS) return TEXTURE_LEG_ARMOR;
        return switch (variant) {
            case 1 -> TEXTURE_ARMOR_1;
            case 2 -> TEXTURE_ARMOR_2;
            case 3 -> TEXTURE_ARMOR_3;
            case 4 -> TEXTURE_ARMOR_4;
            case 5 -> TEXTURE_ARMOR_5;
            case 6 -> TEXTURE_ARMOR_6;
            case 7 -> TEXTURE_ARMOR_7;
            default -> TEXTURE_ARMOR_0;
        };
    }

    @Override
    public void scale(DreadThrallEntity livingEntityIn, MatrixStack stack, float partialTickTime) {
        stack.scale(0.95F, 0.95F, 0.95F);
        if (livingEntityIn.getAnimation() == this.getModel().getSpawnAnimation()) {
            this.itemLayer.hidden = livingEntityIn.getAnimationTick() <= this.getModel().getSpawnAnimation().getDuration() - 10;
            return;
        }
        this.itemLayer.hidden = false;

    }

    @Override
    public Identifier getTexture(DreadThrallEntity entity) {
        return TEXTURE;
    }
}
