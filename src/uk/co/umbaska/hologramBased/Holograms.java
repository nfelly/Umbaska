package uk.co.umbaska.hologramBased;


import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Zachary on 11/3/2014.
 */
public class Holograms{
	public static HashMap<String, Hologram> holograms = new HashMap();
	private static final double distance = 0.23D;
	private ArrayList<String> lines = new ArrayList();
	private ArrayList<Integer> ids = new ArrayList();
	private boolean showing = false;
	private Location location;

	public static Hologram get(String id){

		if (holograms.containsKey(id)){
			return holograms.get(id);
		}
		else{
			Location loc = new Location(Bukkit.getServer().getWorlds().get(0), 0, 0, 0);
			Hologram h = new Hologram(loc, "Default Text").start();
			holograms.put(id, h);
			return h;
		}

//		for (Hologram h : holograms){
//			if (h.isInUse()){
//				return h;
//			}
//		}
//		Location loc = new Location(Bukkit.getServer().getWorlds().get(0), 0, 0, 0);
//
//		return new Hologram(loc, "Default Text").start();
	}



}
