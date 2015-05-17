/*
 * ExprItemCountInSlot.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Misc;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;


public class ExprItemCountInSlot extends SimpleExpression<ItemStack>{

    private Expression<Player> player;
    private Expression<Integer> slot;

    public Class<? extends ItemStack> getReturnType() {

        return ItemStack.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.player = (Expression<Player>) args[0];
        this.slot = (Expression<Integer>) args[1];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "return owner of plot";
    }

    @Override
    @javax.annotation.Nullable
    protected ItemStack[] get(Event arg0) {

        Player p = this.player.getSingle(arg0);
        Integer s = this.slot.getSingle(arg0);

        p.getUniqueId();

        if (p == null || s == null){
            return null;
        }
        PlayerInventory inv = p.getInventory();
        ItemStack red = inv.getItem(s);
        return new ItemStack[] { red };
    }

}