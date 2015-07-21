package uk.co.umbaska.ArmorStands;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import net.minecraft.server.v1_8_R1.EntityArmorStand;
import net.minecraft.server.v1_8_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftArmorStand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.dynmap.bukkit.Armor;

/**
 * Created by Zachary on 12/2/2014.
 */
public class ExprMarker_V1_8_R1 extends SimplePropertyExpression<Entity, Boolean> {
	@Override
	public Boolean convert(Entity ent) {
		if(ent == null)
			return null;
        if (ent.getType() == EntityType.ARMOR_STAND) {
            EntityArmorStand nmsarmorstand = ((CraftArmorStand) ent).getHandle();
            NBTTagCompound compoundTag = nmsarmorstand.getNBTTag();
            return compoundTag.getBoolean("Marker");
        }
        else{
            return false;
        }
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Entity ent = getExpr().getSingle(e); //Called to get the Target which is Player in this case.
		if(ent == null)
			return;
		if(ent.getType() != EntityType.ARMOR_STAND){
			return;
		}
		Boolean b = (Boolean) (delta[0]);
        if (ent.getType() == EntityType.ARMOR_STAND) {
            EntityArmorStand nmsarmorstand = ((CraftArmorStand) ent).getHandle();
            NBTTagCompound compoundTag = nmsarmorstand.getNBTTag();
            if (mode == Changer.ChangeMode.SET) {
                compoundTag.setBoolean("Marker", b);
            }
            nmsarmorstand.f(compoundTag);
        }
        else{
            return;
        }
	}


	@SuppressWarnings("unchecked")
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET) //SET can be replaced with REMOVE ADD or similiar stuff.
			return CollectionUtils.array(Boolean.class); //The Class should be the TypeToGet and in this case Number.
		return null;
	}

	@Override
	public Class<? extends Boolean> getReturnType() { //ReturnType is TypeToGet and in this case Number.
		return Boolean.class;

	}
	@Override
	protected String getPropertyName() {
		// TODO Auto-generated method stub
		return "marker 1.8R1";
	}

}
