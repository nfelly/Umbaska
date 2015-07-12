package uk.co.umbaska.Misc;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Color;
import ch.njol.util.Kleenean;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import uk.co.umbaska.Utils.ItemManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zachary on 6/13/2015.
 */
public class ExprDyed extends SimpleExpression<ItemStack> {

    private Expression<Color> color;
    private Expression<ItemStack> item;

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        this.color = (Expression<Color>) args[1];
        this.item = (Expression<ItemStack>) args[0];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "dyed item";
    }

    @Override
    @javax.annotation.Nullable
    protected ItemStack[] get(Event arg0) {

        ItemStack itemStack = item.getSingle(arg0);
        Color c = color.getSingle(arg0);

        if (Dyable.getDyable().contains(itemStack.getType())){
            LeatherArmorMeta lam = (LeatherArmorMeta)itemStack.getItemMeta();
            lam.setColor(c.getBukkitColor());
            itemStack.setItemMeta(lam);
        }

        return new ItemStack[]{itemStack};
    }

    public Class<? extends ItemStack> getReturnType(){
        return ItemStack.class;
    }

    private enum Dyable{
        LEATHER_HELMET(Material.LEATHER_HELMET), LEATHER_CHESTPLATE(Material.LEATHER_CHESTPLATE), LEATHER_PANTS(Material.LEATHER_LEGGINGS), LEATHER_BOOTS(Material.LEATHER_BOOTS);
        private Material type;
        Dyable(Material type){
            this.type = type;
        }
        public static List<Material> getDyable(){
            List<Material> types = new ArrayList<>();
            for (Dyable t : Dyable.values()){
                types.add(t.type);
            }
            return types;
        }
    }
}
