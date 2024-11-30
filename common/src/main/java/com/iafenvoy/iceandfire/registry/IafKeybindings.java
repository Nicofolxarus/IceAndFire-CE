package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.event.ClientEvents;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public final class IafKeybindings {
    public static KeyBinding dragonBreath = new KeyBinding("key.dragon_fireAttack", GLFW.GLFW_KEY_R, "key.categories.gameplay");
    public static KeyBinding dragonStrike = new KeyBinding("key.dragon_strike", GLFW.GLFW_KEY_G, "key.categories.gameplay");
    public static KeyBinding dragonDown = new KeyBinding("key.dragon_down", GLFW.GLFW_KEY_X, "key.categories.gameplay");
    public static KeyBinding dragonChangeView = new KeyBinding("key.dragon_change_view", GLFW.GLFW_KEY_F7, "key.categories.gameplay");

    public static void init() {
        KeyMappingRegistry.register(dragonBreath);
        KeyMappingRegistry.register(dragonStrike);
        KeyMappingRegistry.register(dragonDown);
        KeyMappingRegistry.register(dragonChangeView);
        ClientTickEvent.CLIENT_POST.register(client -> {
            if (dragonChangeView.wasPressed()) {
                if (ClientEvents.currentView + 1 > 3) ClientEvents.currentView = 0;
                else ClientEvents.currentView++;
            }
        });
    }
}
