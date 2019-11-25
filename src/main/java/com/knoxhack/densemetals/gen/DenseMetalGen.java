package com.knoxhack.densemetals.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class DenseMetalGen implements IWorldGenerator {

	public static final List<WorldGenEntry> GENERATORS = new ArrayList<>();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		GENERATORS.forEach(e -> {
			if (e.dim == world.provider.getDimension()) {
				runGenerator(e, world, random, chunkX, chunkZ, e.chance, e.yMin, e.yMax);
			}
		});
	}

	private static void runGenerator(WorldGenEntry entry, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight) throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

		MutableBlockPos pos = new MutableBlockPos();
		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = (chunk_X << 4) + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int yMax = Math.min(y + 5 + rand.nextInt(10), maxHeight);
			int z = (chunk_Z << 4) + rand.nextInt(16);
			for (; y < yMax; y++) {
				if (world.getBlockState(pos.setPos(x, y, z)) == entry.block.getOriginal()) {
					world.setBlockState(pos, entry.block.getDefaultState(), 2);
					for (int xx = -1; xx <= 1; xx++) {
						for (int yy = -1; yy <= 1; yy++) {
							for (int zz = -1; zz <= 1; zz++) {
								if (world.getBlockState(pos.setPos(x + xx, y + yy, z + zz)) == entry.block.getOriginal()) {
									world.setBlockState(pos, entry.block.getDefaultState(), 2);
								}
							}
						}
					}
					break;
				}
			}
		}
	}

}