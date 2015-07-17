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

import java.util.Calendar;
import java.util.Date;


public class ExprGetDay extends SimpleExpression<DayOfWeek>{

    private Expression<Date> date;

    public Class<? extends DayOfWeek> getReturnType() {

        return DayOfWeek.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.date = (Expression<Date>) args[0];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "day of week from date";
    }

	@Override
    @javax.annotation.Nullable
    protected DayOfWeek[] get(Event arg0) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.date.getSingle(arg0));
		int day = cal.get(Calendar.DAY_OF_WEEK);

        return new DayOfWeek[] { DayOfWeek.getDay(day)};
    }

}

