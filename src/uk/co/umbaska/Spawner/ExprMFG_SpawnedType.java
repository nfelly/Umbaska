/*
 * ExprMFG_SpawnedType.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/


package uk.co.umbaska.Spawner;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;

public class ExprMFG_SpawnedType extends SimpleExpression<EntityType>{

    private Expression<Block> block;

    public Class<? extends EntityType> getReturnType() {

        return EntityType.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.block = (Expression<Block>) args[0];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "get entity type of a spawner";
    }

    @Override
    @javax.annotation.Nullable
    protected EntityType[] get(Event arg0) {
        Block b = block.getSingle(arg0);
        CreatureSpawner cs = (CreatureSpawner) b.getState();
        EntityType e = cs.getSpawnedType();
        return new EntityType[] { e };
    }

}