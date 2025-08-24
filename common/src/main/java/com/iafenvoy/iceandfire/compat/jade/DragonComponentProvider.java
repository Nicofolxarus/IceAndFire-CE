package com.iafenvoy.iceandfire.compat.jade;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.DragonBaseEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum DragonComponentProvider implements IEntityComponentProvider {
    INSTANCE;

    @Override
    public Identifier getUid() {
        return Identifier.of(IceAndFire.MOD_ID, "dragon");
    }

    @Override
    public void appendTooltip(ITooltip iTooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        if (entityAccessor.getEntity() instanceof DragonBaseEntity dragon) {
            iTooltip.add(Text.translatable("dragon.stage").formatted(Formatting.GRAY).append(Text.literal(" " + dragon.getDragonStage())));
            iTooltip.add(Text.literal(dragon.getAgeInDays() + "d"));
            iTooltip.add(Text.literal(dragon.isMale() ? "Male" : "Female"));
        }
    }
}
