package com.iafenvoy.iceandfire.item.armor;

import com.iafenvoy.iceandfire.data.TrollType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class TrollArmorItem extends ArmorItem {
    public final TrollType troll;

    public TrollArmorItem(TrollType troll, Type slot) {
        super(troll.getMaterial(), slot, new Settings().maxDamage(switch (slot) {
            case HELMET -> 220;
            case CHESTPLATE -> 320;
            case LEGGINGS -> 300;
            case BOOTS -> 260;
            case BODY -> 0;
        }));
        this.troll = troll;
    }

    public static String getName(TrollType troll, EquipmentSlot slot) {
        return "%s_troll_leather_%s".formatted(troll.getName(), getArmorPart(slot));
    }

    private static String getArmorPart(EquipmentSlot slot) {
        return switch (slot) {
            case HEAD -> "helmet";
            case CHEST -> "chestplate";
            case LEGS -> "leggings";
            case FEET -> "boots";
            default -> "";
        };
    }

    @Override
    public RegistryEntry<ArmorMaterial> getMaterial() {
        return this.troll.getMaterial();
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.iceandfire.troll_leather_armor_" + getArmorPart(this.type.getEquipmentSlot()) + ".desc").formatted(Formatting.GREEN));
    }
}
