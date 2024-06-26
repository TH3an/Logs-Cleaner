package me.thunthean.Spigot;

import java.io.File;
import java.util.Date;

import static me.thunthean.Spigot.SpigotLogsCleaner.PREFIX;

public class LogsCleaner {
    private static final int keep_logs = SpigotLogsCleaner.getInstance().getConfig().getInt("Keep-logs");
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
                SpigotLogsCleaner.getInstance().getServer().getConsoleSender().sendMessage(PREFIX + "Log cleanup completed. Deleted " + deletedFiles + " logs old than " + keep_logs +  " days.");
                return;
            }

        }

        SpigotLogsCleaner.getInstance().getServer().getConsoleSender().sendMessage(PREFIX + "No logs have been purged...");

    }
}
