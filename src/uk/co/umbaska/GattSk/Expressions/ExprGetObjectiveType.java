/*
 * ExprGetObjectiveType.class - Made by Funnygatt
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/


package uk.co.umbaska.GattSk.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import uk.co.umbaska.GattSk.Extras.*;

import org.bukkit.event.Event;

public class ExprGetObjectiveType extends SimpleExpression<String>{
	
	private Expression<String> objective;
	private Expression<String> board;
	
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
		this.objective = (Expression<String>) args[0];
		this.board = (Expression<String>) args[1];
		return true;
	}

	@Override
	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "entity spawn reason";
	}

	@Override
	@javax.annotation.Nullable
	protected String[] get(Event arg0) {

		String obj = this.objective.getSingle(arg0);
		String board = this.board.getSingle(arg0);
		if (obj == null | board == null){
			return null;
		}
		return Collect.asArray(ScoreboardManagers.getObjectiveType(board, obj));
	}

}