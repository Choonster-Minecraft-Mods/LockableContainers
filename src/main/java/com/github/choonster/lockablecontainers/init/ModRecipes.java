package com.github.choonster.lockablecontainers.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Registers this mod's crafting recipes.
 *
 * @author Choonster
 */
public class ModRecipes {

	/**
	 * Register this mod's crafting recipes.
	 */
	public static void registerRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.KEY), "  I", "GI ", "GG ", 'I', "ingotIron", 'G', "nuggetGold"));
	}
}
