package me.nouma.rpe.notifier;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Notification {

    private NotificationType type;
    private String message;
    private List<Player> targets = new ArrayList<>();

    public Notification(NotificationType type) {
        this.type = type;
    }

    public Notification setMessage(String message) {
        this.message = message;
        return this;
    }

    public Notification setTarget(Player player) {
        this.targets.clear();
        this.targets.add(player);
        return this;
    }

    public Notification setTarget(Player[] players) {
        this.targets = Arrays.asList(players);
        return this;
    }

    public Notification setTarget(List<Player> players) {
        this.targets = players;
        return this;
    }

    public void send() {
        switch(type) {
            case CHAT:
                targets.forEach(target -> target.sendMessage(message));
                break;
        }
    }
}
