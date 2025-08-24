package com.iafenvoy.iceandfire.item.tool;

import com.iafenvoy.iceandfire.entity.DragonArrowEntity;
import com.iafenvoy.iceandfire.registry.IafEntities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DragonArrowItem extends ArrowItem {
    public DragonArrowItem() {
        super(new Item.Settings());
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
        return new DragonArrowEntity(IafEntities.DRAGON_ARROW.get(), shooter, world, shotFrom);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        DragonArrowEntity arrowEntity = new DragonArrowEntity(IafEntities.DRAGON_ARROW.get(), pos.getX(), pos.getY(), pos.getZ(), world, stack.copyWithCount(1), null);
        arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        return arrowEntity;
    }
}
