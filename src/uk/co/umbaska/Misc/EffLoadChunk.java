/*
 * EffNewFile.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Misc;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.Event;

@SuppressWarnings("unused")
public class EffLoadChunk extends Effect {

    private Expression<Location> location;
    private Expression<Chunk> chunk;

    @Override
    protected void execute(Event event) {
        if (this.location.getSingle(event) != null){
            this.location.getSingle(event).getChunk().load(false);
        }
        else if (this.chunk.getSingle(event) != null){
            this.chunk.getSingle(event).load(false);
        }

    }


    @Override
    public String toString(Event event, boolean b){
        return "load chunk";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        chunk = (Expression<Chunk>)expressions[0];
        location = (Expression<Location>)expressions[0];
        return true;
    }
}