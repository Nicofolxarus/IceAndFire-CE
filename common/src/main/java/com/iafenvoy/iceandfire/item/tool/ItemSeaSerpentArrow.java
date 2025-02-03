package com.iafenvoy.iceandfire.item.tool;

import com.iafenvoy.iceandfire.entity.EntitySeaSerpentArrow;
import com.iafenvoy.iceandfire.registry.IafEntities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemSeaSerpentArrow extends ArrowItem {
    public ItemSeaSerpentArrow() {
        super(new Item.Settings());
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
        return new EntitySeaSerpentArrow(IafEntities.SEA_SERPENT_ARROW.get(), world, shooter, shotFrom);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.iceandfire.sea_serpent_arrow.desc").formatted(Formatting.GRAY));
    }
}
