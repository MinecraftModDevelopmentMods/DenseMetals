package com.knoxhack.densemetals.init;

import com.google.common.base.Preconditions;
import com.knoxhack.densemetals.Config;
import com.knoxhack.densemetals.Main;
import com.knoxhack.densemetals.blocks.BlockBase;
import com.knoxhack.densemetals.blocks.BlockBaseModded;
import com.knoxhack.densemetals.blocks.BlockBaseModdedNether;

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

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

@ObjectHolder(Main.MODID)
public class ModBlocks {

	public static final BlockBase
    	denseIronBlock = new BlockBase(Material.IRON, "dense_iron_ore", Blocks.IRON_ORE),
    	denseDiamonBlock = new BlockBase(Material.IRON, "dense_diamond_ore", Items.DIAMOND),
    	denseRedstoneBlock = new BlockBase(Material.IRON, "dense_redstone_ore", Items.REDSTONE),
    	denseEmeraldBlock = new BlockBase(Material.IRON, "dense_emerald_ore", Items.EMERALD),
    	denseCoalBlock = new BlockBase(Material.IRON, "dense_coal_ore", Items.COAL),
    	denseLapisBlock = new BlockBase(Material.IRON, "dense_lapis_ore", Items.DYE),
    	denseGoldBlock = new BlockBase(Material.IRON, "dense_gold_ore", Blocks.GOLD_ORE);
	
	public static final BlockBaseModded
    	denseAdamantineBlock = new BlockBaseModded(Material.IRON, "dense_adamantine_ore", "adamantine"),
    	denseAntimonyBlock = new BlockBaseModded(Material.IRON, "dense_antimony_ore", "antimony"),
    	denseBismuthBlock = new BlockBaseModded(Material.IRON, "dense_bismuth_ore", "bismuth"),
    	denseCopperBlock = new BlockBaseModded(Material.IRON, "dense_copper_ore", "copper"),
    	denseColdironBlock = new BlockBaseModded(Material.IRON, "dense_coldiron_ore", "coldiron"),
    	denseLeadBlock = new BlockBaseModded(Material.IRON, "dense_lead_ore", "lead"),
    	denseNickelBlock = new BlockBaseModded(Material.IRON, "dense_nickel_ore", "nickel"),
    	denseMercuryBlock = new BlockBaseModded(Material.IRON, "dense_mercury_ore", "mercury"),
    	denseStarsteelBlock = new BlockBaseModded(Material.IRON, "dense_starsteel_ore", "starsteel"),
    	denseTinBlock = new BlockBaseModded(Material.IRON, "dense_tin_ore", "tin"),
    	denseZincBlock = new BlockBaseModded(Material.IRON, "dense_zinc_ore", "zinc");
	
	
	// Nether Metals Compat
	public static final BlockBaseModdedNether
	denseNetherGoldBlock = new BlockBaseModdedNether(Material.IRON, "dense_nether_gold_ore", "gold"),
	 denseNetherLapisBlock = new BlockBaseModdedNether(Material.IRON, "dense_nether_lapis_ore", "lapis"),
 denseNetherCoalBlock = new BlockBaseModdedNether(Material.IRON, "dense_nether_coal_ore","coal"),
	 denseNetherEmeraldBlock = new BlockBaseModdedNether(Material.IRON, "dense_nether_emerald_ore", "emerald"),
	 denseNetherRedstoneBlock = new BlockBaseModdedNether(Material.IRON, "dense_nether_redstone_ore", "redstone"),
	 denseNetherDiamonBlock = new BlockBaseModdedNether(Material.IRON, "dense_nether_diamond_ore", "diamond"),
	denseNetherIronBlock = new BlockBaseModdedNether(Material.IRON, "dense_nether_iron_ore", "iron");
	

	@Mod.EventBusSubscriber(modid = Main.MODID)
	public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBaseMetalsBlocks(final RegistryEvent.Register<Block> event) {
            if (Loader.isModLoaded("basemetals"))
                if (Config.enabledBaseMetalsDenseOres)
                    event.getRegistry().registerAll(denseAdamantineBlock, denseAntimonyBlock, denseBismuthBlock,
                            denseCopperBlock, denseColdironBlock, denseLeadBlock, denseNickelBlock, denseMercuryBlock,
                            denseStarsteelBlock, denseTinBlock, denseZincBlock);
        }
        @SubscribeEvent
        public static void registerNetherMetalsVanillaBlocks(final RegistryEvent.Register<Block> event) {
            if (Loader.isModLoaded("nethermetals"))
                if (Config.enabledNetherMetalsDenseOres)
                    event.getRegistry().registerAll(denseNetherGoldBlock, denseNetherLapisBlock,denseNetherCoalBlock,
                    		denseNetherEmeraldBlock,denseNetherRedstoneBlock,denseNetherDiamonBlock,denseNetherIronBlock);
        }
        
