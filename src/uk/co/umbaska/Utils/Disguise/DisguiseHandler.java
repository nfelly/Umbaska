package uk.co.umbaska.Utils.Disguise;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

/**
 * Created by Zachary on 5/10/2015.
 */
public class DisguiseHandler {
    public HashMap<Entity, MyDisguise> disguiseTracker = new HashMap<>();
    Plugin p;

    public DisguiseHandler(Plugin p){
        this.p = p;
    }

    @SuppressWarnings("deprecation")
	public void setDisguise(Entity p, MyDisguise dis){
        disguiseTracker.put(p, dis);
        //new DisguiseTracker(p);
    }

    public void removeDisguise(Entity p){
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
