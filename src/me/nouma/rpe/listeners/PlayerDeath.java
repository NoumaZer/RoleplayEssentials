package me.nouma.rpe.listeners;

import me.nouma.rpe.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if(Main.getInstance().getConfig().getBoolean("death.force_respawn")) p.spigot().respawn();
    }
}
