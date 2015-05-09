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

import java.util.Collection;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Location;

import de.inventivegames.hologram.touch.TouchHandler;
import de.inventivegames.hologram.view.ViewHandler;

/**
 *
 * © Copyright 2015 inventivetalent
 *
 * @author inventivetalent
 *
 */
public abstract interface Hologram {

	/**
	 * @return <code>true</code> if the hologram is spawned
	 */
	public boolean isSpawned();

	/**
	 * Spawns the hologram and despawns it after the specified timeout
	 *
	 * @param ticks
	 *            timeout
	 */
	public void spawn(@Nonnull @Nonnegative long ticks);

	/**
	 * Spawns the hologram
	 */
	public void spawn();

	/**
	 * Despawns the hologram
	 */
	public void despawn();

	/**
	 * @param text
	 *            New text content of the hologram
	 */
	public void setText(@Nullable String text);

	/**
	 * @return The text content of the hologram
	 */
	public String getText();

	/**
	 * Updates the content of the hologram
	 */
	public void update();

	/**
	 * Automatically updates the content of the hologram <br/>
	 * <code>-1</code> as the interval argument will stop the update
	 *
	 * @param interval
	 *            Update interval in ticks, <code>-1</code> to stop updating
	 */
	public void update(long interval);

	/**
	 * @param loc
	 *            changes the {@link Location} of the hologram
	 *
	 * @see Hologram#move(Location)
	 */
	public void setLocation(@Nonnull Location loc);

	/**
	 * @return The {@link Location} of the hologram
	 */
	public Location getLocation();

	/**
	 * Moves the hologram
	 *
	 * @param loc
	 *            new {@link Location} of the hologram
	 */
	public void move(@Nonnull Location loc);

	/**
	 * Changes the touchability
	 *
	 * @param flag
	 *            <code>true</code> if the hologram should be touchable,
	 */
	public void setTouchable(boolean flag);

	/**
	 * @return <code>true</code> if the hologram is touchable
	 */
	public boolean isTouchable();

	/**
	 * Adds a touch handler to the hologram
	 *
	 * @param handler
	 *            {@link TouchHandler} instance
	 */
	public void addTouchHandler(@Nonnull TouchHandler handler);

	/**
	 * Removes a touch handler from the hologram
	 *
	 * @param handler
	 *            {@link TouchHandler} instance
	 */
	public void removeTouchHandler(@Nonnull TouchHandler handler);

	/**
	 * @return a {@link Collection} of registered {@link TouchHandler}s
	 */
	public Collection<TouchHandler> getTouchHandlers();

	/**
	 * Removes all {@link TouchHandler}s from the hologram
	 */
	public void clearTouchHandlers();

	/**
	 * Adds a view handler to the hologram
	 *
	 * @param handler
	 *            {@link ViewHandler} instance
	 */
	public void addViewHandler(@Nonnull ViewHandler handler);

	/**
	 * Removes a view handler from the hologram
	 *
	 * @param handler
	 *            {@link ViewHandler} instance
	 */
	public void removeViewHandler(@Nonnull ViewHandler handler);

	/**
	 * @return a {@link Collection} of registered {@link ViewHandler}s
	 */
	public Collection<ViewHandler> getViewHandlers();

	/**
	 * Removes all {@link ViewHandler}s from the hologram
	 */
	public void clearViewHandlers();

	/**
	 * Adds a {@link Hologram} line below this hologram
	 *
	 * @param text
	 *            Text content of the hologram
	 * @return A new {@link Hologram} instance
	 */
	public Hologram addLineBelow(String text);

	/**
	 * @return The {@link Hologram} line below this hologram
	 */
	public Hologram getLineBelow();

	/**
	 * Removes the line below this hologram
	 *
	 * @return <code>true</code> if the hologram has been removed
	 */
	public boolean removeLineBelow();

	/**
	 * @return a {@link Collection} of all below {@link Hologram} lines
	 */
	public Collection<Hologram> getLinesBelow();

	/**
	 * Adds a {@link Hologram} line above this hologram
	 *
	 * @param text
	 *            Text content of the hologram
	 * @return A new {@link Hologram} instance
	 */
	public Hologram addLineAbove(String text);

	/**
	 * @return The {@link Hologram} line above this hologram
	 */
	public Hologram getLineAbove();

	/**
	 * Removes the line above this hologram
	 *
	 * @return <code>true</code> if the hologram has been removed
	 */
	public boolean removeLineAbove();

	/**
	 * @return a {@link Collection} of all above {@link Hologram} lines
	 */
	public Collection<Hologram> getLinesAbove();

	/**
	 * @return a {@link Collection} of all below and above {@link Hologram} lines (Including this hologram)
	 */
	public Collection<Hologram> getLines();

}
