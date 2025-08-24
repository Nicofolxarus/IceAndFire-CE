package com.iafenvoy.iceandfire.screen.slot;

import com.iafenvoy.iceandfire.data.DragonArmorPart;
import com.iafenvoy.iceandfire.item.DragonArmorItem;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class DragonArmorSlot extends Slot {
    private final DragonArmorPart expectedArmor;

    public DragonArmorSlot(Inventory inventory, int index, int x, int y, DragonArmorPart expectedArmor) {
        super(inventory, index, x, y);
        this.expectedArmor = expectedArmor;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return super.canInsert(stack) && !stack.isEmpty() && stack.getItem() instanceof DragonArmorItem armor && armor.dragonSlot == this.expectedArmor;
    }
}
