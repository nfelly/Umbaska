/*
 * EffCopy.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Misc;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.util.StringUtil;
import uk.co.umbaska.GattSk.Extras.Collect;

public class EffCentredText extends SimpleExpression<String> {
	private Expression<String> msg;

	protected String[] get(Event event) {
		String msg = this.msg.getSingle(event);

		return Collect.asArray(StringUtils.center(msg, 52));
	}

	public boolean isSingle() {
		return true;
	}

	public Class<? extends String> getReturnType() {
		return String.class;
	}

	public String toString(Event event, boolean b) {
		return this.msg.getSingle(event).toString();
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.msg = (Expression<String>) expressions[0];
		return true;
	}
}
