package com.knoxhack.densemetals.init;


import com.google.common.base.Preconditions;
import com.knoxhack.densemetals.Main;
import com.knoxhack.densemetals.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

@ObjectHolder(Main.MODID)
public class ModBlocks {

	public static final BlockBase denseIronBlock = new BlockBase(Material.IRON, "dense_iron_ore");
	public static final BlockBase denseDiamonBlock = new BlockBase(Material.IRON, "dense_diamond_ore");
	public static final BlockBase denseRedstoneBlock = new BlockBase(Material.IRON, "dense_redstone_ore");
	public static final BlockBase denseEmeraldBlock = new BlockBase(Material.IRON, "dense_emerald_ore");
	public static final BlockBase denseCoalBlock = new BlockBase(Material.IRON, "dense_coal_ore");
	public static final BlockBase denseLapisBlock = new BlockBase(Material.IRON, "dense_lapis_ore");
	public static final BlockBase denseGoldBlock = new BlockBase(Material.IRON, "dense_gold_ore");



	@Mod.EventBusSubscriber(modid = Main.MODID)
	public static class RegistrationHandler {
	public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
	final IForgeRegistry<Block> registry = event.getRegistry();
    final Block[] blocks = 
    {

    denseIronBlock,
    denseDiamonBlock,
    denseRedstoneBlock,
    denseEmeraldBlock,
    denseCoalBlock,
    denseLapisBlock,
    denseGoldBlock,

    
    };
	registry.registerAll(blocks);
	}
	@SubscribeEvent
	public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
	final ItemBlock[] items = 
	{
	//new ItemBlock(SLATE),

	new ItemBlock(denseIronBlock),
	new ItemBlock(denseDiamonBlock),
	new ItemBlock(denseRedstoneBlock),
	new ItemBlock(denseEmeraldBlock),
	new ItemBlock(denseCoalBlock),
	new ItemBlock(denseLapisBlock),
	new ItemBlock(denseGoldBlock),

};

	
	final IForgeRegistry<Item> registry = event.getRegistry();
	for (final ItemBlock item : items) {
	final Block block = item.getBlock();
	final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
	registry.register(item.setRegistryName(registryName));
	ITEM_BLOCKS.add(item);
	}
 }
}


	

	public static void initModels() {
    	denseIronBlock.initModel();
    	denseDiamonBlock.initModel();
    	denseRedstoneBlock.initModel();
    	denseEmeraldBlock.initModel();
    	denseCoalBlock.initModel();
    	denseLapisBlock.initModel();
    	denseGoldBlock.initModel();


	}
	

}