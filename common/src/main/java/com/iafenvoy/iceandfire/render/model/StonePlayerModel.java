package com.iafenvoy.iceandfire.render.model;

import com.iafenvoy.iceandfire.entity.StoneStatueEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;

public class StonePlayerModel extends BipedEntityModel<StoneStatueEntity> {
    public StonePlayerModel(ModelPart p_170677_) {
        super(p_170677_);
    }

    @Override
    public void setAngles(StoneStatueEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}
