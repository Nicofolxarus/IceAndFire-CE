package com.iafenvoy.iceandfire.item.armor;

import com.iafenvoy.iceandfire.registry.IafArmorMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlindfoldItem extends ArmorItem {
    public BlindfoldItem() {
        super(IafArmorMaterials.BLINDFOLD, Type.HELMET, new Settings().maxDamage(55));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (entity instanceof PlayerEntity player && player.getEquippedStack(this.getSlotType()) == stack)
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 50, 0, false, false));
    }
}
