package com.knoxhack.densemetals;

import com.knoxhack.densemetals.init.ModBlocks;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class DenseMetalsClient {

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		ModBlocks.DENSE_ORES.forEach(b -> {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), 0, new ModelResourceLocation(b.getRegistryName(), "normal"));
		});
	}
}