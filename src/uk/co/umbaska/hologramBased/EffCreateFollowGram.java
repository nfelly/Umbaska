package uk.co.umbaska.hologramBased;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

/**
 * Created by Zachary on 11/3/2014.
 */
public class EffCreateFollowGram extends Effect {
	private Expression<Entity> entitytoFollow;
	private Expression<String> Text;



	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean,
						SkriptParser.ParseResult parse) {
		this.entitytoFollow = (Expression<Entity>) exprs[0];
		this.Text = (Expression<String>) exprs[1];
		//this.createLoc = (Expression<Location>) exprs[1];
		return true;
	}

	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "create 'follow' gram";
	}

	@Override
	protected void execute(Event event) {
		String[] text = this.Text.getAll(event);
		Entity ent = this.entitytoFollow.getSingle(event);
		if (text == null){
			return;
		}
		if (ent == null){
			return;
		}
		Hologram follow = new Hologram(ent.getLocation(), text).start();
		follow.setFollowEntity(ent, true);
	}
}
