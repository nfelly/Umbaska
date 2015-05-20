/*
 * EffCreateHologram.class - Made by nfell2009
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
import de.inventivegames.hologram.HologramAPI;

public class EffCreateHologram extends Effect {

    private Expression<Location> location;
    private Expression<String> text;

    @Override
    protected void execute(Event event){
        Location l = location.getSingle(event);
        String t = text.getSingle(event);
        if (l == null) {
            return;
        } else if (t == null) {
            return;
        }
        Hologram hologram = HologramAPI.createHologram(l, t);
        hologram.spawn();
        Bukkit.broadcastMessage(l.toString());
    }


    @Override
    public String toString(Event event, boolean b){
        return "Create hologram (EffCreateHologram)";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        location = (Expression<Location>) expressions[0];
        text = (Expression<String>) expressions[1];
        return true;
    }
}