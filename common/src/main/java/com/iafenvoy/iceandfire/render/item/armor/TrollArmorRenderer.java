package com.iafenvoy.iceandfire.render.item.armor;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.data.TrollType;
import com.iafenvoy.iceandfire.item.armor.TrollArmorItem;
import com.iafenvoy.iceandfire.render.model.armor.TrollArmorModel;
import com.iafenvoy.uranus.client.render.armor.IArmorRendererBase;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class TrollArmorRenderer implements IArmorRendererBase<LivingEntity> {
    @Override
    public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot armorSlot, BipedEntityModel<LivingEntity> bipedEntityModel) {
        return new TrollArmorModel(armorSlot == EquipmentSlot.LEGS || armorSlot == EquipmentSlot.HEAD);
    }

    @Override
    public Identifier getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot) {
        TrollType troll = ((TrollArmorItem) stack.getItem()).troll;
        return Identifier.of(IceAndFire.MOD_ID, "textures/entity/armor/armor_troll_" + troll.getName() + (slot == EquipmentSlot.LEGS ? "_legs.png" : ".png"));
    }
}
