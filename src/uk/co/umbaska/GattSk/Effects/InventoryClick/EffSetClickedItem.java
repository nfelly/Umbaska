/*
 * ExprClickedItem.class - Made by Funnygatt
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/


package uk.co.umbaska.GattSk.Effects.InventoryClick;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.umbaska.GattSk.Extras.Collect;

public class EffSetClickedItem extends Effect {

	Expression<ItemStack> item;

	@Override
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parse) {

		this.item = (Expression<ItemStack>)exprs[0];
		return true;
	}

	@Override
	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "set clicked item";
	}
	@Override
	protected void execute(Event event) {
		ItemStack itemTobe = this.item.getSingle(event);
		((InventoryClickEvent)event).getCurrentItem().setType(itemTobe.getType());
		((InventoryClickEvent)event).getCurrentItem().setItemMeta(itemTobe.getItemMeta());
		((InventoryClickEvent)event).getCurrentItem().addEnchantments(itemTobe.getEnchantments());
		((InventoryClickEvent)event).getCurrentItem().setAmount(itemTobe.getAmount());
		((InventoryClickEvent)event).getCurrentItem().setData(itemTobe.getData());
		((InventoryClickEvent)event).getCurrentItem().setDurability(itemTobe.getDurability());
	}

}