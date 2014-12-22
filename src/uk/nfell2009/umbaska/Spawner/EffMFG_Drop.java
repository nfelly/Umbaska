/*
 * EffSetDelay.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.nfell2009.umbaska.Spawner;

import ch.njol.skript.entity.EntityType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Item;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
 
public class EffMFG_Drop extends Effect {
 
  private Expression<Location> location;
  private Expression<EntityType> entity;
 
  @Override
  protected void execute(Event event){
	  		Location l = location.getSingle(event);
	  		try {
	  		Bukkit.broadcastMessage(l.getBlock().getState().toString());
	  		CreatureSpawner cs = (CreatureSpawner) l.getBlock().getState();
	  		} catch {
	  			Bukkit.broadcastMessage("Umbaska had a problem!");
	  		}
	  		if (cs == null) {
	  			return;
	  		}
	  		String e = entity.getSingle(event).toString();
	  		cs.setCreatureTypeByName(e);
	  		Item i = (Item) cs;
	  		World w = l.getWorld();
	  		w.dropItemNaturally(l, (ItemStack) i);
        }
  
 
  @Override
  public String toString(Event event, boolean b){
    return "Set a spawner";
  }
 
  @Override
  @SuppressWarnings("unchecked")
  public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
	  	location = (Expression<Location>) expressions[0];
	  	entity = (Expression<EntityType>) expressions[1];
        return true;
  }
}