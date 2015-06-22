package uk.co.umbaska.Misc;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uk.co.umbaska.Utils.EnchantGlow;

/**
 * Created by Zachary on 6/13/2015.
 */
public class ExprBetterGlow extends SimplePropertyExpression<ItemStack, ItemStack> {
public String getPropertyName(){
        return "better glow";
        }

public ItemStack convert(ItemStack itemStack){

        return EnchantGlow.addGlowReturn(itemStack);
        }

public Class<? extends ItemStack> getReturnType(){
        return ItemStack.class;
}
}
