/*
 * EffSetDelay.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Spawner;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.Location;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.Event;

public class EffSetDelay extends Effect {

    private Expression<Location> location;
    private Expression<Integer> delay;

    @Override
    protected void execute(Event event){
        Location l = location.getSingle(event);
        Integer i = delay.getSingle(event);
        if (l == null) {
            return;
        } else if (i == null) {
            return;
        }
        CreatureSpawner cs = (CreatureSpawner) l.getBlock().getState();
        cs.setDelay(i);
        cs.update();
    }


    @Override
    public String toString(Event event, boolean b){
        return "Set a spawner";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        location = (Expression<Location>) expressions[0];
        delay = (Expression<Integer>) expressions[1];
        return true;
    }
}