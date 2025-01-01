package com.iafenvoy.iceandfire.entity.ai;

import com.iafenvoy.iceandfire.entity.EntityMyrmexBase;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.player.PlayerEntity;

public class MyrmexAIAttackPlayers extends ActiveTargetGoal<PlayerEntity> {
    private final EntityMyrmexBase myrmex;

    public MyrmexAIAttackPlayers(EntityMyrmexBase myrmex) {
        super(myrmex, PlayerEntity.class, 10, true, true, entity -> entity != null && (myrmex.getHive() == null || myrmex.getHive().isPlayerReputationLowEnoughToFight(entity.getUuid())));
        this.myrmex = myrmex;
    }

    @Override
    public boolean canStart() {
        return this.myrmex.shouldHaveNormalAI() && super.canStart();
    }
}
