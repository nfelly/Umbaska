/*
 * EffCopy.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Misc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;

public class EffCopy extends Effect implements Listener {

    private Expression<String> file, fileee;

    @Override
    protected void execute(Event event) {
    	String filee = file.getSingle(event);
    	String fil = fileee.getSingle(event);
    	String path = System.getProperty("user.dir"); 
        File file = new File(path + "/" + filee);
        File fi = new File(path + "/" + fil);
        if (file.exists() && !fi.exists()) {
        	try {
        		Files.copy(file.toPath(), fi.toPath());
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        } else {
        	System.out.print("Can't move non-existant files!");
        }
    }


    @Override
    public String toString(Event event, boolean b){
        return "Delete file";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        file = (Expression<String>) expressions[0];
        fileee = (Expression<String>) expressions[1];
        return true;
    }
}