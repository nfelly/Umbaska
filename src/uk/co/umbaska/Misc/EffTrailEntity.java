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
    private Expression<Number> d, d1, secd;



	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean,
						SkriptParser.ParseResult parse) {
		this.entity = (Expression<Entity>) exprs[0];
		this.particle = (Expression<ParticleEnum>) exprs[1];
        this.d = (Expression<Number>) exprs[2];
        this.d1 = (Expression<Number>) exprs[3];
        this.secd = (Expression<Number>) exprs[4];

		return true;
	}

	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "trail";
	}

	@Override

	protected void execute(Event event) {
		Entity[] entities = this.entity.getAll(event);
		ParticleEnum part = particle.getSingle(event);
        for (Entity e : entities){
            new TrailEntity(e, part, this.d.getSingle(event).intValue(), this.d1.getSingle(event).intValue(), this.secd.getSingle(event).intValue());
        }
	}

    @SuppressWarnings("unused")
    private class TrailEntity{
        BukkitTask runnable;
		Entity ent;
        ParticleEnum part;
        Integer dRun = 0;
        Integer dataRun = 0;
        Integer sdataRun = 0;
        private TrailEntity(final Entity ent, final ParticleEnum part, Integer speed, Integer data, final Integer secData){
            this.ent = ent;
            this.part = part;
            if (speed > -1){
                dRun = speed;
            }
            if (dataRun > -1){
                dataRun = data;
            }
            if (secData > -1){
                sdataRun = secData;
            }
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
                        ParticleFunction.spawnParticle(1, part, dRun, 0, 0, 0, Collect.asArray(ent.getLocation()), dataRun, sdataRun);
                    }
                }
            }, 1, 1);
        }

    }
}
