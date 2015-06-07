package uk.co.umbaska.hologramBased;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

/**
 * Created by --- on 11/25/2014.
 */
public class EffAddHoloLine extends Effect {

	private Expression<String> HologramName;
	private Expression<String> Text;



	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean,
						SkriptParser.ParseResult parse) {
		this.HologramName = (Expression<String>) exprs[0];
		this.Text = (Expression<String>) exprs[1];
		return true;
	}

	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "add holo line";
	}

	@Override
	protected void execute(Event event) {

		String[] text = this.Text.getAll(event);
		String holoName = this.HologramName.getSingle(event);
		if (holoName == null){
			return;
		}
		Hologram h = Holograms.get(holoName);

		//HologramCentral.holoText.get(h).set(null, null);

		h.setLines(text);
	}
}
