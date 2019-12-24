package me.nouma.rpe;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageFile {

    private Main main = Main.INSTANCE;
    private File file;
    private FileConfiguration fc;

    public File getFile() {
        if(file == null) {
            file = new File(main.getDataFolder(), "messages_" + main.getConfig().getString("language") + ".yml");
        }
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public FileConfiguration getConfig() {
        if(fc == null) {
            fc = YamlConfiguration.loadConfiguration(getFile());
        }
        return fc;
    }

    public void save() {
        try {
            getConfig().save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String returnWithPrefix(String string) {
        return string.replaceAll("\\$PREFIX", Main.INSTANCE.getMessage().getString("prefix"));
    }
}
