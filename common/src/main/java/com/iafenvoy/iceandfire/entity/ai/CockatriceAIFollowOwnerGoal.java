package com.iafenvoy.iceandfire.entity.ai;

import com.iafenvoy.iceandfire.entity.CockatriceEntity;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;

public class CockatriceAIFollowOwnerGoal extends FollowOwnerGoal {
    final CockatriceEntity cockatrice;

    public CockatriceAIFollowOwnerGoal(CockatriceEntity cockatrice, double speed, float minDist, float maxDist) {
        super(cockatrice, speed, minDist, maxDist);
        this.cockatrice = cockatrice;
    }

    @Override
    public boolean canStart() {
        return super.canStart() && this.cockatrice.getCommand() == 2;
    }
}
