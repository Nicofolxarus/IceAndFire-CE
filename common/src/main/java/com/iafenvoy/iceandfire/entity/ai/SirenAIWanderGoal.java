package com.iafenvoy.iceandfire.entity.ai;

import com.iafenvoy.iceandfire.entity.SirenEntity;
import net.minecraft.entity.ai.goal.WanderAroundGoal;

public class SirenAIWanderGoal extends WanderAroundGoal {
    private final SirenEntity siren;

    public SirenAIWanderGoal(SirenEntity creatureIn, double speedIn) {
        super(creatureIn, speedIn);
        this.siren = creatureIn;
    }

    @Override
    public boolean canStart() {
        return !this.siren.isTouchingWater() && !this.siren.isSinging() && super.canStart();
    }

    @Override
    public boolean shouldContinue() {
        return !this.siren.isTouchingWater() && !this.siren.isSinging() && super.shouldContinue();
    }
}