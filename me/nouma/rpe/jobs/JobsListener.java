package me.nouma.rpe.jobs;

import me.nouma.rpe.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JobsListener implements Listener {

    private Main main = Main.INSTANCE;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(main.jobs.size() >= 1) {
            main.playerJob.put(e.getPlayer(), main.jobs.get(0));
            return;
        }
        Bukkit.getLogger().warning(String.format("[%s] No job found! To avoid bugs, you may disable this module in config.yml.", main.getDescription().getName()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        main.playerJob.remove(e.getPlayer());
    }
}
