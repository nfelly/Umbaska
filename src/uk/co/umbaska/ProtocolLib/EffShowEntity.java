package uk.co.umbaska.ProtocolLib;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import uk.co.umbaska.Main;

/**
 * Created by Zachary on 4/1/2015.
 */
public class EffShowEntity extends Effect {

	private Expression<Entity[]> entity;
	private Expression<Player> player;

	@Override
	protected void execute(Event event){
		Player p = player.getSingle(event);
		Entity[] e = entity.getSingle(event);
		if (p == null) {
			return;
		}
		for (Entity ent : e){
			if (ent.getType() != EntityType.PLAYER) {
				if (!Main.enthider.canSee(p, ent)) {
					Main.enthider.toggleEntity(p, ent);
				}
			}
		}

	}


	@Override
	public String toString(Event event, boolean b){
		return "Show Entity";
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		entity = (Expression<Entity[]>) expressions[0];
		player = (Expression<Player>) expressions[1];
		return true;
	}
}
