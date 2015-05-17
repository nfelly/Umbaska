/*
 * ExprItemName.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Spawner;

import javax.annotation.Nullable;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;

import org.bukkit.event.Event;
import org.bukkit.event.block.BlockPlaceEvent;


public class ExprItemName extends SimpleExpression<String> {

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, ParseResult pr) {
        if (!ScriptLoader.isCurrentEvent(BlockPlaceEvent.class)) {
            Skript.error(
                    "Cannot use client in other events other than the transaction event",
                    ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "Returns price";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[] { ((BlockPlaceEvent) e).getItemInHand().getItemMeta().getDisplayName().toString() };
    }

}