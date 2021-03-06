/*
 * EffSetLine.class - Made by nfell2009
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

public class EffSetLine extends Effect {

    private Expression<String> file, string;
    private Expression<Integer> line;

    @Override
    protected void execute(Event event){
        String f = file.getSingle(event);
        String s = string.getSingle(event);
        Integer l = line.getSingle(event);
        FileManager fm = new FileManager();
        fm.setLineOfFile(f, s, l);
    }


    @Override
    public String toString(Event event, boolean b){
        return "set line %integer% of %file% to %string%";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        line = (Expression<Integer>) expressions[0];
        file = (Expression<String>) expressions[1];
        string = (Expression<String>) expressions[2];
        return true;
    }
}