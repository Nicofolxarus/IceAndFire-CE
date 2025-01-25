package com.iafenvoy.iceandfire.impl.neoforge;

import com.iafenvoy.iceandfire.data.component.IafEntityData;
import com.iafenvoy.iceandfire.data.component.PortalData;
import com.iafenvoy.iceandfire.neoforge.IafAttachments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class ComponentManagerImpl {
    public static IafEntityData getIafEntityData(LivingEntity living) {
        return living.getData(IafAttachments.IAF_ENTITY_DATA.get()).getData();
    }

    public static PortalData getPortalData(PlayerEntity player) {
        return player.getData(IafAttachments.PORTAL_DATA.get()).getData();
    }
}
