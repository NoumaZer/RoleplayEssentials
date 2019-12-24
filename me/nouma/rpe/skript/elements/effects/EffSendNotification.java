package me.nouma.rpe.skript.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.nouma.rpe.notifier.Notification;
import me.nouma.rpe.notifier.NotificationType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffSendNotification extends Effect {

    static {
        Skript.registerEffect(EffSendNotification.class, "notify %player% with %string% by %string%");
    }

    private Expression<Player> player;
    private Expression<String> message;
    private Expression<String> type;

    @Override
    protected void execute(Event e) {
        NotificationType type1;
        switch(type.getSingle(e)) {
            case "bossbar":
                type1 = NotificationType.BOSSBAR;
                break;
            case "actionbar":
                type1 = NotificationType.ACTIONBAR;
                break;
            default:
                type1 = NotificationType.CHAT;
                break;
        }
        new Notification(type1).setTarget(player.getSingle(e)).setMessage(message.getSingle(e)).send();
    }

    @Override
    public String toString(Event e, boolean b) {
        return "notify a player";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        message = (Expression<String>) expressions[1];
        type = (Expression<String>) expressions[2];
        return true;
    }
}
