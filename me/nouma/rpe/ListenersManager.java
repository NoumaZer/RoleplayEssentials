package me.nouma.rpe;

import me.nouma.rpe.chat.ChatListener;
import me.nouma.rpe.jobs.JobsListener;
import org.bukkit.plugin.PluginManager;

public class ListenersManager {

    private Main main = Main.INSTANCE;

    public void registerListeners() {
        PluginManager pm = main.getServer().getPluginManager();
        if(main.getConfig().getBoolean("modules.chat")) pm.registerEvents(new ChatListener(), main);
        if(main.getConfig().getBoolean("modules.jobs")) pm.registerEvents(new JobsListener(), main);
    }
}
