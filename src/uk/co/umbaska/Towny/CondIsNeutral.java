/*
 * CondIsNeutral.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Towny;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;


public class CondIsNeutral extends Condition {


    private Expression<String> twn1;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, ParseResult pr) {
        twn1 = (Expression<String>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "Relation of a town";
    }

    @Override
    public boolean check(Event e) {
        String tw1 = twn1.getSingle(e);
        Town t1 = null;
        try {
            t1 = TownyUniverse.getDataSource().getTown(tw1);
        } catch (NotRegisteredException e1) {
            e1.printStackTrace();
        }
        Nation n1 = null;
        try {
            n1 = t1.getNation();
        } catch (NotRegisteredException e1) {
            e1.printStackTrace();
        }
        Boolean out = false;
        if (n1.isNeutral()) {
            out = true;
            return (out);
        } else {
            out = false;
            return (out);
        }

    }

}