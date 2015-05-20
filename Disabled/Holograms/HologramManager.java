package uk.co.umbaska.Holograms;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import de.inventivegames.hologram.Hologram;
import de.inventivegames.hologram.HologramAPI;

public class HologramManager {

    public HashMap<String, Hologram> holos = new HashMap<>();

    public Hologram getHologramByLocation(Location location, String world) {
        Collection<Hologram> ch = HologramAPI.getHolograms();
        Iterator<Hologram> iter = ch.iterator();
        Location l = new Location(Bukkit.getWorld(world), location.getX(), location.getY(), location.getZ());
        while (iter.hasNext()) {
            Hologram current = iter.next();
            if (current.getLocation() == l) {
                return current;
            }
        }
        return null;
    }

}
