/*
 * EffCustomName.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/


package uk.co.umbaska.GattSk.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

public class EffCustomName extends Effect {
    private Expression<Entity[]> entities;
    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2,
                        SkriptParser.ParseResult arg3) {

        this.entities = (Expression<Entity[]>) args[0];
        this.name = (Expression<String>) args[1];

        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "create new objective";
    }

    @Override
    protected void execute(Event arg0) {
        Entity[] entities = this.entities.getSingle(arg0);
        String name = this.name.getSingle(arg0);
        for (Entity e : entities){
            e.setCustomName(name);
            e.setCustomNameVisible(true);
        }
    }

}

