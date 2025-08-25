package com.iafenvoy.iceandfire.item.ability;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.registry.tag.IafEntityTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class FireDragonBloodToolAbility implements PostHitAbility {
    private final DamageBonusAbility damageBonus = new DamageBonusAbility(8.0F, IafEntityTags.FIRE_DRAGON, null);
    private final PostHitAbility ignite = new IgniteTargetAbility(IafCommonConfig.INSTANCE.tools.dragonBloodFireDuration.getValue());

    @Override
    public void active(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        this.damageBonus.active(stack, target, attacker);
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
        tooltip.add(Text.translatable("item.iceandfire.legendary_weapon.desc").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("dragon_sword_fire.hurt1").formatted(Formatting.GREEN));
        if (this.isEnable()) {
            tooltip.add(Text.translatable("dragon_sword_fire.hurt2").formatted(Formatting.DARK_RED));
        }
    }
}
