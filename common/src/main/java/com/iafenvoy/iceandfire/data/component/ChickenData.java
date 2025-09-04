package com.iafenvoy.iceandfire.data.component;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.impl.ComponentManager;
import com.iafenvoy.iceandfire.registry.IafItems;
import com.iafenvoy.iceandfire.registry.tag.IafEntityTags;
import com.iafenvoy.iceandfire.util.attachment.IafEntityAttachment;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;

public class ChickenData implements IafEntityAttachment<LivingEntity> {
    public static final Codec<ChickenData> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.INT.fieldOf("timeUntilNextEgg").forGetter(ChickenData::getTimeUntilNextEgg)
    ).apply(i, ChickenData::new));
    private int timeUntilNextEgg = -1;

    public ChickenData() {
    }

    private ChickenData(int timeUntilNextEgg) {
        this.timeUntilNextEgg = timeUntilNextEgg;
    }

    @Override
    public void tick(LivingEntity entity) {
        if (!IafCommonConfig.INSTANCE.cockatrice.chickensLayRottenEggs.getValue() || entity.getWorld().isClient() || !entity.getType().isIn(IafEntityTags.CHICKENS) || entity.isBaby())
            return;
        if (this.timeUntilNextEgg == -1) this.timeUntilNextEgg = this.createDefaultTime(entity.getRandom());
        if (this.timeUntilNextEgg == 0) {
            if (entity.age > 30 && entity.getRandom().nextDouble() < IafCommonConfig.INSTANCE.cockatrice.eggChance.getValue()) {
                entity.playSound(SoundEvents.ENTITY_CHICKEN_HURT, 2.0F, (entity.getRandom().nextFloat() - entity.getRandom().nextFloat()) * 0.2F + 1.0F);
                entity.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (entity.getRandom().nextFloat() - entity.getRandom().nextFloat()) * 0.2F + 1.0F);
                entity.dropItem(IafItems.ROTTEN_EGG.get(), 1);
            }
            this.timeUntilNextEgg = -1;
        } else this.timeUntilNextEgg--;
    }

    public void setTime(int timeUntilNextEgg) {
        this.timeUntilNextEgg = timeUntilNextEgg;
    }

    public int getTimeUntilNextEgg() {
        return this.timeUntilNextEgg;
    }

    private int createDefaultTime(final Random random) {
        return random.nextInt(6000) + 6000;
    }

    public static ChickenData get(LivingEntity living) {
        return ComponentManager.getChickenData(living);
    }
}
