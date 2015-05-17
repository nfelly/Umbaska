/*
 * ExprTopCorner.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.PlotMe;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;

import com.worldcretornica.plotme.PlotManager;

public class ExprTopCorner extends SimpleExpression<Location>{

    private Expression<String> plot;
    private Expression<World> world;

    public Class<? extends Location> getReturnType() {

        return Location.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.plot = (Expression<String>) args[0];
        this.world = (Expression<World>) args[1];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "return owner of plot";
    }

    @Override
    @javax.annotation.Nullable
    protected Location[] get(Event arg0) {

        String plot = this.plot.getSingle(arg0);
        World w = this.world.getSingle(arg0);

        if (plot == null){
            return null;
        } else if (w == null) {
            return null;
        }

        if (!PlotManager.isValidId(plot)) {
            return new Location[] { null } ;
        }
        Location out = PlotManager.getPlotTopLoc(w, plot);
        return new Location[] { out };
    }

}