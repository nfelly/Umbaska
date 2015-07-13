package uk.co.umbaska.Misc;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import uk.co.umbaska.Utils.ItemManager;

/**
 * Created by Zachary on 6/13/2015.
 */
public class ExprBetterGlow_V1_8_R3 extends SimpleExpression<ItemStack> {

    private Expression<ItemStack> item;

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        this.item = (Expression<ItemStack>) args[0];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "glow";
    }

    @Override
    @javax.annotation.Nullable
    protected ItemStack[] get(Event arg0) {

        ItemStack itemStack = item.getSingle(arg0);
        return new ItemStack[] {ItemManager.addGlow(itemStack)};
    }


    public Class<? extends ItemStack> getReturnType(){
        return ItemStack.class;
    }

}
