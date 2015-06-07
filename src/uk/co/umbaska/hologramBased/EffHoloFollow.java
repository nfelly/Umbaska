package uk.co.umbaska.hologramBased;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

public class EffHoloFollow extends Effect {
	private Expression<String> HologramName;
	private Expression<Entity> ent;



	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean,
						SkriptParser.ParseResult parse) {
		this.HologramName = (Expression<String>) exprs[0];
		this.ent = (Expression<Entity>) exprs[1];
		return true;
	}

	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "titles";
	}

	@Override
	protected void execute(Event event) {

		Entity entity = this.ent.getSingle(event);
		String holoName = this.HologramName.getSingle(event);
		if (holoName == null){
			return;
		}
		Hologram h = Holograms.get(holoName);
		h.setFollowEntity(entity);
	}
}
