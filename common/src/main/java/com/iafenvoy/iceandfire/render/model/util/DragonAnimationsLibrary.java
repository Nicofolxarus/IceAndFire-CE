package com.iafenvoy.iceandfire.render.model.util;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.uranus.client.model.TabulaModel;
import com.iafenvoy.uranus.client.model.util.TabulaModelHandlerHelper;
import net.minecraft.util.Identifier;

/**
 * A library containing all animations for all dragons. Contains methods for registering and retrieving models
 */
public class DragonAnimationsLibrary {
    public static TabulaModel getModel(IEnumDragonPoses pose, IEnumDragonModelTypes modelType) {
        Identifier id = new Identifier(IceAndFire.MOD_ID, modelType.getModelType() + "dragon/" + modelType.getModelType() + "dragon_" + pose.getPose());
        TabulaModel result = TabulaModelHandlerHelper.getModel(id);
        if (result == null) IceAndFire.LOGGER.error("No model defined for {} have you registered your animations?", id);
        return result;
    }
}
