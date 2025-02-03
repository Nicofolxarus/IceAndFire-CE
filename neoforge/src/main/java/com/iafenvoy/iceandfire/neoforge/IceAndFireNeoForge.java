package com.iafenvoy.iceandfire.neoforge;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.IceAndFireClient;
import com.iafenvoy.uranus.neoforge.component.CapabilitySyncHelper;
import dev.architectury.platform.Platform;
import net.minecraft.util.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(IceAndFire.MOD_ID)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public final class IceAndFireNeoForge {
    public IceAndFireNeoForge(IEventBus modBus) {
        IafAttachments.REGISTRY.register(modBus);

        IceAndFire.init();
        if (Platform.getEnv() == Dist.CLIENT)
            IceAndFireClient.init();
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(IceAndFire::process);
        CapabilitySyncHelper.registerForLiving(IafAttachments.IAF_ENTITY_DATA.get());
        CapabilitySyncHelper.registerForPlayer(Identifier.of(IceAndFire.MOD_ID, "portal_data"), IafAttachments.PORTAL_DATA.get());
    }
}
