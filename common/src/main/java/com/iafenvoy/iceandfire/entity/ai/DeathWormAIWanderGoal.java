package com.iafenvoy.iceandfire.entity.ai;

import com.iafenvoy.iceandfire.entity.DeathWormEntity;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;

public class DeathWormAIWanderGoal extends WanderAroundFarGoal {
    private final DeathWormEntity worm;

    public DeathWormAIWanderGoal(DeathWormEntity creatureIn, double speedIn) {
        super(creatureIn, speedIn);
        this.worm = creatureIn;
    }

    @Override
    public boolean canStart() {
        return !this.worm.isInSand() && !this.worm.hasPassengers() && super.canStart();
    }

    @Override
    public boolean shouldContinue() {
        return !this.worm.isInSand() && !this.worm.hasPassengers() && super.shouldContinue();
    }
}