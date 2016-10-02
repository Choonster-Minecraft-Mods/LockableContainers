package com.github.choonster.lockablecontainers.api.capability.lock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.LockCode;

/**
 * A capability allowing things to be locked with a {@link LockCode} and only opened by players holding an item with a matching display name.
 * <p>
 * This is a copy of {@link net.minecraft.world.ILockableContainer} that doesn't implement {@link net.minecraft.inventory.IInventory}.
 *
 * @author Choonster
 */
public interface ILock {

	/**
	 * @return Is this locked?
	 */
	boolean isLocked();

	/**
	 * Set the lock code.
	 *
	 * @param code The lock code
	 */
	void setLockCode(LockCode code);

	/**
	 * Get the lock code.
	 *
	 * @return The lock code
	 */

	LockCode getLockCode();

	/**
	 * Get the display name source.
	 *
	 * @return The display name source
	 */
	IWorldNameable getDisplayNameSource();

	/**
	 * Can the player open this lock?
	 *
	 * @param player The player
	 * @return Can the player open this lock?
	 */
	default boolean canOpen(EntityPlayer player) {
		final LockCode lockCode = getLockCode();
		return !isLocked() || player.canOpen(lockCode) || player.isSpectator();
	}

}
