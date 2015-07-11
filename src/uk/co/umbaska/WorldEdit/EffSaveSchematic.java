package uk.co.umbaska.WorldEdit;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

/**
 * Created by Zachary on 7/11/2015.
 */
public class EffSaveSchematic extends Effect {

    private Expression<Player> player;
    private Expression<String> schemname;

    @Override
    protected void execute(Event event){
        Player l = player.getSingle(event);
        String name = schemname.getSingle(event);
        Schematic.save(l, name);
    }


    @Override
    public String toString(Event event, boolean b){
        return "Save Schematic";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        player = (Expression<Player>) expressions[1];
        schemname = (Expression<String>) expressions[0];
        return true;
    }
}
