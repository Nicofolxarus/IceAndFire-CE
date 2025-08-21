package com.iafenvoy.iceandfire.data.component;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.impl.ComponentManager;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import com.iafenvoy.iceandfire.registry.IafWorld;
import com.iafenvoy.iceandfire.world.processor.DreadPortalProcessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public class PortalData {
    private final LivingEntity living;
    private boolean teleported = false;
    private int teleportTick = -1;
    private boolean dirty;

    public PortalData(LivingEntity living) {
        this.living = living;
    }

    public void tick() {
        World world = this.living.getWorld();
        if (!this.isTeleported() && this.getTeleportTick() == 0 && world instanceof ServerWorld serverWorld) {
            this.setTeleported(true);
            MinecraftServer server = serverWorld.getServer();
            if (world.getRegistryKey().getValue().equals(IafWorld.DREAD_LAND.getValue()))
                this.living.teleportTo(new TeleportTarget(server.getOverworld(), this.living.getPos(), Vec3d.ZERO, this.living.headYaw, this.living.getPitch(), TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET));
            else {
                ServerWorld dreadLand = server.getWorld(IafWorld.DREAD_LAND);
                if (dreadLand == null) return;
                this.living.teleportTo(new TeleportTarget(server.getWorld(IafWorld.DREAD_LAND), this.living.getPos(), Vec3d.ZERO, this.living.headYaw, this.living.getPitch(), TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET));
                if (!dreadLand.getBlockState(this.living.getBlockPos()).isOf(IafBlocks.DREAD_PORTAL.get()))
                    server.getStructureTemplateManager().getTemplate(Identifier.of(IceAndFire.MOD_ID, "dread_exit_portal")).ifPresent(structureTemplate -> structureTemplate.place(dreadLand, this.living.getBlockPos().subtract(new BlockPos(2, 1, 2)), BlockPos.ORIGIN, new StructurePlacementData().addProcessor(new DreadPortalProcessor()), dreadLand.random, 2));
                this.living.sendMessage(Text.translatable("warning.iceandfire.dreadland.not_complete"));
            }
        }
        if (world.getBlockState(this.living.getBlockPos()).isOf(IafBlocks.DREAD_PORTAL.get())) {
            if (this.getTeleportTick() > 0) this.setTeleportTick(this.getTeleportTick() - 1);
            else if (this.getTeleportTick() == -1) this.setTeleportTick(100);
        } else {
            this.setTeleported(false);
            this.setTeleportTick(-1);
        }
    }

    public void readFromNbt(NbtCompound tag) {
        this.setTeleported(tag.getBoolean("teleported"));
        this.setTeleportTick(tag.getInt("teleport_tick"));
    }

    public void writeToNbt(NbtCompound tag) {
        tag.putBoolean("teleported", this.isTeleported());
        tag.putInt("teleport_tick", this.getTeleportTick());
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

    public void markDirty() {
        this.dirty = true;
    }

    public boolean isDirty() {
        boolean dirty = this.dirty;
        this.dirty = false;
        return dirty;
    }

    public static PortalData get(PlayerEntity player) {
        return ComponentManager.getPortalData(player);
    }
}
