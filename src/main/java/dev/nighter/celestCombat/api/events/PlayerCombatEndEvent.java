package dev.nighter.celestCombat.api.events;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

@Getter
public class PlayerCombatEndEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Player lastOpponent;
    private final EndReason reason;
    
    public enum EndReason {
        EXPIRED,
        LOGOUT,
        DEATH,
        REMOVED_BY_PLUGIN,
        REMOVED_BY_COMMAND
    }
    
    public PlayerCombatEndEvent(Player player, Player lastOpponent, EndReason reason) {
        super(player);
        this.lastOpponent = lastOpponent;
        this.reason = reason;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}