package com.iafenvoy.iceandfire.fabric.component;

import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.entity.LivingEntity;

public class ModComponentEntry implements EntityComponentInitializer {
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(LivingEntity.class, EntityDataComponent.COMPONENT, EntityDataComponent::new);
        registry.registerForPlayers(PortalDataComponent.COMPONENT, PortalDataComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
    }
}
