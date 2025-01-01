package com.iafenvoy.iceandfire.world.util;

import com.google.common.base.Preconditions;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.TeleportTarget;

public class DimensionUtil {
    public static <E extends Entity> void changeDimension(E teleported, ServerWorld dimension, TeleportTarget target) {
        Preconditions.checkArgument(!teleported.getWorld().isClient, "Entities can only be teleported on the server side");
        Preconditions.checkArgument(Thread.currentThread() == ((ServerWorld) teleported.getWorld()).getServer().getThread(), "Entities must be teleported from the main server thread");

        try {
            ((Teleportable) teleported).iceandfire$setCustomTeleportTarget(target);

            // Fast path for teleporting within the same dimension.
            if (teleported.getWorld() == dimension) {
                if (teleported instanceof ServerPlayerEntity serverPlayerEntity)
                    serverPlayerEntity.networkHandler.requestTeleport(target.position.x, target.position.y, target.position.z, target.yaw, teleported.getPitch());
                else
                    teleported.refreshPositionAndAngles(target.position.x, target.position.y, target.position.z, target.yaw, teleported.getPitch());
                teleported.setVelocity(target.velocity);
                teleported.setHeadYaw(target.yaw);
                return;
            }
            teleported.moveToWorld(dimension);
        } finally {
            ((Teleportable) teleported).iceandfire$setCustomTeleportTarget(null);
        }
    }
}
