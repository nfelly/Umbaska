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
        for (Player p1 : Bukkit.getOnlinePlayers()) {
            try {
                dis.sendDisguise(p1);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
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

    public class DisguiseTracker{

        Plugin plugin = p;
        Player player;
        MyDisguise currentDisguise;
        BukkitTask runnable;
        List<Player> canSee;

        public DisguiseTracker(Player player){

            this.player = player;
            this.currentDisguise = disguiseTracker.get(player);
            start();
        }

        private void start(){
            runnable = Bukkit.getScheduler().runTaskTimer(p, new Runnable() {
                @SuppressWarnings("deprecation")
				@Override
                public void run() {
                    if (disguiseTracker.containsKey(player)){
                        if (disguiseTracker.get(player) != currentDisguise){
                            runnable.cancel();
                        }
                        for (Player player1 : Bukkit.getOnlinePlayers()) {
                            if (!canSee.contains(player1) && player1.getLocation().distance(player.getLocation()) < 25){
                                canSee.add(player1);
                                try {
                                    disguiseTracker.get(player).updateDisguise(player1);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            if (player1.getLocation().distance(player.getLocation()) > 24){
                                canSee.remove(player1);
                            }

                        }
                    }
                    if (!player.isOnline()){
                        removeDisguise(player);
                        runnable.cancel();
                    }
                }
            }, 20l, 20l);
        }
    }

}
