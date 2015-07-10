package uk.co.umbaska.Misc;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.inventory.ItemStack;
import uk.co.umbaska.Utils.ItemManager;

/**
 * Created by Zachary on 6/13/2015.
 */
public class ExprBetterGlow_V1_8_R3 extends SimplePropertyExpression<ItemStack, ItemStack> {
public String getPropertyName(){
        return "better glow";
        }

public ItemStack convert(ItemStack itemStack){

        return ItemManager.addGlow(itemStack);
        }

public Class<? extends ItemStack> getReturnType(){
        return ItemStack.class;
}
}
