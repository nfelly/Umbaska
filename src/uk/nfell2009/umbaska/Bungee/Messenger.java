/*
 * Messneger.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/


package uk.nfell2009.umbaska.Bungee;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Messenger
  implements PluginMessageListener
{
  private static JavaPlugin plugin;
  
  public Messenger(JavaPlugin j)
  {
    j.getServer().getMessenger().registerOutgoingPluginChannel(j, "BungeeCord");
    j.getServer().getMessenger().registerIncomingPluginChannel(j, "BungeeCord", this);
    plugin = j;
  }
  
  public void onPluginMessageReceived(String channel, Player player, byte[] message)
  {
    if (!channel.equals("BungeeCord")) {
      return;
    }
    DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
    try
    {
      String subChannel = in.readUTF();
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
    
  @SuppressWarnings("deprecation")
public static void sendAnonymous(byte[] message)
  {
    if (Bukkit.getOnlinePlayers().length < 1) {
      return;
    }
    Bukkit.getOnlinePlayers()[0].sendPluginMessage(plugin, "BungeeCord", message);
  }
  
  public static void sendTo(byte[] message, Player... players)
  {
    for (Player player : players) {
      player.sendPluginMessage(plugin, "BungeeCord", message);
    }
  }
}
