package com.iafenvoy.iceandfire.config;

import com.google.gson.JsonObject;
import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.jupiter.config.container.FileConfigContainer;
import com.iafenvoy.jupiter.config.entry.BooleanEntry;
import com.iafenvoy.jupiter.interfaces.IConfigEntry;
import net.minecraft.util.Identifier;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IafClientConfig extends FileConfigContainer {
    public static final IafClientConfig INSTANCE = new IafClientConfig();
    public static final int CURRENT_VERSION = 1;
    public final IConfigEntry<Boolean> customMainMenu = new BooleanEntry("iceandfire.customMainMenu", true).json("customMainMenu");
    public final IConfigEntry<Boolean> dragonAuto3rdPerson = new BooleanEntry("iceandfire.dragonAuto3rdPerson", false).json("dragonAuto3rdPerson");
    public final IConfigEntry<Boolean> sirenShader = new BooleanEntry("iceandfire.siren.shader", true).json("siren.shader");

    public IafClientConfig() {
        super(Identifier.of(IceAndFire.MOD_ID, "config.iceandfire.client"), "screen.iceandfire.client.title", "./config/iceandfire/iaf-client.json");
    }

    @Override
    public void init() {
        this.createTab("client", "iceandfire.client")
                .add(this.customMainMenu)
                .add(this.dragonAuto3rdPerson)
                .add(this.sirenShader);
    }

    @Override
    protected boolean shouldLoad(JsonObject obj) {
        if (!obj.has("version")) return true;
        int version = obj.get("version").getAsInt();
        if (version != CURRENT_VERSION) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                FileUtils.copyFile(new File(this.path), new File(IafCommonConfig.backupPath + "iceandfire_client_" + sdf.format(new Date()) + ".json"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.save();
            IceAndFire.LOGGER.info("Wrong client config version {} for mod {}! Automatically use version {} and backup old one.", version, IceAndFire.MOD_NAME, CURRENT_VERSION);
            return false;
        } else IceAndFire.LOGGER.info("{} client config version match.", IceAndFire.MOD_NAME);
        return true;
    }

    @Override
    protected void writeCustomData(JsonObject obj) {
        obj.addProperty("version", CURRENT_VERSION);
    }
}
