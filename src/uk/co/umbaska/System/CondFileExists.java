/*
 * CondFileExists.class - Made by nfell2009
 * umbaska.co.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.System;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import org.bukkit.event.Event;

import uk.co.umbaska.Managers.FileManager;


public class CondFileExists extends Condition {


    private Expression<String> file;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, ParseResult pr) {
        file = (Expression<String>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "Relation of a town";
    }

    @Override
    public boolean check(Event e) {
        String f = file.getSingle(e);
        FileManager fm = new FileManager();
        if (fm.fileExists(f)) {
        	return true;
        } else {
        	return false;
        }
     }

    }
