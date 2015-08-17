package com.github.barneygale.PlumpXP;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlumpXPListener implements Listener {
    public final PlumpXP plugin;
    public PlumpXPListener(PlumpXP instance) {
        plugin = instance;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        Entity target = event.getEntity();
        Player attacker = getAttacker(target.getLastDamageCause());
        int xp = event.getDroppedExp();

        if (attacker != null) {
            if (plugin.config.PLUMP_OUTSIDE_RADIUS && !playerIsOutsideRadius(attacker)) return;
        }
         
        if (event instanceof PlayerDeathEvent) {
            handlePlayerDeath((PlayerDeathEvent) event, attacker, xp);
            return;
        }

        //not killed by player
        if (attacker == null) {
            return;
        }

        handleMonsterDeath(event, xp);

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (plugin.config.PLUMP_OUTSIDE_RADIUS && !playerIsOutsideRadius(event.getPlayer())) return;
        int xp = event.getExpToDrop();
        handleBlockBreak(event, xp);
    }

    private void handleMonsterDeath(EntityDeathEvent event, int exp) {
        EntityType target = event.getEntityType();
        if ((target == EntityType.BLAZE) && (plugin.config.BLAZE_MULTIPLIER > 0)) {
            exp *= plugin.config.BLAZE_MULTIPLIER;
        }
        else {
            if (plugin.config.PLUMP_MULTIPLIER > 0) {
                exp *= plugin.config.PLUMP_MULTIPLIER;
            }
        }
        event.setDroppedExp(exp);
    }

    private void handlePlayerDeath(PlayerDeathEvent event, Player attacker, int exp) {
        //killed by another player
        if ((attacker != null) && (plugin.config.PLAYER_OVERRIDE)) {
            exp *= plugin.config.PLAYER_MULTIPLIER;
        }
        event.setDroppedExp(exp);
    }

    private Player getAttacker(EntityDamageEvent attacker) {
        if ((attacker == null) || (!(attacker instanceof EntityDamageByEntityEvent))){
            return null;
        }

        Player p = null;
        Entity damager = ((EntityDamageByEntityEvent) attacker).getDamager();

        if (damager instanceof Projectile) {
            Projectile projectile = (Projectile) damager;
            if (projectile.getShooter() instanceof Player) {
                p = (Player) projectile.getShooter();
            }
        } 
        else if (damager instanceof Player) {
            p = (Player)damager;
        }
        return p;
    }
    
    private void handleBlockBreak(BlockBreakEvent event, int exp) {
        if (plugin.config.ORE_MULTIPLIER > 0) {
            exp *= plugin.config.ORE_MULTIPLIER;
        }
        event.setExpToDrop(exp);
    }

    private boolean playerIsOutsideRadius(Player player) {

        if (!plugin.config.PLUMP_RADIUS_WORLDS.contains(player.getWorld().getName())) return false; //check if world is enabled

        double centerX = plugin.config.PLUMP_RADIUS_CENTER.get(0);
        double centerZ = plugin.config.PLUMP_RADIUS_CENTER.get(1);
        Location loc = player.getLocation();

        double distance = Math.pow(centerX - loc.getX(), 2) + Math.pow(centerZ - loc.getZ(), 2); //Euclidean
        return distance > Math.pow(plugin.config.PLUMP_RADIUS, 2);

    }

}
