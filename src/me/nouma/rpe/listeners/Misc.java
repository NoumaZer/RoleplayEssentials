package me.nouma.rpe.listeners;

import me.nouma.rpe.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class Misc implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent e) {
        e.setMotd(Main.getInstance().getConfig().getString("general.motd.1") + "\n" + Main.getInstance().getConfig().getString("general.motd.2"));
    }
}
