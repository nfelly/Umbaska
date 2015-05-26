package uk.co.umbaska.Replacers;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import uk.co.umbaska.Enums.ParticleEnum;

/**
 * Created by Zachary on 5/17/2015.
 */
public class ParticleFunction {

    public static Boolean spawnParticle(Integer count, ParticleEnum effect, Number speed, Number offsetx, Number offsety, Number offsetz, Location[] locations, Integer data){
        Effect eff = effect.getEffect();
        if (eff == null){

            return false;
        }
        for (Location location : locations) {
            //location.getWorld().playEffect(location, eff, integer);
            if (data == null) {
                location.getWorld().spigot().playEffect(location, eff);
            } else {
                location.getWorld().spigot().playEffect(location, eff, data, 0, offsetx.floatValue(), offsety.floatValue(), offsetz.floatValue(), speed.floatValue(), count, 500);
            }
        }
        return true;
    }

    public static Boolean spawnParticle(Integer count, ParticleEnum effect, Number speed, Number offsetx, Number offsety, Number offsetz, Location[] locations, Integer data, Player[] players) {
        Effect eff = effect.getEffect();
        if (eff == null) {

            return false;
        }
        for (Player p : players){
            for (Location location : locations) {
                //location.getWorld().playEffect(location, eff, integer);
                p.spigot().playEffect(location, eff, data, 0, offsetx.floatValue(), offsety.floatValue(), offsetz.floatValue(), speed.floatValue(), count, 500);

            }
        }
        return true;
    }
}
