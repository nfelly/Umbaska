package uk.co.umbaska.WildSkript.system;

import uk.co.umbaska.Main;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprTPS extends SimpleExpression<Double>{
	
	protected Double[] get(Event event) {
		double tps = Main.getTimer().getTPS();
		return new Double[] { tps };
    }
	
	public boolean isSingle() { 
		return true;
	}

    public Class<? extends Double> getReturnType() { 
    	return Double.class;
    }

	public String toString(Event event, boolean b) { 
		return this.getClass().getName();
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {    
		return true;
	}
}	

