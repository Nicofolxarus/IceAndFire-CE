package com.iafenvoy.iceandfire.neoforge.component;

import com.iafenvoy.iceandfire.data.component.PortalData;
import com.iafenvoy.uranus.neoforge.component.ITickableAttachment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class PortalDataStorage implements ITickableAttachment, INBTSerializable<NbtCompound> {
    private final PortalData data;

    public PortalDataStorage(PlayerEntity player) {
        this.data = new PortalData(player);
    }

    @Override
    public NbtCompound serializeNBT() {
        NbtCompound compound = new NbtCompound();
        this.data.writeToNbt(compound);
        return compound;
    }

    @Override
    public void deserializeNBT(NbtCompound compound) {
        this.data.readFromNbt(compound);
    }

    public PortalData getData() {
        return this.data;
    }

    @Override
    public void tick() {
        this.data.tick();
    }

    @Override
    public boolean isDirty() {
        return true;
    }
}
