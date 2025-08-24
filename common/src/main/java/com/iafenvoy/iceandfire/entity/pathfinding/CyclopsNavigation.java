package com.iafenvoy.iceandfire.entity.pathfinding;

import com.iafenvoy.iceandfire.entity.CyclopsEntity;
import com.iafenvoy.uranus.object.entity.collision.CustomCollisionsNavigator;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeNavigator;
import net.minecraft.world.World;

public class CyclopsNavigation extends CustomCollisionsNavigator {
    public CyclopsNavigation(CyclopsEntity LivingEntityIn, World worldIn) {
        super(LivingEntityIn, worldIn);
    }

    @Override
    protected PathNodeNavigator createPathNodeNavigator(int i) {
        this.nodeMaker = new LandPathNodeMaker();
        this.nodeMaker.setCanOpenDoors(true);
        this.nodeMaker.setCanSwim(true);
        return new PathNodeNavigator(this.nodeMaker, i);
    }
}