/*
 * EffDelYAML.class - Made by nfell2009
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
import org.bukkit.event.Listener;

import uk.co.umbaska.Managers.YAMLManager;

public class EffDelYAML extends Effect implements Listener {

    private Expression<String> file, path;

    @Override
    protected void execute(Event event) {
    	String filee = file.getSingle(event);
    	String pathe = path.getSingle(event);
        YAMLManager yaml = new YAMLManager();
        yaml.deleteYAML(filee, pathe);
    }


    @Override
    public String toString(Event event, boolean b){
        return "Delete YAML";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        path = (Expression<String>) expressions[0];
        file = (Expression<String>) expressions[1];
        return true;
    }
}