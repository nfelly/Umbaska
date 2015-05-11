package uk.co.umbaska.GattSk.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import uk.co.umbaska.GattSk.Extras.*;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffSetPlayerScoreboard extends Effect{
	private Expression<Player> players;
	private Expression<String> boardname;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] args, int arg1, Kleenean arg2,
			ParseResult arg3) {
		
		this.players = (Expression<Player>) args[0];
		this.boardname = (Expression<String>) args[1];
		
		return true;
	}

	@Override
	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "set player scoreboard";
	}

	@Override
	protected void execute(Event arg0) {
		Player[] players = this.players.getAll(arg0);
		String boardname = this.boardname.getSingle(arg0);
		if (players == null | boardname == null){
			return;
		}
		for (Player p : players){
			ScoreboardManagers.setPlayerScoreboard(p, boardname);
		}
	}

}
