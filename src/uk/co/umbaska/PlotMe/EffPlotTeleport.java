/*
 * EffPlotTeleport.class - Made by nfell2009
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
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.Location;

import com.worldcretornica.plotme.PlotManager;

public class EffPlotTeleport extends Effect {

    private Expression<Player> ply;
    private Expression<String> plot;
    private Expression<World> world;

    @Override
    protected void execute(Event event){
        Player p = ply.getSingle(event);
        String pl = plot.getSingle(event);
        World w = world.getSingle(event);
        if (p == null) {
            return;
        } else if (pl == null) {
            return;
        } else if (w == null) {
            w = p.getWorld();
        }
        if (PlotManager.isValidId(pl)) {
            Location bottom = PlotManager.getPlotBottomLoc(w, pl);
            Location top = PlotManager.getPlotBottomLoc(w, pl);
            p.teleport(new Location(w, bottom.getX() + (top.getBlockX() - bottom.getBlockX()) / 2, PlotManager.getMap(w).RoadHeight + 2, bottom.getZ() - 2));
        }
    }


    @Override
    public String toString(Event event, boolean b){
        return "Teleport player to plot";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        ply = (Expression<Player>) expressions[0];
        plot = (Expression<String>) expressions[1];
        world = (Expression<World>) expressions[2];
        return true;
    }
}