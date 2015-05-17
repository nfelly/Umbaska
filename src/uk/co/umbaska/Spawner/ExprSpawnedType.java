/*
 * ExprSpawnedType.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/


package uk.co.umbaska.Spawner;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.Location;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.Event;

public class ExprSpawnedType extends SimpleExpression<String>{

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
        return "get entity type of a spawner";
    }

    @Override
    @javax.annotation.Nullable
    protected String[] get(Event arg0) {
        Location l = location.getSingle(arg0);
        CreatureSpawner cs = (CreatureSpawner) l.getBlock().getState();
        String e = cs.getCreatureTypeName();
        return new String[] { e };
    }

}