package com.iafenvoy.iceandfire.item.armor;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.registry.IafArmorMaterials;
import com.iafenvoy.uranus.client.render.armor.IArmorTextureProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class BlindfoldItem extends ArmorItem implements IArmorTextureProvider, ArmorFinder {
    public BlindfoldItem() {
        super(IafArmorMaterials.BLINDFOLD_ARMOR_MATERIAL, Type.HELMET, new Settings().maxDamage(55));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (entity instanceof PlayerEntity player && this.isEquipped(player, stack))
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 50, 0, false, false));
    }

    @Override
    public Identifier getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlot equipmentSlot, ArmorMaterial.Layer layer, boolean b) {
        return Identifier.of(IceAndFire.MOD_ID, "textures/entity/armor/blindfold_layer_1.png");
    }
}
