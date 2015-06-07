package uk.co.umbaska.Misc;

/**
 * Created by Zachary on 6/7/2015.
 */
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Direction;
import ch.njol.util.Kleenean;
import javax.annotation.Nullable;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.util.Vector;

public class ExprDirectionLocation extends SimpleExpression<Location>
{
    private Expression<Number> amount;
    private Expression<Direction> dir;
    private Expression<Location> loc;

    public Class<? extends Location> getReturnType()
    {
        return Location.class;
    }

    public boolean isSingle()
    {
        return true;
    }

    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean kl, SkriptParser.ParseResult pr)
    {
        if (matchedPattern == 0)
        {
            this.amount = (Expression<Number>)expr[0];
            this.dir = (Expression<Direction>)expr[1];
            this.loc = (Expression<Location>)expr[2];
        }
        else if (matchedPattern == 1)
        {
            this.dir = (Expression<Direction>)expr[0];
            this.amount = (Expression<Number>)expr[1];
            this.loc = (Expression<Location>)expr[2];
        }
        return true;
    }

    public String toString(@Nullable Event event, boolean b)
    {
        return "Direction Location";
    }

    @Nullable
    protected Location[] get(Event event)
    {
        Location l = this.loc.getSingle(event);
        Number n = (Number)this.amount.getSingle(event);
        Direction d = (Direction)this.dir.getSingle(event);
        if ((l == null) || (n == null) || (d == null)) {
            return null;
        }
        Vector v = d.getDirection(l).clone().multiply(n.doubleValue());
        l.setX(l.getX() + v.getX());
        l.setY(l.getY() + v.getY());
        l.setZ(l.getZ() + v.getZ());
        return new Location[] { l };
    }
}
