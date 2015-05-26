/*
 * ExprYAMLString.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Misc;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.event.Event;

import uk.co.umbaska.Managers.YAMLManager;


public class ExprYAMLString extends SimpleExpression<String>{

    private Expression<String> cmd, ccc;

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
        this.cmd = (Expression<String>) args[0];
        this.ccc = (Expression<String>) args[1];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "permission of cmd";
    }

	@Override
    @javax.annotation.Nullable
    protected String[] get(Event arg0) {

        String c = this.cmd.getSingle(arg0);
        String cc = this.ccc.getSingle(arg0);
        YAMLManager yaml = new YAMLManager();
        String out = (String) yaml.getSingleYAML(cc, c, 3);
        return new String[] { out };
    }

}