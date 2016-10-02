package com.github.choonster.lockablecontainers.api.capability.lock.wrapper;

import com.github.choonster.lockablecontainers.api.capability.lock.ILock;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.LockCode;

/**
 * {@link ILock} wrapper around {@link ILockableContainer}.
 *
 * @author Choonster
 */
public class LockableContainerWrapper implements ILock {
	private final ILockableContainer lockableContainer;

	public LockableContainerWrapper(ILockableContainer lockableContainer) {
		this.lockableContainer = lockableContainer;
	}

	/**
	 * @return Is this locked?
	 */
	@Override
	public boolean isLocked() {
		return lockableContainer.isLocked();
	}

	/**
	 * Set the lock code.
	 *
	 * @param code The lock code
	 */
	@Override
	public void setLockCode(LockCode code) {
		lockableContainer.setLockCode(code);
	}

	/**
	 * Get the lock code.
	 *
	 * @return The lock code, if any
	 */
	@Override
	public LockCode getLockCode() {
		return lockableContainer.getLockCode();
	}

	/**
	 * Get the display name source.
	 *
	 * @return The display name source
	 */
	@Override
	public IWorldNameable getDisplayNameSource() {
		return lockableContainer;
	}

}
