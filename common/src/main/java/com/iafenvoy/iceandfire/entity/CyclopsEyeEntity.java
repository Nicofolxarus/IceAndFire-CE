package com.iafenvoy.iceandfire.entity;

import com.iafenvoy.iceandfire.registry.IafEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.world.World;

public class CyclopsEyeEntity extends MultipartPartEntity {
    public CyclopsEyeEntity(EntityType<?> t, World world) {
        super(t, world);
    }

    public CyclopsEyeEntity(LivingEntity parent, float radius, float angleYaw, float offsetY, float sizeX, float sizeY, float damageMultiplier) {
        super(IafEntities.CYCLOPS_MULTIPART.get(), parent, radius, angleYaw, offsetY, sizeX, sizeY,
                damageMultiplier);
    }

    @Override
    public boolean damage(DamageSource source, float damage) {
        Entity parent = this.getParent();
        if (parent instanceof CyclopsEntity && source.isOf(DamageTypes.ARROW)) {
            ((CyclopsEntity) parent).onHitEye(source, damage);
            return true;
        } else {
            return parent != null && parent.damage(source, damage);
        }
    }
}
