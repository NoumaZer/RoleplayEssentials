package me.nouma.rpe.commands;

import me.nouma.rpe.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class PlayerListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("There are " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + " player(s) online:");
        if(Main.INSTANCE.getConfig().getBoolean("modules.jobs")) {
            Main.INSTANCE.jobs.forEach(job -> {
                sender.sendMessage(" " + job.getColor() + job.getName() + ":");
                for(Player player : Bukkit.getOnlinePlayers()) {
                    if(Main.INSTANCE.playerJob.get(player).equals(job)) {
                        sender.sendMessage(" - " + player.getName());
                    }
                }
            });
        } else {
            StringBuilder builder = new StringBuilder();
            Random random = new Random();
            for(Player player : Bukkit.getOnlinePlayers()) {
                ChatColor color = ChatColor.getByChar(Integer.toHexString(random.nextInt(16)));
                builder.append(color).append(player.getName()).append(" ");
            }
            sender.sendMessage(builder.toString());
        }
        return false;
    }
}
