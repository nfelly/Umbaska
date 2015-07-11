package uk.co.umbaska.Misc;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import uk.co.umbaska.Utils.TitleManager.TitleManager_V1_8_R1;


/**
 * Created by Zachary on 10/25/2014.
 */
public class EffActionBar_V1_8_R1 extends Effect implements Listener {


	private Expression<String> Actiontitle;
	private Expression<Player> Players;



	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean,
						SkriptParser.ParseResult parse) {
		this.Actiontitle = (Expression<String>) exprs[0];
		this.Players = (Expression<Player>) exprs[1];
		return true;
	}

	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "titles";
	}

	@Override

	protected void execute(Event event) {
		String newactiontitle = this.Actiontitle.getSingle(event);
		Player[] playerlist = Players.getAll(event);


		for (Player p : playerlist) {
			TitleManager_V1_8_R1.sendActionTitle(p, newactiontitle);
		}

	}
}
