package com.iafenvoy.iceandfire.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;

import java.util.EnumSet;

public class CyclopsAITargetSheepPlayersGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
    public CyclopsAITargetSheepPlayersGoal(MobEntity goalOwnerIn, Class<T> targetClassIn, boolean checkSight) {
        super(goalOwnerIn, targetClassIn, 0, checkSight, true, livingEntity -> {
            return false; //TODO Sheep hunt cyclops
        });
        this.setControls(EnumSet.of(Control.TARGET));
    }
}