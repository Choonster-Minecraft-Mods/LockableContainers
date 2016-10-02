package com.github.choonster.lockablecontainers.api.capability.lock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.LockCode;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Default implementation of {@link ILock}.
 *
 * @author Choonster
 */
public class Lock implements ILock, INBTSerializable<NBTTagCompound> {

	/**
	 * The lock code.
	 */
	private LockCode code = LockCode.EMPTY_CODE;

	/**
	 * The display name source.
	 */
	private final IWorldNameable displayNameSource;

	public Lock(IWorldNameable displayNameSource) {
		this.displayNameSource = displayNameSource;
	}

	@Override
	public boolean isLocked() {
		return code != null && !code.isEmpty();
	}

	@Override
	public LockCode getLockCode() {
		return code;
	}

	@Override
	public IWorldNameable getDisplayNameSource() {
		return displayNameSource;
	}

	@Override
	public void setLockCode(LockCode code) {
		this.code = code;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		final NBTTagCompound tagCompound = new NBTTagCompound();

		if (code != null) {
			code.toNBT(tagCompound);
		}

		return tagCompound;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		code = LockCode.fromNBT(nbt);
	}
}
