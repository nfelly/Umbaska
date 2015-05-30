package uk.co.umbaska.ArmorStands;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import uk.co.umbaska.GattSk.Extras.Collect;
import uk.co.umbaska.Main;

/**
 * Created by Zachary on 12/2/2014.
 */
public class ExprsLastSpawnedArmorStand extends SimpleExpression<Entity> {
	@Override
	public Class<? extends Entity> getReturnType() {

		return Entity.class;
	}

	@Override
	public boolean isSingle() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean init(Expression<?>[] args, int arg1, Kleenean arg2,
						SkriptParser.ParseResult arg3) {
		return true;
	}

	@Override
	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return "last spawned armor stand";
	}

	@Override
	@javax.annotation.Nullable
	public Entity[] get(Event arg0) {
		return Collect.asArray(Main.armorStand);
	}
}
