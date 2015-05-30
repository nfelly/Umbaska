package uk.co.umbaska.ArmorStands;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.Event;
import uk.co.umbaska.Main;

/**
 * Created by Zachary on 12/2/2014.
 */
public class EffSpawnArmorStand extends Effect {
	private Expression<Location> loc;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean,
						SkriptParser.ParseResult parse) {
		this.loc  = (Expression<Location>) exprs[0];

		return true;
	}
	@Override
	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "spawn armor stand";
	}

	@Override

	protected void execute(Event event) {
		Location[] loc = this.loc.getAll(event);

		if (loc == null){
			return;
		}

		for (Location l : loc){
			ArmorStand stand = l.getWorld().spawn(l, ArmorStand.class);
            ch.njol.skript.effects.EffSpawn.lastSpawned = stand;
            Main.armorStand = stand;
		}


	}
}
