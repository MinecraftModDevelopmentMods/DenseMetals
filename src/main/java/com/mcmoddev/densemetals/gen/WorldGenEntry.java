package com.mcmoddev.densemetals.gen;

import com.mcmoddev.densemetals.blocks.BlockDenseOre;

public class WorldGenEntry {

	protected final BlockDenseOre block;
	protected final int yMin;
	protected final int yMax;
	protected final int chance;
	protected final int dim;

	public WorldGenEntry(BlockDenseOre block, int yMin, int yMax, int chance, int dim) {
		this.block = block;
		this.yMin = yMin;
		this.yMax = yMax;
		this.chance = chance;
		this.dim = dim;
	}

}
