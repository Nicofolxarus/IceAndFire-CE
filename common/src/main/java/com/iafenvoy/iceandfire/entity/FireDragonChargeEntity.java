package com.iafenvoy.iceandfire.entity;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.entity.util.dragon.IafDragonDestructionManager;
import com.iafenvoy.iceandfire.particle.DragonFlameParticleType;
import com.iafenvoy.iceandfire.registry.IafDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireDragonChargeEntity extends DragonChargeEntity {
    public FireDragonChargeEntity(EntityType<? extends AbstractFireballEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public FireDragonChargeEntity(EntityType<? extends AbstractFireballEntity> type, World worldIn, double posX, double posY, double posZ, double accelX, double accelY, double accelZ) {
        super(type, worldIn, posX, posY, posZ, accelX, accelY, accelZ);
    }

    public FireDragonChargeEntity(EntityType<? extends AbstractFireballEntity> type, World worldIn, DragonBaseEntity shooter, double accelX, double accelY, double accelZ) {
        super(type, worldIn, shooter, accelX, accelY, accelZ);
    }

    @Override
    public boolean canHit() {
        return true;
    }

    @Override
    public void tick() {
        for (int i = 0; i < 4; ++i)
            this.getWorld().addParticle(new DragonFlameParticleType(3), this.getX() + ((this.random.nextDouble() - 0.5D) * this.getWidth()), this.getY() + ((this.random.nextDouble() - 0.5D) * this.getWidth()), this.getZ() + ((this.random.nextDouble() - 0.5D) * this.getWidth()), 0.0D, 0.0D, 0.0D);
        if (this.isTouchingWater())
            this.remove(RemovalReason.DISCARDED);
        if (this.isBurning())
            this.setOnFireFor(1);
        super.tick();
    }

    @Override
    public DamageSource causeDamage(Entity cause) {
        return IafDamageTypes.causeDragonFireDamage(cause);
    }

    @Override
    public void destroyArea(World world, BlockPos center, DragonBaseEntity destroyer) {
        IafDragonDestructionManager.destroyAreaCharge(world, center, destroyer);
    }

    @Override
    public float getDamage() {
        return IafCommonConfig.INSTANCE.dragon.attackDamageFire.getValue().floatValue();
    }
}