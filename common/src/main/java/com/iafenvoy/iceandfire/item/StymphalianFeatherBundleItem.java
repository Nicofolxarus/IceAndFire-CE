package com.iafenvoy.iceandfire.item;

import com.iafenvoy.iceandfire.entity.StymphalianFeatherEntity;
import com.iafenvoy.iceandfire.registry.IafEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class StymphalianFeatherBundleItem extends Item {
    public StymphalianFeatherBundleItem() {
        super(new Settings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {
        ItemStack itemStackIn = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        player.getItemCooldownManager().set(this, 15);
        player.playSound(SoundEvents.ENTITY_EGG_THROW, 1, 1);
        if (!worldIn.isClient) {
            float rotation = player.headYaw;
            for (int i = 0; i < 8; i++) {
                StymphalianFeatherEntity feather = new StymphalianFeatherEntity(IafEntities.STYMPHALIAN_FEATHER.get(), worldIn, player);
                rotation += 45;
                feather.setVelocity(player, 0, rotation, 0.0F, 1.5F, 1.0F);
                worldIn.spawnEntity(feather);
            }
        }
        if (!player.isCreative())
            itemStackIn.decrement(1);
        return new TypedActionResult<>(ActionResult.PASS, itemStackIn);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.iceandfire.legendary_weapon.desc").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.iceandfire.stymphalian_feather_bundle.desc_0").formatted(Formatting.GRAY));
    }
}