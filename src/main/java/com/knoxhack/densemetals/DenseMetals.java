package com.knoxhack.densemetals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.knoxhack.densemetals.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = DenseMetals.MODID, name = DenseMetals.MODNAME, version = DenseMetals.VERSION, dependencies = "after:orespawn@[3.3.0,);after:basemetals;")
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
		ModBlocks.DENSE_ORES.forEach(bl -> bl.resolve());
	}

	public static void sendVeinMinerIMC(Block block) {
		NBTTagCompound message = new NBTTagCompound();
		message.setString("whitelistType", "block");
		message.setString("toolType", "pickaxe");
		message.setString("blockName", block.getRegistryName().toString());
		FMLInterModComms.sendMessage("veinminer", "whitelist", message);
	}

}