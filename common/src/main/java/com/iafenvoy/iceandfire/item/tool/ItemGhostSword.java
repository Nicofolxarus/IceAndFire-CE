package com.iafenvoy.iceandfire.item.tool;

import com.iafenvoy.iceandfire.entity.EntityGhostSword;
import com.iafenvoy.iceandfire.registry.IafEntities;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;

import java.util.List;

public class ItemGhostSword extends SwordItem {
    public ItemGhostSword() {
        super(IafToolMaterials.GHOST_SWORD_TOOL_MATERIAL, new Settings());
    }

    public static void spawnGhostSwordEntity(ItemStack stack, PlayerEntity playerEntity) {
        if (playerEntity.getItemCooldownManager().isCoolingDown(stack.getItem()))
            return;
        if (playerEntity.getStackInHand(Hand.MAIN_HAND) != stack)
            return;
        final AttributeModifiersComponent dmg = stack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
        double totalDmg = 0D;
        for (AttributeModifiersComponent.Entry modifier : dmg.modifiers()) {
            if (modifier.attribute().equals(EntityAttributes.GENERIC_ATTACK_DAMAGE))
                totalDmg += modifier.modifier().value();
        }
        playerEntity.playSound(SoundEvents.ENTITY_ZOMBIE_INFECT, 1, 1);
        EntityGhostSword shot = new EntityGhostSword(IafEntities.GHOST_SWORD.get(), playerEntity.getWorld(), playerEntity, totalDmg * 0.5F, stack);
        shot.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 1, 0.5f);
        playerEntity.getWorld().spawnEntity(shot);
        stack.damage(1, playerEntity, EquipmentSlot.MAINHAND);
        playerEntity.getItemCooldownManager().set(stack.getItem(), 10);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity targetEntity, LivingEntity attacker) {
        return super.postHit(stack, targetEntity, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.iceandfire.legendary_weapon.desc").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.iceandfire.ghost_sword.desc_0").formatted(Formatting.GRAY));
    }
}