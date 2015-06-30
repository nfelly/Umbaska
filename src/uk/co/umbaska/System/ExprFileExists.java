/*
 * ExprFileExists.class - Made by nfell2009
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

public class ExprFileExists extends SimpleExpression<Boolean>{

    private Expression<String> file;

    public Class<? extends Boolean> getReturnType() {

        return Boolean.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.file = (Expression<String>) args[0];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "set {v} to existance of %string%";
    }

    @Override
    @javax.annotation.Nullable
    protected Boolean[] get(Event arg0) {
        String f = file.getSingle(arg0);
        FileManager fm = new FileManager();
        return new Boolean[] { fm.fileExists(f) };
    }

}