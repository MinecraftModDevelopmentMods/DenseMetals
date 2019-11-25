package com.knoxhack.densemetals.gen;

import net.minecraft.world.gen.feature.WorldGenMinable;

public class WorldGenEntry {

	protected final WorldGenMinable gen;
	protected final int yMin, yMax, chance, dim;

	public WorldGenEntry(WorldGenMinable gen, int yMin, int yMax, int chance, int dim) {
		this.gen = gen;
		this.yMin = yMin;
		this.yMax = yMax;
		this.chance = chance;
		this.dim = dim;
	}

}
