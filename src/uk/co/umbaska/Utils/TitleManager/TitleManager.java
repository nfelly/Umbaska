package uk.co.umbaska.Utils.TitleManager;

import net.minecraft.server.v1_8_R1.*;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class TitleManager implements Listener {

	public static Class<?>	nmsChatSerializer	= Reflection.getNMSClass("ChatSerializer");
	public static int VERSION= 47;

	public static void sendTitle(Player p, String title) {
		try {
			PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
			title = ChatColor.translateAlternateColorCodes('&', title);
			IChatBaseComponent titleSub = ChatSerializer.a("{\"text\": \"" + title + "\"}");
			PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleSub);
			connection.sendPacket(packetPlayOutSubTitle);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendActionTitle(Player p, String action){
		try {
			PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
			action = ChatColor.translateAlternateColorCodes('&', action);
			IChatBaseComponent titleSub = ChatSerializer.a("{\"text\": \"" + action + "\"}");
			Byte slot = 2;
			PacketPlayOutChat packetPlayOutSubTitle = new PacketPlayOutChat(titleSub, slot);
			connection.sendPacket(packetPlayOutSubTitle);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	public static void sendSubTitle(Player p, String subtitle) {
		try {
			PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
			subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
			IChatBaseComponent titleSub = ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
			PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, titleSub);
			connection.sendPacket(packetPlayOutSubTitle);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
