package com.github.choonster.lockablecontainers.api.capability.lock;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

/**
 * Capability for {@link ILock}.
 *
 * @author Choonster
 */
public class CapabilityLock {

	/**
	 * The {@link Capability} instance.
	 */
	@CapabilityInject(ILock.class)
	public static final Capability<ILock> LOCK_CAPABILITY = null;
}
