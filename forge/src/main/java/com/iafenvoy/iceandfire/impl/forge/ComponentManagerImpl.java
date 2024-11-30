package com.iafenvoy.iceandfire.impl.forge;

import com.iafenvoy.iceandfire.data.component.IafEntityData;
import com.iafenvoy.iceandfire.fabric.component.EntityDataComponent;
import net.minecraft.entity.LivingEntity;

public class ComponentManagerImpl {
    public static IafEntityData getIafEntityData(LivingEntity living) {
        return EntityDataComponent.get(living).getData();
    }
}
