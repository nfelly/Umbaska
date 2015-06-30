/*
 * ExprArmourPoints.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Misc;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

public class ExprEntityFromVariable extends SimpleExpression<Entity>{

    private Expression<Entity> entity;

    public Class<? extends Entity> getReturnType() {

        return Entity.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.entity = (Expression<Entity>) args[0];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "return entity from variable";
    }

    @Override
    @javax.annotation.Nullable
    protected Entity[] get(Event arg0) {

        Entity p = this.entity.getSingle(arg0);

        return new Entity[] { p };
    }

}