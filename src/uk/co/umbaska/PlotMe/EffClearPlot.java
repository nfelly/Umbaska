/*
 * EffClearPlot.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.PlotMe;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;

import com.worldcretornica.plotme.PlotManager;

public class EffClearPlot extends Effect {

    private Expression<String> plot;
    private Expression<World> world;

    @Override
    protected void execute(Event event){
        String pl = plot.getSingle(event);
        World w = world.getSingle(event);
        if (pl == null) {
            return;
        } else if (w == null) {
            return;
        }
        if (PlotManager.isValidId(pl)) {
            Location b = PlotManager.getPlotBottomLoc(w, pl);
            Location t = PlotManager.getPlotTopLoc(w, pl);
            PlotManager.clear(b, t);
        }
    }


    @Override
    public String toString(Event event, boolean b){
        return "Clear a plot";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        plot = (Expression<String>) expressions[0];
        world = (Expression<World>) expressions[1];
        return true;
    }
}