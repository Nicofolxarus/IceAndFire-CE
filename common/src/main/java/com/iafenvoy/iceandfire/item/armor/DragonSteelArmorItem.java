package com.iafenvoy.iceandfire.item.armor;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.config.IafCommonConfig;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class DragonSteelArmorItem extends ArmorItem implements ProtectAgainstDragonItem {
    private static final Identifier[] ARMOR_MODIFIERS = new Identifier[]{
            Identifier.of(IceAndFire.MOD_ID, "dragon_steel_boots"),
            Identifier.of(IceAndFire.MOD_ID, "dragon_steel_leggings"),
            Identifier.of(IceAndFire.MOD_ID, "dragon_steel_chestplate"),
            Identifier.of(IceAndFire.MOD_ID, "dragon_steel_helmet")
    };

    public DragonSteelArmorItem(RegistryEntry<ArmorMaterial> material, Type slot) {
        super(material, slot, new Settings().maxDamage(switch (slot){
            case HELMET -> IafCommonConfig.INSTANCE.armors.dragonsteelHelmetDurability.getValue();
            case CHESTPLATE -> IafCommonConfig.INSTANCE.armors.dragonsteelChestplateDurability.getValue();
            case LEGGINGS -> IafCommonConfig.INSTANCE.armors.dragonsteelLeggingsDurability.getValue();
            case BOOTS -> IafCommonConfig.INSTANCE.armors.dragonsteelBootsDurability.getValue();
            case BODY -> 0;
        }));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.dragonscales_armor.desc").formatted(Formatting.GRAY));
    }

}
