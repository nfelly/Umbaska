package uk.co.umbaska.Replacers;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import uk.co.umbaska.Enums.BukkitEffectEnum;
import uk.co.umbaska.Enums.ParticleEnum;

/**
 * Created by Zachary on 5/17/2015.
 */
public class EffBukkitEffect extends Effect{

    private Expression<BukkitEffectEnum> particleName;
    private Expression<Integer> data;
    private Expression<Location> locations;
    private Expression<Player> players;

    @Override
    protected void execute(Event event){
        BukkitEffectEnum particlename = particleName.getSingle(event);
        Location[] loc = this.locations.getAll(event);
        Player[] players = this.players.getAll(event);
        Integer data = this.data.getSingle(event);
        if (particlename == null) {
            return;
        }
        if (ParticleFunction.spawnEffect(particlename, loc, data, players) == false){
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
        players =(Expression<Player>) expressions[2];
        data =(Expression<Integer>) expressions[3];
        return true;
    }
}
