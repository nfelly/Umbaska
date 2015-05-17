/*
 * EffSetPlotPrice.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Towny;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.Location;
import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.object.TownyUniverse;

public class EffSetPlotPrice extends Effect {

    private Expression<Location> location;
    private Expression<Double> price;

    @Override
    protected void execute(Event event){
        Location l = location.getSingle(event);
        Double i = price.getSingle(event);
        if (l == null) {
            return;
        } else if (i == null) {
            return;
        }
        TownyUniverse.getTownBlock(l).setPlotPrice(i);
    }


    @Override
    public String toString(Event event, boolean b){
        return "Set a plot price";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        location = (Expression<Location>) expressions[0];
        price = (Expression<Double>) expressions[1];
        return true;
    }
}
