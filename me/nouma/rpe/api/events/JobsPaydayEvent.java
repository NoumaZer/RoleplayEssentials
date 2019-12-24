package me.nouma.rpe.api.events;

import me.nouma.rpe.jobs.Job;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class JobsPaydayEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private Job job;
    private boolean cancelled;

    public JobsPaydayEvent(Player player, Job job) {
        this.player = player;
        this.job = job;
    }

    public Player getPlayer() {
        return player;
    }

    public Job getJob() {
        return job;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
