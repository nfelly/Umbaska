package uk.nfell2009.umbaska.ProtocolLib.Disguises;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import uk.nfell2009.umbaska.Main;

/**
 * Created by Zachary on 5/6/2015.
 */
public class EffDisguise  extends Effect {

    private Expression<String> type;
    private Expression<Player> player;

    @Override
    protected void execute(Event event){
        Player[] p = player.getAll(event);
        String e = type.getSingle(event);
        if (p == null) {
            return;
        }
        EntityDisguise disguise = null;
        for (EntityDisguise dis : EntityDisguise.values()){
            if (dis.toString() == e){
                disguise = dis;
            }
        }
        if (disguise == null){
            Skript.error(e + " isn't a valid disguise type!");
        }
        for (Player pl : p) {
            MyDisguise myDisguise = new MyDisguise(pl, disguise);
        }
    }


    @Override
    public String toString(Event event, boolean b){
        return "Hide Entity";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        type = (Expression<String>) expressions[0];
        player = (Expression<Player>) expressions[1];
        return true;
    }
}
