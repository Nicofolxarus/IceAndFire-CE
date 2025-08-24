package com.iafenvoy.iceandfire.item.armor;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.registry.IafArmorMaterials;
import com.iafenvoy.iceandfire.registry.IafItems;
import com.iafenvoy.uranus.client.render.armor.IArmorTextureProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ModArmorItem extends ArmorItem implements IArmorTextureProvider {
    public ModArmorItem(RegistryEntry<ArmorMaterial> material, Type slot, int maxDamage) {
        super(material, slot, new Settings().maxDamage(maxDamage));
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        if (this == IafItems.EARPLUGS.get()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            if (calendar.get(Calendar.MONTH) + 1 == 4 && calendar.get(Calendar.DATE) == 1)
                return "item.iceandfire.air_pods";
        }
        return super.getTranslationKey(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        if (this == IafItems.EARPLUGS.get()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            if (calendar.get(Calendar.MONTH) + 1 == 4 && calendar.get(Calendar.DATE) == 1)
                tooltip.add(Text.translatable("item.iceandfire.air_pods.desc").formatted(Formatting.GREEN));
        }
    }

    @Override
    public Identifier getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlot equipmentSlot, ArmorMaterial.Layer layer, boolean b) {
        if (this.material.value() == IafArmorMaterials.SHEEP_ARMOR_MATERIAL.value())
            return Identifier.of(IceAndFire.MOD_ID, "textures/entity/armor/" + (equipmentSlot == EquipmentSlot.LEGS ? "sheep_disguise_layer_2" : "sheep_disguise_layer_1") + ".png");
        if (this.material.value() == IafArmorMaterials.EARPLUGS_ARMOR_MATERIAL.value())
            return Identifier.of(IceAndFire.MOD_ID, "textures/entity/armor/earplugs_layer_1.png");
        return null;
    }
}
