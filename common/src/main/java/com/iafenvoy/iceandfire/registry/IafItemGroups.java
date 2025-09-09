package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

import java.util.function.Supplier;

public final class IafItemGroups {
    public static final DeferredRegister<ItemGroup> REGISTRY = DeferredRegister.create(IceAndFire.MOD_ID, RegistryKeys.ITEM_GROUP);
    public static final RegistrySupplier<ItemGroup> BLOCKS = register("blocks", () -> CreativeTabRegistry.create(Text.translatable("itemGroup." + IceAndFire.MOD_ID + ".blocks"), () -> new ItemStack(IafBlocks.DRAGON_SCALE_RED.get())));
    public static final RegistrySupplier<ItemGroup> ITEMS = register("items", () -> CreativeTabRegistry.create(Text.translatable("itemGroup." + IceAndFire.MOD_ID + ".items"), () -> new ItemStack(IafItems.DRAGON_SKULL_FIRE.get())));
    public static final RegistrySupplier<ItemGroup> TOOLS_WEAPONS = register("tools_weapons", () -> CreativeTabRegistry.create(Text.translatable("itemGroup." + IceAndFire.MOD_ID + ".tools_weapons"), () -> new ItemStack(IafItems.DRAGONSTEEL_LIGHTNING_SWORD.get())));
    public static final RegistrySupplier<ItemGroup> ARMORS = register("armors", () -> CreativeTabRegistry.create(Text.translatable("itemGroup." + IceAndFire.MOD_ID + ".armors"), () -> new ItemStack(IafItems.DRAGONSTEEL_FIRE_HELMET.get())));

    private static RegistrySupplier<ItemGroup> register(String name, Supplier<ItemGroup> group) {
        return REGISTRY.register(name, group);
    }
}
