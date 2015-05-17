/*
 * ExprRDLastOnline.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/


package uk.co.umbaska.Towny;

import java.text.SimpleDateFormat;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.TownyUniverse;


public class ExprRDLastOnline extends SimpleExpression<String>{

    public static final SimpleDateFormat lastOnlineFormat = new SimpleDateFormat("MMMMM dd '@' HH:mm");

    private Expression<Player> player;

    public Class<? extends  String> getReturnType() {

        return  String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.player = (Expression<Player>) args[0];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "return last online time of player";
    }

    @Override
    @javax.annotation.Nullable
    protected String[] get(Event arg0) {
        String p = this.player.getSingle(arg0).getName();
        Resident r = null;
        try {
            r = TownyUniverse.getDataSource().getResident(p);
        } catch (NotRegisteredException e) {
            e.printStackTrace();
        }

        String s = lastOnlineFormat.format(r.getLastOnline());

        return new String[] { s };
    }

}