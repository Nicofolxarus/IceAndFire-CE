package com.iafenvoy.iceandfire.render.item.armor;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.data.SeaSerpent;
import com.iafenvoy.iceandfire.item.armor.SeaSerpentArmorItem;
import com.iafenvoy.iceandfire.render.model.armor.SeaSerpentArmorModel;
import com.iafenvoy.uranus.client.render.armor.IArmorRendererBase;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class SeaSerpentArmorRenderer implements IArmorRendererBase<LivingEntity> {
    @Override
    public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot armorSlot, BipedEntityModel<LivingEntity> bipedEntityModel) {
        return new SeaSerpentArmorModel(armorSlot == EquipmentSlot.LEGS || armorSlot == EquipmentSlot.HEAD);
    }

    @Override
    public Identifier getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot) {
        SeaSerpent armor_type = ((SeaSerpentArmorItem) stack.getItem()).armorType;
        return Identifier.of(IceAndFire.MOD_ID, "textures/entity/armor/armor_tide_" + armor_type.getName() + (slot == EquipmentSlot.LEGS ? "_legs.png" : ".png"));
    }
}
