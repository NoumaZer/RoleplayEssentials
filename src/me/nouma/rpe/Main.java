package me.nouma.rpe;

import me.nouma.rpe.commands.Advert;
import me.nouma.rpe.commands.ID;
import me.nouma.rpe.commands.Core;
import me.nouma.rpe.commands.Name;
import me.nouma.rpe.listeners.*;
import me.nouma.rpe.managers.Jobs;
import me.nouma.rpe.managers.Userdata;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private static Main INSTANCE;
    public static final Logger log = Bukkit.getLogger();

    // Vault thing
    public static Economy econ = null;
    public static Permission perms = null;

    public static Main getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage("   §3_   §b_   §3__");
        console.sendMessage("  §3|_) §b|_) §3|_    §bRoleplay Essentials §7" + getDescription().getVersion());
        console.sendMessage("  §3|\\  §b|   §3|__   §3Running on Spigot");
        console.sendMessage(" ");

        if (!setupEconomy()) {
            log.severe(String.format("[%s] Vault or/and economy plugin not found ! Disabling..", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        INSTANCE = this;
        saveDefaultConfig();
        registerListeners();
        registerCommands();

        // Jobs.initSalary(); // Init the scheduler that automatically gives money to player depending of their jobs

        for (Player players : Bukkit.getOnlinePlayers()) {
            Userdata userdata = new Userdata(this, players);
            if (userdata.getConfig().get("username") == null) {
                players.kickPlayer(null);
                userdata.getFile().delete();
            }
        }
    }

    // Vault thing

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    // Listeners and commands

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Jobs(), this);

        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerQuit(), this);
        pm.registerEvents(new PlayerDeath(), this);
        pm.registerEvents(new PlayerChat(), this);
        pm.registerEvents(new Misc(), this);
    }

    private void registerCommands() {
        getCommand("rpe").setExecutor(new Core());
        getCommand("advert").setExecutor(new Advert());
        getCommand("name").setExecutor(new Name());
        getCommand("id").setExecutor(new ID());
    }
}
