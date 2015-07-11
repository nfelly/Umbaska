package uk.co.umbaska.WorldEdit;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Zachary on 7/11/2015.
 */
public class EffPasteSchematic extends Effect {

    private Expression<Location> location;
    private Expression<String> schemname;

    @Override
    protected void execute(Event event){
        Location l = location.getSingle(event);
        String name = schemname.getSingle(event);
        Schematic.place(name, l);
    }


    @Override
    public String toString(Event event, boolean b){
        return "Place Schematic";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        location = (Expression<Location>) expressions[1];
        schemname = (Expression<String>) expressions[0];
        return true;
    }
}
