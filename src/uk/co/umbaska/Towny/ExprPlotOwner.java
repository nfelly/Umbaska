/*
 * ExprPlotOwner.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/


package uk.co.umbaska.Towny;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.Location;
import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.TownyUniverse;


public class ExprPlotOwner extends SimpleExpression<String>{

    private Expression<Location> location;

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
        this.location = (Expression<Location>) args[0];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "return plot owner";
    }

    @Override
    @javax.annotation.Nullable
    protected String[] get(Event arg0) {
        Location l = this.location.getSingle(arg0);
        if (l == null){
            return null;
        }


        String s = null;
        try {
            s = TownyUniverse.getTownBlock(l).getResident().toString();
        } catch (NotRegisteredException e) {
            e.printStackTrace();
        }

        if (s == null) {
            return null;
        }

        return new String[] { s };
    }

}
