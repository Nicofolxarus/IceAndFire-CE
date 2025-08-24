package com.iafenvoy.iceandfire.entity;

import com.iafenvoy.iceandfire.registry.IafItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class SeaSerpentArrowEntity extends PersistentProjectileEntity {
    public SeaSerpentArrowEntity(EntityType<? extends PersistentProjectileEntity> t, World worldIn) {
        super(t, worldIn);
        this.setDamage(3F);
    }

    public SeaSerpentArrowEntity(EntityType<? extends PersistentProjectileEntity> t, World worldIn, double x, double y, double z) {
        this(t, worldIn);
        this.setPosition(x, y, z);
        this.setDamage(3F);
    }

    public SeaSerpentArrowEntity(EntityType<? extends PersistentProjectileEntity> t, World worldIn, LivingEntity shooter, ItemStack shotFrom) {
        super(t, shooter, worldIn, new ItemStack(IafItems.SEA_SERPENT_ARROW.get()), shotFrom);
        this.setDamage(3F);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient && !this.inGround) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            double xRatio = this.getVelocity().x * this.getHeight();
            double zRatio = this.getVelocity().z * this.getHeight();
            this.getWorld().addParticle(ParticleTypes.BUBBLE, this.getX() + xRatio + this.random.nextFloat() * this.getWidth() * 1.0F - this.getWidth() - d0 * 10.0D, this.getY() + this.random.nextFloat() * this.getHeight() - d1 * 10.0D, this.getZ() + zRatio + this.random.nextFloat() * this.getWidth() * 1.0F - this.getWidth() - d2 * 10.0D, d0, d1, d2);
            this.getWorld().addParticle(ParticleTypes.SPLASH, this.getX() + xRatio + this.random.nextFloat() * this.getWidth() * 1.0F - this.getWidth() - d0 * 10.0D, this.getY() + this.random.nextFloat() * this.getHeight() - d1 * 10.0D, this.getZ() + zRatio + this.random.nextFloat() * this.getWidth() * 1.0F - this.getWidth() - d2 * 10.0D, d0, d1, d2);
        }
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(IafItems.SEA_SERPENT_ARROW.get());
    }

    @Override
    public boolean isTouchingWater() {
        return false;
    }
}
