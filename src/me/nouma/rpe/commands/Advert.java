package me.nouma.rpe.commands;

import me.nouma.rpe.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Advert implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly a player can execute this command!");
            return false;
        }
        if (args.length == 0) return false;

        StringBuilder advert = new StringBuilder();
        for (String words : args) {
            advert.append(words + " ");
        }
        Bukkit.broadcastMessage(Main.getInstance().getConfig().getString("chat.advert.chat_format").replaceAll("§PLAYER", sender.getName()).replaceAll("§MESSAGE", advert.toString()));
        return true;
    }
}
