package uk.co.umbaska.Utils.GattBossBar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.logging.Level;

/**
 * Created by Zachary on 5/9/2015.
 */
@SuppressWarnings("unused")
public class BossBarHandler {

    private HashMap<Player, BossBar> bossBarTracker = new HashMap<>();
    
	private Plugin p;
    private String ver;

    public BossBarHandler(Plugin p){
        if (Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("R0.3")) {
            this.p = p;
            ver = "1.8";
        }
        else{
            Bukkit.getLogger().log(Level.SEVERE, "[Umbaska] Cannot start Boss Bar Handler. Server is not running Bukkit 1.8");
        }
    }

    public BossBar newBossBar(Player p, String text){
        if (bossBarTracker.containsKey(p)){
            bossBarTracker.remove(p);
        }
        BossBar bossBar;
        if (text != null) {
            bossBar = new BossBar(p, text);
        }
        else{
            bossBar = new BossBar(p);
        }
        bossBarTracker.put(p, bossBar);
        return bossBar;
    }

    public BossBar getBossBar(Player p){
        if (bossBarTracker.containsKey(p)){
            return bossBarTracker.get(p);
        }
        else{
            return newBossBar(p, null);
        }
    }
}
