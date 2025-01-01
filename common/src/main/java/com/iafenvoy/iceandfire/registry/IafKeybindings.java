package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.event.ClientEvents;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public final class IafKeybindings {
    public static final KeyBinding DRAGON_BREATH = new KeyBinding("key.dragon_fireAttack", GLFW.GLFW_KEY_R, "key.categories.gameplay");
    public static final KeyBinding DRAGON_STRIKE = new KeyBinding("key.dragon_strike", GLFW.GLFW_KEY_G, "key.categories.gameplay");
    public static final KeyBinding DRAGON_DOWN = new KeyBinding("key.dragon_down", GLFW.GLFW_KEY_X, "key.categories.gameplay");
    public static final KeyBinding DRAGON_CHANGE_VIEW = new KeyBinding("key.dragon_change_view", GLFW.GLFW_KEY_F7, "key.categories.gameplay");

    public static void init() {
        KeyMappingRegistry.register(DRAGON_BREATH);
        KeyMappingRegistry.register(DRAGON_STRIKE);
        KeyMappingRegistry.register(DRAGON_DOWN);
        KeyMappingRegistry.register(DRAGON_CHANGE_VIEW);
        ClientTickEvent.CLIENT_POST.register(client -> {
            if (DRAGON_CHANGE_VIEW.wasPressed()) {
                if (ClientEvents.currentView + 1 > 3) ClientEvents.currentView = 0;
                else ClientEvents.currentView++;
            }
        });
    }
}
