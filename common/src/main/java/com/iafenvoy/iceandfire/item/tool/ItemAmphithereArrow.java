package com.iafenvoy.iceandfire.item.tool;

import com.iafenvoy.iceandfire.entity.EntityAmphithereArrow;
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

public class ItemAmphithereArrow extends ArrowItem {
    public ItemAmphithereArrow() {
        super(new Item.Settings());
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
        return new EntityAmphithereArrow(IafEntities.AMPHITHERE_ARROW.get(), shooter, world, shotFrom);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.iceandfire.amphithere_arrow.desc").formatted(Formatting.GRAY));
    }
}
