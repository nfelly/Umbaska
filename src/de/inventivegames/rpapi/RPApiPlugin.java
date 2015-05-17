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

package de.inventivegames.rpapi;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.MetricsLite;

public class RPApiPlugin extends JavaPlugin implements Listener {

	public static RPApiPlugin				instance;
	static IPacketPlayResourcePackStatus	packet;

	@Override
	public void onLoad() {
		Class<?> packet_class = null;
		try {
			packet_class = Class.forName("de.inventivegames.rpapi.packets.PacketPlayResourcePackStatus_" + getVersion());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (packet_class == null) {
			Bukkit.getLogger().severe("[RPApi] Can't find compatible Packet class! (Version: " + getVersion() + ")");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}

		try {
			packet = (IPacketPlayResourcePackStatus) packet_class.newInstance();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (packet == null) {
			Bukkit.getLogger().severe("[RPApi] Error while loading Packet!");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}

		try {
			packet.inject();
		} catch (Exception e) {
			e.printStackTrace();
			Bukkit.getLogger().severe("[RPApi] Error while injecting Packet into Classpath!");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}

	}

	@Override
	public void onEnable() {
		instance = this;

		Bukkit.getPluginManager().registerEvents(this, this);

		try {
			MetricsLite metrics = new MetricsLite(this);
			if (metrics.start()) {
				System.out.println("[RPApi] Metrics started.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		packet.addChannelForPlayer(e.getPlayer());
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			packet.removeChannelForPlayer(p);
		}
	}

	public static void onResourcePackResult(Status status, Player p) {
		Bukkit.getPluginManager().callEvent(new ResourcePackStatusEvent(status, p));
	}

	static String getVersion() {
		String name = Bukkit.getServer().getClass().getPackage().getName();
		String version = name.substring(name.lastIndexOf('.') + 1);
		return version;
	}
}
