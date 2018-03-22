package com.knoxhack.densemetals.init;


import com.google.common.base.Preconditions;
import com.knoxhack.densemetals.Config;
import com.knoxhack.densemetals.Main;
import com.knoxhack.densemetals.blocks.BlockBase;
import com.knoxhack.densemetals.blocks.BlockBaseModdedAdamantine;
import com.knoxhack.densemetals.blocks.BlockBaseModdedAntimony;
import com.knoxhack.densemetals.blocks.BlockBaseModdedBismuth;
import com.knoxhack.densemetals.blocks.BlockBaseModdedColdiron;
import com.knoxhack.densemetals.blocks.BlockBaseModdedCopper;
import com.knoxhack.densemetals.blocks.BlockBaseModdedLead;
import com.knoxhack.densemetals.blocks.BlockBaseModdedMercury;
import com.knoxhack.densemetals.blocks.BlockBaseModdedNickel;
import com.knoxhack.densemetals.blocks.BlockBaseModdedStarsteel;
import com.knoxhack.densemetals.blocks.BlockBaseModdedTin;
import com.knoxhack.densemetals.blocks.BlockBaseModdedZinc;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

@ObjectHolder(Main.MODID)
public class ModBlocks {

	public static /*final*/ BlockBase denseIronBlock = new BlockBase(Material.IRON, "dense_iron_ore", 
			Item.getItemFromBlock(Blocks.IRON_ORE));
	public static final BlockBase denseDiamonBlock = new BlockBase(Material.IRON, "dense_diamond_ore", 
			Item.getItemFromBlock(Blocks.DIAMOND_ORE));
	public static final BlockBase denseRedstoneBlock = new BlockBase(Material.IRON, "dense_redstone_ore", 
			Items.REDSTONE );
	public static final BlockBase denseEmeraldBlock = new BlockBase(Material.IRON, "dense_emerald_ore",
			Item.getItemFromBlock(Blocks.EMERALD_ORE));
	public static final BlockBase denseCoalBlock = new BlockBase(Material.IRON, "dense_coal_ore",
			Item.getItemFromBlock(Blocks.COAL_ORE));
	public static final BlockBase denseLapisBlock = new BlockBase(Material.IRON, "dense_lapis_ore",
			Items.DYE);
	public static final BlockBase denseGoldBlock = new BlockBase(Material.IRON, "dense_gold_ore",
			Item.getItemFromBlock(Blocks.GOLD_ORE));
	
	public static final BlockBaseModdedAdamantine denseAdamantineBlock = new BlockBaseModdedAdamantine(Material.IRON, "dense_adamantine_ore");
	public static final BlockBaseModdedAntimony denseAntimonyBlock = new BlockBaseModdedAntimony(Material.IRON, "dense_antimony_ore");
	public static final BlockBaseModdedBismuth denseBismuthBlock = new BlockBaseModdedBismuth(Material.IRON, "dense_bismuth_ore");
	public static final BlockBaseModdedCopper denseCopperBlock = new BlockBaseModdedCopper(Material.IRON, "dense_copper_ore");
	public static final BlockBaseModdedColdiron denseColdironBlock = new BlockBaseModdedColdiron(Material.IRON, "dense_coldiron_ore");
	public static final BlockBaseModdedLead denseLeadBlock = new BlockBaseModdedLead(Material.IRON, "dense_lead_ore");
	public static final BlockBaseModdedNickel denseNickelBlock = new BlockBaseModdedNickel(Material.IRON, "dense_nickel_ore");
	public static final BlockBaseModdedMercury denseMercuryBlock = new BlockBaseModdedMercury(Material.IRON, "dense_mercury_ore");
	public static final BlockBaseModdedStarsteel denseStarsteelBlock = new BlockBaseModdedStarsteel(Material.IRON, "dense_starsteel_ore");
	public static final BlockBaseModdedTin denseTinBlock = new BlockBaseModdedTin(Material.IRON, "dense_tin_ore");
	public static final BlockBaseModdedZinc denseZincBlock = new BlockBaseModdedZinc(Material.IRON, "dense_zinc_ore");

