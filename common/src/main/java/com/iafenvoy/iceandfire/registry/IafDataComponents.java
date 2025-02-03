package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.component.DragonHornComponent;
import com.iafenvoy.iceandfire.component.DragonSkullComponent;
import com.iafenvoy.iceandfire.component.StoneStatusComponent;
import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.component.ComponentType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Uuids;

import java.util.List;
import java.util.UUID;

public final class IafDataComponents {
    public static final DeferredRegister<ComponentType<?>> REGISTRY = DeferredRegister.create(IceAndFire.MOD_ID, RegistryKeys.DATA_COMPONENT_TYPE);

    public static final RegistrySupplier<ComponentType<Integer>> INT = register("int", ComponentType.<Integer>builder()
            .codec(Codec.INT)
            .packetCodec(PacketCodecs.INTEGER)
    );
    public static final RegistrySupplier<ComponentType<Boolean>> BOOL = register("bool", ComponentType.<Boolean>builder()
            .codec(Codec.BOOL)
            .packetCodec(PacketCodecs.BOOL)
    );
    public static final RegistrySupplier<ComponentType<String>> STRING = register("string", ComponentType.<String>builder()
            .codec(Codec.STRING)
            .packetCodec(PacketCodecs.STRING)
    );
    public static final RegistrySupplier<ComponentType<UUID>> UUID = register("uuid", ComponentType.<UUID>builder()
            .codec(Uuids.CODEC)
            .packetCodec(Uuids.PACKET_CODEC)
    );
    public static final RegistrySupplier<ComponentType<NbtCompound>> NBT_COMPOUND = register("nbt_compound", ComponentType.<NbtCompound>builder()
            .codec(NbtCompound.CODEC)
            .packetCodec(PacketCodecs.codec(NbtCompound.CODEC))
    );

    public static final RegistrySupplier<ComponentType<List<String>>> BESTIARY_PAGES = register("bestiary_pages", ComponentType.<List<String>>builder()
            .codec(Codec.STRING.listOf())
            .packetCodec(PacketCodecs.codec(Codec.STRING.listOf())));
    public static final RegistrySupplier<ComponentType<DragonHornComponent>> DRAGON_HORN = register("dragon_horn", ComponentType.<DragonHornComponent>builder()
            .codec(DragonHornComponent.CODEC)
            .packetCodec(PacketCodecs.codec(DragonHornComponent.CODEC))
    );
    public static final RegistrySupplier<ComponentType<DragonSkullComponent>> DRAGON_SKULL = register("dragon_skull", ComponentType.<DragonSkullComponent>builder()
            .codec(DragonSkullComponent.CODEC)
            .packetCodec(PacketCodecs.codec(DragonSkullComponent.CODEC))
    );
    public static final RegistrySupplier<ComponentType<StoneStatusComponent>> STONE_STATUS = register("stone_status", ComponentType.<StoneStatusComponent>builder()
            .codec(StoneStatusComponent.CODEC)
            .packetCodec(PacketCodecs.codec(StoneStatusComponent.CODEC))
    );

    public static <T> RegistrySupplier<ComponentType<T>> register(String id, ComponentType.Builder<T> builder) {
        return REGISTRY.register(id, builder::build);
    }
}
