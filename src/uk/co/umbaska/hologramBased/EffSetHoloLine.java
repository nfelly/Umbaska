package uk.co.umbaska.hologramBased;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

/**
 * Created by Zachary on 11/3/2014.
 */
public class EffSetHoloLine extends Effect {
	private Expression<Integer> Line;
	private Expression<String> HologramName;
	private Expression<String> Text;



	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean,
						SkriptParser.ParseResult parse) {
		this.Line = (Expression<Integer>) exprs[0];
		this.HologramName = (Expression<String>) exprs[1];
		this.Text = (Expression<String>) exprs[2];
		return true;
	}

	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "set specific line number";
	}

	@Override
	protected void execute(Event event) {
		Integer lineNo = this.Line.getSingle(event);
		String text = this.Text.getSingle(event);
		String holoName = this.HologramName.getSingle(event);
		if (holoName == null){
			return;
		}
		Hologram h = Holograms.get(holoName);
		String[] currentLines = h.getLines();
		currentLines[lineNo] = text;
		h.setLines(currentLines);
	}
}
