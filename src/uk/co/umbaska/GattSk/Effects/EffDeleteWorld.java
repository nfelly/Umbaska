package uk.co.umbaska.GattSk.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import uk.co.umbaska.GattSk.Extras.*;

import org.bukkit.event.Event;

/**
 * Created by Zachary on 11/14/2014.
 */
public class EffDeleteWorld extends Effect {
    private Expression<String> worldname;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2,
                        SkriptParser.ParseResult arg3) {

        this.worldname = (Expression<String>) args[0];

        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "create new world";
    }

    @Override
    protected void execute(Event arg0) {
        String worldname = this.worldname.getSingle(arg0);
        if (worldname == null){
            return;
        }
        WorldManagers.deleteWorld(worldname);
    }
}
