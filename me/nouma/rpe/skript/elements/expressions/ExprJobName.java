package me.nouma.rpe.skript.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.nouma.rpe.Main;
import me.nouma.rpe.jobs.Job;
import org.bukkit.event.Event;

public class ExprJobName extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprJobName.class, String.class, ExpressionType.SIMPLE, "%job%'s name");
    }

    private Main main = Main.INSTANCE;

    private Expression<Job> jobs;

    @Override
    protected String[] get(Event e) {
        Job job = jobs.getSingle(e);
        if(job == null) return null;
        return new String[] { job.getName() };
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event e, boolean b) {
        return "name of job " + jobs.toString(e, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        jobs = (Expression<Job>) expressions[0];
        return true;
    }
}
