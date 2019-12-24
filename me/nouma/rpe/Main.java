package me.nouma.rpe;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import me.nouma.rpe.jobs.Job;
import me.nouma.rpe.jobs.JobFile;
import me.nouma.rpe.jobs.JobsLoader;
import me.nouma.rpe.jobs.SalaryScheduler;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {

    public static Main INSTANCE;

    // Vault
    public Economy econ = null;

    // Skript
    public SkriptAddon addon;

    private MessageFile message;
    private JobFile job;

    public SalaryScheduler salary;

    public List<Job> jobs = new ArrayList<>();
    public HashMap<Player, Job> playerJob = new HashMap<>();

    @Override
    public void onEnable() {
        INSTANCE = this;
        moduleThing();

        setupSkript();
        setupEconomy();

        Metrics metrics = new Metrics(this);
    }

    public void moduleThing() {
        message = new MessageFile();
        job = new JobFile();

        setupDefaultConfig();
        setupDefaultMessage();

        new ListenersManager().registerListeners();
        new CommandsManager().registerCommands();

        if(getConfig().getBoolean("modules.jobs")) {
            JobsLoader.loadJobs();
            JobsLoader.loadPlayers();
            if(salary != null) salary.stop();
            if(getConfig().getBoolean("jobs.salary.enable")) {
                salary = new SalaryScheduler();
                salary.start();
            }
        }
    }

    private void setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            Bukkit.getLogger().warning(String.format("[%s] Vault not found!", getDescription().getName()));
            return;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            Bukkit.getLogger().warning(String.format("[%s] Vault economy dependency not found!", getDescription().getName()));
            return;
        }
        econ = rsp.getProvider();
    }

    private void setupSkript() {
        if(Main.INSTANCE.getServer().getPluginManager().getPlugin("Skript") == null) {
            Bukkit.getLogger().warning(String.format("[%s] Skript not found!", Main.INSTANCE.getDescription().getName()));
            return;
        };
        addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("me.nouma.rpe.skript", "elements");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupDefaultConfig() {
        getConfig().addDefault("language", "en");

        getConfig().addDefault("modules.chat", true);
        getConfig().addDefault("modules.jobs", true);

        getConfig().addDefault("chat.ooc.default", true);
        getConfig().addDefault("chat.ooc.prefix", '!');
        getConfig().addDefault("chat.ooc.format", "§8OOC | §7$PLAYER §8» §7$MESSAGE");
        getConfig().addDefault("chat.rp.radius", 20);
        getConfig().addDefault("chat.rp.prefix", '.');
        getConfig().addDefault("chat.rp.format", "§7Someone say §8» §f$MESSAGE");

        getConfig().addDefault("jobs.salary.enable", true);
        getConfig().addDefault("jobs.salary.time", 60);

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void setupDefaultMessage() {
        getMessage().addDefault("prefix", "§6RPE §8»§r");

        getMessage().addDefault("plugin_reloaded", "$PREFIX §7Reloaded!");

        getMessage().addDefault("only_player", "$PREFIX §cOnly players can do that!");
        getMessage().addDefault("no_permission", "$PREFIX §cYou don't have the permission to do that!");
        getMessage().addDefault("disabled_module", "$PREFIX §cThis command has been disabled!");

        getMessage().addDefault("kick_while_reloading", "$PREFIX §7The plugin is reloading!");

        getMessage().options().copyDefaults(true);
        saveMessage();
    }

    // Message's file shortcuts things

    public FileConfiguration getMessage() {
        return message.getConfig();
    }

    public void saveMessage() {
        message.save();
    }

    // Job's file shortcuts things

    public FileConfiguration getJob() {
        return job.getConfig();
    }

    public void saveJob() {
        job.save();
    }
}
