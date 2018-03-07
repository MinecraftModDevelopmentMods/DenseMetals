package com.knoxhack.densemetals;



import com.knoxhack.densemetals.init.ModBlocks;
import com.knoxhack.densemetals.proxy.CommonProxy;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import java.util.Arrays;

import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.MODVERSION, dependencies = "required-after:forge@[14.23.2.2624,)", useMetadata = true)
public class Main {

    public static final String MODID = "densemetals";
    public static final String MODNAME = "Dense Metals";
    public static final String MODVERSION = "1.0.3";
	public static final CreativeTabMain creativeTab = new CreativeTabMain();

    @SidedProxy(clientSide = "com.knoxhack.densemetals.proxy.ClientProxy", serverSide = "com.knoxhack.densemetals.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Main instance;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
       // logger =  (Logger) event.getModLog();
        proxy.preInit(event);
        Arrays.asList( ModBlocks.denseIronBlock,
            ModBlocks.denseDiamonBlock,
            ModBlocks.denseRedstoneBlock,
            ModBlocks.denseEmeraldBlock,
            ModBlocks.denseCoalBlock,
            ModBlocks.denseLapisBlock,
            ModBlocks.denseGoldBlock).stream()
        .forEach( bl -> sendVeinMinerIMC(bl) );

    }

    public void sendVeinMinerIMC( Block block ) {
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