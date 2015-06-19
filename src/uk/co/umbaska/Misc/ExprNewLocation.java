/*
 * ExprNewLocation.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Misc;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Event;


public class ExprNewLocation extends SimpleExpression<Location>{

    private Expression<String> cmd;
    private Expression<Integer> xx, yy, zz;

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
        this.cmd = (Expression<String>) args[3];
        this.xx = (Expression<Integer>) args[0];
        this.yy = (Expression<Integer>) args[1];
        this.zz = (Expression<Integer>) args[2];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "New location";
    }

	@Override
    @javax.annotation.Nullable
    protected Location[] get(Event arg0) {

        String c = this.cmd.getSingle(arg0);
        Integer x = this.xx.getSingle(arg0);
        Integer y = this.yy.getSingle(arg0);
        Integer z = this.zz.getSingle(arg0);
        
        Location out = new Location(Bukkit.getWorld(c), x, y, z);
        return new Location[] { out };
    }

}