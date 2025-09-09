package com.iafenvoy.iceandfire.item;

import com.iafenvoy.iceandfire.entity.StoneStatueEntity;
import com.iafenvoy.iceandfire.item.component.StoneStatusComponent;
import com.iafenvoy.iceandfire.registry.IafDataComponents;
import com.iafenvoy.iceandfire.registry.IafEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;

import java.util.List;
import java.util.Optional;

public class StoneStatueItem extends Item {
    public StoneStatueItem() {
        super(new Settings().maxCount(1).component(IafDataComponents.STONE_STATUS.get(), new StoneStatusComponent(true, "", new NbtCompound())));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        if (stack.contains(IafDataComponents.STONE_STATUS.get())) {
            StoneStatusComponent component = stack.get(IafDataComponents.STONE_STATUS.get());
            Optional<EntityType<?>> optional = EntityType.get(component.entityType());
            if (optional.isPresent()) {
                MutableText untranslated;
                if (component.isPlayer()) untranslated = Text.translatable("entity.minecraft.player");
                else untranslated = Text.translatable(optional.get().getTranslationKey());
                tooltip.add(untranslated.formatted(Formatting.GRAY));
            }
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getSide() != Direction.UP) return ActionResult.FAIL;
        else {
            assert context.getPlayer() != null;
            ItemStack stack = context.getPlayer().getStackInHand(context.getHand());
            if (stack.contains(IafDataComponents.STONE_STATUS.get())) {
                StoneStatusComponent component = stack.get(IafDataComponents.STONE_STATUS.get());
                StoneStatueEntity statue = new StoneStatueEntity(IafEntities.STONE_STATUE.get(), context.getWorld());
                statue.readCustomDataFromNbt(component.nbt());
                statue.setTrappedEntityTypeString(component.entityType());
                double d1 = context.getPlayer().getX() - (context.getBlockPos().getX() + 0.5);
                double d2 = context.getPlayer().getZ() - (context.getBlockPos().getZ() + 0.5);
                float yaw = (float) (MathHelper.atan2(d2, d1) * (180F / (float) Math.PI)) - 90;
                statue.prevYaw = yaw;
                statue.setYaw(yaw);
                statue.headYaw = yaw;
                statue.bodyYaw = yaw;
                statue.prevBodyYaw = yaw;
                statue.updatePositionAndAngles(context.getBlockPos().getX() + 0.5, context.getBlockPos().getY() + 1, context.getBlockPos().getZ() + 0.5, yaw, 0);
                if (!context.getWorld().isClient) context.getWorld().spawnEntity(statue);
                statue.setCrackAmount(0);
                if (!context.getPlayer().isCreative()) stack.decrement(1);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.SUCCESS;
    }
}
