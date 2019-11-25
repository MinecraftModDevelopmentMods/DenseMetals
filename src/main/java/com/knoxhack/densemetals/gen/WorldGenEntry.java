package com.knoxhack.densemetals.gen;

import com.knoxhack.densemetals.blocks.BlockDenseOre;

public class WorldGenEntry {

	protected final BlockDenseOre block;
	protected final int yMin, yMax, chance, dim;

	public WorldGenEntry(BlockDenseOre block, int yMin, int yMax, int chance, int dim) {
		this.block = block;
		this.yMin = yMin;
		this.yMax = yMax;
		this.chance = chance;
		this.dim = dim;
	}

}
