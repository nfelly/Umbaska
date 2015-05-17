/*
 * EffMovePlot.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.PlotMe;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.World;
import org.bukkit.event.Event;

import com.worldcretornica.plotme.PlotManager;

public class EffMovePlot extends Effect {

    private Expression<String> plot1;
    private Expression<String> plot2;
    private Expression<World> world;

    @Override
    protected void execute(Event event){
        String pl1 = plot1.getSingle(event);
        String pl2 = plot2.getSingle(event);
        World w = world.getSingle(event);
        if (pl1 == null) {
            return;
        } else if (pl2 == null) {
            return;
        } else if (w == null) {
            return;
        }
        if (PlotManager.isValidId(pl1)) {
            if (PlotManager.isValidId(pl2)){
                PlotManager.movePlot(w, pl1, pl2);
            }
        }
    }


    @Override
    public String toString(Event event, boolean b){
        return "Move a plot to another plot";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        plot1 = (Expression<String>) expressions[0];
        plot2 = (Expression<String>) expressions[1];
        world = (Expression<World>) expressions[2];
        return true;
    }
}