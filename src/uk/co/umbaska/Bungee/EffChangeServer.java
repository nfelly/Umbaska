package uk.co.umbaska.Bungee;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffChangeServer extends Effect {
    private Expression<Player> player;
    private Expression<String> server;

    protected void execute(Event event)
    {
        String s = (String)this.server.getSingle(event);
        try
        {
            for (Player p : (Player[])this.player.getAll(event))
            {
                ByteArrayOutputStream msg = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(msg);
                out.writeUTF("Connect");
                out.writeUTF(s);
                out.close();
                ProxiedPlayer pp = (ProxiedPlayer) p;
                pp.getUniqueId();
                Messenger.sendTo(msg.toByteArray(), new Player[] { p });
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public String toString(Event event, boolean b){
        return "Change server";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        player = (Expression<Player>) expressions[0];
        server = (Expression<String>) expressions[1];
        return true;
    }
}
