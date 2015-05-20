/*
 * EffAddLineAbove.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Holograms;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Event;

import de.inventivegames.hologram.Hologram;
import uk.co.umbaska.Main;

public class EffAddLineAbove extends Effect {

    private Expression<Location> location;
    private Expression<String> text, world;

    @Override
    protected void execute(Event event){
        Location l = location.getSingle(event);
        String t = text.getSingle(event);
        String w = world.getSingle(event);
        if (l == null) {
            return;
        } else if (t == null) {
            return;
        }
        Bukkit.broadcastMessage(l.toString());
        Hologram hologram = Main.holoManager.getHologramByLocation(l, w);
        if (hologram == null) {
            return;
        }
        hologram.addLineAbove(t);
        hologram.update();
    }


    @Override
    public String toString(Event event, boolean b){
        return "Add line below (EffAddLineAbove)";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        text = (Expression<String>) expressions[0];
        location = (Expression<Location>) expressions[1];
        world = (Expression<String>) expressions[2];
        return true;
    }
}