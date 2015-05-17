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

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import net.minecraft.server.v1_8_R2.EntityPlayer;
import net.minecraft.server.v1_8_R2.EnumProtocol;
import net.minecraft.server.v1_8_R2.EnumProtocolDirection;
import net.minecraft.server.v1_8_R2.NetworkManager;
import net.minecraft.server.v1_8_R2.Packet;
import net.minecraft.server.v1_8_R2.PacketDataSerializer;
import net.minecraft.server.v1_8_R2.PacketListener;
import net.minecraft.server.v1_8_R2.PacketPlayInResourcePackStatus.EnumResourcePackStatus;

import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.google.common.collect.BiMap;

import de.inventivegames.rpapi.IPacketPlayResourcePackStatus;
import de.inventivegames.rpapi.RPApiPlugin;
import de.inventivegames.rpapi.Status;

public class PacketPlayResourcePackStatus_v1_8_R2 implements Packet, IPacketPlayResourcePackStatus {

	private Status	status;
	private Player	p;

	@Override
	public void a(PacketDataSerializer serializer) throws IOException {
		serializer.c(40);
		int status = serializer.a(EnumResourcePackStatus.class).ordinal();
		this.status = Status.byID(status);
		if (getStatus() != null && p != null) {
			RPApiPlugin.onResourcePackResult(getStatus(), p);
		}
	}

	@Override
	public void b(PacketDataSerializer serializer) throws IOException {
	}

	@Override
	public void a(PacketListener paramPacketListener) {
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void addPacket(EnumProtocol protocol, boolean clientbound, int id, Class<? extends Packet> packet) throws NoSuchFieldException, IllegalAccessException {
		EnumProtocolDirection dir = clientbound ? EnumProtocolDirection.CLIENTBOUND : EnumProtocolDirection.SERVERBOUND;
		Field mapField = EnumProtocol.class.getDeclaredField("j");
		mapField.setAccessible(true);
		Map map = (Map) mapField.get(protocol);

		BiMap<Integer, Class> biMap = (BiMap<Integer, Class>) map.get(dir);
		biMap.put(Integer.valueOf(id), packet);
		map.put(dir, biMap);
	}

	private static Field	channelField;

	@Override
	public void addChannelForPlayer(final Player p) {
		if (channelField == null) {
			try {
				channelField = NetworkManager.class.getDeclaredField("k");
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
				channelField = NetworkManager.class.getDeclaredField("k");
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

	public class ChannelHandler extends ByteToMessageDecoder {

		private Player	p;

		public ChannelHandler(Player p) {
			this.p = p;
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			if (IPacketPlayResourcePackStatus.class.isAssignableFrom(msg.getClass())) {
				((IPacketPlayResourcePackStatus) msg).onPacketReceive(msg, p);
			}
			super.channelRead(ctx, msg);
		}

		@Override
		protected void decode(ChannelHandlerContext paramChannelHandlerContext, ByteBuf paramByteBuf, List<Object> paramList) throws Exception {
		}

	}

}
