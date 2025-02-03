package com.iafenvoy.iceandfire;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.data.DragonArmor;
import com.iafenvoy.iceandfire.data.IafSkullType;
import com.iafenvoy.iceandfire.data.SeaSerpent;
import com.iafenvoy.iceandfire.data.TrollType;
import com.iafenvoy.iceandfire.event.ServerEvents;
import com.iafenvoy.iceandfire.network.ServerNetworkHelper;
import com.iafenvoy.iceandfire.registry.*;
import com.iafenvoy.jupiter.ConfigManager;
import com.iafenvoy.jupiter.ServerConfigManager;
import com.iafenvoy.uranus.event.EntityEvents;
import com.iafenvoy.uranus.event.LivingEntityEvents;
import com.iafenvoy.uranus.event.PlayerEvents;
import dev.architectury.event.events.common.BlockEvent;
import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.platform.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IceAndFire {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "iceandfire";
    public static final String MOD_NAME = "Ice And Fire";
    public static final String VERSION;

    static {
        VERSION = Platform.getMod(IceAndFire.MOD_ID).getVersion();
    }

    public static void init() {
        ConfigManager.getInstance().registerConfigHandler(IafCommonConfig.INSTANCE);
        ServerConfigManager.registerServerConfig(IafCommonConfig.INSTANCE, ServerConfigManager.PermissionChecker.IS_OPERATOR);

        DragonArmor.initArmors();
        SeaSerpent.initArmors();
        IafSkullType.initItems();
        TrollType.initArmors();

        IafArmorMaterials.REGISTRY.register();
        IafSounds.REGISTRY.register();
        IafBlocks.REGISTRY.register();
        IafBlockEntities.REGISTRY.register();
        IafDataComponents.REGISTRY.register();
        IafEntities.REGISTRY.register();
        IafItemGroups.REGISTRY.register();
        IafItems.REGISTRY.register();
        IafLoots.REGISTRY.register();
        IafRecipes.REGISTRY.register();
        IafRecipeSerializers.REGISTRY.register();
        IafParticles.REGISTRY.register();
        IafPlacementFilters.REGISTRY.register();
        IafProcessors.REGISTRY.register();
        IafFeatures.REGISTRY.register();
        IafStructurePieces.REGISTRY.register();
        IafStructureTypes.REGISTRY.register();
        IafScreenHandlers.REGISTRY.register();
        //Trade
        IafTrades.POI_REGISTRY.register();
        IafTrades.PROFESSION_REGISTRY.register();
        IafEntities.bakeAttributes();
    }

    public static void process() {
        IafEntities.init();
        IafTrades.init();
        IafRecipes.init();
        IafFeatures.init();
        IafItemGroups.init();
        IafToolMaterials.init();

        BlockEvent.BREAK.register(ServerEvents::onBreakBlock);
        InteractionEvent.INTERACT_ENTITY.register(ServerEvents::onEntityInteract);
        InteractionEvent.RIGHT_CLICK_ITEM.register(ServerEvents::onEntityUseItem);
        InteractionEvent.RIGHT_CLICK_BLOCK.register(ServerEvents::onPlayerRightClick);
        EntityEvent.LIVING_DEATH.register(ServerEvents::onEntityDie);
        PlayerEvent.ATTACK_ENTITY.register(ServerEvents::onPlayerAttack);
        PlayerEvents.LOGGED_OUT.register(ServerEvents::onPlayerLeaveEvent);
        EntityEvents.ON_JOIN_WORLD.register(ServerEvents::onEntityJoinWorld);
        EntityEvents.START_TRACKING_TAIL.register(ServerEvents::onLivingSetTarget);
        LivingEntityEvents.DAMAGE.register(ServerEvents::onEntityDamage);
        LivingEntityEvents.FALL.register(ServerEvents::onEntityFall);

        ServerNetworkHelper.registerReceivers();
    }
}