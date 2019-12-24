package me.nouma.rpe.chat;

import me.nouma.rpe.Main;
import me.nouma.rpe.MessageFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!Main.INSTANCE.getConfig().getBoolean("modules.chat")) {
            sender.sendMessage(MessageFile.returnWithPrefix(Main.INSTANCE.getMessage().getString("disabled_module")));
            return true;
        }

        if(!(sender instanceof Player)) {
            sender.sendMessage(MessageFile.returnWithPrefix(Main.INSTANCE.getMessage().getString("only_player")));
            return true;
        }
        if(args.length == 0) return false;

        StringBuilder builder = new StringBuilder();
        for(String arg : args) {
            builder.append(arg + " ");
        }

        if(Main.INSTANCE.getCommand("ooc").getAliases().contains(label) || label.equalsIgnoreCase("ooc")) {
            if(!Main.INSTANCE.getConfig().getBoolean("chat.ooc.default")) builder.insert(0, Main.INSTANCE.getConfig().getString("chat.ooc.prefix").charAt(0));
            ((Player) sender).chat(builder.toString());
        } else {
            rpChat((Player) sender, builder.toString());
        }
        return true;
    }

    public static void rpChat(Player player, String message) {
        double radius = Main.INSTANCE.getConfig().getDouble("chat.rp.radius");
        player.getNearbyEntities(radius, radius, radius).forEach(entity -> {
            if(entity instanceof Player) ((Player) entity).sendMessage(Main.INSTANCE.getConfig().getString("chat.rp.format").replaceAll("\\$PLAYER", player.getName()).replaceAll("\\$MESSAGE", message));
        });
        player.sendMessage(Main.INSTANCE.getConfig().getString("chat.rp.format").replaceAll("\\$PLAYER", player.getName()).replaceAll("\\$MESSAGE", message));
    }
}
