/*
 * ExprGroupOfPlayer.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/


package uk.nfell2009.umbaska.Vault;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import uk.nfell2009.umbaska.Main;

public class ExprGroupOfPlayer extends SimpleExpression<String>{

	private Expression<Player> player;
	
	public Class<? extends String> getReturnType() {
		
		return String.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
		this.player = (Expression<Player>) args[0];
		return true;
	}

	@Override
	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "return primary group of player";
	}

	@Override
	@javax.annotation.Nullable
	protected String[] get(Event arg0) {
		Player p = this.player.getSingle(arg0);
		Permission perms = Main.perms;
		String out = perms.getPrimaryGroup(p);

		return new String[] { out };
	}

}