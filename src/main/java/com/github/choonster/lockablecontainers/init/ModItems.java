package com.github.choonster.lockablecontainers.init;

import com.github.choonster.lockablecontainers.LockableContainers;
import com.github.choonster.lockablecontainers.item.ItemKey;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Stores and registers this mod's {@link Item}s.
 *
 * @author Choonster
 */
@SuppressWarnings("WeakerAccess")
@GameRegistry.ObjectHolder(LockableContainers.MODID)
public class ModItems {

	@GameRegistry.ObjectHolder("key")
	public static final ItemKey KEY = new ItemKey();

	@Mod.EventBusSubscriber
	public static class RegistrationHandler {

		/**
		 * Register this mod's {@link Item}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			event.getRegistry().register(KEY);
		}
	}
}
