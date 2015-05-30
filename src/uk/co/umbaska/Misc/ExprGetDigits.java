/*
 * ExprGetDigits.class - Made by nfell2009
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


public class ExprGetDigits extends SimpleExpression<String>{

    private Expression<String> cmd;

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
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "permission of cmd";
    }

	@Override
    @javax.annotation.Nullable
    protected String[] get(Event arg0) {

        String src = this.cmd.getSingle(arg0);

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < src.length(); i++) {
		    char c = src.charAt(i);
		    if (Character.isDigit(c)) {
		        builder.append(c);
		    }
		}
		String out = builder.toString();
        return new String[] { out };
    }

}