	@Mod.EventBusSubscriber(modid = Main.MODID)
	public static class RegistrationHandler {
	public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

	@SubscribeEvent
	public static void registerBaseMetalsBlocks(final RegistryEvent.Register<Block> event) {
	final IForgeRegistry<Block> registry = event.getRegistry();
    final Block[] blocks = 
    {
    denseAdamantineBlock, 
    denseAntimonyBlock, 
    denseBismuthBlock, 
    denseCopperBlock, 
    denseColdironBlock,
    denseLeadBlock, 
    denseNickelBlock,
    denseMercuryBlock,
    denseStarsteelBlock, 
    denseTinBlock, 
    denseZincBlock
    };
    if (Loader.isModLoaded("basemetals")) {
	if (Config.enabledBaseMetalsDenseOres){
	registry.registerAll(blocks);
	}
    }
	}
	@SubscribeEvent
	public static void registerVanillaBlocks(final RegistryEvent.Register<Block> event) {
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
	if (Config.enabledVanillaDenseOres){

	registry.registerAll(blocks);
	}
	}
	@SubscribeEvent
	public static void registerBaseMetalsItemBlocks(final RegistryEvent.Register<Item> event) {
	final ItemBlock[] items = 
	{
	
	new ItemBlock(denseAdamantineBlock),
	new ItemBlock(denseAntimonyBlock),
	new ItemBlock(denseBismuthBlock),
	new ItemBlock(denseCopperBlock),
	new ItemBlock(denseColdironBlock),
	new ItemBlock(denseNickelBlock),
	new ItemBlock(denseMercuryBlock),
	new ItemBlock(denseStarsteelBlock),
	new ItemBlock(denseTinBlock),
	new ItemBlock(denseZincBlock),
	new ItemBlock(denseLeadBlock)

};
    if (Loader.isModLoaded("basemetals")) {
	if (Config.enabledBaseMetalsDenseOres){
	
	final IForgeRegistry<Item> registry = event.getRegistry();
	for (final ItemBlock item : items) {
	final Block block = item.getBlock();
	final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
	registry.register(item.setRegistryName(registryName));
	
	ITEM_BLOCKS.add(item);
	}
	}
    }
 }

	@SubscribeEvent
	public static void registerVanillaItemBlocks(final RegistryEvent.Register<Item> event) {
	final ItemBlock[] items = 
	{

	new ItemBlock(denseIronBlock),
	new ItemBlock(denseDiamonBlock),
	new ItemBlock(denseRedstoneBlock),
	new ItemBlock(denseEmeraldBlock),
	new ItemBlock(denseCoalBlock),
	new ItemBlock(denseLapisBlock),
	new ItemBlock(denseGoldBlock),

};
	if (Config.enabledVanillaDenseOres){
	
	final IForgeRegistry<Item> registry = event.getRegistry();
	for (final ItemBlock item : items) {
	final Block block = item.getBlock();
	final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
	registry.register(item.setRegistryName(registryName));
	
	ITEM_BLOCKS.add(item);
	}
	}
 }
 }
	public static void initBaseMetalsModels() {
        if (Loader.isModLoaded("basemetals")) {
		if (Config.enabledBaseMetalsDenseOres){
    	denseAdamantineBlock.initModel();
    	denseAntimonyBlock.initModel();
    	denseBismuthBlock.initModel();
    	denseCopperBlock.initModel();
    	denseColdironBlock.initModel();
    	denseNickelBlock.initModel();
    	denseMercuryBlock.initModel();
    	denseStarsteelBlock.initModel();
    	denseTinBlock.initModel();
    	denseZincBlock.initModel();
    	denseLeadBlock.initModel();
		}
	}
	}
	public static void initVanillaModels() {
		if (Config.enabledVanillaDenseOres){
    	denseIronBlock.initModel();
    	denseDiamonBlock.initModel();
    	denseRedstoneBlock.initModel();
    	denseEmeraldBlock.initModel();
    	denseCoalBlock.initModel();
    	denseLapisBlock.initModel();
    	denseGoldBlock.initModel();

		}
	}
}