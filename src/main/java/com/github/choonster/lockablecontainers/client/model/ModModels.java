package com.github.choonster.lockablecontainers.client.model;

import com.github.choonster.lockablecontainers.init.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Registers this mod's block and item models.
 *
 * @author Choonster
 */
@Mod.EventBusSubscriber(Side.CLIENT)
public class ModModels {

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event){
		registerItemModel(ModItems.KEY);
	}

	/**
	 * Register the default model for an {@link Item}.
	 * @param item The item
	 */
	private static void registerItemModel(Item item){
		final ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), "inventory");
		ModelLoader.setCustomMeshDefinition(item, MeshDefinitionFix.create(stack -> location));
	}
}
