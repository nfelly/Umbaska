/*
 * ExprGetOwner.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.PlotMe;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.event.Event;

import com.worldcretornica.plotme.Plot;
import com.worldcretornica.plotme.PlotManager;

public class ExprGetOwner extends SimpleExpression<String>{

    private Expression<String> plot;

    public Class<? extends String> getReturnType() {

        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.plot = (Expression<String>) args[0];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "return owner of plot";
    }

    @Override
    @javax.annotation.Nullable
    protected String[] get(Event arg0) {

        String plot = this.plot.getSingle(arg0);

        if (plot == null){
            return null;
        }

        if (!PlotManager.isValidId(plot)) {
            return new String[] { null } ;
        }
        Plot p = PlotManager.getPlotById(plot, plot);
        String owner = p.owner;
        return new String[] { owner };
    }

}