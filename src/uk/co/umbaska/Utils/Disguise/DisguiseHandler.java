package uk.co.umbaska.Utils.Disguise;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Zachary on 5/10/2015.
 */
public class DisguiseHandler {
    public HashMap<Player, MyDisguise> disguiseTracker = new HashMap<>();
    Plugin p;

    public DisguiseHandler(Plugin p){
        this.p = p;
    }

    @SuppressWarnings("deprecation")
	public void setDisguise(Player p, MyDisguise dis){
        disguiseTracker.put(p, dis);
        //new DisguiseTracker(p);
    }

    public void removeDisguise(Player p){
        if (disguiseTracker.containsKey(p)){
            try {
                disguiseTracker.get(p).removeDisguise();
            }catch (ReflectiveOperationException ex){
                ex.printStackTrace();
            }
        }
        disguiseTracker.remove(p);
    }

}
