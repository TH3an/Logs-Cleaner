package me.thunthean.BungeeCord;

import me.thunthean.Spigot.SpigotLogsCleaner;

import java.io.File;
import java.util.Date;

import static me.thunthean.Spigot.SpigotLogsCleaner.PREFIX;

public class LogsCleaner {
    private static final int keep_logs = BungeeLogsCleaner.getInstance().getConfig().getInt("Keep-logs");
    public static void cleanupLogs() {

        File logDir = new File("logs");

        int deletedFiles = 0;

        if (logDir.exists()) {

            for (File file : logDir.listFiles()) {

                long diff = new Date().getTime() - file.lastModified();

                if (diff > SpigotLogsCleaner.getInstance().keepLogs) {
                    file.delete();
                    deletedFiles++;
                }

            }

            if (deletedFiles > 0) {
                BungeeLogsCleaner.getInstance().getLogger().info("Log cleanup completed. Deleted " + deletedFiles + " logs old than " + keep_logs +  " days.");
                return;
            }

        }

        BungeeLogsCleaner.getInstance().getLogger().info("No logs have been purged...");

    }
}
