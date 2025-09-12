package com.iafenvoy.iceandfire.world;

import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DragonPosWorldData extends PersistentState {
    private static final Type<DragonPosWorldData> TYPE = new Type<>(DragonPosWorldData::new, DragonPosWorldData::fromNbt, DataFixTypes.CHUNK);
    private static final String IDENTIFIER = "iceandfire_dragonPositions";
    protected final Map<UUID, BlockPos> lastDragonPositions = new HashMap<>();

    private static DragonPosWorldData fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        DragonPosWorldData data = new DragonPosWorldData();
        NbtList list = nbt.getList("DragonMap", 10);
        for (int i = 0; i < list.size(); ++i) {
            NbtCompound obj = list.getCompound(i);
            UUID uuid = obj.getUuid("DragonUUID");
            BlockPos pos = new BlockPos(obj.getInt("DragonPosX"), obj.getInt("DragonPosY"), obj.getInt("DragonPosZ"));
            data.lastDragonPositions.put(uuid, pos);
        }
        return data;
    }

    public static DragonPosWorldData get(World world) {
        if (world instanceof ServerWorld serverWorld) {
            PersistentStateManager storage = serverWorld.getPersistentStateManager();
            DragonPosWorldData data = storage.getOrCreate(TYPE, IDENTIFIER);
            if (data != null) data.markDirty();
            return data;
        }
        return null;
    }

    public void addDragon(UUID uuid, BlockPos pos) {
        this.lastDragonPositions.put(uuid, pos);
        this.markDirty();
    }

    public void removeDragon(UUID uuid) {
        this.lastDragonPositions.remove(uuid);
        this.markDirty();
    }

    public BlockPos getDragonPos(UUID uuid) {
        return this.lastDragonPositions.get(uuid);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        NbtList list = new NbtList();
        for (Map.Entry<UUID, BlockPos> pair : this.lastDragonPositions.entrySet()) {
            NbtCompound obj = new NbtCompound();
            obj.putUuid("DragonUUID", pair.getKey());
            obj.putInt("DragonPosX", pair.getValue().getX());
            obj.putInt("DragonPosY", pair.getValue().getY());
            obj.putInt("DragonPosZ", pair.getValue().getZ());
            list.add(obj);
        }
        nbt.put("DragonMap", list);
        return nbt;
    }
}
