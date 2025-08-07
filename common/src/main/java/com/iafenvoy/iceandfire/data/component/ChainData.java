package com.iafenvoy.iceandfire.data.component;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ChainData extends NeedUpdateData {
    @NotNull
    private final List<UUID> chainedTo = new LinkedList<>();

    public void tickChain(final LivingEntity entity) {
        if (entity.getWorld() instanceof ServerWorld world)
            for (UUID uuid : this.chainedTo) {
                Entity chain = world.getEntity(uuid);
                if (chain == null) continue;
                double distance = chain.distanceTo(entity);
                if (distance > 7) {
                    double x = (chain.getX() - entity.getX()) / distance;
                    double y = (chain.getY() - entity.getY()) / distance;
                    double z = (chain.getZ() - entity.getZ()) / distance;
                    entity.setVelocity(entity.getVelocity().add(x * Math.abs(x) * 0.4D, y * Math.abs(y) * 0.2D, z * Math.abs(z) * 0.4D));
                }
            }
    }

    public @NotNull List<UUID> getChainedTo() {
        return this.chainedTo;
    }

    public void clearChains() {
        this.chainedTo.clear();
        this.triggerUpdate();
    }

    public void attachChain(final UUID chain) {
        if (this.chainedTo.contains(chain)) return;
        this.chainedTo.add(chain);
        this.triggerUpdate();
    }

    public void removeChain(final UUID chain) {
        this.chainedTo.remove(chain);
        this.triggerUpdate();
    }

    public boolean isChainedTo(final UUID toCheck) {
        return this.chainedTo.contains(toCheck);
    }

    public void serialize(final NbtCompound tag) {
        NbtList uuids = new NbtList();
        for (UUID uuid : this.chainedTo) uuids.add(NbtHelper.fromUuid(uuid));
        tag.put("chainedTo", uuids);
    }

    public void deserialize(final NbtCompound tag) {
        this.chainedTo.clear();
        tag.getList("chainedTo", NbtElement.INT_ARRAY_TYPE).stream().map(NbtHelper::toUuid).forEach(this.chainedTo::add);
    }
}
