package uk.co.umbaska.Misc;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

/**
 * Created by Zachary on 12/2/2014.
 */
public class ExprForceFly extends SimplePropertyExpression<Player, Boolean> {
	@Override
	public Boolean convert(Player ent) {
		if(ent == null)
			return null;
		return ent.isFlying();
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Player ent = getExpr().getSingle(e); //Called to get the Target which is Player in this case.
		if(ent == null)
			return;
		Boolean b = (Boolean) (delta[0]);
		if (mode == Changer.ChangeMode.SET){
            ent.setFlying(b);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET) //SET can be replaced with REMOVE ADD or similiar stuff.
			return CollectionUtils.array(Boolean.class); //The Class should be the TypeToGet and in this case Number.
		return null;
	}

	@Override
	public Class<? extends Boolean> getReturnType() { //ReturnType is TypeToGet and in this case Number.
		return Boolean.class;

	}
	@Override
	protected String getPropertyName() {
		// TODO Auto-generated method stub
		return "Force Fly";
	}

}
