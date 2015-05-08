package uk.nfell2009.umbaska.ProtocolLib.Disguises;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import uk.nfell2009.umbaska.Main;

/**
 * Created by Zachary on 5/6/2015.
 */
public class EffDisguiseName extends Effect {

    private Expression<String> type;
    private Expression<Player> player;
    private Expression<String> displayedName;

    @Override
    protected void execute(Event event){
        Player[] p = player.getAll(event);
        String e = type.getSingle(event);
        String name = displayedName.getSingle(event);
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
            myDisguise.setCustomName(name);
            Main.disguiseHolder.put(pl, myDisguise);
            for (Player player1 : Bukkit.getOnlinePlayers()){
                try {
                    if (player1 != pl) {
                        myDisguise.sendDisguise(player1);
                    }
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
        displayedName = (Expression<String>) expressions[2];
        return true;
    }
}
