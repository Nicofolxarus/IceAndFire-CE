package com.iafenvoy.iceandfire.item.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.iafenvoy.iceandfire.IceAndFire;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class ItemDragonSteelArmor extends ArmorItem implements IProtectAgainstDragonItem {
    private static final Identifier[] ARMOR_MODIFIERS = new Identifier[]{
            Identifier.of(IceAndFire.MOD_ID, "dragon_steel_boots"),
            Identifier.of(IceAndFire.MOD_ID, "dragon_steel_leggings"),
            Identifier.of(IceAndFire.MOD_ID, "dragon_steel_chestplate"),
            Identifier.of(IceAndFire.MOD_ID, "dragon_steel_helmet")
    };
    private final ArmorMaterial material;
    private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifierMultimap;

    public ItemDragonSteelArmor(RegistryEntry<ArmorMaterial> material, int renderIndex, Type slot) {
        super(material, slot, new Settings());
        this.material = material.value();
        this.attributeModifierMultimap = this.createAttributeMap();
    }

    //Workaround for armor attributes being registered before the config gets loaded
    private Multimap<EntityAttribute, EntityAttributeModifier> createAttributeMap() {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        Identifier id = ARMOR_MODIFIERS[this.type.getEquipmentSlot().getEntitySlotId()];
        builder.put(EntityAttributes.GENERIC_ARMOR.value(), new EntityAttributeModifier(id, this.material.getProtection(this.type), EntityAttributeModifier.Operation.ADD_VALUE));
        builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS.value(), new EntityAttributeModifier(id, this.material.toughness(), EntityAttributeModifier.Operation.ADD_VALUE));
        if (this.material.knockbackResistance() > 0)
            builder.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE.value(), new EntityAttributeModifier(id, this.material.knockbackResistance(), EntityAttributeModifier.Operation.ADD_VALUE));
        return builder.build();
    }

    private Multimap<EntityAttribute, EntityAttributeModifier> getOrUpdateAttributeMap() {
        //If the armor values have changed recreate the map
        //There might be a prettier way of accomplishing this but it works
        EntityAttribute armorAttribute = EntityAttributes.GENERIC_ARMOR.value();
        if (this.attributeModifierMultimap.containsKey(armorAttribute)
                && !this.attributeModifierMultimap.get(armorAttribute).isEmpty()
                && this.attributeModifierMultimap.get(armorAttribute).toArray()[0] instanceof EntityAttributeModifier
                && ((EntityAttributeModifier) this.attributeModifierMultimap.get(armorAttribute).toArray()[0]).value() != this.getProtection()
        )
            this.attributeModifierMultimap = this.createAttributeMap();
        return this.attributeModifierMultimap;
    }

//    @Override
//    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
//        if (this.type != null)
//            return this.getMaterial().value().getDurability(this.type);
//        return super.getMaxUseTime(stack, user);
//    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.dragonscales_armor.desc").formatted(Formatting.GRAY));
    }

//    @Override
//    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot) {
//        return equipmentSlot == this.type.getEquipmentSlot() ? this.getOrUpdateAttributeMap() : super.getAttributeModifiers(equipmentSlot);
//    }

    @Override
    public int getProtection() {
        if (this.material != null)
            return this.material.getProtection(this.getType());
        return super.getProtection();
    }
}
