package uk.co.umbaska.Replacers;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Zachary on 5/21/2015.
 */
public class EffSetResultSlot extends Effect {

    private Expression<Player> player;
    private Expression<ItemStack> itemstack;

    @Override
    protected void execute(Event event){
        Player p = player.getSingle(event);
        ItemStack item = itemstack.getSingle(event);
        Inventory inv = p.getOpenInventory().getTopInventory();
        if (inv.getType() == InventoryType.WORKBENCH){
            ((CraftingInventory)inv).setResult(item);
        }
        if (inv.getType() == InventoryType.FURNACE){
            ((FurnaceInventory)inv).setResult(item);
        }
        p.updateInventory();
    }


    @Override
    public String toString(Event event, boolean b){
        return "Set Result Slot";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        itemstack = (Expression<ItemStack>) expressions[1];
        player = (Expression<Player>) expressions[0];
        return true;
    }
}
