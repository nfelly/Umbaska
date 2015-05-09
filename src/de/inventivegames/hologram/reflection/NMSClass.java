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

package de.inventivegames.hologram.reflection;

import java.lang.reflect.Field;

/**
 *
 * © Copyright 2015 inventivetalent
 *
 * @author inventivetalent
 *
 */
public abstract class NMSClass {

	private static boolean	initialized	= false;

	public static Class<?>	Entity;
	public static Class<?>	EntityLiving;
	public static Class<?>	EntityInsentient;
	public static Class<?>	EntityAgeable;
	public static Class<?>	EntityHorse;
	public static Class<?>	EntityArmorStand;
	public static Class<?>	EntityWitherSkull;
	public static Class<?>	EntitySlime;
	public static Class<?>	World;
	public static Class<?>	PacketPlayOutSpawnEntityLiving;
	public static Class<?>	PacketPlayOutSpawnEntity;
	public static Class<?>	PacketPlayOutEntityDestroy;
	public static Class<?>	PacketPlayOutAttachEntity;
	public static Class<?>	PacketPlayOutEntityTeleport;
	public static Class<?>	PacketPlayOutEntityMetadata;
	public static Class<?>	DataWatcher;
	public static Class<?>	WatchableObject;
	public static Class<?>	ItemStack;
	public static Class<?>	ChunkCoordinates;
	public static Class<?>	BlockPosition;
	public static Class<?>	Vector3f;
	public static Class<?>	EnumEntityUseAction;

	static {
		if (!initialized) {
			for (Field f : NMSClass.class.getDeclaredFields()) {
				if (f.getType().equals(Class.class)) {
					try {
						f.set(null, Reflection.getNMSClassWithException(f.getName()));
					} catch (Exception e) {
						if (f.getName().equals("WatchableObject")) {
							try {
								f.set(null, Reflection.getNMSClassWithException("DataWatcher$WatchableObject"));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
			initialized = true;
		}
	}

}
