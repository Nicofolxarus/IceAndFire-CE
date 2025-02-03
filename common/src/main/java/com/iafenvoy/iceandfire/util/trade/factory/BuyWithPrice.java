package com.iafenvoy.iceandfire.util.trade.factory;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BuyWithPrice implements TradeOffers.Factory {
    private final ItemStack input1;
    private final ItemStack input2;
    private final ItemStack output;
    private final int maxUses;
    private final int experience;
    private final float multiplier;

    public BuyWithPrice(ItemStack input, ItemStack output, int maxUses, int experience, float priceMultiplier) {
        this(input, null, output, maxUses, experience, priceMultiplier);
    }

    public BuyWithPrice(ItemStack input1, ItemStack input2, ItemStack output, int maxUses, int experience, float priceMultiplier) {
        this.input1 = input1;
        this.input2 = input2;
        this.output = output;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = priceMultiplier;
    }

    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
        if (this.input2 == null)
            return new TradeOffer(new TradedItem(this.input1.getItem(), this.input1.getCount()), this.output, this.maxUses, this.experience, this.multiplier);
        return new TradeOffer(new TradedItem(this.input1.getItem(), this.input1.getCount()), Optional.of(new TradedItem(this.input2.getItem(), this.input2.getCount())), this.output, this.maxUses, this.experience, this.multiplier);
    }
}
