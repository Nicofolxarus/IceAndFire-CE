package com.iafenvoy.iceandfire.entity.ai;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

import java.util.EnumSet;

public class AquaticAIFindWaterTargetGoal extends Goal {
    private final MobEntity mob;

    public AquaticAIFindWaterTargetGoal(MobEntity mob) {
        this.mob = mob;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if (!this.mob.isTouchingWater() || this.mob.hasVehicle() || this.mob.hasPassengers())
            return false;
        Path path = this.mob.getNavigation().getCurrentPath();
        if (this.mob.getRandom().nextFloat() < 0.15F && path != null && path.getEnd() != null && this.mob.squaredDistanceTo(path.getEnd().x, path.getEnd().y, path.getEnd().z) < 3) {
            assert path.getEnd() != null;
            if (path.getEnd() != null || !this.mob.getNavigation().isIdle() && !this.isDirectPathBetweenPoints(this.mob, this.mob.getPos(), new Vec3d(path.getEnd().x, path.getEnd().y, path.getEnd().z)))
                this.mob.getNavigation().stop();
            if (this.mob.getNavigation().isIdle()) {
                BlockPos vec3 = this.findWaterTarget();
                if (vec3 != null) { // TODO :: Performance impact
                    this.mob.getNavigation().startMovingTo(vec3.getX(), vec3.getY(), vec3.getZ(), 1.0);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean shouldContinue() {
        return false;
    }

    public BlockPos findWaterTarget() {
        BlockPos blockpos = BlockPos.ofFloored(this.mob.getBlockX(), this.mob.getBoundingBox().minY, this.mob.getBlockZ());
        if (this.mob.getTarget() == null || !this.mob.getTarget().isAlive()) {
            for (int i = 0; i < 10; ++i) {
                BlockPos blockpos1 = blockpos.add(this.mob.getRandom().nextInt(20) - 10, this.mob.getRandom().nextInt(6) - 3, this.mob.getRandom().nextInt(20) - 10);
                if (this.mob.getWorld().getBlockState(blockpos1).isOf(Blocks.WATER))
                    return blockpos1;
            }
        } else return this.mob.getTarget().getBlockPos();
        return null;
    }

    public boolean isDirectPathBetweenPoints(Entity entity, Vec3d vec1, Vec3d vec2) {
        return this.mob.getWorld().raycast(new RaycastContext(vec1, vec2, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, entity)).getType() == HitResult.Type.MISS;
    }
}