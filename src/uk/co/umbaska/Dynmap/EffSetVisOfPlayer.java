/*
 * EffSetVisOfPlayer.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Dynmap;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import uk.co.umbaska.Main;

public class EffSetVisOfPlayer extends Effect {

    private Expression<Player> player;
    private Expression<Boolean> vis;

    @Override
    protected void execute(Event event){
        Player p = player.getSingle(event);
        Boolean b = vis.getSingle(event);
        if (p == null) {
            return;
        }
        Main.api.setPlayerVisiblity(p, b);
    }


    @Override
    public String toString(Event event, boolean b){
        return "Set a players visibility on dynmap";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        player = (Expression<Player>) expressions[0];
        vis = (Expression<Boolean>) expressions[1];
        return true;
    }
}