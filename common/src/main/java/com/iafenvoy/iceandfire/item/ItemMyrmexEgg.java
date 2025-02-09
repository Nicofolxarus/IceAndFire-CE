package com.iafenvoy.iceandfire.item;

import com.iafenvoy.iceandfire.entity.EntityMyrmexEgg;
import com.iafenvoy.iceandfire.registry.IafDataComponents;
import com.iafenvoy.iceandfire.registry.IafEntities;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class ItemMyrmexEgg extends Item {
    private final boolean isJungle;

    public ItemMyrmexEgg(boolean isJungle) {
        super(new Settings().maxCount(1));
        this.isJungle = isJungle;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        String caste;
        int eggOrdinal = 0;
        if (stack.contains(IafDataComponents.INT.get()))
            eggOrdinal = stack.get(IafDataComponents.INT.get());
        caste = switch (eggOrdinal) {
            case 1 -> "soldier";
            case 2 -> "royal";
            case 3 -> "sentinel";
            case 4 -> "queen";
            default -> "worker";
        };
        if (eggOrdinal == 4)
            tooltip.add(Text.translatable("myrmex.caste_" + caste + ".name").formatted(Formatting.LIGHT_PURPLE));
        else
            tooltip.add(Text.translatable("myrmex.caste_" + caste + ".name").formatted(Formatting.GRAY));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        assert context.getPlayer() != null;
        ItemStack itemstack = context.getPlayer().getStackInHand(context.getHand());
        BlockPos offset = context.getBlockPos().offset(context.getSide());
        EntityMyrmexEgg egg = new EntityMyrmexEgg(IafEntities.MYRMEX_EGG.get(), context.getWorld());
        int eggOrdinal = 0;
        if (itemstack.contains(IafDataComponents.INT.get()))
            eggOrdinal = itemstack.get(IafDataComponents.INT.get());
        egg.setJungle(this.isJungle);
        egg.setMyrmexCaste(eggOrdinal);
        egg.refreshPositionAndAngles(offset.getX() + 0.5, offset.getY(), offset.getZ() + 0.5, 0, 0);
        context.getPlayer();
        if (itemstack.contains(DataComponentTypes.CUSTOM_NAME))
            egg.setCustomName(itemstack.getName());
        if (!context.getWorld().isClient)
            context.getWorld().spawnEntity(egg);
        itemstack.decrement(1);
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        int eggOrdinal = 0;
        if (stack.contains(IafDataComponents.INT.get()))
            eggOrdinal = stack.get(IafDataComponents.INT.get());
        return super.hasGlint(stack) || eggOrdinal == 4;
    }
}
