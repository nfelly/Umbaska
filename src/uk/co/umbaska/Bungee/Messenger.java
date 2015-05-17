/*
 * Messneger.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/


package uk.co.umbaska.Bungee;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.*;

public class Messenger
        implements PluginMessageListener
{
    public static Plugin plugin;
    public static ByteArrayDataInput bytein;
    public static Integer playerCount;
    public static Integer playerCountGlobal;
    public static String playerListString;
    public static String plServer;
    public static String[] playerList;
    public static String[] serverList;

    public Messenger(Plugin j)
    {
        j.getServer().getMessenger().registerOutgoingPluginChannel(j, "BungeeCord");
        j.getServer().getMessenger().registerIncomingPluginChannel(j, "BungeeCord", this);
        //	Bukkit.getPluginManager().getPlugin("BukkitBungee").getPluginLoader().disablePlugin(Bukkit.getPluginManager().getPlugin("BukkitBungee"));
        plugin = j;
    }

    public void onPluginMessageReceived(String channel, Player player, byte[] message)
    {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        bytein = ByteStreams.newDataInput(message);
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
        try
        {
            String subChannel = in.readUTF();
            if (subChannel.equals("KickPlayer"))
            {
                short size = in.readShort();
                byte[] bytes = new byte[size];
                in.readFully(bytes);
                DataInputStream cmdMsg = new DataInputStream(new ByteArrayInputStream(bytes));
                String reason = cmdMsg.readUTF();
                String executor = cmdMsg.readUTF();
                Player executingPlayer = Bukkit.getPlayer(executor);
                if (executingPlayer == null) {
                    return;
                }
                executingPlayer.kickPlayer(colorString(reason));
            }
            if (subChannel.equals("Message"))
            {
                short size = in.readShort();
                byte[] bytes = new byte[size];
                in.readFully(bytes);
                DataInputStream cmdMsg = new DataInputStream(new ByteArrayInputStream(bytes));
                String msg2Send = cmdMsg.readUTF();
                String executor = cmdMsg.readUTF();
                Player executingPlayer = Bukkit.getPlayer(executor);
                if (executingPlayer == null) {
                    return;
                }
                executingPlayer.sendMessage(colorString(msg2Send));
            }
            if (subChannel.equals("GetServers")) {
                String _temp = in.readUTF();
                _temp = _temp.replace(" and ", " ");
                _temp = _temp.replace(", ", " ");
                _temp = _temp.replace(" ", ", ");
                serverList = _temp.split(", ");
            }
            if (subChannel.equals("PlayerList"))
            {
                playerListString = "";
                plServer = in.readUTF();
                String originalPlayerList = in.readUTF();
                playerList = originalPlayerList.split(", ");
            }
            if (subChannel.equals("PlayerCount")) {
                if (in.readUTF() == "ALL") {
                    playerCountGlobal = in.readInt();
                } else {
                    playerCount = in.readInt();
                }
            }
            if (subChannel.equals("ConsoleCommand"))
            {
                short size = in.readShort();
                byte[] bytes = new byte[size];
                in.readFully(bytes);
                DataInputStream cmdMsg = new DataInputStream(new ByteArrayInputStream(bytes));
                String command = cmdMsg.readUTF();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            }
            else if (subChannel.equals("PlayerCommand"))
            {
                short size = in.readShort();
                byte[] bytes = new byte[size];
                in.readFully(bytes);
                DataInputStream cmdMsg = new DataInputStream(new ByteArrayInputStream(bytes));
                String command = cmdMsg.readUTF();
                String executor = cmdMsg.readUTF();
                Player executingPlayer = Bukkit.getPlayer(executor);
                if (executingPlayer == null) {
                    return;
                }
                Bukkit.dispatchCommand(executingPlayer, command);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String colorString(String s)
    {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static Integer getServerCount(String server)
    {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(server);
        try
        {
            sendAnonymous(out.toByteArray());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return playerCount;
    }

    public static String[] getAllPlayersOnServer(String server)
    {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerList");
        out.writeUTF(server);
        try
        {
            sendAnonymous(out.toByteArray());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return playerList;
    }

    public static String[] getAllPlayers()
    {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerList");
        out.writeUTF("ALL");
        try
        {
            sendAnonymous(out.toByteArray());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return playerList;
    }

    public static String[] getAllServers()
    {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("GetServers");
        try
        {
            sendAnonymous(out.toByteArray());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return serverList;
    }

    public static void sendMsgToPlayer(String player, String message) {
        ByteArrayOutputStream msg = new ByteArrayOutputStream();
        //Bukkit.broadcastMessage("passed Msg");
        DataOutputStream out = new DataOutputStream(msg);
        //Bukkit.broadcastMessage("MSG:" + msg + "PLAYER:" + player + " OUT: " + out);
        try
        {
            //Bukkit.broadcastMessage("passed try");
            out.writeUTF("Message");
            //Bukkit.broadcastMessage("passed out 1");
            out.writeUTF(player);
            //Bukkit.broadcastMessage("passed out 2");
            out.writeUTF(message);
            //Bukkit.broadcastMessage("passed out 3");
            out.close();
            //Bukkit.broadcastMessage("passed close");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            //Bukkit.broadcastMessage("errrorrrrssss");
        }
        //Bukkit.broadcastMessage("passed stacktrace");
        sendAnonymous(msg.toByteArray());
        //Bukkit.broadcastMessage("finished");
    }

    public static void kickPlayer(String player, String message)
    {
        ByteArrayOutputStream msg = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(msg);
        try
        {
            out.writeUTF("KickPlayer");
            out.writeUTF(player);
            out.writeUTF(message);
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        sendAnonymous(msg.toByteArray());
        //Player p = Bukkit.getPlayer(player);
        //p.sendPluginMessage(plugin, "BungeeCord", msg.toByteArray());
    }

    @SuppressWarnings("deprecation")
    public static void sendAnonymous(byte[] message)
    {
        if (Bukkit.getOnlinePlayers().length < 1) {
            return;
        }
        Bukkit.getOnlinePlayers()[0].sendPluginMessage(plugin, "BungeeCord", message);
    }

    public static void sendTo(byte[] message, Player[] players)
    {
        for (Player player : players) {
            player.sendPluginMessage(plugin, "BungeeCord", message);
        }
    }
}
