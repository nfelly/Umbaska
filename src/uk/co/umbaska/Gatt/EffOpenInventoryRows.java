package uk.co.umbaska.Gatt;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import uk.co.umbaska.Enums.InventoryTypes;

/**
 * Created by Zachary on 3/30/2015.
 */
public class EffOpenInventoryRows extends Effect{

    private Expression<InventoryTypes> types;
    private Expression<String> name;
    private Expression<Player> player;
    private Expression<Integer> rows;

    @Override
    protected void execute(Event event){
        Player[] p = player.getAll(event);
        String n = name.getSingle(event);
        Integer i = rows.getSingle(event);
        InventoryType t = types.getSingle(event).getType();
        if (p == null) {
            return;
        }
        if (n == null){
            n = t.getDefaultTitle();
        }
        if (i != null){
            t = InventoryType.CHEST;
        }
        if (t == InventoryType.CHEST) {
            if (i == null) {
                i = 1;
            }
            i = i * 9;
        }
        for (Player pl : p){
            Inventory inv = Bukkit.createInventory(null, t, n);
            if (t == InventoryType.CHEST){
                inv = Bukkit.createInventory(null, i, n);
            }

            pl.openInventory(inv);
        }
    }


    @Override
    public String toString(Event event, boolean b){
        return "Open Inventory";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        types = (Expression<InventoryTypes>) expressions[0];
        name = (Expression<String>) expressions[1];
        rows = (Expression<Integer>) expressions[2];
        player = (Expression<Player>) expressions[3];
        return true;
    }
}
