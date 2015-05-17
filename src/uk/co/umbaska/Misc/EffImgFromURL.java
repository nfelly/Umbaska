/*
 * EffImgFromURL.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Misc;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import uk.nfell2009.umbaskapi.ImageManager.*;


public class EffImgFromURL extends Effect implements Listener {

    private Expression<Player> eplayer;
    private Expression<String> epath;
    private Expression<String> eline1;
    private Expression<String> eline2;
    private Expression<String> eline3;

    @Override
    protected void execute(Event event){
        Player player = eplayer.getSingle(event);
        String path = epath.getSingle(event);
        String line1 = eline1.getSingle(event);
        String line2 = eline2.getSingle(event);
        String line3 = eline3.getSingle(event);
        ImgInChat.ShowImgFromURL(player, path, line1, line2, line3);
    }


    @Override
    public String toString(Event event, boolean b){
        return "Move a plot to another plot";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        eplayer = (Expression<Player>) expressions[0];
        epath = (Expression<String>) expressions[1];
        eline1 = (Expression<String>) expressions[2];
        eline2 = (Expression<String>) expressions[3];
        eline3 = (Expression<String>) expressions[4];
        return true;
    }
}