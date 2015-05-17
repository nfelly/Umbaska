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

package de.inventivegames.rpapi.packets;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.EnumProtocol;
import net.minecraft.server.v1_7_R4.NetworkManager;
import net.minecraft.server.v1_7_R4.Packet;
import net.minecraft.server.v1_7_R4.PacketDataSerializer;
import net.minecraft.server.v1_7_R4.PacketListener;
import net.minecraft.util.io.netty.channel.Channel;
import net.minecraft.util.io.netty.channel.ChannelDuplexHandler;
import net.minecraft.util.io.netty.channel.ChannelHandlerContext;
import net.minecraft.util.io.netty.channel.ChannelPromise;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import de.inventivegames.rpapi.IPacketPlayResourcePackStatus;
import de.inventivegames.rpapi.RPApiPlugin;
import de.inventivegames.rpapi.Status;

public class PacketPlayResourcePackStatus_v1_7_R4 extends Packet implements IPacketPlayResourcePackStatus {

	private Status	status;
	private Player	p;

	@Override
	public void a(PacketDataSerializer serializer) throws IOException {
		serializer.c(255);
		status = Status.byID(serializer.a());
		if (getStatus() != null && p != null) {
			RPApiPlugin.onResourcePackResult(getStatus(), p);
		}
	}

	@Override
	public void b(PacketDataSerializer serializer) throws IOException {
	}

	@Override
	public void handle(PacketListener paramPacketListener) {
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public void onPacketReceive(Object packet, final Player p) {
		if (!(packet instanceof Packet)) return;
		if (!packet.getClass().equals(getClass())) return;
		this.p = p;
		if (getStatus() != null && p != null) {
			RPApiPlugin.onResourcePackResult(getStatus(), p);
		}
	}

	@Override
	public void inject() throws NoSuchFieldException, IllegalAccessException {
		addPacket(EnumProtocol.PLAY, false, 25, this.getClass());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPacket(EnumProtocol protocol, boolean clientbound, int id, Class<? extends Packet> packet) throws NoSuchFieldException, IllegalAccessException {
		Field packets;
		if (!clientbound) {
			packets = EnumProtocol.class.getDeclaredField("h");
		} else {
			packets = EnumProtocol.class.getDeclaredField("i");
		}
		packets.setAccessible(true);
		net.minecraft.util.com.google.common.collect.BiMap<Integer, Class<? extends Packet>> pMap = (net.minecraft.util.com.google.common.collect.BiMap) packets.get(protocol);
		pMap.put(Integer.valueOf(id), packet);
		Field map = EnumProtocol.class.getDeclaredField("f");
		map.setAccessible(true);
		Map<Class<? extends Packet>, EnumProtocol> protocolMap = (Map) map.get(null);
		protocolMap.put(packet, protocol);
	}

	private static Field	channelField;

	@Override
	public void addChannelForPlayer(final Player p) {
		if (channelField == null) {
			try {
				channelField = NetworkManager.class.getDeclaredField("m");
			} catch (NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
			channelField.setAccessible(true);
		}
		try {
			EntityPlayer ep = ((CraftPlayer) p).getHandle();
			final Channel channel = (Channel) channelField.get(ep.playerConnection.networkManager);
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						channel.pipeline().addBefore("packet_handler", "RPApi", new ChannelHandler(p));
					} catch (Exception e) {
					}
				}
			}, "RPApi channel adder").start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeChannelForPlayer(Player p) {
		if (channelField == null) {
			try {
				channelField = NetworkManager.class.getDeclaredField("m");
			} catch (NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
			channelField.setAccessible(true);
		}
		try {
			EntityPlayer ep = ((CraftPlayer) p).getHandle();
			final Channel channel = (Channel) channelField.get(ep.playerConnection.networkManager);
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						channel.pipeline().remove("RPApi");
					} catch (Exception e) {
					}
				}
			}, "RPApi channel remover").start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class ChannelHandler extends ChannelDuplexHandler {

		private Player	p;

		public ChannelHandler(Player p) {
			this.p = p;
		}

		@Override
		public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
			super.write(ctx, msg, promise);
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			if (IPacketPlayResourcePackStatus.class.isAssignableFrom(msg.getClass())) {
				((IPacketPlayResourcePackStatus) msg).onPacketReceive(msg, p);
			}
			super.channelRead(ctx, msg);
		}

	}
}
