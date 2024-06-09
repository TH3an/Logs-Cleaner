package me.thunthean.BungeeCord;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Logger;

public class Config {
    private static File configFile;
    public static Configuration config;
    public static void loadConfigs() {

        Logger logger = BungeeLogsCleaner.getInstance().getLogger();

        try {
            if (!BungeeLogsCleaner.getInstance().getDataFolder().exists()) {
                logger.warning("Plugin data folder does not exist, creating it...");
                BungeeLogsCleaner.getInstance().getDataFolder().mkdir();
            }

            configFile = new File(BungeeLogsCleaner.getInstance().getDataFolder(), "config.yml");

            if (!configFile.exists()) {
                logger.warning("Config file not found, creating default...");
                InputStream in = BungeeLogsCleaner.getInstance().getResourceAsStream("config.yml");
                Files.copy(in, configFile.toPath());
            }

            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
            saveConfig();

        } catch (IOException e) {
            config = null;
            logger.warning("Failed to load configurations! Your config may be broken. "
                    + "Remember that some characters like tabs are not allowed in .yml files! "
                    + "You can try fixing your current config or delete it to generate the default config.");
            e.printStackTrace();
        }
    }

    private static void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
