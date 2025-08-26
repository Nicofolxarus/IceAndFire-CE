package com.iafenvoy.iceandfire.compat.jade;

import com.iafenvoy.iceandfire.entity.*;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class IceAndFireJadePlugin implements IWailaPlugin {
    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerEntityComponent(DragonComponentProvider.INSTANCE, FireDragonEntity.class);
        registration.registerEntityComponent(DragonComponentProvider.INSTANCE, IceDragonEntity.class);
        registration.registerEntityComponent(DragonComponentProvider.INSTANCE, LightningDragonEntity.class);
        registration.registerEntityComponent(MultipartComponentProvider.INSTANCE, MultipartPartEntity.class);
    }
}
