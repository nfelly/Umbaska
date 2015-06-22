package uk.co.umbaska.Misc;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

import uk.co.umbaska.Enums.ParticleEnum;
import uk.co.umbaska.GattSk.Extras.Collect;
import uk.co.umbaska.Main;
import uk.co.umbaska.Replacers.ParticleFunction;


/**
 * Created by Zachary on 10/25/2014.
 */
public class EffTrailEntity extends Effect implements Listener {


	private Expression<Entity> entity;
	private Expression<ParticleEnum> particle;



	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean,
						SkriptParser.ParseResult parse) {
		this.entity = (Expression<Entity>) exprs[0];
		this.particle = (Expression<ParticleEnum>) exprs[1];
		return true;
	}

	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "titles";
	}

	@Override

	protected void execute(Event event) {
		Entity[] entities = this.entity.getAll(event);
		ParticleEnum part = particle.getSingle(event);
        for (Entity e : entities){
            new TrailEntity(e, part);
        }
	}

    @SuppressWarnings("unused")
    private class TrailEntity{
        BukkitTask runnable;
		Entity ent;
        ParticleEnum part;
        private TrailEntity(final Entity ent, final ParticleEnum part){
            this.ent = ent;
            this.part = part;
            runnable = Bukkit.getScheduler().runTaskTimer(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    if (ent == null || !ent.isValid()){
                        runnable.cancel();
                    }
                    if (ent.getType() == EntityType.ARROW && ((Arrow)ent).isOnGround()){
                        runnable.cancel();
                    }
                    else{
                        ParticleFunction.spawnParticle(1, part, 0, 0, 0, 0, Collect.asArray(ent.getLocation()), 0, 0);
                    }
                }
            }, 1, 1);
        }

    }
}
