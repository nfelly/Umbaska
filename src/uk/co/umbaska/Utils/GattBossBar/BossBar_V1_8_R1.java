package uk.co.umbaska.Utils.GattBossBar;

import net.minecraft.server.v1_8_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import uk.co.umbaska.Enums.BarType;
import uk.co.umbaska.Main;

import java.lang.reflect.Field;
import java.util.HashMap;

public class BossBar_V1_8_R1 {
	public final int ENTITY_ID = 1234;

	private HashMap<String, Boolean> hasHealthBar = new HashMap<String, Boolean>();
	private HashMap<String, String> healthBarText = new HashMap<String, String>();
	private HashMap<String, BukkitTask> taskTracker = new HashMap<String, BukkitTask>();

	public void sendPacket(Player player, Packet packet) {
		EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();

		entityPlayer.playerConnection.sendPacket(packet);
	}

	public Field getField(Class<?> cl, String field_name) {
		try {
			Field field = cl.getDeclaredField(field_name);
			return field;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Accessing packets
	@SuppressWarnings("deprecation")
	public PacketPlayOutSpawnEntityLiving getMobPacket(String text,
													   Location loc) {
		PacketPlayOutSpawnEntityLiving mobPacket = new PacketPlayOutSpawnEntityLiving();

		try {
			Field a = getField(mobPacket.getClass(), "a");
			a.setAccessible(true);
			a.set(mobPacket, (int) ENTITY_ID);

			Field b = getField(mobPacket.getClass(), "b");
			b.setAccessible(true);
			b.set(mobPacket, (byte) EntityType.WITHER.getTypeId());

			Field c = getField(mobPacket.getClass(), "c");
			c.setAccessible(true);
			c.set(mobPacket, (int) Math.floor(loc.getBlockX() * 32.0D));

			Field d = getField(mobPacket.getClass(), "d");
			d.setAccessible(true);
			d.set(mobPacket, (int) Math.floor(loc.getBlockY() * 32.0D));

			Field e = getField(mobPacket.getClass(), "e");
			e.setAccessible(true);
			e.set(mobPacket, (int) Math.floor(loc.getBlockZ() * 32.0D));

			Field f = getField(mobPacket.getClass(), "f");
			f.setAccessible(true);
			f.set(mobPacket, (byte) 0);

			Field g = getField(mobPacket.getClass(), "g");
			g.setAccessible(true);
			g.set(mobPacket, (byte) 0);

			Field h = getField(mobPacket.getClass(), "h");
			h.setAccessible(true);
			h.set(mobPacket, (byte) 0);

			Field i = getField(mobPacket.getClass(), "i");
			i.setAccessible(true);
			i.set(mobPacket, (byte) 0);

			Field j = getField(mobPacket.getClass(), "j");
			j.setAccessible(true);
			j.set(mobPacket, (byte) 0);

			Field k = getField(mobPacket.getClass(), "k");
			k.setAccessible(true);
			k.set(mobPacket, (byte) 0);

		} catch (IllegalArgumentException e1) {
// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		DataWatcher watcher = getWatcher(text, 300);

		try {
			Field t = PacketPlayOutSpawnEntityLiving.class
					.getDeclaredField("l");
			t.setAccessible(true);
			t.set(mobPacket, watcher);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return mobPacket;
	}

	public PacketPlayOutEntityDestroy getDestroyEntityPacket() {
		PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy();

		Field a = getField(packet.getClass(), "a");
		a.setAccessible(true);
		try {
			a.set(packet, new int[]{ENTITY_ID});
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return packet;
	}

	public PacketPlayOutEntityMetadata getMetadataPacket(
			DataWatcher watcher) {
		PacketPlayOutEntityMetadata metaPacket = new PacketPlayOutEntityMetadata();

		Field a = getField(metaPacket.getClass(), "a");
		a.setAccessible(true);
		try {
			a.set(metaPacket, (int) ENTITY_ID);
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}

		try {
			Field b = PacketPlayOutEntityMetadata.class.getDeclaredField("b");
			b.setAccessible(true);
			b.set(metaPacket, watcher.c());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return metaPacket;
	}

	public PacketPlayInClientCommand getRespawnPacket() {
		PacketPlayInClientCommand packet = new PacketPlayInClientCommand();

		Field a = getField(packet.getClass(), "a");
		a.setAccessible(true);
		try {
			a.set(packet, (int) 1);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return packet;
	}

	public DataWatcher getWatcher(String text, int health) {
		DataWatcher watcher = new DataWatcher(null);

		watcher.a(0, (Byte) (byte) 0x20); // Flags, 0x20 = invisible
		watcher.a(6, (Float) (float) health);
		watcher.a(10, (String) text); // Entity name
		watcher.a(11, (Byte) (byte) 1); // Show name, 1 = show, 0 = don't show
// watcher.a(16, (Integer) (int) health); //Wither health, 300 = full
// health

		return watcher;
	}
	// Other methods

	public class BarTracker{

		private Player p;
		private BukkitTask task;
		private int healthup;
		private long delay;
		private String text;

		public BarTracker(Player p, BarType type, String text){

			if (!taskTracker.containsKey(p.getName())){
				taskTracker.put(p.getName(), null);
			}

			this.p = p;
			this.text = text;
			if (type == BarType.NORMAL || type == BarType.REGULAR){
				displayTextBar(this.text, this.p);
			}
			else if (type == BarType.LOAD_UP){
				displayLoadingBar(this.text, this.p, 20, true);
			}
			else if (type == BarType.TIME_DOWN){
				displayLoadingBar(this.text, this.p, 20, false);
			}
		}

		public BarTracker(Player p, BarType type, String text, int secondsDelay){
			this.p = p;
			this.text = text;
			if (!taskTracker.containsKey(p.getName())){
				taskTracker.put(p.getName(), null);
			}
			healthBarText.put(p.getName(), text);
			if (type == BarType.NORMAL || type == BarType.REGULAR){
				displayTextBar(this.text, this.p);
			}else if (type == BarType.LOAD_UP){
				displayLoadingBar(this.text, this.p, secondsDelay, true);
			}
			else if (type == BarType.TIME_DOWN){
				displayLoadingBar(this.text, this.p, secondsDelay, false);
			}
		}

		public void displayTextBar(String text, final Player player) {
			PacketPlayOutSpawnEntityLiving mobPacket = getMobPacket(text,
					player.getLocation());
			if (taskTracker.containsKey(p.getName())){
				if (taskTracker.get(p.getName()) != null){
					taskTracker.get(p.getName()).cancel();
				}
			}
			sendPacket(player, mobPacket);
			hasHealthBar.put(player.getName(), true);
            final String text1 = this.text;

			taskTracker.put(p.getName(), new BukkitRunnable() {

				public void run() {
					PacketPlayOutEntityDestroy destroyEntityPacket = getDestroyEntityPacket();

					sendPacket(player, destroyEntityPacket);
					if (!healthBarText.get(player.getName()).equals(text1)){
						destroyEntityPacket = getDestroyEntityPacket();
						sendPacket(player, destroyEntityPacket);
						task.cancel();
						return;
					}
					if (!hasHealthBar.get(player.getName())){
						destroyEntityPacket = getDestroyEntityPacket();
						sendPacket(player, destroyEntityPacket);
						task.cancel();
						return;
					}
					hasHealthBar.put(player.getName(), false);
				}
			}.runTaskTimer(Main.getInstance(), 120L, 120L));
		}

		public void displayLoadingBar(final String completeText, final Player player,
									  final int secondsDelay, final boolean loadUp) {
			final int healthChangePerSecond = 300 / secondsDelay;

			displayLoadingBar(completeText, player, healthChangePerSecond,
					20L, loadUp);
		}

		public void displayLoadingBar(final String completeText, final Player player,
									  final int healthAdd, final long delay, final boolean loadUp) {
			PacketPlayOutSpawnEntityLiving mobPacket = getMobPacket(text,
					player.getLocation());
			if (taskTracker.containsKey(p.getName())){
				if (taskTracker.get(p.getName()) != null){
					taskTracker.get(p.getName()).cancel();
				}
			}
			sendPacket(player, mobPacket);
			hasHealthBar.put(player.getName(), true);

			taskTracker.put(p.getName(), new BukkitRunnable() {
				int health = (loadUp ? 0 : 300);

				public void run() {
					if ((loadUp ? health < 300 : health > 0)) {
						DataWatcher watcher = getWatcher(text, health);
						PacketPlayOutEntityMetadata metaPacket = getMetadataPacket(watcher);

						sendPacket(player, metaPacket);

						if (loadUp) {
							health += healthAdd;
						} else {
							health -= healthAdd;
						}
					} else {
						DataWatcher watcher = getWatcher(text, (loadUp ? 300 : 0));
						PacketPlayOutEntityMetadata metaPacket = getMetadataPacket(watcher);
						PacketPlayOutEntityDestroy destroyEntityPacket = getDestroyEntityPacket();

						sendPacket(player, metaPacket);
						sendPacket(player, destroyEntityPacket);
						hasHealthBar.put(player.getName(), false);

// Complete text
						PacketPlayOutSpawnEntityLiving mobPacket = getMobPacket(
								completeText, player.getLocation());

						sendPacket(player, mobPacket);
						hasHealthBar.put(player.getName(), true);

						DataWatcher watcher2 = getWatcher(completeText, 300);
						PacketPlayOutEntityMetadata metaPacket2 = getMetadataPacket(watcher2);

						sendPacket(player, metaPacket2);
						if (!healthBarText.get(player.getName()).equals(text)){
							destroyEntityPacket = getDestroyEntityPacket();
							sendPacket(player, destroyEntityPacket);
							task.cancel();
							return;
						}
						if (!hasHealthBar.get(player.getName())){
							destroyEntityPacket = getDestroyEntityPacket();
							sendPacket(player, destroyEntityPacket);
							task.cancel();
							return;
						}

						new BukkitRunnable() {

							public void run() {
								PacketPlayOutEntityDestroy destroyEntityPacket = getDestroyEntityPacket();

								sendPacket(player, destroyEntityPacket);
								hasHealthBar.put(player.getName(), false);
							}
						}.runTaskLater(Main.getInstance(), 40L);

						this.cancel();
					}
				}
			}.runTaskTimer(Main.getInstance(), delay, delay));
		}

	}

}