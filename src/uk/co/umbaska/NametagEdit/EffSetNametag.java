/*
 * EffSetNametag.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.NametagEdit;

import ca.wacos.nametagedit.NametagAPI;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffSetNametag extends Effect {

    private Expression<Player> player;
    private Expression<String> changeto;
    private Expression<String> changeto2;

    @Override
    protected void execute(Event event){
        String p = player.getSingle(event).toString();
        String ct = changeto.getSingle(event);
        String ct2 = changeto2.getSingle(event);
        if (p == null) {
            return;
        } else if (ct == null) {
            return;
        }
        NametagAPI.setNametagHard(p, ct, ct2);
    }


    @Override
    public String toString(Event event, boolean b){
        return "Clear a plot";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        player = (Expression<Player>) expressions[0];
        changeto = (Expression<String>) expressions[1];
        changeto2 = (Expression<String>) expressions[2];
        return true;
    }
}