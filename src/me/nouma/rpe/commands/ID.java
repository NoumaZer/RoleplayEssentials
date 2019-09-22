package me.nouma.rpe.commands;

import me.nouma.rpe.Main;
import me.nouma.rpe.managers.Userdata;
import me.nouma.rpe.utils.ItemBuilder;
import me.nouma.rpe.utils.XMaterial;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ID implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Main.getInstance().getConfig().getString("message.wrong_user"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) return false;

        if (args[0].equalsIgnoreCase("check")) {
            for (Entity entities : player.getNearbyEntities(3, 3, 3)) {
                if (entities instanceof Player) {
                    Userdata userdata = new Userdata(Main.getInstance(), (Player) entities);
                    player.sendMessage("First name: " + userdata.getConfig().get("character.first_name").toString());
                    player.sendMessage("Second name: " + userdata.getConfig().get("character.second_name").toString());
                    break;
                }
            }
            player.sendMessage(Main.getInstance().getConfig().getString("message.nobody_found"));
            return true;
        } else if (args[0].equalsIgnoreCase("give")) {
            Userdata userdata = new Userdata(Main.getInstance(), player);
            ItemStack item = new ItemBuilder(XMaterial.PAPER.parseMaterial()).setName("§rID Card").addLoreLine("§8First name: §7" + userdata.getConfig().get("character.first_name").toString()).addLoreLine("§8Second name: §7" + userdata.getConfig().get("character.second_name").toString()).toItemStack();

            if (player.getInventory().containsAtLeast(item, 1)) {
                player.sendMessage(Main.getInstance().getConfig().getString("message.item_already_owned"));
                return true;
            }

            player.getInventory().addItem(item);
            return true;
        }
        return false;
    }
}
