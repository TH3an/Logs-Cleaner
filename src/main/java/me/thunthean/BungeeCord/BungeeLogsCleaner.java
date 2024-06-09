package me.thunthean.BungeeCord;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BungeeLogsCleaner extends Plugin {

    public static BungeeLogsCleaner instance;
    public int keepLogs;
    private Logger logger;
    @Override
    public void onEnable() {
        // Enable Logic
        instance = this;

        //Loading Config.yml
        Config.loadConfigs();

        //Check in config.yml
        keepLogs = getConfig().getInt("Keep-logs", 7) * 24 * 60 * 60 * 1000;

        //Checking if it enables or disables on the config.yml file
        if(getConfig().getBoolean("24hours-Check")) {
            //Perform a task to check every 24 hours
            getProxy().getScheduler().schedule(this, LogsCleaner::cleanupLogs, 0, 1, TimeUnit.DAYS);
        }

        //Print a message
        logger.info("is Enabled by thunthean");

        //Checking logs
        if(keepLogs > 0) {
            LogsCleaner.cleanupLogs();
        }

    }

    public static BungeeLogsCleaner getInstance() {
        return instance;
    }

    public Configuration getConfig() {
        return Config.config;
    }

    @Override
    public void onDisable() {
        // Disable logic
        logger.info("is Disabled");
    }
}
