package dev.nighter.celestCombat.api.events;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

@Getter
public class NewbieProtectionEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private final ProtectionAction action;
    private final long duration;
    
    public enum ProtectionAction {
        GRANTED,
        REMOVED
    }
    
    public NewbieProtectionEvent(Player player, ProtectionAction action, long duration) {
        super(player);
        this.action = action;
        this.duration = duration;
    }
    
    @Override
    public boolean isCancelled() {
        return cancelled;
    }
    
    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}