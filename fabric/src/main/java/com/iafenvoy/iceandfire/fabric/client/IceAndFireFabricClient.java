package com.iafenvoy.iceandfire.fabric.client;

import com.iafenvoy.iceandfire.IceAndFireClient;
import com.iafenvoy.iceandfire.registry.IafRenderers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public final class IceAndFireFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        IceAndFireClient.init();
        IceAndFireClient.process();
        IafRenderers.registerParticleRenderers(holder -> holder.applyRegister(ParticleFactoryRegistry.getInstance()::register, (t, f) -> ParticleFactoryRegistry.getInstance().register(t, f::create)));
    }
}
