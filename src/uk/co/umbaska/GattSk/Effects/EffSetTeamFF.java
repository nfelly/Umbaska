package uk.co.umbaska.GattSk.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import uk.co.umbaska.GattSk.Extras.*;

import org.bukkit.event.Event;

/**
 * Created by Zachary on 11/28/2014.
 */
public class EffSetTeamFF extends Effect {
	private Expression<String> boardname;
	private Expression<String> teamName;
	private Expression<Boolean> bool;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] args, int arg1, Kleenean arg2,
						SkriptParser.ParseResult arg3) {
		this.boardname = (Expression<String>) args[1];
		this.teamName = (Expression<String>) args[0];
		this.bool = (Expression<Boolean>) args[2];
		return true;
	}
	@Override
	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "create new objective";
	}
	@Override
	protected void execute(Event arg0) {
		String boardname = this.boardname.getSingle(arg0);
		String teamname = this.teamName.getSingle(arg0);
		Boolean value = this.bool.getSingle(arg0);
		if (teamname == null | boardname == null){
			return;
		}
		//(String boardname, String teamName, String option, String stringValue, Boolean boolValue, Integer intValue)
		ScoreboardManagers.setTeamOption(boardname, teamname, "friendly fire", null, value, null);
	}
}
