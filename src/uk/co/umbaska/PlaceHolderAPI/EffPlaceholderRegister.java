/*
 * EffPlaceholderRegister.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.PlaceHolderAPI;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.variables.Variables;
import ch.njol.util.Kleenean;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;

import uk.nfell2009.umbaskapi.Main;

public class EffPlaceholderRegister extends Effect {

    private Expression<String> id, variable;

    @Override
    protected void execute(Event event){
        final String s = id.getSingle(event);
        final String v = variable.getSingle(event);
        if (s == null) {
            return;
        } else if (v == null) {
        	return;
        }
        Plugin umbaska = Main.getInstance();

        if (umbaska != null) {
		
			boolean Umbaska = PlaceholderAPI.registerPlaceholderHook("Umbaska", new PlaceholderHook() {

				@Override
				public String onPlaceholderRequest(Player p, String identifier) {

					if (!identifier.isEmpty()) {
						return String.valueOf(Variables.getVariable(v, null, true));
					}

					return null;
				}

			});

	if (Umbaska) {
		Main.getInstance().logger.info("Registered new Placeholder!");
	}
		}
    }


    @Override
    public String toString(Event event, boolean b){
        return "Registers a variable for clips placeholderapi";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        id = (Expression<String>) expressions[0];
        variable = (Expression<String>) expressions[0];
        return true;
    }
}