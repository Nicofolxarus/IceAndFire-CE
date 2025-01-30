package com.iafenvoy.iceandfire.entity.ai;

import com.iafenvoy.iceandfire.entity.EntityCyclops;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.util.Hand;

public class CyclopsAIAttackMelee extends MeleeAttackGoal {
    public CyclopsAIAttackMelee(EntityCyclops creature, double speedIn, boolean useLongMemory) {
        super(creature, speedIn, useLongMemory);
    }

    @Override
    protected void attack(LivingEntity entity) {
        float distance = this.mob.distanceTo(entity);
        final double d0 = Math.sqrt(this.getSquaredMaxAttackDistance(entity));
        if (this.isCyclopsBlinded() && distance >= 6) {
            this.stop();
            return;
        }
        if (distance <= d0) {
            this.mob.swingHand(Hand.MAIN_HAND);
            this.mob.tryAttack(entity);
        }
    }

    private boolean isCyclopsBlinded() {
        return this.mob instanceof EntityCyclops cyclops && cyclops.isBlinded();
    }

    protected double getSquaredMaxAttackDistance(LivingEntity entity) {
        return this.mob.getWidth() * 2.0F * this.mob.getWidth() * 2.0F + entity.getWidth();
    }
}
