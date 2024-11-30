package com.iafenvoy.iceandfire.forge.component;

import com.iafenvoy.iceandfire.data.component.IafEntityData;
import com.iafenvoy.uranus.forge.component.ITickableCapability;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class EntityDataStorage implements ITickableCapability {
    private final IafEntityData playerData;

    public EntityDataStorage(LivingEntity living) {
        this.playerData = new IafEntityData(living);
    }

    @Override
    public NbtCompound serializeNBT() {
        NbtCompound compound = new NbtCompound();
        this.playerData.serialize(compound);
        return compound;
    }

    @Override
    public void deserializeNBT(NbtCompound compound) {
        this.playerData.deserialize(compound);
    }

    public IafEntityData getData() {
        return this.playerData;
    }

    @Override
    public void tick() {
        this.playerData.tick();
    }

    @Override
    public boolean isDirty() {
        return this.playerData.isDirty();
    }
}
