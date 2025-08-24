package com.iafenvoy.iceandfire.entity;

import com.iafenvoy.iceandfire.registry.IafItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class StymphalianArrowEntity extends PersistentProjectileEntity {
    public StymphalianArrowEntity(EntityType<? extends PersistentProjectileEntity> t, World worldIn) {
        super(t, worldIn);
        this.setDamage(3.5F);
    }

    public StymphalianArrowEntity(EntityType<? extends PersistentProjectileEntity> t, World worldIn, double x, double y, double z) {
        this(t, worldIn);
        this.setPosition(x, y, z);
        this.setDamage(3.5F);
    }

    public StymphalianArrowEntity(EntityType<? extends PersistentProjectileEntity> t, World worldIn, LivingEntity shooter, ItemStack from) {
        super(t, shooter, worldIn, new ItemStack(IafItems.STYMPHALIAN_ARROW.get()), from);
        this.setDamage(3.5F);
    }

    @Override
    public void tick() {
        super.tick();
        float sqrt = MathHelper.sqrt((float) (this.getVelocity().x * this.getVelocity().x + this.getVelocity().z * this.getVelocity().z));
        if (sqrt < 0.1F) {
            this.setVelocity(this.getVelocity().add(0, -0.01F, 0));
        }
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(IafItems.STYMPHALIAN_ARROW.get());
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }
}
