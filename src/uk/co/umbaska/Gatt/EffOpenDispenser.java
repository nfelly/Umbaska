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

/**
 * Created by Zachary on 3/30/2015.
 */
public class EffOpenDispenser extends Effect{

	private Expression<String> name;
	private Expression<Player[]> player;

	@Override
	protected void execute(Event event){
		Player[] p = player.getSingle(event);
		String n = name.getSingle(event);
		if (p == null) {
			return;
		} else if (name == null) {
			return;
		}
		for (Player pl : p){
			Inventory inv = Bukkit.createInventory(pl, InventoryType.DISPENSER, n);
			pl.openInventory(inv);
		}
	}


	@Override
	public String toString(Event event, boolean b){
		return "Open Hopper";
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		name = (Expression<String>) expressions[0];
		player = (Expression<Player[]>) expressions[1];
		return true;
	}
}
