package com.iafenvoy.iceandfire.event;

import com.iafenvoy.uranus.event.Event;
import net.minecraft.client.particle.ParticleManager;

import java.util.function.Consumer;

public class RegistryEvents {
    public static final Event<Consumer<ParticleManager>> PARTICLE = new Event<>(consumers -> manager -> consumers.forEach(x -> x.accept(manager)));
}
