package me.nouma.rpe.jobs;

import me.nouma.rpe.Main;
import me.nouma.rpe.api.events.JobsPaydayEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SalaryScheduler {

    private Main main = Main.INSTANCE;
    private int id = -1;

    public void start() {
        if(id != -1) stop();
        if(main.jobs.size() >= 1) {
            id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.INSTANCE, () -> {
                for (Player player : Main.INSTANCE.getServer().getOnlinePlayers()) {
                    Job job = main.playerJob.get(player);
                    if(job == null) continue;

                    JobsPaydayEvent e = new JobsPaydayEvent(player, job);
                    Bukkit.getPluginManager().callEvent(e);
                    if(e.isCancelled()) continue;

                    player.sendMessage("PAYDAY! §e+" + main.econ.format(job.getSalary()));
                    if(main.econ!= null) main.econ.depositPlayer(player, job.getSalary());
                }
            }, Main.INSTANCE.getConfig().getLong("jobs.salary.time") * 20, Main.INSTANCE.getConfig().getLong("jobs.salary.time") * 20);
            return;
        }
        Bukkit.getLogger().warning(String.format("[%s] No job found! To avoid bugs, you may disable this module in config.yml.", main.getDescription().getName()));
    }

    public void stop() {
        if(id != -1) {
            Bukkit.getScheduler().cancelTask(id);
        }
    }
}
