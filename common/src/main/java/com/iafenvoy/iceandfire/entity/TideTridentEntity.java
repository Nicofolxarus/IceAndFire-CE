package com.iafenvoy.iceandfire.entity;

import com.iafenvoy.iceandfire.registry.IafEntities;
import com.iafenvoy.iceandfire.registry.IafItems;
import com.iafenvoy.uranus.object.RegistryHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TideTridentEntity extends TridentEntity {
    private static final int ADDITIONALPIERCING = 2;
    private int entitiesHit = 0;

    public TideTridentEntity(EntityType<? extends TridentEntity> type, World worldIn) {
        super(type, worldIn);
        this.stack = new ItemStack(IafItems.TIDE_TRIDENT.get());
    }

    public TideTridentEntity(World worldIn, LivingEntity thrower, ItemStack thrownStackIn) {
        this(IafEntities.TIDE_TRIDENT.get(), worldIn);
        this.setPosition(thrower.getX(), thrower.getEyeY() - 0.1F, thrower.getZ());
        this.setOwner(thrower);
        this.stack = thrownStackIn;
        this.dataTracker.set(LOYALTY, (byte) EnchantmentHelper.getLevel(RegistryHelper.getEnchantment(worldIn.getRegistryManager(), Enchantments.LOYALTY), thrownStackIn));
        this.dataTracker.set(ENCHANTED, thrownStackIn.hasGlint());
        int piercingLevel = EnchantmentHelper.getLevel(RegistryHelper.getEnchantment(worldIn.getRegistryManager(), Enchantments.PIERCING), thrownStackIn);
        this.setPierceLevel((byte) piercingLevel);
    }

    @Override
    protected void onEntityHit(EntityHitResult result) {
        Entity entity = result.getEntity();
        float f = 12.0F;
        Entity entity2 = this.getOwner();
        DamageSource damageSource = this.getDamageSources().trident(this, entity2 == null ? this : entity2);
        if (entity instanceof LivingEntity && this.getWorld() instanceof ServerWorld serverWorld) {
            f = EnchantmentHelper.getDamage(serverWorld, this.getItemStack(), entity, damageSource, f);
        }

        Entity entity1 = this.getOwner();
        DamageSource damagesource = this.getWorld().getDamageSources().trident(this, entity1 == null ? this : entity1);
        this.entitiesHit++;
        if (this.entitiesHit >= this.getMaxPiercing())
            this.dealtDamage = true;
        SoundEvent soundevent = SoundEvents.ITEM_TRIDENT_HIT;
        if (entity.damage(damagesource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) return;

            if (entity instanceof LivingEntity livingentity1) {
                if (entity1 instanceof LivingEntity && this.getWorld() instanceof ServerWorld serverWorld)
                    EnchantmentHelper.onTargetDamaged(serverWorld, entity, damageSource, this.getWeaponStack());
                this.onHit(livingentity1);
            }
        }

        float f1 = 1.0F;
        if (this.getWorld() instanceof ServerWorld && this.getWorld().isThundering() && EnchantmentHelper.getLevel(RegistryHelper.getEnchantment(this.getWorld().getRegistryManager(), Enchantments.CHANNELING), this.getItemStack()) > 0) {
            BlockPos blockpos = entity.getBlockPos();
            if (this.getWorld().isSkyVisible(blockpos)) {
                LightningEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(this.getWorld());
                assert lightningboltentity != null;
                lightningboltentity.refreshPositionAfterTeleport(Vec3d.ofCenter(blockpos));
                lightningboltentity.setChanneler(entity1 instanceof ServerPlayerEntity ? (ServerPlayerEntity) entity1 : null);
                this.getWorld().spawnEntity(lightningboltentity);
                soundevent = SoundEvents.ITEM_TRIDENT_THUNDER.value();
                f1 = 5.0F;
            }
        }

        this.playSound(soundevent, f1, 1.0F);
    }

    private int getMaxPiercing() {
        return ADDITIONALPIERCING + this.getPierceLevel();
    }

}