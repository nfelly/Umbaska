package uk.co.umbaska.Replacers;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.event.Event;
import uk.co.umbaska.Enums.BukkitEffectEnum;

/**
 * Created by Zachary on 5/17/2015.
 */
public class EffBukkitEffectAll_V1_8_R3 extends Effect{

    private Expression<BukkitEffectEnum> particleName;
    private Expression<Number>  data, secondaryData;
    private Expression<Location> locations;

    @Override
    protected void execute(Event event){
        BukkitEffectEnum particlename = particleName.getSingle(event);
        Location[] loc = this.locations.getAll(event);
        Integer data = this.data.getSingle(event).intValue();
        Integer secondaryData = this.secondaryData.getSingle(event).intValue();
        if (particlename == null) {
            return;
        }
        if (ParticleFunction.spawnEffect(particlename, loc, data, secondaryData) == false){
            Skript.error("Unknown Effect! " + particlename + " isn't a valid effect! \nSee https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Effect.html for valid particle effects!");
        }
        return;
    }


    @Override
    public String toString(Event event, boolean b){
        return "Spawn Particle";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        particleName = (Expression<BukkitEffectEnum>) expressions[0];
        locations = (Expression<Location>) expressions[1];
        data =(Expression<Number>) expressions[2];
        secondaryData =(Expression<Number>) expressions[3];
        return true;
    }
}
