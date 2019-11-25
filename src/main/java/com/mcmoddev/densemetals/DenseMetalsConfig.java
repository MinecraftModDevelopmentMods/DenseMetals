package com.mcmoddev.densemetals;

import net.minecraftforge.common.config.Configuration;

public class DenseMetalsConfig {

	public static int denseOreValue = 2;
	public static String prefModid = "thermalfoundation";
	public static boolean doWorldGen = true;

	public static void readConfig() {
		Configuration cfg = DenseMetals.config;

		denseOreValue = cfg.getInt("Dense Ore Value", "_general", denseOreValue, 1, Integer.MAX_VALUE, "How much a dense ore is worth, in terms of the original ore's drops.");
		prefModid = cfg.getString("Preferred Mod", "_general", prefModid, "The modid that will take precedence when calculating the drops, exp, and hardness/resistance of dense ores.");
		doWorldGen = cfg.getBoolean("World Generation", "_general", doWorldGen, "If dense metals will try to auto-generate ores.");

		if (cfg.hasChanged()) cfg.save();
	}

}