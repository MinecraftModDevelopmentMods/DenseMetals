package com.knoxhack.densemetals;

import net.minecraftforge.common.config.Configuration;

public class Config {

	public static int denseOreValue = 2;

	public static void readConfig() {
		Configuration cfg = DenseMetals.config;

		denseOreValue = cfg.getInt("Dense Ore Value", "general", denseOreValue, 1, Integer.MAX_VALUE, "How much a dense ore is worth, in terms of the original ore's drops.");

		if (cfg.hasChanged()) cfg.save();
	}

}