package uk.co.umbaska.Replacers;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.Location;
import org.bukkit.event.Event;

import uk.co.umbaska.Enums.ParticleEnum;

/**
 * Created by Zachary on 5/17/2015.
 */
public class EffParticleAll_V1_8_R1 extends Effect{

    private Expression<ParticleEnum> particleName;
    private Expression<Number> offx, offy, offz, speed, data, secondaryData, count;
    private Expression<Location> locations;

    @Override
    protected void execute(Event event){
        ParticleEnum particlename = particleName.getSingle(event);
        Number offx = this.offx.getSingle(event);
        Number offy = this.offy.getSingle(event);
        Number offz = this.offz.getSingle(event);
        Number speed = this.speed.getSingle(event);
        Location[] loc = this.locations.getAll(event);
        Integer count = this.count.getSingle(event).intValue();
        Integer data = this.data.getSingle(event).intValue();
        Integer secondaryData = this.secondaryData.getSingle(event).intValue();
        if (particlename == null) {
            return;
        }
        ParticleFunction.spawnParticle(count, particlename, speed, offx, offy, offz, loc,  data, secondaryData);
    }


    @Override
    public String toString(Event event, boolean b){
        return "Spawn Particle";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        count = (Expression<Number>) expressions[0];
        particleName = (Expression<ParticleEnum>) expressions[1];
        speed = (Expression<Number>) expressions[2];
        offx =(Expression<Number>) expressions[3];
        offy =(Expression<Number>) expressions[4];
        offz =(Expression<Number>) expressions[5];
        locations = (Expression<Location>) expressions[6];
        data =(Expression<Number>) expressions[7];
        secondaryData =(Expression<Number>) expressions[8];
        return true;
    }
}
