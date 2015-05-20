package uk.co.umbaska.Utils.Disguise;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import uk.co.umbaska.Utils.ReflectionUtils;


/**
 * @author fillpant, Blame him!
 *
 */
public class MyDisguise {
	private static final String bukkitversion = Bukkit.getServer().getClass()
			.getPackage().getName().substring(23);
	private String customName;
	private EntityDisguise type;
	private Player disguised;
	private ItemStack hand, helm, chst, leg, boot;

	/**
	 * @param p
	 *            player to disguise
	 * @param type
	 *            Entity type of disguise
	 */
	public MyDisguise(Player p, EntityDisguise type) {
		this(p, type, null);
	}

	/**
	 * @param p
	 *            player to disguise
	 * @param type
	 *            Entity type of disguise
	 * @param name
	 *            the display name of the disguised player (chat color is
	 *            supported)
	 */
	public MyDisguise(Player p, EntityDisguise type, String name) {
		this(p, type, name, null, null, null, null, null);
	}

	/**
	 * @param p
	 *            player to disguise
	 * @param type
	 *            Entity type of disguise
	 * @param name
	 *            the display name of the disguised player (chat color is
	 *            supported)
	 * @param inhand
	 *            Item in hand
	 * @param helmet
	 *            helmet item
	 * @param chestplate
	 *            chestplate armor item
	 * @param leggings
	 *            leggings armor item
	 * @param boots
	 *            boots armor item <b>If You dont want a armor item like boots
	 *            or something, provide 'null'</b>
	 */
	public MyDisguise(Player p, EntityDisguise type, String name,
					  ItemStack inhand, ItemStack helmet, ItemStack chestplate,
					  ItemStack leggings, ItemStack boots) {
		this.customName = name;
		this.type = type;
		this.disguised = p;
		this.hand = inhand;
		this.helm = helmet;
		this.chst = chestplate;
		this.leg = leggings;
		this.boot = boots;
	}

	/**
	 * @param to
	 *            Player that will see the disguise (where the packets will be
	 *            sent to.)
	 * @throws Exception
	 *             Many exceptions can occur due to reflection used.
	 */
	public void sendDisguise(Player to) throws Exception {
		if (to.equals(disguised))
			throw new IllegalArgumentException(
					"Target Player cannot be the same as the disguised player");
		Object packetplayoutentitydestroy = ReflectionUtils.instantiateObject(
                "PacketPlayOutEntityDestroy", ReflectionUtils.PackageType.MINECRAFT_SERVER,
                new int[]{disguised.getEntityId()});
		Object world = (Object) ReflectionUtils.invokeMethod(disguised.getWorld(),
				"getHandle", null);
		Class<?> entity = Class.forName(type.getClassName());
		Object ent = ReflectionUtils.instantiateObject(entity, world);
		ReflectionUtils.invokeMethod(ent, "setPosition", disguised
						.getLocation().getX(), disguised.getLocation().getY(),
				disguised.getLocation().getZ());
		ReflectionUtils.getMethod(entity, "d", int.class).invoke(ent,
				disguised.getEntityId());
		if (customName != null) {
			ReflectionUtils.getMethod(entity, "setCustomName", String.class)
					.invoke(ent, customName);
			ReflectionUtils.getMethod(entity, "setCustomNameVisible",
					boolean.class).invoke(ent, true);
		}
		handleSpecialTypes(type, ent);
		Object packetplayoutspawnentityliving = ReflectionUtils
				.instantiateObject("PacketPlayOutSpawnEntityLiving",
						ReflectionUtils.PackageType.MINECRAFT_SERVER, ent);

		sendPacket(to, packetplayoutentitydestroy);
		sendPacket(to, packetplayoutspawnentityliving);
		if (hand != null)
			sendArmorContentPackets(to, disguised.getEntityId(), 0, hand);
		if (helm != null)
			sendArmorContentPackets(to, disguised.getEntityId(), 1, helm);
		if (chst != null)
			sendArmorContentPackets(to, disguised.getEntityId(), 2, chst);
		if (leg != null)
			sendArmorContentPackets(to, disguised.getEntityId(), 3, leg);
		if (boot != null)
			sendArmorContentPackets(to, disguised.getEntityId(), 4, boot);

	}

