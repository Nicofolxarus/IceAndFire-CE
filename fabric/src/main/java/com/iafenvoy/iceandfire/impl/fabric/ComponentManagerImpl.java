package com.iafenvoy.iceandfire.impl.fabric;

import com.iafenvoy.iceandfire.data.component.IafEntityData;
import com.iafenvoy.iceandfire.data.component.PortalData;
import com.iafenvoy.iceandfire.fabric.component.EntityDataComponent;
import com.iafenvoy.iceandfire.fabric.component.PortalDataComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class ComponentManagerImpl {
    public static IafEntityData getIafEntityData(LivingEntity living) {
        return EntityDataComponent.get(living).getData();
    }

    public static PortalData getPortalData(PlayerEntity player){
        return PortalDataComponent.get(player).getData();
    }
}
