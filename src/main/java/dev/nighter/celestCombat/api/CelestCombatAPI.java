package dev.nighter.celestCombat.api;

import dev.nighter.celestCombat.CelestCombat;
import lombok.Setter;
import org.bukkit.entity.Player;

public class CelestCombatAPI {
    @Setter
    private static CelestCombat plugin;

    public static boolean isInCombat(Player player) {
        return plugin.getCombatManager().isInCombat(player);
    }
    
    public static void tagPlayer(Player player, Player attacker) {
        plugin.getCombatManager().tagPlayer(player, attacker);
    }
    
    public static void removeFromCombat(Player player) {
        plugin.getCombatManager().removeFromCombat(player);
    }
    
    public static void removeFromCombatSilently(Player player) {
        plugin.getCombatManager().removeFromCombatSilently(player);
    }
    
    public static Player getCombatOpponent(Player player) {
        return plugin.getCombatManager().getCombatOpponent(player);
    }
    
    public static long getRemainingCombatTimeMillis(Player player) {
        return plugin.getCombatManager().getRemainingCombatTimeMillis(player);
    }
    
    public static double getRemainingCombatTimeSeconds(Player player) {
        return plugin.getCombatManager().getRemainingCombatTimeSeconds(player);
    }
    
    public static boolean isEnderPearlOnCooldown(Player player) {
        return plugin.getCombatManager().isEnderPearlOnCooldown(player);
    }
    
    public static void setEnderPearlCooldown(Player player) {
        plugin.getCombatManager().setEnderPearlCooldown(player);
    }
    
    public static long getRemainingEnderPearlCooldownMillis(Player player) {
        return plugin.getCombatManager().getRemainingEnderPearlCooldownMillis(player);
    }
    
    public static double getRemainingEnderPearlCooldownSeconds(Player player) {
        return plugin.getCombatManager().getRemainingEnderPearlCooldownSeconds(player);
    }
    
    public static boolean isTridentOnCooldown(Player player) {
        return plugin.getCombatManager().isTridentOnCooldown(player);
    }
    
    public static void setTridentCooldown(Player player) {
        plugin.getCombatManager().setTridentCooldown(player);
    }
    
    public static long getRemainingTridentCooldownMillis(Player player) {
        return plugin.getCombatManager().getRemainingTridentCooldownMillis(player);
    }
    
    public static double getRemainingTridentCooldownSeconds(Player player) {
        return plugin.getCombatManager().getRemainingTridentCooldownSeconds(player);
    }
    
    public static boolean isTridentBanned(Player player) {
        return plugin.getCombatManager().isTridentBanned(player);
    }
    
    public static boolean hasNewbieProtection(Player player) {
        return plugin.getNewbieProtectionManager().hasProtection(player);
    }
    
    public static void removeNewbieProtection(Player player) {
        plugin.getNewbieProtectionManager().removeProtection(player);
    }
    
    public static long getRemainingNewbieProtectionMillis(Player player) {
        return plugin.getNewbieProtectionManager().getRemainingProtectionMillis(player);
    }
    
    public static double getRemainingNewbieProtectionSeconds(Player player) {
        return plugin.getNewbieProtectionManager().getRemainingProtectionSeconds(player);
    }
}