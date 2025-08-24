package com.iafenvoy.iceandfire.entity.ai;

import com.iafenvoy.iceandfire.entity.CyclopsEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.util.Hand;

public class CyclopsAIAttackMeleeGoal extends MeleeAttackGoal {
    public CyclopsAIAttackMeleeGoal(CyclopsEntity creature, double speedIn, boolean useLongMemory) {
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
        return this.mob instanceof CyclopsEntity cyclops && cyclops.isBlinded();
    }

    protected double getSquaredMaxAttackDistance(LivingEntity entity) {
        return this.mob.getWidth() * 2.0F * this.mob.getWidth() * 2.0F + entity.getWidth();
    }
}
