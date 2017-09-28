package com.knoxhack.densemetals.world;

import java.util.Random;

import com.knoxhack.densemetals.Config;
import com.knoxhack.densemetals.init.ModBlocks;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGen implements IWorldGenerator {

	private WorldGenerator denseIronOre;		
	private WorldGenerator denseDiamonBlock;		
	private WorldGenerator denseRedstoneBlock;		
	private WorldGenerator denseEmeraldBlock;		
	private WorldGenerator denseCoalBlock;		
	private WorldGenerator denseLapisBlock;		
	private WorldGenerator denseGoldBlock;		

	
	public WorldGen() {
		denseIronOre = new WorldGenMinable(ModBlocks.denseIronBlock.getDefaultState(), 20, BlockMatcher.forBlock(Blocks.IRON_ORE));
		denseDiamonBlock = new WorldGenMinable(ModBlocks.denseDiamonBlock.getDefaultState(), 30, BlockMatcher.forBlock(Blocks.DIAMOND_ORE));
		denseRedstoneBlock = new WorldGenMinable(ModBlocks.denseRedstoneBlock.getDefaultState(), 30, BlockMatcher.forBlock(Blocks.REDSTONE_ORE));
		denseEmeraldBlock = new WorldGenMinable(ModBlocks.denseEmeraldBlock.getDefaultState(), 30, BlockMatcher.forBlock(Blocks.EMERALD_ORE));
		denseCoalBlock = new WorldGenMinable(ModBlocks.denseCoalBlock.getDefaultState(), 30, BlockMatcher.forBlock(Blocks.COAL_ORE));
		denseLapisBlock = new WorldGenMinable(ModBlocks.denseLapisBlock.getDefaultState(), 30, BlockMatcher.forBlock(Blocks.LAPIS_ORE));
		denseGoldBlock = new WorldGenMinable(ModBlocks.denseGoldBlock.getDefaultState(), 30, BlockMatcher.forBlock(Blocks.GOLD_ORE));


	}
    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
	    if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
	        throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

	    int heightDiff = maxHeight - minHeight + 1;
	    for (int i = 0; i < chancesToSpawn; i ++) {
	        int x = chunk_X * 16 + rand.nextInt(16);
	        int y = minHeight + rand.nextInt(heightDiff);
	        int z = chunk_Z * 16 + rand.nextInt(16);
	        generator.generate(world, rand, new BlockPos(x, y, z));
	    }
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		  switch (world.provider.getDimension()) {
		    case 0: //
		    	
		    	
		    	if (Config.enabledInternalWorldGen) {
	                if (Config.enabledVanillaDenseOres){
	                if (Config.enabledDenseIronOre) {
		        this.runGenerator(this.denseIronOre, world, random, chunkX, chunkZ, 20, 0, 120);}
	                if (Config.enabledDenseDiamondOre) {
		        this.runGenerator(this.denseDiamonBlock, world, random, chunkX, chunkZ, 30, 0, 30);}
	                if (Config.enabledDenseRedstoneOre) {
		        this.runGenerator(this.denseRedstoneBlock, world, random, chunkX, chunkZ, 30, 0, 35);}
	                if (Config.enabledDenseEmeraldOre) {
		        this.runGenerator(this.denseEmeraldBlock, world, random, chunkX, chunkZ, 30, 0, 100);}
	                if (Config.enabledDenseCoalOre) {
		        this.runGenerator(this.denseCoalBlock, world, random, chunkX, chunkZ, 20, 0, 120);}
	                if (Config.enabledDenseLapisOre) {
		        this.runGenerator(this.denseLapisBlock, world, random, chunkX, chunkZ, 30, 0, 35);}
	                if (Config.enabledDenseGoldOre) {
		        this.runGenerator(this.denseGoldBlock, world, random, chunkX, chunkZ, 30, 0, 35);}
	                }
		    	}
		        
		        
		        break;
			case -1: // Nether
				// Vanilla


				

				break;
			case 1: // End
				break;
			}
	}
}
