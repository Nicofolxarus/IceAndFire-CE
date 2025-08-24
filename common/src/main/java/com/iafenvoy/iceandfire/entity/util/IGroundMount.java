package com.iafenvoy.iceandfire.entity.util;

import net.minecraft.entity.player.PlayerEntity;

//TODO: Fix death worm riding
public interface IGroundMount {
    PlayerEntity getRidingPlayer();

    double getRideSpeedModifier();
}
