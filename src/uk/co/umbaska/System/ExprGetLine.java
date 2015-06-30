/*
 * ExprGetLine.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/


package uk.co.umbaska.System;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.event.Event;

import uk.co.umbaska.Managers.FileManager;

public class ExprGetLine extends SimpleExpression<String>{

    private Expression<Integer> line;
    private Expression<String> file;

    public Class<? extends String> getReturnType() {

        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.line = (Expression<Integer>) args[0];
        this.file = (Expression<String>) args[1];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "set {v} to line %integer% of %file%";
    }

    @Override
    @javax.annotation.Nullable
    protected String[] get(Event arg0) {
        String f = file.getSingle(arg0);
        Integer l = line.getSingle(arg0);
        FileManager fm = new FileManager();
        return new String[] { fm.getLineOfFile(f, l) };
    }

}