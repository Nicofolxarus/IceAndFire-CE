package com.iafenvoy.iceandfire.entity.ai;

import com.iafenvoy.iceandfire.entity.DragonBaseEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.mob.PathAwareEntity;

public class DragonAIWatchClosestGoal extends LookAtEntityGoal {
    public DragonAIWatchClosestGoal(PathAwareEntity LivingEntityIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance) {
        super(LivingEntityIn, watchTargetClass, maxDistance);
    }

    @Override
    public boolean canStart() {
        if (this.mob instanceof DragonBaseEntity && ((DragonBaseEntity) this.mob).getAnimation() == DragonBaseEntity.ANIMATION_SHAKEPREY)
            return false;
        return super.canStart();
    }

    @Override
    public boolean shouldContinue() {
        if (this.mob instanceof DragonBaseEntity && !((DragonBaseEntity) this.mob).canMove())
            return false;
        return super.shouldContinue();
    }
}
