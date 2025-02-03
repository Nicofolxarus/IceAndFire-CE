package com.iafenvoy.iceandfire.mixin;

import com.iafenvoy.iceandfire.registry.IafItems;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrewingRecipeRegistry.Builder.class)
public abstract class BrewingRecipeRegistry$BuilderMixin {
    @Shadow
    public abstract void registerPotionRecipe(RegistryEntry<Potion> input, Item ingredient, RegistryEntry<Potion> output);

    //TODO Event
    @Inject(method = "build", at = @At("HEAD"))
    private void build(CallbackInfoReturnable<BrewingRecipeRegistry> cir) {
        this.registerPotionRecipe(Potions.WATER, IafItems.SHINY_SCALES.get(), Potions.WATER_BREATHING);
    }
}
