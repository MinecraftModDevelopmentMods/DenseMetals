package com.knoxhack.densemetals.blocks;

import java.util.Random;

import com.knoxhack.densemetals.Main;
import com.knoxhack.densemetals.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBase extends Block {
	private final Item breakItem;
	
	public BlockBase(final Material material, final MapColor mapColor, final String blockName, Item breakItem) {
		super(material, mapColor);
		setBlockName(this, blockName);
		setCreativeTab(Main.creativeTab);
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(4.0f);
		this.setResistance(15.0f);
		this.breakItem = breakItem;
	}

	public BlockBase(final Material materialIn, final String blockName, Item breakItem) {
		this(materialIn, materialIn.getMaterialMapColor(), blockName, breakItem);
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
    	return this.breakItem;
    }
    
    
    
	@Override

    public int damageDropped(IBlockState state)
    {
        return this == ModBlocks.denseLapisBlock ? EnumDyeColor.BLUE.getDyeDamage() : 0;
    }
    
    

	@Override
    public int quantityDropped(Random random)
    {
    	
        return this == ModBlocks.denseIronBlock ? 2 + random.nextInt(3):
            (this == ModBlocks.denseDiamonBlock ? 2 + random.nextInt(3):
            (this == ModBlocks.denseRedstoneBlock ? 15 + random.nextInt(5):
            (this == ModBlocks.denseEmeraldBlock ? 2 + random.nextInt(3):
            (this == ModBlocks.denseCoalBlock ? 2 + random.nextInt(3):
            (this == ModBlocks.denseLapisBlock ? 15 + random.nextInt(5):
            (this == ModBlocks.denseGoldBlock ? 2 + random.nextInt(3):

            	
            1))))));

    	
		
		
    }

    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
        {
            int i = 0;

            if (this == ModBlocks.denseCoalBlock)
            {
                i = MathHelper.getInt(rand, 2, 4);
            }
            else if (this == ModBlocks.denseDiamonBlock)
            {
                i = MathHelper.getInt(rand, 6, 14);
            }
            else if (this == ModBlocks.denseEmeraldBlock)
            {
                i = MathHelper.getInt(rand, 6, 14);
            }
            else if (this == ModBlocks.denseLapisBlock)
            {
                i = MathHelper.getInt(rand, 4, 10);
            }


            return i;
        }
        return 0;
    }
}