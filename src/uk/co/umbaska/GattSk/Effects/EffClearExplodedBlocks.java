/*
 * ExprSpawnReason.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * Originally by: Funnygatt
 * 
*/

package uk.co.umbaska.GattSk.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EffClearExplodedBlocks extends Effect {
	@Override
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parse) {
		return true;
	}

	@Override
	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "remove all exploded block";
	}
	@Override
	protected void execute(Event event) {
		((EntityExplodeEvent)event).blockList().clear();
	}
}