        @SubscribeEvent
        public static void registerVanillaBlocks(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> registry = event.getRegistry();
            final Block[] blocks = {
                    denseIronBlock, denseDiamonBlock, denseRedstoneBlock, denseEmeraldBlock, denseCoalBlock,
                    denseLapisBlock, denseGoldBlock,
            };
            if (Config.enabledVanillaDenseOres)
                registry.registerAll(blocks);
        }

        @SubscribeEvent
        public static void registerBaseMetalsItemBlocks(final RegistryEvent.Register<Item> event) {
            final ItemBlock[] items = {
                    new ItemBlock(denseAdamantineBlock), new ItemBlock(denseAntimonyBlock),
                    new ItemBlock(denseBismuthBlock), new ItemBlock(denseCopperBlock),
                    new ItemBlock(denseColdironBlock), new ItemBlock(denseNickelBlock),
                    new ItemBlock(denseMercuryBlock), new ItemBlock(denseStarsteelBlock), new ItemBlock(denseTinBlock),
                    new ItemBlock(denseZincBlock), new ItemBlock(denseLeadBlock)
            };

            if (Loader.isModLoaded("basemetals"))
                if (Config.enabledBaseMetalsDenseOres) {
                    final IForgeRegistry<Item> registry = event.getRegistry();
                    for (final ItemBlock item : items) {
                        final Block block = item.getBlock();
                        final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(),
                                "Block %s has null registry name", block);
                        registry.register(item.setRegistryName(registryName));
                    }
                }
        }

        @SubscribeEvent
        public static void registerNetherMetalsVanillaItemBlocks(final RegistryEvent.Register<Item> event) {
            final ItemBlock[] items = {
                    new ItemBlock(denseNetherGoldBlock), new ItemBlock(denseNetherLapisBlock),
                    new ItemBlock(denseNetherCoalBlock), new ItemBlock(denseNetherEmeraldBlock),
                    new ItemBlock(denseNetherRedstoneBlock), new ItemBlock(denseNetherDiamonBlock),
                    new ItemBlock(denseNetherIronBlock)  
            };
            
            if (Loader.isModLoaded("nethermetals"))
                if (Config.enabledNetherMetalsDenseOres) {
                    final IForgeRegistry<Item> registry = event.getRegistry();
                    for (final ItemBlock item : items) {
                        final Block block = item.getBlock();
                        final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(),
                                "Block %s has null registry name", block);
                        registry.register(item.setRegistryName(registryName));
                    }
                }
        }
        @SubscribeEvent
        public static void registerVanillaItemBlocks(final RegistryEvent.Register<Item> event) {
            final ItemBlock[] items = {
                    new ItemBlock(denseIronBlock), new ItemBlock(denseDiamonBlock), new ItemBlock(denseRedstoneBlock),
                    new ItemBlock(denseEmeraldBlock), new ItemBlock(denseCoalBlock), new ItemBlock(denseLapisBlock),
                    new ItemBlock(denseGoldBlock),
            };
            if (Config.enabledVanillaDenseOres) {
                final IForgeRegistry<Item> registry = event.getRegistry();
                for (final ItemBlock item : items) {
                    final Block block = item.getBlock();
                    final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(),
                            "Block %s has null registry name", block);
                    registry.register(item.setRegistryName(registryName));
                }
            }
        }
    }

    public static void initBaseMetalsModels() {
        if (Loader.isModLoaded("basemetals"))
            if (Config.enabledNetherMetalsDenseOres) {
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
    
    public static void initNetherMetalsModels() {
        if (Loader.isModLoaded("nethermetals"))
            if (Config.enabledBaseMetalsDenseOres) {
            	denseNetherGoldBlock.initModel();
            	denseNetherLapisBlock.initModel();
            	denseNetherCoalBlock.initModel();
            	denseNetherEmeraldBlock.initModel();
            	denseNetherRedstoneBlock.initModel();
            	denseNetherDiamonBlock.initModel();
            	denseNetherIronBlock.initModel();

            }
    }

    public static void initVanillaModels() {
        if (Config.enabledVanillaDenseOres) {
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