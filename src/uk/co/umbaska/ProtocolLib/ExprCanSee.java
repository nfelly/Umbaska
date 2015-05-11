package uk.co.umbaska.ProtocolLib;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import uk.co.umbaska.Main;
import uk.co.umbaska.GattSk.Extras.Collect;

/**
 * Created by Zachary on 4/1/2015.
 */
public class ExprCanSee extends SimpleExpression<Boolean> {

	private Expression<Entity> ent;
	private Expression<Player> play;

	@Override
	public Class<? extends Boolean> getReturnType() {

		return Boolean.class;
	}

	@Override
	public boolean isSingle() {
		// TODO Auto-generated method stub
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] args, int arg1, Kleenean arg2,
						SkriptParser.ParseResult arg3) {
		this.ent = (Expression<Entity>) args[0];
		this.play = (Expression<Player>) args[1];
		return true;
	}

	@Override
	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return "get visibility";
	}

	@Override
	@javax.annotation.Nullable
	protected Boolean[] get(Event arg0) {

		Entity entity = this.ent.getSingle(arg0);
		Player player = this.play.getSingle(arg0);
		if (entity == null | player == null){
			return null;
		}
		return Collect.asArray(Main.enthider.canSee(player, entity));
	}
}
