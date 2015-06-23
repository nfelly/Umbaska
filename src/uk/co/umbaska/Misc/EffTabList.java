package uk.co.umbaska.Misc;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R1.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import uk.co.umbaska.Main;
import uk.co.umbaska.Utils.TitleManager.TitleManager;

import java.lang.reflect.Field;

/**
 * Created by Zachary on 10/23/2014.
 */
@Deprecated
public class EffTabList extends Effect implements Listener {

	private Expression<Player> Players;
	private Expression<String> format;
	private boolean footer;


	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean,
						SkriptParser.ParseResult parse) {
		this.footer = parse.mark == 0;
		this.format = (Expression<String>) exprs[0];
		this.Players = (Expression<Player>) exprs[1];

		return true;
	}

	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "tablist header 1.8";
	}

	@Override

	protected void execute(Event event) {
		Player[] playerlist = Players.getAll(event);
		String format = this.format.toString(event, true);
        return;
//		for (Player p : playerlist) {
//			//Bukkit.broadcastMessage("Footer = " + type.toString());
//			if (this.footer == false) {
//				PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
//				IChatBaseComponent tabTitle = ChatSerializer.a(TitleManager.newheader.toString());
//				IChatBaseComponent tabFoot = ChatSerializer.a(TitleManager.newfooter.toString());
//				PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);
//				try
//				{
//					Field field = headerPacket.getClass().getDeclaredField("b");
//					field.setAccessible(true);
//					field.set(headerPacket, tabFoot);
//                } catch (Exception e)
//				{
//					e.printStackTrace();
//				}
//				finally
//				{
//					connection.sendPacket(headerPacket);
//				}
//				//Bukkit.broadcastMessage("Footer");
//			}
//			if (this.footer == true) {
//                TitleManager.newheader = ChatSerializer.a(format);
//				if (TitleManager.newfooter == null) {
//                    TitleManager.newfooter = ChatSerializer.a("Default");
//				}
//				PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
//				IChatBaseComponent tabTitle = ChatSerializer.a(TitleManager.newheader.toString());
//				IChatBaseComponent tabFoot = ChatSerializer.a(TitleManager.newfooter.toString());
//				PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);
//				try
//				{
//					Field field = headerPacket.getClass().getDeclaredField("b");
//					field.setAccessible(true);
//					field.set(headerPacket, tabFoot);
//				}
//				catch (Exception e)
//				{
//					e.printStackTrace();
//				}
//				finally
//				{
//					connection.sendPacket(headerPacket);
//				}
//				//Bukkit.broadcastMessage("Header");
//			}
//		}
	}
}

