package com.iafenvoy.iceandfire.item.tool;

import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import com.iafenvoy.uranus.object.RegistryHelper;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class ItemHippogryphSword extends SwordItem {
    public ItemHippogryphSword() {
        super(IafToolMaterials.HIPPOGRYPH_SWORD_TOOL_MATERIAL, new Settings().component(DataComponentTypes.ATTRIBUTE_MODIFIERS, createAttributeModifiers(IafToolMaterials.HIPPOGRYPH_SWORD_TOOL_MATERIAL, 3, -2.4F)));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity targetEntity, LivingEntity attacker) {
        float f = (float) attacker.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).getValue();
        float f3 = 1.0F + getMultiplier(EnchantmentHelper.getEquipmentLevel(RegistryHelper.getEnchantment(attacker.getRegistryManager(), Enchantments.SWEEPING_EDGE), attacker)) * f;
        if (attacker instanceof PlayerEntity player) {
            for (LivingEntity LivingEntity : attacker.getWorld().getNonSpectatingEntities(LivingEntity.class, targetEntity.getBoundingBox().expand(1.0D, 0.25D, 1.0D)))
                if (LivingEntity != player && LivingEntity != targetEntity && !attacker.isTeammate(LivingEntity) && attacker.squaredDistanceTo(LivingEntity) < 9.0D) {
                    LivingEntity.takeKnockback(0.4F, MathHelper.sin(attacker.getYaw() * 0.017453292F), -MathHelper.cos(attacker.getYaw() * 0.017453292F));
                    LivingEntity.damage(attacker.getWorld().getDamageSources().playerAttack(player), f3);
                }
            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
            player.spawnSweepAttackParticles();
        }
        return super.postHit(stack, targetEntity, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.iceandfire.legendary_weapon.desc").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.iceandfire.hippogryph_sword.desc_0").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.iceandfire.hippogryph_sword.desc_1").formatted(Formatting.GRAY));
    }

    public static float getMultiplier(int level) {
        return 1.0F - 1.0F / (float) (level + 1);
    }
}
