package uk.co.umbaska.GattSk.Effects.SimpleScoreboards;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

/**
 * Created by Zachary on 6/13/2015.
 */
public class EffSetSlot extends Effect {
    private Expression<Number> slot;
    private Expression<String> scoreboardName, value;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2,
                        SkriptParser.ParseResult arg3) {

        this.scoreboardName = (Expression<String>) args[1];
        this.slot = (Expression<Number>) args[0];
        this.value = (Expression<String>) args[2];

        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "simple set slot";
    }

    @Override
    protected void execute(Event arg0) {

        String board = this.scoreboardName.getSingle(arg0);
        String txt = this.value.getSingle(arg0);
        if (board == null || txt == null){
            return;
        }
        SimpleScoreboard.setScore(board, slot.getSingle(arg0).intValue(), txt);
    }

}
