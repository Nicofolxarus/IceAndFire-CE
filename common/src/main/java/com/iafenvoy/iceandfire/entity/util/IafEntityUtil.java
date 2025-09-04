package com.iafenvoy.iceandfire.entity.util;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.GorgonEntity;
import com.iafenvoy.iceandfire.entity.MultipartPartEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.UUID;

public class IafEntityUtil {
    public static void updatePart(final MultipartPartEntity part, final LivingEntity parent) {
        if (part == null || !(parent.getWorld() instanceof ServerWorld serverLevel) || parent.isRemoved())
            return;
        if (!part.shouldContinuePersisting()) {
            UUID uuid = part.getUuid();
            Entity existing = serverLevel.getEntity(uuid);
            // Update UUID if a different entity with the same UUID exists already
            if (existing != null && existing != part) {
                while (serverLevel.getEntity(uuid) != null)
                    uuid = MathHelper.randomUuid(parent.getRandom());
                IceAndFire.LOGGER.debug("Updated the UUID of [{}] due to a clash with [{}]", part, existing);
            }
            part.setUuid(uuid);
            serverLevel.spawnEntity(part);
        }
        part.setParent(parent);
    }

    public static boolean isEntityLookingAt(LivingEntity looker, LivingEntity seen, double degree) {
        degree *= 1 + (looker.distanceTo(seen) * 0.1);
        Vec3d Vector3d = looker.getRotationVec(1.0F).normalize();
        Vec3d Vector3d1 = new Vec3d(seen.getX() - looker.getX(), seen.getBoundingBox().minY + (double) seen.getStandingEyeHeight() - (looker.getY() + (double) looker.getStandingEyeHeight()), seen.getZ() - looker.getZ());
        double d0 = Vector3d1.length();
        Vector3d1 = Vector3d1.normalize();
        double d1 = Vector3d.dotProduct(Vector3d1);
        return d1 > 1.0D - degree / d0 && (looker.canSee(seen) && !GorgonEntity.isStoneMob(seen));
    }
}
