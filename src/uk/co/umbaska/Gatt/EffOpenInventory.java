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
public class EffOpenInventory extends Effect{

    private Expression<InventoryTypes> types;
    private Expression<String> name;
    private Expression<Player> player;

    @Override
    protected void execute(Event event){
        Player[] p = player.getAll(event);
        String n = name.getSingle(event);
        InventoryType t = types.getSingle(event).getType();
        if (p == null) {
            return;
        }
        if (n == null){
            n = t.getDefaultTitle();
        }
        for (Player pl : p){
            Inventory inv = Bukkit.createInventory(null, t, n);

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
        player = (Expression<Player>) expressions[2];
        return true;
    }
}
