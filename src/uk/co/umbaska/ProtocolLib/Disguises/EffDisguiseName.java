package uk.co.umbaska.ProtocolLib.Disguises;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import uk.co.umbaska.Main;
import uk.co.umbaska.Utils.Disguise.EntityDisguise;
import uk.co.umbaska.Utils.Disguise.MyDisguise;

/**
 * Created by Zachary on 5/6/2015.
 */
public class EffDisguiseName extends Effect {


    private Expression<String> name;
    private Expression<String> type;
    private Expression<Player> player;

    @SuppressWarnings("deprecation")
	@Override
    protected void execute(Event event){
        Player[] p = player.getAll(event);
        String e = type.getSingle(event);
        if (p == null) {
            return;
        }
        EntityDisguise disguise = EntityDisguise.UNKNOWN;
        for (EntityDisguise dis : EntityDisguise.values()){
            if (dis.toString().equalsIgnoreCase(e)){
                disguise = dis;
            }
        }
        if (disguise == EntityDisguise.UNKNOWN){
            Skript.error(e + " isn't a valid disguise type!");
            return;
        }
        for (Player pl : p) {
            MyDisguise myDisguise = new MyDisguise(pl, disguise);
            myDisguise.setCustomName(name.getSingle(event));
            Main.disguiseHandler.setDisguise(pl, myDisguise);
            for (Player player1 : Bukkit.getOnlinePlayers()){
                try {
                    myDisguise.sendDisguise(player1);
                }catch (Exception xe){
                    xe.printStackTrace();
                }
            }

        }
    }


    @Override
    public String toString(Event event, boolean b){
        return "Hide Entity";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        type = (Expression<String>) expressions[1];
        player = (Expression<Player>) expressions[0];
        name = (Expression<String>) expressions[2];
        return true;
    }
}
