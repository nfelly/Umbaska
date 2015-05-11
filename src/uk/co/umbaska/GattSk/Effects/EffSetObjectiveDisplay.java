package uk.co.umbaska.GattSk.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import uk.co.umbaska.GattSk.Extras.*;

import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by Zachary on 11/12/2014.
 */
public class EffSetObjectiveDisplay extends Effect {

	private Expression<String> boardName;
	private Expression<String> objectiveName;
	private Expression<String> slot;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] args, int arg1, Kleenean arg2,
						SkriptParser.ParseResult arg3) {
		this.objectiveName = (Expression<String>) args[0];
		this.boardName = (Expression<String>) args[1];
		this.slot = (Expression<String>) args[2];
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
		String slot = this.slot.getSingle(arg0);

		if (board == null | objective == null| slot == null){
			return;
		}

		ScoreboardManagers.setObjectiveDisplay(board, objective, slot);
	}

}
