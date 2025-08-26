package com.iafenvoy.iceandfire.datafix;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.Map;

public class BlockDataFix {
    private static final Map<String, String> BLOCK_NAME_FIX_MAP = ImmutableMap.<String, String>builder()
            // 1.1.2 -> 1.1.3
            .build();

    public static <A> Codec.ResultFunction<A> fixBlockName(Codec<A> codec) {
        return new Codec.ResultFunction<>() {
            @Override
            public <T> DataResult<Pair<A, T>> apply(DynamicOps<T> ops, T input, DataResult<Pair<A, T>> a) {
                if (a.isSuccess()) return a;
                MutableObject<DataResult<Pair<A, T>>> mutableObject = new MutableObject<>(a);
                ops.getMap(input).ifSuccess(map -> {
                    T t = map.get("Name");
                    if (t != null) ops.getStringValue(t).ifSuccess(s -> {
                        String s1 = BLOCK_NAME_FIX_MAP.get(s);
                        if (s1 != null) ops.mergeToMap(input, ops.createString("Name"), ops.createString(s1))
                                .ifSuccess(t1 -> mutableObject.setValue(codec.decode(ops, t1)));
                    });
                });
                return mutableObject.getValue();
            }

            @Override
            public <T> DataResult<T> coApply(DynamicOps<T> ops, A input, DataResult<T> t) {
                return t;
            }
        };
    }
}
