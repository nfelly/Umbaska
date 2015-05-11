package uk.co.umbaska.GattSk.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import uk.co.umbaska.GattSk.Extras.*;

import org.bukkit.event.Event;

public class EffNewScoreboard extends Effect{
	private Expression<String> scoreboardName;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] args, int arg1, Kleenean arg2,
			ParseResult arg3) {
		
		this.scoreboardName = (Expression<String>) args[0];
		
		return true;
	}

	@Override
	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "new scoreboard";
	}

	@Override
	protected void execute(Event arg0) {
		
		String board = this.scoreboardName.getSingle(arg0);
		if (board == null){
			return;
		}
		ScoreboardManagers.createNewScoreboard(board);
	}

}
