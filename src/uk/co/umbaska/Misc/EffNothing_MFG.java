/*
 * EffNewFile.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Misc;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.co.umbaska.Managers.YAMLManager;

public class EffNothing_MFG extends Effect {

    @Override
    protected void execute(Event event) {
    	String potato = "potato";
    }


    @Override
    public String toString(Event event, boolean b){
        return "this effect literally just returns. and does thing. :)";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        return true;
    }
}