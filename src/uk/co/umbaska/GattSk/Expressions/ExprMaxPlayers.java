/*
 * ExprMaxPlayers.class - Made by Funnygatt
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/


package uk.co.umbaska.GattSk.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;


public class ExprMaxPlayers extends SimpleExpression<Integer>{
	
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
		return "faction of player";
	}

	@Override
	@javax.annotation.Nullable
	protected Integer[] get(Event arg0) {
		return new Integer[] { Integer.valueOf(Bukkit.getMaxPlayers()) };
	}

}