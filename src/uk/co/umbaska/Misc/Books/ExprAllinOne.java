package uk.co.umbaska.Misc.Books;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import uk.co.umbaska.GattSk.Extras.Collect;

/**
 * Created by Zachary on 4/24/2015.
 */
public class ExprAllinOne extends SimpleExpression<ItemStack> {

    private Expression<String> title;
    private Expression<String> pages;
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
        this.title = (Expression<String>) args[0];
        this.author = (Expression<String>) args[1];
        this.pages = (Expression<String>) args[2];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "return book";
    }

    @Override
    @javax.annotation.Nullable
    protected ItemStack[] get(Event arg0) {
        String title = this.title.getSingle(arg0);
        String author = this.author.getSingle(arg0);
        String[] pages = this.pages.getAll(arg0);
        if (author == null || title == null || pages == null){
            return null;
        }
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bm = (BookMeta)book.getItemMeta();
        bm.setTitle(title);
        bm.setPages(pages);
        bm.setAuthor(author);
        bm.setDisplayName(title);
        return Collect.asArray(book);
    }

}