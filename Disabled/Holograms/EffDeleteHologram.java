/*
 * EffDeleteHologram.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Holograms;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.Location;
import org.bukkit.event.Event;

import de.inventivegames.hologram.Hologram;
import uk.co.umbaska.Main;

public class EffDeleteHologram extends Effect {

    private Expression<Location> location;
    private Expression<String> world;

    @Override
    protected void execute(Event event){
        Location l = location.getSingle(event);
        String w = world.getSingle(event);
        if (l == null) {
            return;
        } else if (w == null) {
            return;
        }
        HologramManager hm = Main.holoManager;
        Hologram hologram = hm.getHologramByLocation(l, w);
        hologram.despawn();

    }


    @Override
    public String toString(Event event, boolean b){
        return "Delete hologram (EffDeleteHologram)";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        location = (Expression<Location>) expressions[0];
        world = (Expression<String>) expressions[1];
        return true;
    }
}