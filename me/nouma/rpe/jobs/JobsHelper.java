package me.nouma.rpe.jobs;

import me.nouma.rpe.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class JobsHelper {

    public static Job getJobFromName(String rawName) {
        Job[] result = new Job[1];
        Main.INSTANCE.jobs.forEach(job -> {
            if(job.getRawName().equalsIgnoreCase(rawName)) result[0] = job;
        });
        return result[0];
    }

    /*public static void setJob(Player player, String job) {
        Main.INSTANCE.playerJob.put(player, getJobFromName(job));
    }*/

    public static void setJob(Player player, Job job) {
        Main.INSTANCE.playerJob.put(player, job);
    }
}