	/**
	 * @param players
	 *            players that will see the disguise happening. The rest will
	 *            see the disguised player as player...
	 */
	private void sendDisguise(Player... players) {
		for (Player P : players) {
			if (P.equals(disguised))
				continue;
			try {
				sendDisguise(P);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param forwho
	 *            who will see this update?
	 * @throws Exception
	 *             Reflection exceptions
	 */
	public void updateDisguise(Player forwho) throws Exception {
		sendDisguise(forwho);
	}

	/**
	 * @param players
	 *            a array of players that will see the update
	 */
	public void updateDisguise(Player... players) {
		sendDisguise(players);
	}


	@SuppressWarnings("deprecation")
	public void removeDisguise() throws ReflectiveOperationException {
		Object ppoed = ReflectionUtils.instantiateObject(
				"PacketPlayOutEntityDestroy", ReflectionUtils.PackageType.MINECRAFT_SERVER,
				new int[] { disguised.getEntityId() });
		Object ppones = ReflectionUtils.instantiateObject(
				"PacketPlayOutNamedEntitySpawn", ReflectionUtils.PackageType.MINECRAFT_SERVER,
				ReflectionUtils.invokeMethod(disguised, "getHandle", null));
		for (Player p : Bukkit.getOnlinePlayers()) {
			if(p.equals(disguised)) continue;
			try {
				sendPacket(p, ppoed);
				sendPacket(p, ppones);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param type
	 *            the new Disguise type
	 * @param sendto
	 *            the player who will see the change
	 * @throws Exception
	 *             Reflection exceptions
	 */
	public void changePlayerDisguise(EntityDisguise type, Player sendto)
			throws Exception {
		this.type = type;
		sendDisguise(sendto);
	}

	/*
	 * @param type the new Disguise type
	 *
	 * @param sendto the player who will see the change
	 *
	 * @throws Exception Reflection exceptions
	 */
	public void changePlayerDisguise(EntityDisguise type, Player... sendto)
			throws Exception {
		this.type = type;
		sendDisguise(sendto);
	}

	//Dont mind this
	private void sendPacket(Player p, Object pack) throws Exception {
		Class<?> packet = Class.forName("net.minecraft.server." + bukkitversion
				+ ".Packet");
		Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit."
				+ bukkitversion + ".entity.CraftPlayer");
		Object handle = craftPlayer.getMethod("getHandle").invoke(p);
		Object con = handle.getClass().getField("playerConnection").get(handle);
		con.getClass().getMethod("sendPacket", packet).invoke(con, pack);
	}
	//Dont mind this too.
	private void sendArmorContentPackets(Player to, int entityID, int slot,
										 ItemStack item) throws Exception {
		ReflectionUtils.PackageType type;
		if (bukkitversion.startsWith("v1_7_"))
			type = ReflectionUtils.PackageType.CRAFTBUKKIT;
		else
			type = ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY;
		Object craftitmstk = ReflectionUtils.getMethod("CraftItemStack", type,
				"asNMSCopy", item.getClass()).invoke(null, item);
		Object metadarapacket = ReflectionUtils.instantiateObject(
				"PacketPlayOutEntityEquipment", ReflectionUtils.PackageType.MINECRAFT_SERVER,
				entityID, slot, craftitmstk);
		sendPacket(to, metadarapacket);
	}

	//Forget this as well :3
	private Object handleSpecialTypes(EntityDisguise type, Object entity)
			throws Exception {
		switch (type) {
			case WITHER_SKELETON:
				ReflectionUtils.invokeMethod(entity, "setSkeletonType", 1);
				break;
		}
		return entity;
	}
	/*To be documented,*/
	public ItemStack getItemInHand() {
		return hand;
	}

	public void setItemInHand(ItemStack hand) {
		this.hand = hand;
	}

	public ItemStack getHelmet() {
		return helm;
	}

	public void setHelmet(ItemStack helm) {
		this.helm = helm;
	}

	public ItemStack getChestplate() {
		return chst;
	}

	public void setChestplate(ItemStack chst) {
		this.chst = chst;
	}

	public ItemStack getLeggings() {
		return leg;
	}

	public void setLeggings(ItemStack leg) {
		this.leg = leg;
	}

	public ItemStack getBoots() {
		return boot;
	}

	public void setBoots(ItemStack boot) {
		this.boot = boot;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public EntityDisguise getType() {
		return type;
	}

	public void setType(EntityDisguise type) {
		this.type = type;
	}

	public Player getDisguised() {
		return disguised;
	}
}

/**
 *@author fillpant, Blame him!
 */

