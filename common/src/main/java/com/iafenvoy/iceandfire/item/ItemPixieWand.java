package com.iafenvoy.iceandfire.item;

import com.iafenvoy.iceandfire.entity.EntityPixieCharge;
import com.iafenvoy.iceandfire.registry.IafEntities;
import com.iafenvoy.iceandfire.registry.IafItems;
import com.iafenvoy.iceandfire.registry.IafSounds;
import com.iafenvoy.uranus.object.RegistryHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class ItemPixieWand extends Item {
    public ItemPixieWand() {
        super(new Settings().maxCount(1).maxDamage(500));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStackIn = user.getStackInHand(hand);
        boolean flag = user.isCreative() || EnchantmentHelper.getLevel(RegistryHelper.getEnchantment(user.getRegistryManager(), Enchantments.INFINITY), itemStackIn) > 0;
        ItemStack itemstack = this.findAmmo(user);
        user.setCurrentHand(hand);
        user.swingHand(hand);
        if (!itemstack.isEmpty() || flag) {
            boolean flag1 = user.isCreative() || this.isInfinite(itemstack, itemStackIn, user);
            if (!flag1) {
                itemstack.decrement(1);
                if (itemstack.isEmpty())
                    user.getInventory().removeOne(itemstack);
            }
            double d2 = user.getRotationVector().x;
            double d3 = user.getRotationVector().y;
            double d4 = user.getRotationVector().z;
            float inaccuracy = 1.0F;
            d2 = d2 + user.getRandom().nextGaussian() * 0.007499999832361937D * inaccuracy;
            d3 = d3 + user.getRandom().nextGaussian() * 0.007499999832361937D * inaccuracy;
            d4 = d4 + user.getRandom().nextGaussian() * 0.007499999832361937D * inaccuracy;
            EntityPixieCharge charge = new EntityPixieCharge(IafEntities.PIXIE_CHARGE.get(), world, user, d2, d3, d4);
            charge.setPosition(user.getX(), user.getY() + 1, user.getZ());
            if (!world.isClient)
                world.spawnEntity(charge);
            user.playSound(IafSounds.PIXIE_WAND.get(), 1F, 0.75F + 0.5F * user.getRandom().nextFloat());
            itemstack.damage(1, user, LivingEntity.getSlotForHand(user.getActiveHand()));
            user.getItemCooldownManager().set(this, 5);
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStackIn);
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, PlayerEntity player) {
        int enchant = EnchantmentHelper.getLevel(RegistryHelper.getEnchantment(player.getRegistryManager(), Enchantments.INFINITY), bow);
        return enchant > 0 && stack.getItem() == IafItems.PIXIE_DUST.get();
    }

    private ItemStack findAmmo(PlayerEntity player) {
        if (this.isAmmo(player.getStackInHand(Hand.OFF_HAND)))
            return player.getStackInHand(Hand.OFF_HAND);
        else if (this.isAmmo(player.getStackInHand(Hand.MAIN_HAND)))
            return player.getStackInHand(Hand.MAIN_HAND);
        else {
            for (int i = 0; i < player.getInventory().size(); ++i) {
                ItemStack itemstack = player.getInventory().getStack(i);
                if (this.isAmmo(itemstack))
                    return itemstack;
            }
            return ItemStack.EMPTY;
        }
    }

    protected boolean isAmmo(ItemStack stack) {
        return !stack.isEmpty() && stack.getItem() == IafItems.PIXIE_DUST.get();
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.iceandfire.legendary_weapon.desc").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.iceandfire.pixie_wand.desc_0").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.iceandfire.pixie_wand.desc_1").formatted(Formatting.GRAY));
    }
}
