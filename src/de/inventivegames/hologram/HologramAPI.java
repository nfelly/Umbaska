/*
 * Copyright 2015 Marvin Schäfer (inventivetalent). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and contributors and should not be interpreted as representing official policies,
 * either expressed or implied, of anybody else.
 */

package de.inventivegames.hologram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import de.inventivegames.hologram.reflection.AccessUtil;
import de.inventivegames.hologram.reflection.NMUClass;
import de.inventivegames.hologram.reflection.Reflection;

/**
 *
 * © Copyright 2015 inventivetalent
 *
 * @author inventivetalent
 *
 */
public abstract class HologramAPI {

	protected static boolean				is1_8			= false;
	protected static boolean				packetsEnabled	= false;

	protected static final List<Hologram>	holograms		= new ArrayList<>();

	/**
	 * Creates a new {@link Hologram}
	 *
	 * @param loc
	 *            {@link Location} to spawn the hologram at
	 * @param text
	 *            Initial text content of the hologram
	 * @return a new {@link Hologram}
	 */
	public static Hologram createHologram(Location loc, String text) {
		Hologram hologram = new DefaultHologram(loc, text);
		holograms.add(hologram);
		return hologram;
	}

	/**
	 * Removes a {@link Hologram}
	 *
	 * @param loc
	 *            {@link Location} of the hologram
	 * @param text
	 *            content of the hologram
	 * @return <code>true</code> if a hologram was found and has been removed, <code>false</code> otherwise
	 */
	public static boolean removeHologram(Location loc, String text) {
		Hologram toRemove = null;
		for (Hologram h : holograms) {
			if (h.getLocation().equals(loc) && h.getText().equals(text)) {
				toRemove = h;
				break;
			}
		}
		if (toRemove != null) return removeHologram(toRemove);
		return false;
	}

	/**
	 * Removes a {@link Hologram}
	 *
	 * @param hologram
	 *            {@link Hologram} to remove
	 * @return <code>true</code> if the hologram has been removed
	 */
	public static boolean removeHologram(@Nonnull Hologram hologram) {
		if (hologram.isSpawned()) {
			hologram.despawn();
		}
		return holograms.remove(hologram);
	}

	/**
	 * @return {@link Collection} of all registered {@link Hologram}s
	 */
	public static Collection<Hologram> getHolograms() {
		return new ArrayList<>(holograms);
	}

	protected static boolean spawn(@Nonnull final Hologram hologram, final Collection<? extends Player> receivers) throws Exception {
		if (hologram == null) throw new IllegalArgumentException("hologram cannot be null");
		if (receivers.isEmpty()) return false;

		((CraftHologram) hologram).sendSpawnPackets(receivers, true, true);
		((CraftHologram) hologram).sendTeleportPackets(receivers, true, true);
		((CraftHologram) hologram).sendNamePackets(receivers);
		return true;
	}

	protected static boolean despawn(@Nonnull Hologram hologram, Collection<? extends Player> receivers) throws Exception {
		if (hologram == null) throw new IllegalArgumentException("hologram cannot be null");
		if (receivers.isEmpty()) return false;

		((CraftHologram) hologram).sendDestroyPackets(receivers);
		return true;
	}

	protected static void sendPacket(Player p, Object packet) {
		if (p == null || packet == null) throw new IllegalArgumentException("player and packet cannot be null");
		try {
			Object handle = Reflection.getHandle(p);
			Object connection = Reflection.getField(handle.getClass(), "playerConnection").get(handle);
			Reflection.getMethod(connection.getClass(), "sendPacket", Reflection.getNMSClass("Packet")).invoke(connection, new Object[] { packet });
		} catch (Exception e) {
			System.err.println("Exception while sending " + packet + " to " + p);
			e.printStackTrace();
		}
	}

	protected static int getVersion(Player p) {
		try {
			final Object handle = Reflection.getHandle(p);
			Object connection = AccessUtil.setAccessible(handle.getClass().getDeclaredField("playerConnection")).get(handle);
			Object network = AccessUtil.setAccessible(connection.getClass().getDeclaredField("networkManager")).get(connection);
			String name = null;
			if (HologramAPI.is1_8) {
				if (Reflection.getVersion().contains("1_8_R1")) {
					name = "i";
				}
				if (Reflection.getVersion().contains("1_8_R2")) {
					name = "k";
				}
			} else {
				name = "m";
			}
			if (name == null) {
				System.err.println("Invalid server version! Unable to find proper channel-field in getVersion.");
				return 0;
			}
			Object channel = AccessUtil.setAccessible(network.getClass().getDeclaredField(name)).get(network);

			Object version = 0;
			try {
				version = AccessUtil.setAccessible(network.getClass().getDeclaredMethod("getVersion", NMUClass.io_netty_channel_Channel)).invoke(network, channel);
			} catch (Exception e) {
				// e.printStackTrace();
				return 182;
			}
			return (int) version;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static boolean is1_8() {
		return is1_8;
	}

	public static boolean packetsEnabled() {
		return packetsEnabled;
	}

	static {
		is1_8 = Reflection.getVersion().contains("1_8");
	}
}
