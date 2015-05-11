/*
 * ExprClickedSlot.class - Made by Funnygatt
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/


package uk.co.umbaska.GattSk.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;

import uk.co.umbaska.GattSk.Extras.Collect;

public class ExprClickedSlot extends SimpleExpression<Integer>{
	
	public Class<? extends Integer> getReturnType() {
		
		return Integer.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
		return true;
	}

	@Override
	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "entity spawn reason";
	}

	@Override
	@javax.annotation.Nullable
	protected Integer[] get(Event event) {
		return Collect.asArray(((InventoryClickEvent)event).getSlot());
	}

}