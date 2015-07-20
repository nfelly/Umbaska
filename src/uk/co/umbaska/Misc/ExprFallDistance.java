package uk.co.umbaska.Misc;

/**
 * Created by Zachary on 6/7/2015.
 */

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprFallDistance extends SimpleExpression<Number> {
    private Expression<Entity> ent;

    public Class<? extends Number> getReturnType()
    {
        return Number.class;
    }

    public boolean isSingle()
    {
        return true;
    }

    @SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean kl, ParseResult pr)
    {
        ent = (Expression<Entity>)expr[0];
        return true;
    }

    public String toString(@Nullable Event event, boolean b)
    {
        return "Fall Distance";
    }

    @Nullable
    protected Number[] get(Event event)
    {
        return new Number[]{this.ent.getSingle(event).getFallDistance()};
    }
}
