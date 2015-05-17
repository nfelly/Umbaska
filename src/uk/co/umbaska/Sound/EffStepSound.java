/*
 * EffStepSound.class - Made by RickPlayingPL
 * (C) RickPlayingPL | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Sound;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffStepSound extends Effect {

    private Expression<String> stringMaterial;
    private Expression<Location> location;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int arg1, Kleenean arg2,
                        ParseResult arg3) {
        stringMaterial=(Expression<String>) expressions[0];
        location=(Expression<Location>) expressions[1];
        return false;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return "Play Block Break particles with sound at location";
    }

    @Override
    protected void execute(Event event) {
        String stringMaterial2 = stringMaterial.getSingle(event);
        Location loc = location.getSingle(event);
        World w = loc.getWorld();
        if(stringMaterial2==null || loc==null) {
            return;
        }
        stringMaterial2.toUpperCase();
        w.playEffect(loc, org.bukkit.Effect.STEP_SOUND, Material.getMaterial(stringMaterial2.replace(" ", "_")));
    }

}
