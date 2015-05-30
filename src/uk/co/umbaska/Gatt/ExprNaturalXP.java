package uk.co.umbaska.Gatt;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.co.umbaska.GattSk.Extras.Collect;
/**
 * Created by Zachary on 5/27/2015.
 */
public class ExprNaturalXP extends SimpleExpression<Number> {
    private Expression<Number> count;

    protected Number[] get(Event event) {
        Number amt = 0;

        return Collect.asArray(amt);
    }

    public boolean isSingle() {
        return true;
    }

    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    public String toString(Event event, boolean b) {
        return this.count.getSingle(event).toString();
    }

    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.count = (Expression<Number>) expressions[0];
        return true;
    }
}
