package uk.co.umbaska.hologramBased;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

/**
 * Created by Zachary on 11/3/2014.
 */
public class EffSetHoloType extends Effect {
	private boolean as;


	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean,
						SkriptParser.ParseResult parse) {
		this.as = parse.mark == 0;
		return true;
	}
	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "titles";
	}

	@Override
	protected void execute(Event event) {

		HologramCentral.setUsingWitherSkulls(as);
	}
}
