package me.nouma.rpe.skript.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.events.EvtChat;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.nouma.rpe.api.events.JobsPaydayEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EvtPayday extends SkriptEvent {

    static {
        Skript.registerEvent("Payday", EvtPayday.class, JobsPaydayEvent.class, "payday");

        EventValues.registerEventValue(JobsPaydayEvent.class, Player.class, new Getter<Player, JobsPaydayEvent>() {
            @Override
            public Player get(JobsPaydayEvent e) {
                return e.getPlayer();
            }
        }, 0);
    }

    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event e) {
        return true;
    }

    @Override
    public String toString(Event e, boolean b) {
        return "Payday";
    }
}
