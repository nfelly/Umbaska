/*
 * ExprGetText.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Holograms;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.Location;
import org.bukkit.event.Event;

import de.inventivegames.hologram.Hologram;
import uk.co.umbaska.Main;

public class ExprGetText extends SimpleExpression<String>{

    private Expression<Location> location;
    private Expression<String> world;

    public Class<? extends String> getReturnType() {

        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.location = (Expression<Location>) args[0];
        this.world = (Expression<String>) args[1];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "Holograms (ExprGetText)";
    }

    @Override
    @javax.annotation.Nullable
    protected String[] get(Event arg0) {

        Location l = this.location.getSingle(arg0);
        String w = this.world.getSingle(arg0);
        if (l == null){
            return null;
        } else if (w == null) {
            return null;
        }
        HologramManager hm = Main.holoManager;
        Hologram hologram = hm.getHologramByLocation(l, w);
        String out = hologram.getText();
        return new String[] { out };
    }

}