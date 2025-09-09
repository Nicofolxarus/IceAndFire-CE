package com.iafenvoy.iceandfire.screen.handler;

import com.iafenvoy.iceandfire.data.DragonArmorPart;
import com.iafenvoy.iceandfire.entity.DragonBaseEntity;
import com.iafenvoy.iceandfire.registry.IafScreenHandlers;
import com.iafenvoy.iceandfire.screen.slot.BannerSlot;
import com.iafenvoy.iceandfire.screen.slot.DragonArmorSlot;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class DragonScreenHandler extends ScreenHandler {
    private final Inventory dragonInventory;
    private final DragonBaseEntity dragon;

    public DragonScreenHandler(int i, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(i, new SimpleInventory(5), playerInventory, (DragonBaseEntity) MinecraftClient.getInstance().world.getEntityById(buf.readInt()));
    }

    public DragonScreenHandler(int id, Inventory dragonInventory, PlayerInventory playerInventory, DragonBaseEntity dragon) {
        super(IafScreenHandlers.DRAGON_SCREEN.get(), id);
        this.dragonInventory = dragonInventory;
        this.dragon = dragon;
        dragonInventory.onOpen(playerInventory.player);
        this.addSlot(new BannerSlot(dragonInventory, 0, 8, 54));
        this.addSlot(new DragonArmorSlot(dragonInventory, 1, 8, 18, DragonArmorPart.HEAD));
        this.addSlot(new DragonArmorSlot(dragonInventory, 2, 8, 36, DragonArmorPart.NECK));
        this.addSlot(new DragonArmorSlot(dragonInventory, 3, 153, 18, DragonArmorPart.BODY));
        this.addSlot(new DragonArmorSlot(dragonInventory, 4, 153, 36, DragonArmorPart.TAIL));
        for (int j = 0; j < 3; ++j)
            for (int k = 0; k < 9; ++k)
                this.addSlot(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 132 + j * 18));
        for (int j = 0; j < 9; ++j)
            this.addSlot(new Slot(playerInventory, j, 8 + j * 18, 190));
    }

    @Override
    public boolean canUse(PlayerEntity playerIn) {
        return !this.dragon.hasInventoryChanged(this.dragonInventory) && this.dragonInventory.canPlayerUse(playerIn) && this.dragon.isAlive() && this.dragon.distanceTo(playerIn) < 8.0F;
    }

    @Override
    public ItemStack quickMove(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < this.dragonInventory.size()) {
                if (!this.insertItem(itemstack1, this.dragonInventory.size(), this.slots.size(), true))
                    return ItemStack.EMPTY;
            } else if (this.getSlot(1).canInsert(itemstack1) && !this.getSlot(1).hasStack()) {
                if (!this.insertItem(itemstack1, 1, 2, false))
                    return ItemStack.EMPTY;
            } else if (this.getSlot(2).canInsert(itemstack1) && !this.getSlot(2).hasStack()) {
                if (!this.insertItem(itemstack1, 2, 3, false))
                    return ItemStack.EMPTY;
            } else if (this.getSlot(3).canInsert(itemstack1) && !this.getSlot(3).hasStack()) {
                if (!this.insertItem(itemstack1, 3, 4, false))
                    return ItemStack.EMPTY;
            } else if (this.getSlot(4).canInsert(itemstack1) && !this.getSlot(4).hasStack()) {
                if (!this.insertItem(itemstack1, 4, 5, false))
                    return ItemStack.EMPTY;
            } else if (this.getSlot(0).canInsert(itemstack1)) {
                if (!this.insertItem(itemstack1, 0, 1, false))
                    return ItemStack.EMPTY;
            } else if (this.dragonInventory.size() <= 5 || !this.insertItem(itemstack1, 5, this.dragonInventory.size(), false))
                return ItemStack.EMPTY;
            if (itemstack1.isEmpty())
                slot.setStackNoCallbacks(ItemStack.EMPTY);
            else
                slot.markDirty();
        }
        return itemstack;
    }

    @Override
    public void onClosed(PlayerEntity playerIn) {
        super.onClosed(playerIn);
        this.dragonInventory.onClose(playerIn);
    }

    public DragonBaseEntity getDragon() {
        return this.dragon;
    }
}