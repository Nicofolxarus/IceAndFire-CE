package com.iafenvoy.iceandfire.render.model.util;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.uranus.client.model.TabulaModel;
import com.iafenvoy.uranus.client.model.util.TabulaModelHandlerHelper;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A library containing all animations for all dragons. Contains methods for registering and retrieving models
 */
public class DragonAnimationsLibrary {
    private static final Map<Identifier, TabulaModel> CACHES = new LinkedHashMap<>();

    public static TabulaModel getModel(IEnumDragonPoses pose, IEnumDragonModelTypes modelType) {
        Identifier id = new Identifier(IceAndFire.MOD_ID, modelType.getModelType() + "dragon/" + modelType.getModelType() + "dragon_" + pose.getPose());
        if (CACHES.containsKey(id)) return CACHES.get(id);
        TabulaModel result = TabulaModelHandlerHelper.getModel(id);
        if (result == null) IceAndFire.LOGGER.error("No model defined for {} have you registered your animations?", id);
        CACHES.put(id, result);
        return result;
    }
}
