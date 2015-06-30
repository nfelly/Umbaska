package uk.co.umbaska.Misc;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import uk.co.umbaska.Enums.InventoryTypes;
import uk.co.umbaska.Main;
import uk.co.umbaska.Utils.Scatter;

/**
 * Created by Zachary on 6/30/2015.
 */
public class EffScatter extends Effect {
    private Expression<Entity> entities;
    private Expression<Integer> x, z, radius, delay;
    private Expression<World> world;
    private Expression<ItemStack> bad;

    @Override
    protected void execute(Event event){
        Entity[] p = entities.getAll(event);
        ItemStack[] bad = this.bad.getAll(event);
        Scatter scatter = new Scatter(Main.plugin, this.world.getSingle(event), this.radius.getSingle(event), this.x.getSingle(event), this.z.getSingle(event), bad, this.delay.getSingle(event), p);
    }


    @Override
    public String toString(Event event, boolean b){
        return "Open Inventory";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        entities = (Expression<Entity>) expressions[0];
        x = (Expression<Integer>) expressions[1];
        z = (Expression<Integer>) expressions[2];
        world = (Expression<World>) expressions[3];
        radius = (Expression<Integer>) expressions[4];
        bad = (Expression<ItemStack>) expressions[5];
        delay = (Expression<Integer>) expressions[6];
        return true;
    }
}
