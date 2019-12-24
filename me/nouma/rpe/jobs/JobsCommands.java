package me.nouma.rpe.jobs;

import me.nouma.rpe.Main;
import me.nouma.rpe.MessageFile;
import me.nouma.rpe.notifier.Notification;
import me.nouma.rpe.notifier.NotificationType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class JobsCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!Main.INSTANCE.getConfig().getBoolean("modules.jobs")) {
            sender.sendMessage(MessageFile.returnWithPrefix(Main.INSTANCE.getMessage().getString("disabled_module")));
            return true;
        }

        if(Main.INSTANCE.getCommand("joblist").getAliases().contains(label) || label.equalsIgnoreCase("joblist")) {
            Main.INSTANCE.jobs.forEach(job -> {
                Random random = new Random();
                ChatColor color = ChatColor.getByChar(Integer.toHexString(random.nextInt(16)));
                sender.sendMessage(color + job.getRawName());
                sender.sendMessage(color + job.getName());
                sender.sendMessage(color + job.getDesc());
                sender.sendMessage(color + String.valueOf(job.getSalary()));
            });
        } else {
            Job job = Main.INSTANCE.playerJob.get(sender);
            sender.sendMessage(job.getRawName());
            sender.sendMessage(job.getName());
            sender.sendMessage(job.getDesc());
            sender.sendMessage(String.valueOf(job.getSalary()));
        }
        return true;
    }
}
