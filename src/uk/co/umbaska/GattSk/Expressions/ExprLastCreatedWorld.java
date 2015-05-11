/*
 * ExprLastCreatedWorld.class - Made by Funnygatt
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/


package uk.co.umbaska.GattSk.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.World;
import org.bukkit.event.Event;

import uk.co.umbaska.GattSk.Extras.*;


public class ExprLastCreatedWorld extends SimpleExpression<World>{

	
	public Class<? extends World> getReturnType() {
		
		return World.class;
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
	protected World[] get(Event arg0) {
		return Collect.asArray(WorldManagers.lastCreatedWorld);
	}

}