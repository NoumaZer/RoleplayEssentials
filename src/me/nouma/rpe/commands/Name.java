package me.nouma.rpe.commands;

import me.nouma.rpe.Main;
import me.nouma.rpe.managers.Userdata;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.Normalizer;

public class Name implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getInstance().getConfig().getString("message.wrong_user"));
            return true;
        }

        if (args.length < 2) return false;

        Player player = (Player) sender;

        Userdata userdata = new Userdata(Main.getInstance(), player);

        userdata.getConfig().set("character.first_name", Normalizer.normalize(args[0], Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replaceAll("[^A-Za-z]", ""));
        userdata.getConfig().set("character.second_name", Normalizer.normalize(args[1], Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replaceAll("[^A-Za-z]", ""));
        userdata.saveConfig();

        player.sendMessage(Main.getInstance().getConfig().getString("message.name_changed"));


        return true;
    }
}
