package com.iafenvoy.iceandfire.entity.ai;

import com.iafenvoy.iceandfire.entity.AmphithereEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

public class AmphithereAIHurtByTargetGoal extends RevengeGoal {
    public AmphithereAIHurtByTargetGoal(AmphithereEntity amphithere, boolean help, Class<?>[] classes) {
        super(amphithere, classes);
    }

    protected static void setEntityAttackTarget(MobEntity creatureIn, LivingEntity LivingEntityIn) {
        AmphithereEntity amphithere = (AmphithereEntity) creatureIn;
        if (amphithere.isTamed() || !(LivingEntityIn instanceof PlayerEntity)) {
            amphithere.setTarget(LivingEntityIn);
        }
    }
}
