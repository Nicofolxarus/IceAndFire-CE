package com.iafenvoy.iceandfire.mixin.datafix;

import com.iafenvoy.iceandfire.datafix.ItemDataFix;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.serialization.Codec;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public abstract class ItemStackFixer {
    @ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/DefaultedRegistry;getEntryCodec()Lcom/mojang/serialization/Codec;"))
    private static Codec<RegistryEntry<Item>> fixItemName(Codec<RegistryEntry<Item>> original) {
        return ItemDataFix.fixItemName(original);
    }
}
