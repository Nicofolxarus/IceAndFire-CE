package com.iafenvoy.iceandfire.render.armor;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.data.DragonArmor;
import com.iafenvoy.iceandfire.data.DragonType;
import com.iafenvoy.iceandfire.item.armor.ItemScaleArmor;
import com.iafenvoy.iceandfire.render.model.armor.ModelFireDragonScaleArmor;
import com.iafenvoy.iceandfire.render.model.armor.ModelIceDragonScaleArmor;
import com.iafenvoy.iceandfire.render.model.armor.ModelLightningDragonScaleArmor;
import com.iafenvoy.uranus.client.render.armor.IArmorRendererBase;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ScaleArmorRenderer implements IArmorRendererBase<LivingEntity> {
    @Override
    public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot armorSlot, BipedEntityModel<LivingEntity> bipedEntityModel) {
        boolean inner = armorSlot == EquipmentSlot.LEGS || armorSlot == EquipmentSlot.HEAD;
        if (itemStack.getItem() instanceof ItemScaleArmor scaleArmor) {
            DragonType dragonType = scaleArmor.armorType.getColor().dragonType();
            if (DragonType.FIRE == dragonType) return new ModelFireDragonScaleArmor(inner);
            if (DragonType.ICE == dragonType) return new ModelIceDragonScaleArmor(inner);
            if (DragonType.LIGHTNING == dragonType) return new ModelLightningDragonScaleArmor(inner);
        }
        return null;
    }

    @Override
    public Identifier getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot) {
        DragonArmor armor_type = ((ItemScaleArmor) stack.getItem()).armorType;
        return Identifier.of(IceAndFire.MOD_ID, "textures/entity/armor/armor_" + armor_type.getColor().name() + (slot == EquipmentSlot.LEGS ? "_legs.png" : ".png"));
    }
}
