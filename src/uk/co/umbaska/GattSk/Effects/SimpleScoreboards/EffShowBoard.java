package uk.co.umbaska.GattSk.Effects.SimpleScoreboards;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

/**
 * Created by Zachary on 6/13/2015.
 */
public class EffShowBoard extends Effect {
    private Expression<Player> players;
    private Expression<String> scoreboardName;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2,
                        SkriptParser.ParseResult arg3) {

        this.scoreboardName = (Expression<String>) args[1];
        this.players = (Expression<Player>) args[0];

        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "simple show board";
    }

    @Override
    protected void execute(Event arg0) {

        String board = this.scoreboardName.getSingle(arg0);
        if (board == null){
            return;
        }
        for (Player p : this.players.getAll(arg0)) {
            SimpleScoreboard.showSimpleBoard(p, board);
        }
    }

}
