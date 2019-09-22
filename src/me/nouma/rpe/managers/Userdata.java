package me.nouma.rpe.managers;

import me.nouma.rpe.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Userdata {

    private Main main;
    private Player player;
    private FileConfiguration fileConfiguration;
    private File file;

    public Userdata(Main main, Player player) {
        this.main = main;
        this.player = player;
    }

    public Player getOwner() {
        if (player == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                main.getLogger().warning(String.format("[%s] §7Userdata §r- §ogetOwner §r- Player is null!", main.getDescription().getName()));
                e.printStackTrace();
            }
        }
        return player;
    }

    public File getFile() {
        if (file == null) {
            file = new File(main.getDataFolder(), getOwner().getUniqueId().toString() + ".yml");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    public boolean exists() {
        if (file == null || fileConfiguration == null) {
            File temp = new File(main.getDataFolder(), getOwner().getUniqueId().toString() + ".yml");
            if (!temp.exists()) {
                return false;
            } else {
                file = temp;
            }
        }
        return true;
    }

    public FileConfiguration getConfig() {
        if (fileConfiguration == null) {
            fileConfiguration = YamlConfiguration.loadConfiguration(getFile());
        }
        return fileConfiguration;
    }

    public void saveConfig() {
        try {
            getConfig().save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
