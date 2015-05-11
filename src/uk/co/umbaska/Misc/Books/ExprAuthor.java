package uk.co.umbaska.Misc.Books;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import uk.co.umbaska.GattSk.Extras.Collect;

/**
 * Created by Zachary on 4/24/2015.
 */
public class ExprAuthor extends SimpleExpression<ItemStack> {

    private Expression<ItemStack> book;
    private Expression<String> author;

    public Class<? extends ItemStack> getReturnType() {

        return ItemStack.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        this.book = (Expression<ItemStack>) args[0];
        this.author = (Expression<String>) args[1];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "return book";
    }

    @Override
    @javax.annotation.Nullable
    protected ItemStack[] get(Event arg0) {
        ItemStack book = this.book.getSingle(arg0);
        String author = this.author.getSingle(arg0);
        if (author == null || book == null){
            return null;
        }
        BookMeta bm = (BookMeta)book.getItemMeta();
        bm.setAuthor(author);
        book.setItemMeta(bm);
        return Collect.asArray(book);
    }

}
