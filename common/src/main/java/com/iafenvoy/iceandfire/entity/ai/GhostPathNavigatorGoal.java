package com.iafenvoy.iceandfire.entity.ai;

import com.iafenvoy.iceandfire.entity.GhostEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.world.World;

public class GhostPathNavigatorGoal extends BirdNavigation {
    public final GhostEntity ghost;

    public GhostPathNavigatorGoal(GhostEntity entityIn, World worldIn) {
        super(entityIn, worldIn);
        this.ghost = entityIn;
    }

    @Override
    public boolean startMovingTo(Entity entityIn, double speedIn) {
        this.ghost.getMoveControl().moveTo(entityIn.getX(), entityIn.getY(), entityIn.getZ(), speedIn);
        return true;
    }

    @Override
    public boolean startMovingTo(double x, double y, double z, double speedIn) {
        this.ghost.getMoveControl().moveTo(x, y, z, speedIn);
        return true;
    }
}
