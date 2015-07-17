/*
 * ExprGetDigits.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Misc.Date;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.co.umbaska.Utils.StringToDateClass;

import java.util.Date;
import java.util.Locale;


public class ExprGetDateWithLocale extends SimpleExpression<Date>{

    private Expression<String> date;
	private Expression<String> format;
	private Expression<Locale> locale;

    public Class<? extends Date> getReturnType() {

        return Date.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.date = (Expression<String>) args[0];
		this.format = (Expression<String>) args[1];
		this.locale = (Expression<Locale>) args[2];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "date from format <locale>";
    }

	@Override
    @javax.annotation.Nullable
    protected Date[] get(Event arg0) {

        String date = this.date.getSingle(arg0);
		String form = this.format.getSingle(arg0);

		Date out = StringToDateClass.getDate(date, form, locale.getSingle(arg0));
        return new Date[] { out };
    }

}

