package me.nouma.rpe.managers;

import me.nouma.rpe.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Jobs implements Listener {

    public static void initSalary() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    String m = Main.getInstance().getConfig().getString("message.payday");
                    // Temporary system to distinguish different jobs
                    Main.perms.getPrimaryGroup(players);
                }
            }
        }, 200, Main.getInstance().getConfig().getInt("jobs.payday") * 20);
    }

    /* public static void joinJob(Player player, String job) {
        Userdata.set(player, "job", job);
    }

    public static void leaveJob(Player player) {
        Userdata.set(player, "job", "citizen");
    }

    public static String getJob(Player player) {
        if (Userdata.get(player, "job").toString() == null) return "null";
        return Userdata.get(player, "job").toString();
    } */
}
