package com.iafenvoy.iceandfire.fabric.component;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.data.component.IafEntityData;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistryV3;
import org.ladysnake.cca.api.v3.component.ComponentV3;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class EntityDataComponent implements ComponentV3, AutoSyncedComponent, CommonTickingComponent {
    protected static final ComponentKey<EntityDataComponent> COMPONENT = ComponentRegistryV3.INSTANCE.getOrCreate(Identifier.of(IceAndFire.MOD_ID, "entity_data"), EntityDataComponent.class);

    private final LivingEntity entity;
    private final IafEntityData data;

    public EntityDataComponent(LivingEntity entity) {
        this.entity = entity;
        this.data = new IafEntityData(entity);
    }

    public static EntityDataComponent get(LivingEntity entity) {
        return COMPONENT.get(entity);
    }

    @Override
    public void readFromNbt(@NotNull NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.data.deserialize(tag);
    }

    @Override
    public void writeToNbt(@NotNull NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.data.serialize(tag);
    }

    @Override
    public void tick() {
        this.data.tick();
        if (this.data.isDirty())
            COMPONENT.sync(this.entity);
    }

    public IafEntityData getData() {
        return this.data;
    }
}
