/*
 * EffSetPlotOwner.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Towny;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.TownyUniverse;

public class EffSetPlotOwner extends Effect {

    private Expression<Location> location;
    private Expression<Player> player;

    @Override
    protected void execute(Event event){
        Location l = location.getSingle(event);
        String p = player.getSingle(event).toString();
        if (l == null) {
            return;
        } else if (p == null) {
            return;
        }
        Resident r = null;
        try {
            r = TownyUniverse.getDataSource().getResident(p);
        } catch (NotRegisteredException e) {
            e.printStackTrace();
        }
        TownyUniverse.getTownBlock(l).setResident(r);
    }


    @Override
    public String toString(Event event, boolean b){
        return "Set a plot owner";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        location = (Expression<Location>) expressions[0];
        player = (Expression<Player>) expressions[1];
        return true;
    }
}