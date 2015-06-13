package uk.co.umbaska.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

/**
 * Created by Zachary on 6/8/2015.
 */
public class FreezeListener implements Listener {

    private Plugin p;
    private HashMap<Player, Boolean> freezeTracker = new HashMap<>();

    public FreezeListener(Plugin p){
        this.p = p;
        Bukkit.getPluginManager().registerEvents(this, this.p);
    }

    public void setFreezeState(Player p, Boolean isFrozen){
        freezeTracker.put(p, isFrozen);
    }

    public Boolean isFrozen(Player p){
        if (freezeTracker.containsKey(p)){
            return freezeTracker.get(p);
        }
        return false;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent e){
        if (isFrozen(e.getPlayer())){
            if (e.getTo().getX() != e.getFrom().getX() || e.getTo().getZ() != e.getFrom().getZ()){
                Location loc = e.getFrom();
                loc.setPitch(e.getTo().getPitch());
                loc.setYaw(e.getTo().getYaw());
                e.getPlayer().teleport(loc);
            }
        }
    }
}
