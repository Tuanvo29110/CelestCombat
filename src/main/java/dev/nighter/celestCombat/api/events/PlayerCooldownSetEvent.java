package dev.nighter.celestCombat.api.events;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

@Getter
public class PlayerCooldownSetEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private final CooldownType type;
    private final long duration;
    
    public enum CooldownType {
        ENDER_PEARL,
        TRIDENT
    }
    
    public PlayerCooldownSetEvent(Player player, CooldownType type, long duration) {
        super(player);
        this.type = type;
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