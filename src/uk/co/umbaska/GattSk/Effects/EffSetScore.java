package uk.co.umbaska.GattSk.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import uk.co.umbaska.GattSk.Extras.*;

import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffSetScore extends Effect{
	
	private Expression<String> boardName;
	private Expression<String> objectiveName;
	private Expression<String> scoreName;
	private Expression<Number> score;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] args, int arg1, Kleenean arg2,
			ParseResult arg3) {
		this.boardName = (Expression<String>) args[1];
		this.objectiveName = (Expression<String>) args[2];
		this.scoreName = (Expression<String>) args[0];
		this.score = (Expression<Number>) args[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return "set scoreboard score";
	}

	@Override
	protected void execute(Event arg0) {
		String board = this.boardName.getSingle(arg0);
		String objective = this.objectiveName.getSingle(arg0);
		String namescore = this.scoreName.getSingle(arg0);
		Number score = this.score.getSingle(arg0);
		
		if (board == null | objective == null| namescore == null| score == null){
			return;
		}
		
		ScoreboardManagers.setScore(board, objective, namescore, score.intValue());
		
	}

}
