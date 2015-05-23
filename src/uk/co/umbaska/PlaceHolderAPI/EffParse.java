/*
 * EffParse.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.PlaceHolderAPI;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffParse extends SimpleExpression<String>{

    private Expression<String> in;
    private Expression<Player> player;

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
        this.in = (Expression<String>) args[0];
        this.player = (Expression<Player>) args[1];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "placeholder parse %string% as %player%";
    }

    @Override
    @javax.annotation.Nullable
    protected String[] get(Event arg0) {

        String s = this.in.getSingle(arg0);
        Player p = this.player.getSingle(arg0);

        if (s == null){
            return null;
        } else if (p == null){
        	return null;
        }


        return new String[] { PlaceholderAPI.setPlaceholders(p, s) };
    }

}