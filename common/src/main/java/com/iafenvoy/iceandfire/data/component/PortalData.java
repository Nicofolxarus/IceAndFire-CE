package com.iafenvoy.iceandfire.data.component;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.impl.ComponentManager;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import com.iafenvoy.iceandfire.registry.IafWorld;
import com.iafenvoy.iceandfire.util.attachment.NeedUpdateData;
import com.iafenvoy.iceandfire.world.processor.DreadPortalProcessor;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public class PortalData extends NeedUpdateData<LivingEntity> {
    public static final Codec<PortalData> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.BOOL.fieldOf("teleported").forGetter(PortalData::isTeleported),
            Codec.INT.fieldOf("teleportTick").forGetter(PortalData::getTeleportTick)
    ).apply(i, PortalData::new));
    public static final PacketCodec<RegistryByteBuf, PortalData> PACKET_CODEC = PacketCodecs.registryCodec(CODEC);
    private boolean teleported = false;
    private int teleportTick = -1;

    public PortalData() {
    }

    private PortalData(boolean teleported, int teleportTick) {
        this.teleported = teleported;
        this.teleportTick = teleportTick;
    }

    @Override
    public void tick(LivingEntity living) {
        World world = living.getWorld();
        if (!this.isTeleported() && this.getTeleportTick() == 0 && world instanceof ServerWorld serverWorld) {
            this.setTeleported(true);
            MinecraftServer server = serverWorld.getServer();
            if (world.getRegistryKey().getValue().equals(IafWorld.DREAD_LAND.getValue()))
                living.teleportTo(new TeleportTarget(server.getOverworld(), living.getPos(), Vec3d.ZERO, living.headYaw, living.getPitch(), TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET));
            else {
                ServerWorld dreadLand = server.getWorld(IafWorld.DREAD_LAND);
                if (dreadLand == null) return;
                living.teleportTo(new TeleportTarget(server.getWorld(IafWorld.DREAD_LAND), living.getPos(), Vec3d.ZERO, living.headYaw, living.getPitch(), TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET));
                if (!dreadLand.getBlockState(living.getBlockPos()).isOf(IafBlocks.DREAD_PORTAL.get()))
                    server.getStructureTemplateManager().getTemplate(Identifier.of(IceAndFire.MOD_ID, "dread_exit_portal")).ifPresent(structureTemplate -> structureTemplate.place(dreadLand, living.getBlockPos().subtract(new BlockPos(2, 1, 2)), BlockPos.ORIGIN, new StructurePlacementData().addProcessor(new DreadPortalProcessor()), dreadLand.random, 2));
                living.sendMessage(Text.translatable("warning.iceandfire.dreadland.not_complete"));
            }
        }
        if (world.getBlockState(living.getBlockPos()).isOf(IafBlocks.DREAD_PORTAL.get())) {
            if (this.getTeleportTick() > 0) this.setTeleportTick(this.getTeleportTick() - 1);
            else if (this.getTeleportTick() == -1) this.setTeleportTick(100);
        } else {
            this.setTeleported(false);
            this.setTeleportTick(-1);
        }
    }

    public boolean isTeleported() {
        return this.teleported;
    }

    public int getTeleportTick() {
        return this.teleportTick;
    }

    public void setTeleported(boolean teleported) {
        if (this.teleported != teleported) this.markDirty();
        this.teleported = teleported;
    }

    public void setTeleportTick(int teleportTick) {
        if (this.teleportTick != teleportTick) this.markDirty();
        this.teleportTick = teleportTick;
    }

    public static PortalData get(PlayerEntity player) {
        return ComponentManager.getPortalData(player);
    }
}
