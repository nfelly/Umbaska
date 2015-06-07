/*
 * ExprYAMLString.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Misc;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityExplodeEvent;
import uk.co.umbaska.GattSk.Extras.Collect;
import uk.co.umbaska.Managers.YAMLManager;


public class ExprBetterExplodedBlocks extends SimpleExpression<Block>{

    public Class<? extends Block> getReturnType() {

        return Block.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "better exploded blocks";
    }

	@Override
    @javax.annotation.Nullable
    protected Block[] get(Event arg0) {
        return (Block[])Collect.asArray(((EntityExplodeEvent)arg0).blockList().toArray());
    }

}