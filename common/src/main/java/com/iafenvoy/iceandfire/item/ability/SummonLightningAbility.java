package com.iafenvoy.iceandfire.item.ability;

import com.iafenvoy.iceandfire.event.ServerEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface SummonLightningAbility extends PostHitAbility {
    @Override
    default void active(LivingEntity target, LivingEntity attacker) {
        if (isEnable()) {
            if (attacker instanceof PlayerEntity && attacker.handSwingProgress > 0.2) {
                return;
            }
            if (!attacker.getWorld().isClient) {
                LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(target.getWorld());
                assert lightningEntity != null;
                lightningEntity.getCommandTags().add(ServerEvents.BOLT_DONT_DESTROY_LOOT);
                lightningEntity.getCommandTags().add(attacker.getUuidAsString());
                lightningEntity.refreshPositionAfterTeleport(target.getPos());
                if (!target.getWorld().isClient) {
                    target.getWorld().spawnEntity(lightningEntity);
                }
            }
        }
    }
}
