package me.nouma.rpe.skript.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.nouma.rpe.Main;
import me.nouma.rpe.jobs.Job;
import me.nouma.rpe.jobs.JobsHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprPlayerJob extends SimpleExpression<Job> {

    static {
        Skript.registerExpression(ExprPlayerJob.class, Job.class, ExpressionType.SIMPLE, "%player%'s job");
    }

    private Main main = Main.INSTANCE;

    private Expression<Player> players;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Job> getReturnType() {
        return Job.class;
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        players = (Expression<Player>) expressions[0];
        return true;
    }

    @Override
    public String toString(Event e, boolean b) {
        return "job of player";
    }

    @Override
    @Nullable
    protected Job[] get(Event e) {
        Player player = players.getSingle(e);
        if(player == null) return null;
        if(!main.playerJob.containsKey(player)) return null;
        Job job = main.playerJob.get(player);
        if(job == null) return null;
        return new Job[] { job };
    }

    @Override
    public void change(Event e, Object[] delta, ChangeMode mode) {
        if(mode != ChangeMode.SET) return;
        Player player = players.getSingle(e);
        if(player == null) return;
        Job job = JobsHelper.getJobFromName((String) delta[0]);
        if(job == null) return;
        JobsHelper.setJob(player, job);
    }

    @Override
    public Class<?>[] acceptChange(ChangeMode mode) {
        if(mode == ChangeMode.SET) return CollectionUtils.array(String.class);
        return null;
    }
}
