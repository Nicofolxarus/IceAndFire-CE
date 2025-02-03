package com.iafenvoy.iceandfire.item.tool;

import com.iafenvoy.iceandfire.data.TrollType;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemTrollWeapon extends SwordItem {
    public final TrollType.ITrollWeapon weapon;

    public ItemTrollWeapon(TrollType.ITrollWeapon weapon) {
        super(IafToolMaterials.TROLL_WEAPON_TOOL_MATERIAL, new Settings());
        this.weapon = weapon;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player)
            return player.getAttackCooldownProgress(0) < 0.95 || player.handSwingProgress != 0;
        else return super.postHit(stack, target, attacker);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player && selected)
            if (player.getAttackCooldownProgress(0) < 0.95 && player.handSwingProgress > 0)
                player.handSwingTicks--;
    }

    public boolean onEntitySwing(LivingEntity LivingEntity, ItemStack stack) {
        if (LivingEntity instanceof PlayerEntity player)
            if (player.getAttackCooldownProgress(0) < 1 && player.handSwingProgress > 0)
                return true;
            else
                player.handSwingTicks = -1;
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.iceandfire.legendary_weapon.desc").formatted(Formatting.GRAY));
    }
}
