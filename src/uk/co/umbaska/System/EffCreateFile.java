/*
 * EffCreateFile.class - Made by nfell2009
 * http://umbaska.co.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.System;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import uk.co.umbaska.Managers.FileManager;
import org.bukkit.event.Event;

public class EffCreateFile extends Effect {

    private Expression<String> file;

    @Override
    protected void execute(Event event){
        String f = file.getSingle(event);
        FileManager fm = new FileManager();
        fm.createFile(f);
    }


    @Override
    public String toString(Event event, boolean b){
        return "Set a spawner";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        file = (Expression<String>) expressions[0];
        return true;
    }
}