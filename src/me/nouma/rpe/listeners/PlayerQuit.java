package me.nouma.rpe.listeners;

import me.nouma.rpe.Main;
import me.nouma.rpe.managers.Userdata;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(Main.getInstance().getConfig().getString("general.quit_message").replaceAll("§PLAYER", e.getPlayer().getName()));

        Userdata userdata = new Userdata(Main.getInstance(), e.getPlayer());

        userdata.saveConfig();
    }
}
