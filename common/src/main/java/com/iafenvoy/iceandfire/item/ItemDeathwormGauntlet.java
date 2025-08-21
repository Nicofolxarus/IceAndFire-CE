package com.iafenvoy.iceandfire.item;

import com.iafenvoy.iceandfire.data.component.MiscData;
import com.iafenvoy.iceandfire.registry.IafDataComponents;
import com.iafenvoy.iceandfire.registry.IafSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class ItemDeathwormGauntlet extends Item {
    private boolean deathwormReceded = true;
    private boolean deathwormLaunched = false;
    private int specialDamage = 0;

    public ItemDeathwormGauntlet() {
        super(new Settings().maxDamage(500).component(IafDataComponents.INT.get(), -1));
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 1;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand) {
        ItemStack itemStackIn = playerIn.getStackInHand(hand);
        playerIn.setCurrentHand(hand);
        return new TypedActionResult<>(ActionResult.PASS, itemStackIn);
    }

    @Override
    public void usageTick(World level, LivingEntity entity, ItemStack stack, int count) {
        if (!this.deathwormReceded && !this.deathwormLaunched) {
            if (entity instanceof PlayerEntity player) {
                if (stack.get(IafDataComponents.INT.get()) != player.getId())
                    stack.set(IafDataComponents.INT.get(), player.getId());

                if (player.getItemCooldownManager().getCooldownProgress(this, 0.0F) == 0) {
                    player.getItemCooldownManager().set(this, 10);
                    player.playSound(IafSounds.DEATHWORM_ATTACK.get(), 1F, 1F);
                    this.deathwormReceded = false;
                    this.deathwormLaunched = true;
                }
            }
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World worldIn, LivingEntity user, int timeLeft) {
        if (this.specialDamage > 0) {
            stack.damage(this.specialDamage, user, LivingEntity.getSlotForHand(user.getActiveHand()));
            this.specialDamage = 0;
        }

        if (stack.get(IafDataComponents.INT.get()) != -1)
            stack.set(IafDataComponents.INT.get(), -1);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player)
            player.getItemCooldownManager().set(this, 20);
        return super.finishUsing(stack, world, user);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!(entity instanceof LivingEntity living)) return;
        MiscData miscData = MiscData.get(living);
        int tempLungeTicks = miscData.lungeTicks;

        if (this.deathwormReceded) {
            if (tempLungeTicks > 0) tempLungeTicks = tempLungeTicks - 4;
            if (tempLungeTicks <= 0) {
                tempLungeTicks = 0;
                this.deathwormReceded = false;
                this.deathwormLaunched = false;
            }
        } else if (this.deathwormLaunched) {
            tempLungeTicks = 4 + tempLungeTicks;
            if (tempLungeTicks > 20) this.deathwormReceded = true;
        }

        if (miscData.lungeTicks >= 20) {
            tempLungeTicks = 0;
            if (entity instanceof PlayerEntity player) {
                Vec3d Vector3d = player.getRotationVec(1.0F).normalize();
                double range = 5;

                for (LivingEntity livingEntity : world.getEntitiesByClass(LivingEntity.class, new Box(player.getX() - range, player.getY() - range, player.getZ() - range, player.getX() + range, player.getY() + range, player.getZ() + range), livingEntity -> true)) {
                    //Let's not pull/hit ourselves
                    if (livingEntity == entity) continue;

                    Vec3d Vector3d1 = new Vec3d(livingEntity.getX() - player.getX(), livingEntity.getY() - player.getY(), livingEntity.getZ() - player.getZ());
                    double d0 = Vector3d1.length();
                    Vector3d1 = Vector3d1.normalize();
                    double d1 = Vector3d.dotProduct(Vector3d1);
                    boolean canSee = d1 > 1.0D - 0.5D / d0 && player.canSee(livingEntity);

                    if (canSee) {
                        this.specialDamage++;
                        livingEntity.damage(entity.getWorld().damageSources.playerAttack(player), 3F);
                        livingEntity.takeKnockback(0.5F, livingEntity.getX() - player.getX(), livingEntity.getZ() - player.getZ());
                    }
                }
            }
        }
        miscData.setLungeTicks(tempLungeTicks);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.iceandfire.legendary_weapon.desc").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.iceandfire.deathworm_gauntlet.desc_0").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.iceandfire.deathworm_gauntlet.desc_1").formatted(Formatting.GRAY));
    }
}
