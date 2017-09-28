package com.knoxhack.densemetals.blocks;

import java.util.Random;

import com.knoxhack.densemetals.Main;
import com.knoxhack.densemetals.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBase extends Block {
	public BlockBase(final Material material, final MapColor mapColor, final String blockName) {
		super(material, mapColor);
		setBlockName(this, blockName);
		setCreativeTab(Main.creativeTab);
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(4.0f);
		this.setResistance(15.0f);
	}

	public BlockBase(final Material materialIn, final String blockName) {
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
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
            return this == ModBlocks.denseIronBlock ? Item.getItemFromBlock(Blocks.IRON_ORE) : 

            (this == ModBlocks.denseDiamonBlock ? Item.getItemFromBlock(Blocks.DIAMOND_ORE) :
                (this == ModBlocks.denseRedstoneBlock ? Item.getItemFromBlock(Blocks.REDSTONE_ORE) :
                    (this == ModBlocks.denseEmeraldBlock ? Item.getItemFromBlock(Blocks.EMERALD_ORE) :
                        (this == ModBlocks.denseCoalBlock ? Item.getItemFromBlock(Blocks.COAL_ORE) :
                            (this == ModBlocks.denseLapisBlock ? Item.getItemFromBlock(Blocks.LAPIS_ORE) :
                                (this == ModBlocks.denseGoldBlock ? Item.getItemFromBlock(Blocks.GOLD_ORE) :

            Item.getItemFromBlock(this)))))));
    }

    @Override
    public int quantityDropped(Random random)
    {
    	
        return this == ModBlocks.denseIronBlock ? 1 + random.nextInt(3):
            (this == ModBlocks.denseDiamonBlock ? 1 + random.nextInt(3):
            (this == ModBlocks.denseRedstoneBlock ? 1 + random.nextInt(3):
            (this == ModBlocks.denseEmeraldBlock ? 1 + random.nextInt(3):
            (this == ModBlocks.denseCoalBlock ? 1 + random.nextInt(3):
            (this == ModBlocks.denseLapisBlock ? 1 + random.nextInt(3):
            (this == ModBlocks.denseGoldBlock ? 1 + random.nextInt(3):

            	
            1))))));

    	
		
		
    }

    
}