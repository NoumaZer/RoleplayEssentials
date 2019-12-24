package me.nouma.rpe.jobs;

import me.nouma.rpe.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class JobsLoader {

    private static Main main = Main.INSTANCE;

    public static void loadJobs() {
        FileConfiguration config = Main.INSTANCE.getJob();
        Main.INSTANCE.jobs.clear();
        config.getKeys(false).forEach(key -> {
            Main.INSTANCE.jobs.add(new Job(key, config.getString(key + ".name"), config.getString(key + ".color").replaceAll("&", "§"), config.getString(key + ".desc"), config.getDouble(key + ".salary")));
        });
    }

    public static void loadPlayers() {
        if(main.jobs.size() >= 1) {
            for (Player player : Bukkit.getOnlinePlayers()) Main.INSTANCE.playerJob.put(player, Main.INSTANCE.jobs.get(0));
            return;
        }
        Bukkit.getLogger().warning(String.format("[%s] No job found! To avoid bugs, you may disable this module in config.yml.", main.getDescription().getName()));
    }
}
