package com.iafenvoy.iceandfire.neoforge.component;

import com.iafenvoy.iceandfire.data.component.PortalData;
import com.iafenvoy.uranus.neoforge.component.ITickableAttachment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

public class PortalDataStorage implements ITickableAttachment, INBTSerializable<NbtCompound> {
    private final PortalData data;

    public PortalDataStorage(LivingEntity living) {
        this.data = new PortalData(living);
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

    @Override
    public NbtCompound serializeNBT(RegistryWrapper.@NotNull WrapperLookup lookup) {
        return this.serializeNBT();
    }

    @Override
    public void deserializeNBT(RegistryWrapper.@NotNull WrapperLookup lookup, @NotNull NbtCompound nbt) {
        this.deserializeNBT(nbt);
    }
}
