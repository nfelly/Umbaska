package uk.co.umbaska.ArmorStands;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

/**
 * Created by Zachary on 12/2/2014.
 */
public class EffClearHitbox_V1_8_R3 extends Effect {
	private Expression<Entity> entity;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean,
						SkriptParser.ParseResult parse) {
		this.entity  = (Expression<Entity>) exprs[0];

		return true;
	}
	@Override
	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "clear hitbox (shhhh! SECRETSSSS)";
	}

	@Override

	protected void execute(Event event) {

        net.minecraft.server.v1_8_R3.Entity nmsent = ((CraftEntity)this.entity.getSingle(event)).getHandle();
        nmsent.a(new CustomBoundingBox_V1_8_R3(0D, 0D, 0D, 0D, 0D, 0D));

	}
}
