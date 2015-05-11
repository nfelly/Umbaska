package uk.co.umbaska.GattSk.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import uk.co.umbaska.GattSk.Extras.*;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

/**
 * Created by Zachary on 11/28/2014.
 */
public class EffTeamPlayer extends Effect{
	private Expression<String> boardname;
	private Expression<String> teamName;
	private Expression<OfflinePlayer> player;
	private boolean add;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] args, int arg1, Kleenean arg2,
						SkriptParser.ParseResult arg3) {
		this.boardname = (Expression<String>) args[2];
		this.teamName = (Expression<String>) args[1];
		this.player = (Expression<OfflinePlayer>) args[0];
		this.add = arg3.mark == 0;
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
		OfflinePlayer[] player = this.player.getAll(arg0);
		if (teamname == null | boardname == null){
			return;
		}
		if (this.add == false) {
			for (OfflinePlayer p : player) {
				ScoreboardManagers.addPlayerToTeam(boardname, teamname, p);
			}
		}
		if (this.add == true) {
			for (OfflinePlayer p : player) {
				ScoreboardManagers.removePlayerFromTeam(boardname, teamname, p);
			}
		}
	}
}
