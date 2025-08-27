package com.iafenvoy.iceandfire.item.tool;

import com.iafenvoy.iceandfire.entity.TideTridentEntity;
import com.iafenvoy.uranus.object.RegistryHelper;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class TideTridentItem extends TridentItem {
    public TideTridentItem() {
        super(new Item.Settings().maxDamage(400).component(DataComponentTypes.ATTRIBUTE_MODIFIERS, createAttributeModifiers()));
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 12, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.9F, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .build();
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World worldIn, LivingEntity user, int timeLeft) {
        if (user instanceof PlayerEntity player) {
            int time = this.getMaxUseTime(stack, user) - timeLeft;
            if (time >= 10) {
                int riptideLevel = EnchantmentHelper.getLevel(RegistryHelper.getEnchantment(worldIn.getRegistryManager(), Enchantments.RIPTIDE), stack);
                if (riptideLevel <= 0 || player.isTouchingWaterOrRain()) {
                    if (!worldIn.isClient) {
                        stack.damage(1, player, LivingEntity.getSlotForHand(user.getActiveHand()));
                        if (riptideLevel == 0) {
                            TideTridentEntity tideTrident = new TideTridentEntity(worldIn, player, stack);
                            tideTrident.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 2.5F + (float) riptideLevel * 0.5F, 1.0F);
                            if (player.getAbilities().creativeMode)
                                tideTrident.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                            worldIn.spawnEntity(tideTrident);
                            worldIn.playSoundFromEntity(null, tideTrident, SoundEvents.ITEM_TRIDENT_THROW.value(), SoundCategory.PLAYERS, 1.0F, 1.0F);
                            if (!player.getAbilities().creativeMode)
                                player.getInventory().removeOne(stack);
                        }
                    }

                    player.incrementStat(Stats.USED.getOrCreateStat(this));
                    if (riptideLevel > 0) {
                        float yaw = player.getYaw();
                        float pitch = player.getPitch();
                        float velocityX = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
                        float velocityY = -MathHelper.sin(pitch * 0.017453292F);
                        float velocityZ = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
                        float speed = MathHelper.sqrt(velocityX * velocityX + velocityY * velocityY + velocityZ * velocityZ);
                        float targetSpeed = 3.0F * ((1.0F + (float) riptideLevel) / 4.0F);
                        velocityX *= targetSpeed / speed;
                        velocityY *= targetSpeed / speed;
                        velocityZ *= targetSpeed / speed;
                        player.addVelocity(velocityX, velocityY, velocityZ);
                        player.useRiptide(20, 8.0F, stack);
                        if (player.isOnGround())
                            player.move(MovementType.SELF, new Vec3d(0.0D, 1.1999999284744263D, 0.0D));

                        RegistryEntry<SoundEvent> sound;
                        if (riptideLevel >= 3) sound = SoundEvents.ITEM_TRIDENT_RIPTIDE_3;
                        else if (riptideLevel == 2) sound = SoundEvents.ITEM_TRIDENT_RIPTIDE_2;
                        else sound = SoundEvents.ITEM_TRIDENT_RIPTIDE_1;

                        worldIn.playSoundFromEntity(null, player, sound.value(), SoundCategory.PLAYERS, 1.0F, 1.0F);
                    }
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.iceandfire.legendary_weapon.desc").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.iceandfire.tide_trident.desc_0").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.iceandfire.tide_trident.desc_1").formatted(Formatting.GRAY));
    }
}
