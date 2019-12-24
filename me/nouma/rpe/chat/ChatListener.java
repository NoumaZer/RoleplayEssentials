package me.nouma.rpe.chat;

import me.nouma.rpe.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private Main main = Main.INSTANCE;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();

        if(message.charAt(0) != Main.INSTANCE.getConfig().getString("chat.rp.prefix").charAt(0) && (main.getConfig().getBoolean("chat.ooc.default") || message.charAt(0) == Main.INSTANCE.getConfig().getString("chat.ooc.prefix").charAt(0))) {
            if(message.charAt(0) == Main.INSTANCE.getConfig().getString("chat.ooc.prefix").charAt(0)) message = message.substring(1);
            e.setFormat(Main.INSTANCE.getConfig().getString("chat.ooc.format").replaceAll("\\$PLAYER", player.getName()).replaceAll("\\$MESSAGE", message));
        } else {
            e.setCancelled(true);
            if(message.charAt(0) == Main.INSTANCE.getConfig().getString("chat.rp.prefix").charAt(0)) message = message.substring(1);
            ChatCommands.rpChat(player, message);
        }
    }
}
