package com.iafenvoy.iceandfire.item.armor;

import com.iafenvoy.iceandfire.data.SeaSerpent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class SeaSerpentArmorItem extends ArmorItem implements ArmorFinder {
    public final SeaSerpent armorType;

    public SeaSerpentArmorItem(SeaSerpent armorType, RegistryEntry<ArmorMaterial> material, Type slot) {
        super(material, slot, new Settings().maxDamage(switch (slot) {
            case HELMET -> 330;
            case CHESTPLATE -> 480;
            case LEGGINGS -> 450;
            case BOOTS -> 390;
            case BODY -> 0;
        }));
        this.armorType = armorType;
    }

    @Override
    public String getTranslationKey() {
        return switch (this.type) {
            case HELMET -> "item.iceandfire.sea_serpent_helmet";
            case CHESTPLATE -> "item.iceandfire.sea_serpent_chestplate";
            case LEGGINGS -> "item.iceandfire.sea_serpent_leggings";
            case BOOTS -> "item.iceandfire.sea_serpent_boots";
            case BODY -> "???";
        };
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (entity instanceof PlayerEntity player && this.isEquipped(player, stack)) {
            int headMod = player.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof SeaSerpentArmorItem ? 1 : 0;
            int chestMod = player.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof SeaSerpentArmorItem ? 1 : 0;
            int legMod = player.getEquippedStack(EquipmentSlot.LEGS).getItem() instanceof SeaSerpentArmorItem ? 1 : 0;
            int footMod = player.getEquippedStack(EquipmentSlot.FEET).getItem() instanceof SeaSerpentArmorItem ? 1 : 0;
            int modifier = headMod + chestMod + legMod + footMod - 1;
            if (modifier >= 0) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 50, 0, false, false));
                if (player.isTouchingWaterOrRain())
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 50, modifier, false, false));
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("sea_serpent." + this.armorType.getName()).formatted(this.armorType.getColor()));
        tooltip.add(Text.translatable("item.iceandfire.sea_serpent_armor.desc_0").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.iceandfire.sea_serpent_armor.desc_1").formatted(Formatting.GRAY));
    }
}
