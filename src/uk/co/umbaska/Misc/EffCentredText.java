/*
 * EffCopy.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Misc;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffCentredText extends Effect {

    private Expression<String> message;
    private Expression<Player> player;

    @Override
    protected void execute(Event event) {
    	String msg = message.getSingle(event);
    	Player[] p = player.getAll(event);
    	String out = StringUtils.center(msg, 52);
    	for (Player p1 : p){
    	    p1.sendMessage(out);
    	}
    }


    @Override
    public String toString(Event event, boolean b){
        return "Send centred text";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        message = (Expression<String>) expressions[0];
        player = (Expression<Player>) expressions[1];
        return true;
    }
}
