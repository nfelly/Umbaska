/*
 * EffUnDeny.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.PlotMe;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.worldcretornica.plotme.Plot;
import com.worldcretornica.plotme.PlotManager;

public class EffUnDeny extends Effect {

    private Expression<String> plot;
    private Expression<Player> player;

    @Override
    protected void execute(Event event){
        String pl = plot.getSingle(event);
        String p = player.getSingle(event).toString();
        if (pl == null) {
            return;
        } else if (p == null) {
            return;
        }
        if (PlotManager.isValidId(pl)) {
            Plot plot = PlotManager.getPlotById(pl, pl);
            plot.removeDenied(p);
            plot.addAllowed(p);
        }
    }


    @Override
    public String toString(Event event, boolean b){
        return "Allow a player to a plot";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        plot = (Expression<String>) expressions[0];
        player = (Expression<Player>) expressions[1];
        return true;
    }
}