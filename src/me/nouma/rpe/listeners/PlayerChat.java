package me.nouma.rpe.listeners;

import me.nouma.rpe.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (e.getMessage().charAt(0) == Main.getInstance().getConfig().getString("chat.ooc.prefix").toCharArray()[0]) {
            String m = Main.getInstance().getConfig().getString("chat.ooc.chat_format");
            m = m.replaceAll("§PLAYER", e.getPlayer().getName());
            m = m.replaceAll("§DISPLAYNAME", e.getPlayer().getDisplayName());
            m = m.replaceAll("§MESSAGE", e.getMessage().substring(1));
            e.setFormat(m);
        } else {
            e.setCancelled(true);
            double d = Main.getInstance().getConfig().getDouble("chat.rp.radius");
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (players.getLocation().distance(e.getPlayer().getLocation()) <= d) {
                    players.sendMessage(Main.getInstance().getConfig().getString("chat.rp.chat_format").replaceAll("§PLAYER", e.getPlayer().getName()).replaceAll("§MESSAGE", e.getMessage()));
                    /*if (!Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays") && Main.getInstance().getConfig().getBoolean("chat.rp.use_holograms")) {
                        HologramsAPI.createHologram(Main.getInstance(), e.getPlayer().getLocation()).appendTextLine("ouiiiiiiiiiiiiiiiii");
                    }*/
                }
            }
        }
    }
}
