package com.github.choonster.lockablecontainers.init;

import com.github.choonster.lockablecontainers.LockableContainers;
import com.github.choonster.lockablecontainers.network.MessageLockSetLockCode;
import com.github.choonster.lockablecontainers.network.MessageOpenLockGui;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Registers this mod's {@link IMessage}s.
 *
 * @author Choonster
 */
public class ModMessages {
	private static int messageID = 0;

	/**
	 * Register this mod's {@link IMessage}s.
	 */
	public static void registerMessages() {
		registerMessage(MessageLockSetLockCode.Handler.class, MessageLockSetLockCode.class, Side.SERVER);
		registerMessage(MessageOpenLockGui.Handler.class, MessageOpenLockGui.class, Side.CLIENT);
	}

	/**
	 * Register an {@link IMessage}.
	 *
	 * @param messageHandler     The message handler class
	 * @param requestMessageType The message class
	 * @param receivingSide      The side that will receive the message
	 * @param <REQ>              The request type
	 * @param <REPLY>            The reply type
	 */
	private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side receivingSide) {
		LockableContainers.NETWORK.registerMessage(messageHandler, requestMessageType, messageID++, receivingSide);
	}
}
