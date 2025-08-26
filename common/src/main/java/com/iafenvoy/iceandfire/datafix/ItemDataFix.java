package com.iafenvoy.iceandfire.datafix;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Map;

public class ItemDataFix {
    private static final Map<String, String> ITEM_NAME_FIX_MAP = ImmutableMap.<String, String>builder()
            // 1.1.2 -> 1.1.3
            .build();

    public static Codec<RegistryEntry<Item>> fixItemName(Codec<RegistryEntry<Item>> codec) {
        return codec.mapResult(new Codec.ResultFunction<>() {
            @Override
            public <T> DataResult<Pair<RegistryEntry<Item>, T>> apply(DynamicOps<T> ops, T input, DataResult<Pair<RegistryEntry<Item>, T>> a) {
                if (a.isSuccess()) return a;
                return ops.getStringValue(input).result().map(s -> {
                    String s1 = ITEM_NAME_FIX_MAP.get(s);
                    return s1 == null ? a : codec.decode(ops, ops.createString(s1));
                }).orElse(a);
            }

            @Override
            public <T> DataResult<T> coApply(DynamicOps<T> ops, RegistryEntry<Item> input, DataResult<T> t) {
                return t;
            }
        });
    }
}
