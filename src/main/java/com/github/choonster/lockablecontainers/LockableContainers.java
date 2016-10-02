package com.github.choonster.lockablecontainers;

import com.github.choonster.lockablecontainers.init.ModCapabilities;
import com.github.choonster.lockablecontainers.init.ModItems;
import com.github.choonster.lockablecontainers.init.ModMessages;
import com.github.choonster.lockablecontainers.init.ModRecipes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = LockableContainers.MODID, name = LockableContainers.NAME, acceptedMinecraftVersions = "[1.10.2]")
public class LockableContainers {
	public static final String MODID = "lockablecontainers";
	public static final String NAME = "Lockable Containers";

	@Mod.Instance(MODID)
	public static LockableContainers INSTANCE;

	public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

	public static final CreativeTabs TAB = new CreativeTabs(MODID) {
		@Override
		public Item getTabIconItem() {
			return ModItems.KEY;
		}
	};

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		ModMessages.registerMessages();
		ModCapabilities.registerCapabilities();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		ModRecipes.registerRecipes();
	}
}
