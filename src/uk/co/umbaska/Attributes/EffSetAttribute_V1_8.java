package uk.co.umbaska.Attributes;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.minecraft.server.v1_8_R1.EntityInsentient;
import net.minecraft.server.v1_8_R1.IAttribute;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import uk.co.umbaska.Enums.Attributes;

/**
 * Created by Zachary on 6/30/2015.
 */
public class EffSetAttribute_V1_8 extends Effect {

    private Expression<Entity> entities;
    private Expression<Attributes> attribute;
    private Expression<Number> value;

    @Override
    protected void execute(Event event){
        Entity[] ents = entities.getAll(event);
        Number b = this.value.getSingle(event);
        IAttribute ab = attribute.getSingle(event).getAttribute();
        for (Entity e : ents) {
            EntityInsentient nmsEntity = (EntityInsentient) ((CraftLivingEntity) e).getHandle();
            nmsEntity.getAttributeInstance(ab).setValue(b.doubleValue());
        }
    }


    @Override
    public String toString(Event event, boolean b){
        return "Set Entity Attribute";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        attribute = (Expression<Attributes>) expressions[0];
        entities = (Expression<Entity>) expressions[1];
        value = (Expression<Number>) expressions[2];
        return true;
    }

}
