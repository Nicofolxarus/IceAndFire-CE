package com.iafenvoy.iceandfire.item;

import com.iafenvoy.iceandfire.item.component.DragonSkullComponent;
import com.iafenvoy.iceandfire.data.DragonType;
import com.iafenvoy.iceandfire.entity.DragonSkullEntity;
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
import net.minecraft.util.math.Direction;

import java.util.List;

public class DragonSkullItem extends Item {
    private final DragonType dragonType;

    public DragonSkullItem(DragonType dragonType) {
        super(new Settings().maxCount(1).component(IafDataComponents.DRAGON_SKULL.get(), new DragonSkullComponent(4, 75)));
        this.dragonType = dragonType;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        String s = "dragon." + this.dragonType.name();
        tooltip.add(Text.translatable(s).formatted(Formatting.GRAY));
        if (stack.contains(IafDataComponents.DRAGON_SKULL.get()))
            tooltip.add(Text.translatable("dragon.stage").formatted(Formatting.GRAY).append(Text.literal(" " + stack.get(IafDataComponents.DRAGON_SKULL.get()).stage())));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        assert context.getPlayer() != null;
        ItemStack stack = context.getPlayer().getStackInHand(context.getHand());
        /*
         * DragonEggEntity egg = new DragonEggEntity(worldIn);
         * egg.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() +
         * 0.5); if(!worldIn.isRemote){ worldIn.spawnEntityInWorld(egg); }
         */
        if (stack.contains(IafDataComponents.DRAGON_SKULL.get())) {
            DragonSkullComponent component = stack.get(IafDataComponents.DRAGON_SKULL.get());
            DragonSkullEntity skull = new DragonSkullEntity(IafEntities.DRAGON_SKULL.get(), context.getWorld());
            skull.setDragonType(this.dragonType.name());
            skull.setStage(component.stage());
            skull.setDragonAge(component.dragonAge());
            BlockPos offset = context.getBlockPos().offset(context.getSide(), 1);
            skull.refreshPositionAndAngles(offset.getX() + 0.5, offset.getY(), offset.getZ() + 0.5, 0, 0);
            float yaw = context.getPlayer().getYaw();
            if (context.getSide() != Direction.UP)
                yaw = context.getPlayer().getHorizontalFacing().asRotation();
            skull.setYaw(yaw);
            if (stack.contains(DataComponentTypes.CUSTOM_NAME))
                skull.setCustomName(stack.getName());
            if (!context.getWorld().isClient)
                context.getWorld().spawnEntity(skull);
            if (!context.getPlayer().isCreative())
                stack.decrement(1);
        }
        return ActionResult.SUCCESS;
    }
}
