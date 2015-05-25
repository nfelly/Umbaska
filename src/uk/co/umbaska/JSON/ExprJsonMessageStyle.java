package uk.co.umbaska.JSON;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.event.Event;
import uk.co.umbaska.GattSk.Extras.Collect;
import uk.co.umbaska.Utils.JSONMessage;

import java.util.ArrayList;

public class ExprJsonMessageStyle
        extends SimpleExpression<JSONMessage> {
    private Expression<JSONMessage> json;
    private Expression<ChatColor> append;

    protected JSONMessage[] get(Event event) {
        JSONMessage j = (JSONMessage) this.json.getSingle(event);
        if (j == null) {
            return null;
        }
        ArrayList<ChatColor> styles = new ArrayList();
        for (ChatColor a : (ChatColor[]) this.append.getAll(event)) {
            switch (a) {
                case BLACK:
                case DARK_BLUE:
                case DARK_GREEN:
                case DARK_AQUA:
                case DARK_RED:
                case DARK_PURPLE:
                case GOLD:
                case GRAY:
                case DARK_GRAY:
                case BLUE:
                case GREEN:
                case AQUA:
                case RED:
                case LIGHT_PURPLE:
                case YELLOW:
                case WHITE:
                case RESET:
                    j.color(a);
                    break;
                case MAGIC:
                case BOLD:
                case STRIKETHROUGH:
                case UNDERLINE:
                case ITALIC:
                    styles.add(a);
            }
        }
        j.style((ChatColor[]) styles.toArray(new ChatColor[styles.size()]));
        return (JSONMessage[]) Collect.asArray(new JSONMessage[]{j});
    }

    public boolean isSingle() {
        return true;
    }

    public Class<? extends JSONMessage> getReturnType() {
        return JSONMessage.class;
    }

    public String toString(Event event, boolean b) {
        return ((JSONMessage) this.json.getSingle(event)).toOldMessageFormat();
    }

    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.json = expressions[0];
        this.append = expressions[1];
        return true;
    }
}