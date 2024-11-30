package com.iafenvoy.iceandfire.impl;

import com.iafenvoy.iceandfire.data.component.IafEntityData;
import com.iafenvoy.iceandfire.data.component.PortalData;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class ComponentManager {
    @ExpectPlatform
    public static IafEntityData getIafEntityData(LivingEntity living) {
        throw new AssertionError("This method should be replaced by Architectury.");
    }

    @ExpectPlatform
    public static PortalData getPortalData(PlayerEntity player){
        throw new AssertionError("This method should be replaced by Architectury.");
    }
}
