package dev.nighter.celestCombat.listeners;

import dev.nighter.celestCombat.CelestCombat;
import dev.nighter.celestCombat.combat.CombatManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class ElytraListener implements Listener {
    private final CelestCombat plugin;
    private final CombatManager combatManager;

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onElytraUse(PlayerInteractEvent event) {
        if (!plugin.getConfig().getBoolean("elytra.block-gliding", true)) {
            return;
        }
        
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();
        
        if (item != null && item.getType() == Material.ELYTRA &&
                (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {
            
            if (combatManager.isInCombat(player)) {
                event.setCancelled(true);
                
                Map<String, String> placeholders = new HashMap<>();
                placeholders.put("player", player.getName());
                plugin.getMessageService().sendMessage(player, "elytra_use_blocked_in_combat", placeholders);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }
        
        ItemStack cursor = event.getCursor();
        ItemStack current = event.getCurrentItem();

        if ((event.getSlotType() == InventoryType.SlotType.ARMOR && cursor != null && cursor.getType() == Material.ELYTRA)
                || (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY && current != null && current.getType() == Material.ELYTRA)) {
                
            if (combatManager.isInCombat(player)) {
                event.setCancelled(true);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!plugin.getConfig().getBoolean("elytra.block-gliding", true)) {
            return;
        }
        
        Player player = event.getPlayer();
        
        if (combatManager.isInCombat(player)) {
            checkGlide(player);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onElytraFirework(PlayerInteractEvent event) {
        if (!plugin.getConfig().getBoolean("elytra.block-fireworks", true)) {
            return;
        }
        
        Player player = event.getPlayer();
        
        if (!player.isGliding()) {
            return;
        }
        
        ItemStack item = event.getItem();
        Action action = event.getAction();
        
        if (item != null && item.getType() == Material.FIREWORK_ROCKET &&
                (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {
                    
            if (combatManager.isInCombat(player)) {
                event.setCancelled(true);
                
                Map<String, String> placeholders = new HashMap<>();
                placeholders.put("player", player.getName());
                plugin.getMessageService().sendMessage(player, "elytra_firework_use_blocked_in_combat", placeholders);
            }
        }
    }
    
    private void checkGlide(Player player) {
        if (!player.isGliding()) return;
        
        player.setGliding(false);
        player.setFallDistance(0);
        
        ItemStack chestplate = player.getInventory().getChestplate();
        if (chestplate == null || chestplate.getType() != Material.ELYTRA) return;
        player.getInventory().setChestplate(null);
        Map<Integer, ItemStack> item = player.getInventory().addItem(chestplate);
        if (!item.isEmpty()) {
            player.getWorld().dropItemNaturally(player.getLocation(), item.values().stream().findFirst().orElse(chestplate));
        }
    }
}