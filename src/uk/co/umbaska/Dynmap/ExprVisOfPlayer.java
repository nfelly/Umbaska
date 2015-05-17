/*
 * ExprVisOfPlayer.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Dynmap;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import uk.co.umbaska.Main;

public class ExprVisOfPlayer extends SimpleExpression<Boolean>{

    private Expression<Player> player;

    public Class<? extends Boolean> getReturnType() {

        return Boolean.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.player = (Expression<Player>) args[0];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "return dynmap vis of player";
    }

    @Override
    @javax.annotation.Nullable
    protected Boolean[] get(Event arg0) {

        Player p = this.player.getSingle(arg0);

        if (p == null){
            return null;
        }
        Boolean out = Main.api.getPlayerVisbility(p);
        return new Boolean[] { out };
    }

}