package com.iafenvoy.iceandfire.item.ability;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class DragonsteelFireToolAbility implements PostHitAbility {
    private final PostHitAbility ignite = new IgniteTargetAbility(IafCommonConfig.INSTANCE.tools.dragonsteelFireDuration.getValue());

    @Override
    public void active(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (this.isEnable()) {
            this.ignite.active(stack, target, attacker);
            BuiltinAbilities.TAKE_KNOCKBACK.active(stack, target, attacker);
        }
    }

    @Override
    public boolean isEnable() {
        return IafCommonConfig.INSTANCE.tools.dragonFireAbility.getValue();
    }

    @Override
    public void addDescription(List<Text> tooltip) {
        if (this.isEnable()) {
            tooltip.add(Text.translatable("dragon_sword_fire.hurt2").formatted(Formatting.DARK_RED));
        }
    }
}
