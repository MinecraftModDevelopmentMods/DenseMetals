package com.mcmoddev.densemetals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mcmoddev.densemetals.gen.DenseMetalGen;
import com.mcmoddev.densemetals.gen.WorldGenEntry;
import com.mcmoddev.densemetals.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = DenseMetals.MODID,
	name = DenseMetals.MODNAME,
	version = DenseMetals.VERSION,
	acceptedMinecraftVersions = "[1.12,)",
	certificateFingerprint = "@FINGERPRINT@",
	updateJSON = DenseMetals.UPDATEJSON)
public class DenseMetals {

	public static final String MODID = "densemetals";
	public static final String MODNAME = "Dense Metals";
	public static final String VERSION = "2.0.0";
	protected static final String UPDATEJSON = "https://raw.githubusercontent.com/MinecraftModDevelopmentMods/"
			+ "DenseMetals/master/update.json";
	public static final CreativeTabs TAB = new CreativeTabs(MODID) {
		public ItemStack createIcon() {
			return new ItemStack(ModBlocks.DENSE_ORES.get(0));
		};
	};
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public static Configuration config;

	/**
	 * Logs a warning when ever the mod finger print does not match the certificate loaded from the mod jar.
	 * @param event The event that represents the finger print violation.
	 */
	@EventHandler
	public void onFingerprintViolation(final FMLFingerprintViolationEvent event) {
		LOGGER.warn("Invalid fingerprint detected!");
	}

	@EventHandler
	public void preInit(final FMLPreInitializationEvent event) {
		ModBlocks.DENSE_ORES.forEach(bl -> sendVeinMinerIMC(bl));
		config = new Configuration(event.getSuggestedConfigurationFile());
		DenseMetalsConfig.readConfig();
	}

	@EventHandler
	public void init(final FMLInitializationEvent event) {
		ModBlocks.DENSE_ORES.forEach(bl -> sendVeinMinerIMC(bl));
	}

	@EventHandler
	public void postInit(final FMLPostInitializationEvent event) {
		ModBlocks.DENSE_ORES.forEach(bl -> {
			if (bl.resolve() && DenseMetalsConfig.doWorldGen) {
				final String path = bl.getRegistryName().getPath();
				if (config.getBoolean("Generate", path, true, "If this ore will attempt to replace ores with itself.")) {
					final int yMin = config.getInt("Min Y Level", path, bl.getYMin(), 0, 255, "The minimum Y level replacement will occur at.");
					final int yMax = config.getInt("Max Y Level", path, bl.getYMax(), 0, 255, "The maximum Y level replacement will occur at.");
					final int chance = config.getInt("Spawn Chances", path, bl.getChance(), 0, 255, "How many times this ore will attempt to spawn each chunk.  A value of 0 will not generate.");
					final int dim = config.getInt("Dimension", path, bl.getDim(), Integer.MIN_VALUE, Integer.MAX_VALUE, "What dimension this replacement occurs in.");
					if (yMax >= yMin && chance > 0) DenseMetalGen.GENERATORS.add(new WorldGenEntry(bl, yMin, yMax, chance, dim));
				}
			}
		});
		if (config.hasChanged()) config.save();
		GameRegistry.registerWorldGenerator(new DenseMetalGen(), 0);
	}

	public static void sendVeinMinerIMC(final Block block) {
		final NBTTagCompound message = new NBTTagCompound();
		message.setString("whitelistType", "block");
		message.setString("toolType", "pickaxe");
		message.setString("blockName", block.getRegistryName().toString());
		FMLInterModComms.sendMessage("veinminer", "whitelist", message);
	}

}