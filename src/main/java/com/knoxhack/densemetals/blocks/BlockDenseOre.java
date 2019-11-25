package com.knoxhack.densemetals.blocks;

import java.util.function.Supplier;

import com.knoxhack.densemetals.DenseMetals;
import com.knoxhack.densemetals.DenseMetalsConfig;
import com.knoxhack.densemetals.util.SingleBlockAccess;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDenseOre extends Block {

	private IBlockState original;
	private Supplier<IBlockState> supplier;
	private boolean failed = false;
	private int yMin, yMax, chance, dim = 0;

	public BlockDenseOre(IBlockState original) {
		super(original.getMaterial(), original.getBlock().blockMapColor);
		setCreativeTab(DenseMetals.TAB);
		this.setHarvestLevel("pickaxe", original.getBlock().getHarvestLevel(original));
		setHardness(original.getBlock().blockHardness);
		setResistance(original.getBlock().blockResistance);
		this.original = original;
	}

	public BlockDenseOre(Supplier<IBlockState> original) {
		super(Material.ROCK);
		setCreativeTab(DenseMetals.TAB);
		this.supplier = original;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		if (resolve()) {
			SingleBlockAccess sba = new SingleBlockAccess(original, pos);
			for (int i = 0; i < DenseMetalsConfig.denseOreValue; i++)
				original.getBlock().getDrops(drops, sba, pos, original, fortune);
		}
	}

	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		if (resolve()) {
			SingleBlockAccess sba = new SingleBlockAccess(original, pos);
			return original.getBlock().getExpDrop(original, sba, pos, fortune) * DenseMetalsConfig.denseOreValue;
		}
		return 0;
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	public IBlockState getOriginal() {
		return original;
	}

	public int getYMin() {
		return yMin;
	}

	public int getYMax() {
		return yMax;
	}

	public int getChance() {
		return chance;
	}

	public int getDim() {
		return dim;
	}

	public BlockDenseOre stats(int yMin, int yMax, int chance) {
		this.yMin = yMin;
		this.yMax = yMax;
		this.chance = chance;
		return this;
	}

	public BlockDenseOre dim(int dim) {
		this.dim = dim;
		return this;
	}

	public boolean resolve() {
		if (original != null) return true;
		else if (failed) return false;
		else {
			original = supplier.get();
			if (original != null) {
				this.setHarvestLevel("pickaxe", original.getBlock().getHarvestLevel(original));
				setHardness(original.getBlock().blockHardness);
				setResistance(original.getBlock().blockResistance);
				return true;
			} else {
				failed = true;
				return false;
			}
		}
	}
}