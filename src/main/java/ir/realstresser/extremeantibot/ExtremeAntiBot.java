package ir.realstresser.extremeantibot;

import net.md_5.bungee.api.plugin.Plugin;
public class ExtremeAntiBot extends Plugin {
    private static ExtremeAntiBot instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("----------------------------------------");
        getLogger().info("ExtremeAntiBot made by XyPz with love!");
        getLogger().info("Action: Enabling...");
        getLogger().info("----------------------------------------");
        InitHandler.Init();
    }
    public static ExtremeAntiBot getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        getLogger().info("----------------------------------------");
        getLogger().info("ExtremeAntiBot made by XyPz with love!");
        getLogger().info("Action: Disabling...");
        getLogger().info("----------------------------------------");
        InitHandler.Shutdown();
    }
}
