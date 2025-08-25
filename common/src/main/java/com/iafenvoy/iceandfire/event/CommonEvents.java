package com.iafenvoy.iceandfire.event;

import com.iafenvoy.uranus.event.Event;
import net.minecraft.entity.LivingEntity;

import java.util.function.Consumer;

public final class CommonEvents {
    public static final Event<Consumer<LivingEntity>> LIVING_TICK = new Event<>(listeners -> living -> listeners.forEach(x -> x.accept(living)));
}
