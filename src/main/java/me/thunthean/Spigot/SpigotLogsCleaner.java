package me.thunthean.Spigot;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class SpigotLogsCleaner extends JavaPlugin {

    public static SpigotLogsCleaner instance;
    public static final String PREFIX = ChatColor.translateAlternateColorCodes('&', "&8[&cLogs-Cleaner&8] > &f");
    public int keepLogs = getConfig().getInt("Keep-logs", 7) * 24 * 60 * 60 * 1000;
    private Logger logger;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        //Print a message
        logger.info("is Enabled by thunthean");

        //Perform a logs cleaning!
        if(keepLogs > 0) {
            LogsCleaner.cleanupLogs();
        }

        if(getConfig().getBoolean("24hours-Check")) {
            //Checking Logs every 24 hours
            getServer().getScheduler().runTaskTimerAsynchronously(this, LogsCleaner::cleanupLogs, 0L, 20L * 60L * 60L * 24L);
        }

    }


    public static SpigotLogsCleaner getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("is Disabled");
    }
}
