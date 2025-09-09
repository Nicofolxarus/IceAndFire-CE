package com.iafenvoy.iceandfire.loot;

import com.iafenvoy.iceandfire.entity.SeaSerpentEntity;
import com.iafenvoy.iceandfire.item.SeaSerpentScaleItem;
import com.iafenvoy.iceandfire.registry.IafItems;
import com.iafenvoy.iceandfire.registry.IafLoots;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;

import java.util.List;

public class SeaSerpentLootFunction extends ConditionalLootFunction {
    public static final MapCodec<SeaSerpentLootFunction> CODEC = RecordCodecBuilder.mapCodec((instance) -> addConditionsField(instance).apply(instance, SeaSerpentLootFunction::new));

    public SeaSerpentLootFunction(List<LootCondition> conditionsIn) {
        super(conditionsIn);
    }

    @Override
    public ItemStack process(ItemStack stack, LootContext context) {
        if (!stack.isEmpty() && context.get(LootContextParameters.THIS_ENTITY) instanceof SeaSerpentEntity seaSerpent) {
            final int ancientModifier = seaSerpent.isAncient() ? 2 : 1;
            if (stack.getItem() instanceof SeaSerpentScaleItem) {
                stack.setCount(1 + seaSerpent.getRandom().nextInt(1 + (int) Math.ceil(seaSerpent.getSeaSerpentScale() * 3 * ancientModifier)));
                return new ItemStack(seaSerpent.getEnum().scale.get(), stack.getCount());
            }
            if (stack.getItem() == IafItems.SERPENT_FANG.get()) {
                stack.setCount(1 + seaSerpent.getRandom().nextInt(1 + (int) Math.ceil(seaSerpent.getSeaSerpentScale() * 2 * ancientModifier)));
                return stack;
            }
        }
        return stack;
    }

    @Override
    public LootFunctionType<? extends ConditionalLootFunction> getType() {
        return IafLoots.SEA_SERPENT_LOOT.get();
    }
}