package me.nouma.rpe.commands;

import me.nouma.rpe.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Core implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage("");
            sender.sendMessage("                   §b§lRoleplay Essentials §8" + Main.getInstance().getDescription().getVersion());
            sender.sendMessage("");
            sender.sendMessage("§7 /rpe §rhelp");
            sender.sendMessage("§7 /rpe §rreload");
            sender.sendMessage("");
            sender.sendMessage("§7 /§fadvert");
            sender.sendMessage("§7 /§fname");
            sender.sendMessage("");
            return true;
        }

        switch (args[0]) {
            case "reload":
                Main.getInstance().reloadConfig();
                sender.sendMessage(Main.getInstance().getConfig().getString("message.prefix") + " Config and userdata successfully reloaded!");
                break;
        }

        return true;
    }
}
