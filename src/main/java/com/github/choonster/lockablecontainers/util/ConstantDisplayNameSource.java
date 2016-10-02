package com.github.choonster.lockablecontainers.util;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IWorldNameable;

/**
 * A simple implementation of {@link IWorldNameable}.
 *
 * @author Choonster
 */
public class ConstantDisplayNameSource implements IWorldNameable {
	/**
	 * The display name.
	 */
	private final ITextComponent displayName;

	public ConstantDisplayNameSource(ITextComponent displayName) {
		this.displayName = displayName;
	}

	/**
	 * Get the displayName of this object. For players this returns their username
	 */
	@Override
	public String getName() {
		return this.displayName.getUnformattedText();
	}

	/**
	 * Returns true if this thing is named
	 */
	@Override
	public boolean hasCustomName() {
		return true;
	}

	/**
	 * Get the formatted ChatComponent that will be used for the sender's username in chat
	 */
	@Override
	public ITextComponent getDisplayName() {
		return displayName;
	}
}
