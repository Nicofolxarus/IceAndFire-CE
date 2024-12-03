package com.iafenvoy.iceandfire.fabric.client;

import com.iafenvoy.iceandfire.IceAndFireClient;
import com.iafenvoy.uranus.client.model.util.TabulaModelHandlerHelper;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

public final class IceAndFireFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        IceAndFireClient.init();
        IceAndFireClient.process();
    }
}
