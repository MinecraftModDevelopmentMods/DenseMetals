package com.knoxhack.densemetals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.knoxhack.densemetals.gen.DenseMetalGen;
import com.knoxhack.densemetals.gen.WorldGenEntry;
import com.knoxhack.densemetals.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = DenseMetals.MODID, name = DenseMetals.MODNAME, version = DenseMetals.VERSION)
public class DenseMetals {

	public static final String MODID = "densemetals";
	public static final String MODNAME = "Dense Metals";
	public static final String VERSION = "1.3.0";
	public static final CreativeTabs TAB = new CreativeTabs(MODID) {
		public ItemStack createIcon() {
			return new ItemStack(ModBlocks.DENSE_ORES.get(0));
		};
	};
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public static Configuration config;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		ModBlocks.DENSE_ORES.forEach(bl -> sendVeinMinerIMC(bl));
		config = new Configuration(e.getSuggestedConfigurationFile());
		DenseMetalsConfig.readConfig();
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		ModBlocks.DENSE_ORES.forEach(bl -> sendVeinMinerIMC(bl));
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		ModBlocks.DENSE_ORES.forEach(bl -> {
			if (bl.resolve()) {
				String path = bl.getRegistryName().getPath();
				if (config.getBoolean("Generate", path, true, "If this ore will attempt to replace ores with itself.")) {
					WorldGenMinable wgm = DenseMetalGen.getWGM(bl, config.getInt("Gen Count", path, 35, 1, Integer.MAX_VALUE, "How many ores are spawned per spawn chance."), bl.getOriginal());
					int yMin = config.getInt("Min Y Level", path, bl.getYMin(), 0, 255, "The minimum Y level replacement will occur at.");
					int yMax = config.getInt("Max Y Level", path, bl.getYMax(), 0, 255, "The maximum Y level replacement will occur at.");
					int chance = config.getInt("Spawn Chances", path, bl.getChance(), 0, 255, "How many times this ore will attempt to spawn each chunk.  A value of 0 will not generate.");
					int dim = config.getInt("Dimension", path, bl.getDim(), Integer.MIN_VALUE, Integer.MAX_VALUE, "What dimension this replacement occurs in.");
					if (chance > 0) DenseMetalGen.GENERATORS.add(new WorldGenEntry(wgm, yMin, yMax, chance, dim));
				}
			}
		});
		if (config.hasChanged()) config.save();
		GameRegistry.registerWorldGenerator(new DenseMetalGen(), 0);
	}

	public static void sendVeinMinerIMC(Block block) {
		NBTTagCompound message = new NBTTagCompound();
		message.setString("whitelistType", "block");
		message.setString("toolType", "pickaxe");
		message.setString("blockName", block.getRegistryName().toString());
		FMLInterModComms.sendMessage("veinminer", "whitelist", message);
	}

}