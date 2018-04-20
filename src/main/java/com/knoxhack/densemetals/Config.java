package com.knoxhack.densemetals;

import com.knoxhack.densemetals.proxy.CommonProxy;

import net.minecraftforge.common.config.Configuration;

public class Config {

    private static final String CATEGORY_GENERATOR = " World Generator";

    private static final String CATEGORY_GENERAL = " General";
    private static final String CATEGORY_DENSE_ORES_VANILLA = "Vanilla Dense Ores";

    public static boolean enabledOreSpawn = true;

    public static boolean enabledInternalWorldGen = false;
    public static boolean enabledVanillaDenseOres = true;
    public static boolean enabledBaseMetalsDenseOres = true;

    public static boolean enabledDenseIronOre = true;
    public static boolean enabledDenseDiamondOre = true;
    public static boolean enabledDenseRedstoneOre = true;
    public static boolean enabledDenseGoldOre = true;
    public static boolean enabledDenseEmeraldOre = true;
    public static boolean enabledDenseLapisOre = true;
    public static boolean enabledDenseCoalOre = true;
                         //still needs to be finished
	public static boolean enabledNetherMetalsVanillasDenseOres =  true;

	public static boolean enabledNetherMetalsDenseOres = true;

    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneratorConfig(cfg);
            initGeneralConfig(cfg);
            initVanillaDenseOresConfig(cfg);
        } catch (Exception e1) {

        } finally {
            if (cfg.hasChanged())
                cfg.save();
        }
    }
    private static void initGeneratorConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERATOR, "World Generator configuration");
        enabledOreSpawn = cfg.getBoolean("enableOreSpawnIntregration", CATEGORY_GENERATOR, enabledOreSpawn, "Set to true to enable the use of MMD OreSpawn for world generation");
        enabledInternalWorldGen = cfg.getBoolean("enableInternalWorldGen", CATEGORY_GENERATOR, enabledInternalWorldGen, "Set to false to disable the use of the world generator included with this mod");

    }
    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        enabledVanillaDenseOres = cfg.getBoolean("enableVanillaDenseOres", CATEGORY_GENERAL, enabledVanillaDenseOres, "Set to false to disable Vanilla Dense ores");
        enabledBaseMetalsDenseOres = cfg.getBoolean("enableBaseMetalsDenseOres", CATEGORY_GENERAL, enabledBaseMetalsDenseOres, "Set to false to disable BaseMetals Dense ores");
        enabledNetherMetalsDenseOres = cfg.getBoolean("enableNetherMetalsDenseOres", CATEGORY_GENERAL, enabledNetherMetalsDenseOres, "Set to false to disable Nether Metals Dense ores");

    
    
    }

    private static void initVanillaDenseOresConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_DENSE_ORES_VANILLA, "Vanilla Dense Ores configuration");
        //Food
        enabledDenseIronOre = cfg.getBoolean("enableDenseIronOre", CATEGORY_DENSE_ORES_VANILLA, enabledDenseIronOre, "Set to false to disable Iron Dense Ore");
        enabledDenseDiamondOre = cfg.getBoolean("enableDenseDiamondOre", CATEGORY_DENSE_ORES_VANILLA, enabledDenseDiamondOre, "Set to false to disable Diamond Dense Ore");
        enabledDenseRedstoneOre = cfg.getBoolean("enableDenseRedstoneOre", CATEGORY_DENSE_ORES_VANILLA, enabledDenseRedstoneOre, "Set to false to disable Redstone Dense Ore");
        enabledDenseGoldOre = cfg.getBoolean("enableDenseGoldOre", CATEGORY_DENSE_ORES_VANILLA, enabledDenseGoldOre, "Set to false to disable Gold Dense Ore");
        enabledDenseEmeraldOre = cfg.getBoolean("enableDenseEmeraldOre", CATEGORY_DENSE_ORES_VANILLA, enabledDenseEmeraldOre, "Set to false to disable Emerald Dense Ore");
        enabledDenseLapisOre = cfg.getBoolean("enableDenseLapisOre", CATEGORY_DENSE_ORES_VANILLA, enabledDenseLapisOre, "Set to false to disable Lapis Dense Ore");
        enabledDenseCoalOre = cfg.getBoolean("enableDenseCoalOre", CATEGORY_DENSE_ORES_VANILLA, enabledDenseCoalOre, "Set to false to disable Coal Dense Ore");
    }
}