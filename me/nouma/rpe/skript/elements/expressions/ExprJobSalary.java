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

public class ExprJobSalary extends SimpleExpression<Double> {

    static {
        Skript.registerExpression(ExprJobSalary.class, Double.class, ExpressionType.SIMPLE, "%job%'s salary");
    }

    private Main main = Main.INSTANCE;

    private Expression<Job> jobs;

    @Override
    protected Double[] get(Event e) {
        Job job = jobs.getSingle(e);
        if(job == null) return null;
        return new Double[] { job.getSalary() };
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Double> getReturnType() {
        return Double.class;
    }

    @Override
    public String toString(Event e, boolean b) {
        return "salary of job " + jobs.toString(e, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        jobs = (Expression<Job>) expressions[0];
        return true;
    }
}
