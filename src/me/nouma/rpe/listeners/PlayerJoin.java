package me.nouma.rpe.listeners;

import me.nouma.rpe.Main;
import me.nouma.rpe.managers.Userdata;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(Main.getInstance().getConfig().getString("general.join_message").replaceAll("§PLAYER", e.getPlayer().getName()));

        Userdata userdata = new Userdata(Main.getInstance(), e.getPlayer());

        if (userdata.getConfig().get("username") == null) Main.getInstance().getLogger().warning(String.format("%s doesn't have a profile, creating one..", e.getPlayer().getName()));

        userdata.getConfig().set("username", e.getPlayer().getName());
        if (userdata.getConfig().get("job") == null) userdata.getConfig().set("job", "none");
        if (userdata.getConfig().get("character.first_name") == null) userdata.getConfig().set("character.first_name", "none");
        if (userdata.getConfig().get("character.second_name") == null) userdata.getConfig().set("character.second_name", "none");

        userdata.saveConfig();
    }
}
