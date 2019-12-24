package me.nouma.rpe.commands;

import me.nouma.rpe.Main;
import me.nouma.rpe.MessageFile;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    private Main main = Main.INSTANCE;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length >= 1) {
            if(args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                if(!sender.hasPermission("rpe.reload.use")) {
                    sender.sendMessage(MessageFile.returnWithPrefix(main.getMessage().getString("no_permission")));
                    return true;
                }
                for(Player player : Bukkit.getOnlinePlayers()) if(!player.hasPermission("rpe.reload.bypass_kick")) player.kickPlayer(MessageFile.returnWithPrefix(main.getMessage().getString("kick_while_reloading")));
                main.reloadConfig();
                main.moduleThing();
                sender.sendMessage(MessageFile.returnWithPrefix(main.getMessage().getString("plugin_reloaded")));
                return true;
            }
        }

        sender.sendMessage(" §8§m----------§r Roleplay §bEssentials §7v" + main.getDescription().getVersion() + " §8§m----------");
        sender.sendMessage("");
        sender.sendMessage("  §8/§rrpe §7reload");
        sender.sendMessage("  §8/§rplayerlist");
        if(main.getConfig().getBoolean("modules.chat")) {
            sender.sendMessage("");
            sender.sendMessage("  §8/§rooc §7<message>");
            sender.sendMessage("  §8/§rrp §7<message>");
        }
        if(main.getConfig().getBoolean("modules.jobs")) {
            sender.sendMessage("");
            sender.sendMessage("  §8/§rjoblist");
            sender.sendMessage("  §8/§rjobinfo");
        }
        sender.sendMessage("");
        return true;
    }
}
