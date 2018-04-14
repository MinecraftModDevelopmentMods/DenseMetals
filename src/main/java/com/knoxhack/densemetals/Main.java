package com.knoxhack.densemetals;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;

import com.knoxhack.densemetals.init.ModBlocks;
import com.knoxhack.densemetals.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION, dependencies = "required-after:forge@[14.23.2.2600,);required-after:orespawn@[3.3.0,);after:basemetals;before:buildingbricks", useMetadata = true, certificateFingerprint = "@FINGERPRINT@")
public class Main {

    public static final String MODID = "densemetals";
    public static final String MODNAME = "Dense Metals";
    public static final String VERSION = "1.2.0";
    public static final CreativeTabMain creativeTab = new CreativeTabMain();

    @SidedProxy(clientSide = "com.knoxhack.densemetals.proxy.ClientProxy", serverSide = "com.knoxhack.densemetals.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Main instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
        Arrays.asList(ModBlocks.denseIronBlock,
            ModBlocks.denseDiamonBlock,
            ModBlocks.denseRedstoneBlock,
            ModBlocks.denseEmeraldBlock,
            ModBlocks.denseCoalBlock,
            ModBlocks.denseLapisBlock,
            ModBlocks.denseGoldBlock).stream()
        .forEach(bl -> sendVeinMinerIMC(bl));
    }

    @EventHandler
    public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
        if (!(Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment"))
            logger.warn("Invalid fingerprint detected!");
    }

    public void sendVeinMinerIMC(Block block) {
        NBTTagCompound message = new NBTTagCompound();
        message.setString("whitelistType", "block");
        message.setString("toolType", "pickaxe");
        message.setString("blockName", block.getRegistryName().toString());
        FMLInterModComms.sendMessage("veinminer", "whitelist", message);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    public static boolean hasOreSpawn() {
        return Loader.isModLoaded("orespawn");
    }
}