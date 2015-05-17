/*
 * EffPlayTrack.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Sound;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.Song;
import com.xxmicloxx.NoteBlockAPI.SongPlayer;


import java.io.File;

public class EffPlayTrack extends Effect {

    private Expression<String> trk;
    private Expression<Player> ply;

    @Override
    protected void execute(Event event){
        Player p = ply.getSingle(event);
        String t = trk.getSingle(event);
        if (p == null) {
            return;
        } else if (t == null) {
            return;
        }
        Song s = NBSDecoder.parse(new File(t));
        SongPlayer sp = new RadioSongPlayer(s);
        sp.setAutoDestroy(true);
        sp.addPlayer(p);
        sp.setPlaying(true);
    }


    @Override
    public String toString(Event event, boolean b){
        return "Playing song";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        trk = (Expression<String>) expressions[0];
        ply = (Expression<Player>) expressions[1];
        return true;
    }
}
