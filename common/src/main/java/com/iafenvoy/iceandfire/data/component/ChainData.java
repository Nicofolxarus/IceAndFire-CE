package com.iafenvoy.iceandfire.data.component;

import com.iafenvoy.iceandfire.impl.ComponentManager;
import com.iafenvoy.iceandfire.util.attachment.NeedUpdateData;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Uuids;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ChainData extends NeedUpdateData<LivingEntity> {
    public static final Codec<ChainData> CODEC = RecordCodecBuilder.create(i -> i.group(
            Uuids.CODEC.listOf().fieldOf("chainedTo").forGetter(ChainData::getChainedTo)
    ).apply(i, ChainData::new));
    public static final PacketCodec<RegistryByteBuf, ChainData> PACKET_CODEC = PacketCodecs.registryCodec(CODEC);
    @NotNull
    private final List<UUID> chainedTo = new LinkedList<>();

    public ChainData() {
    }

    private ChainData(List<UUID> chainedTo) {
        this.chainedTo.addAll(chainedTo);
    }

    @Override
    public void tick(LivingEntity entity) {
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
        return List.copyOf(this.chainedTo);
    }

    public void clearChains() {
        this.chainedTo.clear();
        this.markDirty();
    }

    public void attachChain(final UUID chain) {
        if (this.chainedTo.contains(chain)) return;
        this.chainedTo.add(chain);
        this.markDirty();
    }

    public void removeChain(final UUID chain) {
        this.chainedTo.remove(chain);
        this.markDirty();
    }

    public boolean isChainedTo(final UUID toCheck) {
        return this.chainedTo.contains(toCheck);
    }

    public static ChainData get(LivingEntity living) {
        return ComponentManager.getChainData(living);
    }
}
