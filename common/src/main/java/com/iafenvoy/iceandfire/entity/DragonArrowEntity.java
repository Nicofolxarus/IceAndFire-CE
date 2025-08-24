package com.iafenvoy.iceandfire.entity;

import com.iafenvoy.iceandfire.registry.IafItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DragonArrowEntity extends PersistentProjectileEntity {
    public DragonArrowEntity(EntityType<? extends PersistentProjectileEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
        this.setDamage(10);
    }

    public DragonArrowEntity(EntityType<? extends PersistentProjectileEntity> typeIn, double x, double y, double z, World world, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(typeIn, x, y, z, world, stack, shotFrom);
        this.setDamage(10);
    }

    public DragonArrowEntity(EntityType<? extends PersistentProjectileEntity> typeIn, LivingEntity shooter, World worldIn, ItemStack from) {
        super(typeIn, shooter, worldIn, new ItemStack(IafItems.DRAGONBONE_ARROW.get()), from);
        this.setDamage(10);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound tagCompound) {
        super.writeCustomDataToNbt(tagCompound);
        tagCompound.putDouble("damage", 10);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound tagCompund) {
        super.readCustomDataFromNbt(tagCompund);
        this.setDamage(tagCompund.getDouble("damage"));
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(IafItems.DRAGONBONE_ARROW.get());
    }
}