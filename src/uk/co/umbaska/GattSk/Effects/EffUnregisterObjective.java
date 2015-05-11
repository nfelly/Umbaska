package uk.co.umbaska.GattSk.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import uk.co.umbaska.GattSk.Extras.*;

import org.bukkit.event.Event;

public class EffUnregisterObjective extends Effect{
	private Expression<String> boardname;
	private Expression<String> objectivename;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] args, int arg1, Kleenean arg2,
			ParseResult arg3) {
		this.objectivename = (Expression<String>) args[0];
		this.boardname = (Expression<String>) args[1];

		
		return true;
	}

	@Override
	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "create new objective";
	}

	@Override
	protected void execute(Event arg0) {
		String boardname = this.boardname.getSingle(arg0);
		String objectivename = this.objectivename.getSingle(arg0);
		if (boardname == null | objectivename == null){
			return;
		}
		ScoreboardManagers.unregisterObjective(boardname, objectivename);
	}

}
