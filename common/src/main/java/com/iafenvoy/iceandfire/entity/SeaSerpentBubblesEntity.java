package com.iafenvoy.iceandfire.entity;

import com.iafenvoy.iceandfire.entity.util.dragon.IDragonProjectile;
import com.iafenvoy.iceandfire.registry.IafParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class SeaSerpentBubblesEntity extends AbstractFireballEntity implements IDragonProjectile {
    public SeaSerpentBubblesEntity(EntityType<? extends AbstractFireballEntity> t, World worldIn) {
        super(t, worldIn);
    }

    public SeaSerpentBubblesEntity(EntityType<? extends AbstractFireballEntity> t, World worldIn, double posX, double posY, double posZ, double accelX, double accelY, double accelZ) {
        super(t, posX, posY, posZ, new Vec3d(accelX, accelY, accelZ), worldIn);
    }

    public SeaSerpentBubblesEntity(EntityType<? extends AbstractFireballEntity> t, World worldIn, SeaSerpentEntity shooter, double accelX, double accelY, double accelZ) {
        super(t, shooter, new Vec3d(accelX, accelY, accelZ), worldIn);
    }

    @Override
    public boolean canHit() {
        return false;
    }

    @Override
    protected boolean isBurning() {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick() {
        Entity shootingEntity = this.getOwner();
        if (this.age > 400) this.remove(RemovalReason.DISCARDED);
        this.autoTarget();

        if (this.getWorld().isClient || (shootingEntity == null || !shootingEntity.isAlive()) && this.getWorld().isChunkLoaded(this.getBlockPos())) {
            this.baseTick();
            HitResult raytraceresult = ProjectileUtil.getCollision(this, this::canHit);
            if (raytraceresult.getType() != HitResult.Type.MISS)
                this.onCollision(raytraceresult);

            Vec3d vec3d = this.getVelocity();
            double d0 = this.getX() + vec3d.x;
            double d1 = this.getY() + vec3d.y;
            double d2 = this.getZ() + vec3d.z;
            ProjectileUtil.setRotationFromVelocity(this, 0.2F);
            float f = this.getDrag();
            if (this.getWorld().isClient)
                for (int i = 0; i < 3; ++i)
                    this.getWorld().addParticle(IafParticles.SERPENT_BUBBLE.get(), this.getX() + (double) (this.random.nextFloat() * this.getWidth()) - (double) this.getWidth() * 0.5F, this.getY() - 0.5D, this.getZ() + (double) (this.random.nextFloat() * this.getWidth()) - (double) this.getWidth() * 0.5F, 0, 0, 0);

            this.setVelocity(vec3d.add(vec3d.normalize().multiply(this.movementMultiplier)).multiply(f));
            this.setPosition(d0, d1, d2);
            this.setPosition(this.getX(), this.getY(), this.getZ());
        }
        this.setPosition(this.getX(), this.getY(), this.getZ());
        if (this.age > 20 && !this.isTouchingWaterOrRain())
            this.remove(RemovalReason.DISCARDED);
    }

    @Override
    protected boolean canHit(Entity entityIn) {
        return super.canHit(entityIn) && !(entityIn instanceof MultipartPartEntity) && !(entityIn instanceof SeaSerpentBubblesEntity);
    }


    public void autoTarget() {
        if (this.getWorld().isClient) {
            Entity shootingEntity = this.getOwner();
            if (shootingEntity instanceof SeaSerpentEntity seaSerpent && seaSerpent.getTarget() != null) {
            } else if (this.age > 20)
                this.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    protected ParticleEffect getParticleType() {
        return ParticleTypes.BUBBLE;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public float getTargetingMargin() {
        return 0F;
    }

    @Override
    protected void onCollision(HitResult movingObject) {
        boolean flag = this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
        if (!this.getWorld().isClient) {
            if (movingObject.getType() == HitResult.Type.ENTITY) {
                Entity entity = ((EntityHitResult) movingObject).getEntity();

                if (entity instanceof SlowPartEntity) return;
                Entity shootingEntity = this.getOwner();
                if (shootingEntity instanceof SeaSerpentEntity dragon) {
                    if (dragon.isTeammate(entity) || dragon.isPartOf(entity)) return;
                    entity.damage(this.getWorld().getDamageSources().mobAttack(dragon), 6.0F);
                }
            }
        }
    }
}
