/*
 * EffDropAll.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Misc;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EffDropAll extends Effect {

    private Expression<Player> player;
    private Expression<Location> location;

    @Override
    protected void execute(Event event){
        Location l = location.getSingle(event);
        Player p = player.getSingle(event);
        if (l == null) {
            return;
        } else if (p == null) {
            return;
        }
        Location loc = p.getLocation().clone();
        Inventory inv = p.getInventory();
        for (ItemStack item : inv.getContents()) {
            if (item != null) {
                loc.getWorld().dropItem(l, item.clone());
            }
        }
        inv.clear();
    }


    @Override
    public String toString(Event event, boolean b){
        return "Drop all of a players inventory at a location";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        player = (Expression<Player>) expressions[0];
        location = (Expression<Location>) expressions[1];
        return true;
    }
}