package uk.nfell2009.umbaska.Utils.Disguise;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

/**
 * Created by Zachary on 5/10/2015.
 */
public class DisguiseHandler {
    public HashMap<Player, MyDisguise> disguiseTracker = new HashMap<>();
    Plugin p;

    public DisguiseHandler(Plugin p){
        this.p = p;
    }

    public void setDisguise(Player p, MyDisguise dis){
        disguiseTracker.put(p, dis);
        for (Player p1 : Bukkit.getOnlinePlayers()) {
            try {
                dis.sendDisguise(p1);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
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

    public class DisguiseTracker extends BukkitRunnable{

        Plugin plugin = p;
        Player player;
        public DisguiseTracker(Player player){
            this.player = player;
        }

        @Override
        public void run(){
            if (disguiseTracker.containsKey(player)){
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    try {
                        disguiseTracker.get(player).updateDisguise(player1);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
            if (!player.isOnline()){
                removeDisguise(player);
                this.cancel();
            }
        }
    }

}
