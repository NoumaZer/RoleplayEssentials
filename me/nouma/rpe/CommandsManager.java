package me.nouma.rpe;

import me.nouma.rpe.chat.ChatCommands;
import me.nouma.rpe.commands.MainCommand;
import me.nouma.rpe.commands.PlayerListCommand;
import me.nouma.rpe.jobs.JobsCommands;

public class CommandsManager {

    private Main main = Main.INSTANCE;

    public void registerCommands() {
        main.getCommand("rpe").setExecutor(new MainCommand());
        main.getCommand("playerlist").setExecutor(new PlayerListCommand());
        // Chat
        main.getCommand("ooc").setExecutor(new ChatCommands());
        main.getCommand("rp").setExecutor(new ChatCommands());
        // Jobs
        main.getCommand("joblist").setExecutor(new JobsCommands());
        main.getCommand("jobinfo").setExecutor(new JobsCommands());
    }
}
