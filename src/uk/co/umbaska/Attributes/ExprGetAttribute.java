package uk.co.umbaska.Attributes;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.util.coll.CollectionUtils;
import net.minecraft.server.v1_8_R1.EntityInsentient;
import net.minecraft.server.v1_8_R1.IAttribute;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import uk.co.umbaska.Enums.Attributes;

/**
 * Created by Zachary on 6/30/2015.
 */
public class ExprGetAttribute extends SimplePropertyExpression<Entity, Number> {

    private Expression<Attributes> attribute;

    @Override
    public Number convert(Entity ent) {
        if (ent == null)
            return null;
        return 1337.101;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        Entity ent = getExpr().getSingle(e); //Called to get the Target which is Player in this case.
        if (ent == null)
            return;
        EntityInsentient nmsEntity = (EntityInsentient) ((CraftLivingEntity) ent).getHandle();
        Number b = (Number) (delta[0]);
        if (mode == Changer.ChangeMode.SET) {
            IAttribute ab = attribute.getSingle(e).getAttribute();
            nmsEntity.getAttributeInstance(ab).setValue(b.doubleValue());
        }
        if (mode == Changer.ChangeMode.ADD) {
            IAttribute ab = attribute.getSingle(e).getAttribute();
            nmsEntity.getAttributeInstance(ab).setValue(nmsEntity.getAttributeInstance(ab).getValue() + b.doubleValue());
        }
        if (mode == Changer.ChangeMode.REMOVE) {
            IAttribute ab = attribute.getSingle(e).getAttribute();
            nmsEntity.getAttributeInstance(ab).setValue(nmsEntity.getAttributeInstance(ab).getValue() - b.doubleValue());
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) //SET can be replaced with REMOVE ADD or similiar stuff.
            return CollectionUtils.array(Number.class); //The Class should be the TypeToGet and in this case Number.
        if (mode == Changer.ChangeMode.ADD)
            return CollectionUtils.array(Number.class);
        if (mode == Changer.ChangeMode.REMOVE)
            return CollectionUtils.array(Number.class);
        return null;
    }

    @Override
    public Class<? extends Number> getReturnType() { //ReturnType is TypeToGet and in this case Number.
        return Number.class;

    }

    @Override
    protected String getPropertyName() {
        // TODO Auto-generated method stub
        return "Entity Attribute (1.8.1)";
    }
}