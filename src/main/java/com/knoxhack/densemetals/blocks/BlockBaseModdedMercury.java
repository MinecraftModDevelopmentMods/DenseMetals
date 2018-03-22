package com.knoxhack.densemetals.blocks;

import java.util.Random;

import com.knoxhack.densemetals.Main;
import com.mcmoddev.lib.init.Materials;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBaseModdedMercury extends Block {
	
	public BlockBaseModdedMercury(final Material material, final MapColor mapColor, final String blockName) {
		super(material, mapColor);
		setBlockName(this, blockName);
		setCreativeTab(Main.creativeTab);
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(4.0f);
		this.setResistance(15.0f);
	}

	public BlockBaseModdedMercury(final Material materialIn, final String blockName) {
		this(materialIn, materialIn.getMaterialMapColor(), blockName);
	}

	public static void setBlockName(final Block block, final String blockName) {
		block.setRegistryName(Main.MODID, blockName);
		block.setUnlocalizedName(block.getRegistryName().toString());
	}
	
	
	
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }    

   
    
    
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        Item item = Item.getItemFromBlock(Materials.getMaterialByName("mercury").getBlock("ore"));
        Random rand = world instanceof World ? ((World) world).rand : RANDOM;
        int count = quantityDropped(state, fortune, rand);
        for (int i = 0; i < count; i++) {
            ret.add(new ItemStack(item, 3, this.damageDropped(state)));
        }
        return ret;
    }


}