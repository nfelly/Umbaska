package uk.co.umbaska.Replacers;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import uk.co.umbaska.Enums.ParticleEnum;

/**
 * Created by Zachary on 5/17/2015.
 */
public class EffParticle extends Effect{

    private Expression<ParticleEnum> particleName;
    private Expression<Number> offx, offy, offz, speed;
    private Expression<Integer> count, data;
    private Expression<Location> locations;
    private Expression<Player> players;

    @Override
    protected void execute(Event event){
        ParticleEnum particlename = particleName.getSingle(event);
        Number offx = this.offx.getSingle(event);
        Number offy = this.offy.getSingle(event);
        Number offz = this.offz.getSingle(event);
        Number speed = this.speed.getSingle(event);
        Location[] loc = this.locations.getAll(event);
        Integer count = this.count.getSingle(event);
        Player[] players = this.players.getAll(event);
        Integer data = this.data.getSingle(event);
        if (particlename == null) {
            return;
        }
        if (ParticleFunction.spawnParticle(count, particlename, speed, offx, offy, offz, loc, data, players) == false){
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
        count = (Expression<Integer>) expressions[0];
        particleName = (Expression<ParticleEnum>) expressions[1];
        speed = (Expression<Number>) expressions[2];
        offx =(Expression<Number>) expressions[3];
        offy =(Expression<Number>) expressions[4];
        offz =(Expression<Number>) expressions[5];
        locations = (Expression<Location>) expressions[6];
        players =(Expression<Player>) expressions[7];
        data =(Expression<Integer>) expressions[8];
        return true;
    }
}
