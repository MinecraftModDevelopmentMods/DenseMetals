package com.knoxhack.densemetals.world;

import java.util.Random;
import com.knoxhack.densemetals.Config;
import com.knoxhack.densemetals.init.ModBlocks;
import com.mcmoddev.lib.init.Materials;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Loader;

public class WorldGen implements IWorldGenerator {

	private WorldGenerator denseIronOre;		
	private WorldGenerator denseDiamonBlock;		
	private WorldGenerator denseRedstoneBlock;		
	private WorldGenerator denseEmeraldBlock;		
	private WorldGenerator denseCoalBlock;		
	private WorldGenerator denseLapisBlock;		
	private WorldGenerator denseGoldBlock;		
	private WorldGenerator denseAdamantineBlock;		
	private WorldGenerator denseAntimonyBlock;		
	private WorldGenerator denseBismuthBlock;		
	private WorldGenerator denseCopperBlock;		
	private WorldGenerator denseColdironBlock;		
	private WorldGenerator denseLeadBlock;		
	private WorldGenerator denseNickelBlock;		
	private WorldGenerator denseMercuryBlock;		
	private WorldGenerator denseStarsteelBlock;		
	private WorldGenerator denseTinBlock;	
	private WorldGenerator denseZincBlock;		



	
	public WorldGen() {
		denseIronOre = new WorldGenMinable(ModBlocks.denseIronBlock.getDefaultState(), 35, BlockMatcher.forBlock(Blocks.IRON_ORE));
		denseDiamonBlock = new WorldGenMinable(ModBlocks.denseDiamonBlock.getDefaultState(), 35, BlockMatcher.forBlock(Blocks.DIAMOND_ORE));
		denseRedstoneBlock = new WorldGenMinable(ModBlocks.denseRedstoneBlock.getDefaultState(), 35, BlockMatcher.forBlock(Blocks.REDSTONE_ORE));
		denseEmeraldBlock = new WorldGenMinable(ModBlocks.denseEmeraldBlock.getDefaultState(), 35, BlockMatcher.forBlock(Blocks.EMERALD_ORE));
		denseCoalBlock = new WorldGenMinable(ModBlocks.denseCoalBlock.getDefaultState(), 30, BlockMatcher.forBlock(Blocks.COAL_ORE));
		denseLapisBlock = new WorldGenMinable(ModBlocks.denseLapisBlock.getDefaultState(), 35, BlockMatcher.forBlock(Blocks.LAPIS_ORE));
		denseGoldBlock = new WorldGenMinable(ModBlocks.denseGoldBlock.getDefaultState(), 35, BlockMatcher.forBlock(Blocks.GOLD_ORE));
        if (Loader.isModLoaded("basemetals")) {
        	if (Config.enabledBaseMetalsDenseOres){
        {
		denseAdamantineBlock = new WorldGenMinable(ModBlocks.denseAdamantineBlock.getDefaultState(), 35, BlockMatcher.forBlock( Materials.getMaterialByName("adamntine").getBlock("ore")));
		denseAntimonyBlock = new WorldGenMinable(ModBlocks.denseAntimonyBlock.getDefaultState(), 35, BlockMatcher.forBlock( Materials.getMaterialByName("antimony").getBlock("ore")));
		denseBismuthBlock = new WorldGenMinable(ModBlocks.denseBismuthBlock.getDefaultState(), 35, BlockMatcher.forBlock( Materials.getMaterialByName("bismuth").getBlock("ore")));
		denseCopperBlock = new WorldGenMinable(ModBlocks.denseCopperBlock.getDefaultState(), 35, BlockMatcher.forBlock( Materials.getMaterialByName("copper").getBlock("ore")));
		denseColdironBlock = new WorldGenMinable(ModBlocks.denseColdironBlock.getDefaultState(), 35, BlockMatcher.forBlock( Materials.getMaterialByName("coldiron").getBlock("ore")));
		denseLeadBlock = new WorldGenMinable(ModBlocks.denseLeadBlock.getDefaultState(), 35, BlockMatcher.forBlock( Materials.getMaterialByName("lead").getBlock("ore")));
		denseNickelBlock = new WorldGenMinable(ModBlocks.denseNickelBlock.getDefaultState(), 35, BlockMatcher.forBlock( Materials.getMaterialByName("nickel").getBlock("ore")));
		denseMercuryBlock = new WorldGenMinable(ModBlocks.denseMercuryBlock.getDefaultState(), 35, BlockMatcher.forBlock( Materials.getMaterialByName("mercury").getBlock("ore")));
		denseStarsteelBlock = new WorldGenMinable(ModBlocks.denseStarsteelBlock.getDefaultState(), 35, BlockMatcher.forBlock( Materials.getMaterialByName("starsteel").getBlock("ore")));
		denseTinBlock = new WorldGenMinable(ModBlocks.denseTinBlock.getDefaultState(), 35, BlockMatcher.forBlock( Materials.getMaterialByName("tin").getBlock("ore")));
		denseZincBlock = new WorldGenMinable(ModBlocks.denseZincBlock.getDefaultState(), 35, BlockMatcher.forBlock( Materials.getMaterialByName("zinc").getBlock("ore")));
        }
        }
        	}
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
		        this.runGenerator(this.denseIronOre, world, random, chunkX, chunkZ, 20, 0, 96);}
	                if (Config.enabledDenseDiamondOre) {
		        this.runGenerator(this.denseDiamonBlock, world, random, chunkX, chunkZ, 30, 0, 30);}
	                if (Config.enabledDenseRedstoneOre) {
		        this.runGenerator(this.denseRedstoneBlock, world, random, chunkX, chunkZ, 30, 0, 35);}
	                if (Config.enabledDenseEmeraldOre) {
		        this.runGenerator(this.denseEmeraldBlock, world, random, chunkX, chunkZ, 30, 0, 96);}
	                if (Config.enabledDenseCoalOre) {
		        this.runGenerator(this.denseCoalBlock, world, random, chunkX, chunkZ, 20, 0, 96);}
	                if (Config.enabledDenseLapisOre) {
		        this.runGenerator(this.denseLapisBlock, world, random, chunkX, chunkZ, 30, 0, 35);}
	                if (Config.enabledDenseGoldOre) {
		        this.runGenerator(this.denseGoldBlock, world, random, chunkX, chunkZ, 30, 0, 35);}
	                
	                
	                
	                
	                }
	                if (Config.enabledInternalWorldGen) {
		                if (Config.enabledBaseMetalsDenseOres){
	    		        this.runGenerator(this.denseAntimonyBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseBismuthBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseCopperBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseLeadBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseNickelBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseMercuryBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseTinBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseZincBlock, world, random, chunkX, chunkZ, 100, 0, 96);

	    	               	                }  
	                }

		    	}
		        
		        
		        break;
		        
		        
		        
		        
		    case 6: //
		    	
		    	
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
	                
	                
	                if (Config.enabledInternalWorldGen) {
		                if (Config.enabledBaseMetalsDenseOres){
	    		        this.runGenerator(this.denseAntimonyBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseBismuthBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseCopperBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseLeadBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseNickelBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseMercuryBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseTinBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseZincBlock, world, random, chunkX, chunkZ, 100, 0, 96);

	    	               	                } 
	                
	                }
	                
		    	}
		        
		        
		        break;
		    case 69: //
		    	
		    	
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
	                
	                
	                if (Config.enabledInternalWorldGen) {
		                if (Config.enabledBaseMetalsDenseOres){
	    		        this.runGenerator(this.denseAntimonyBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseBismuthBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseCopperBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseLeadBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseNickelBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseMercuryBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseTinBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	    		        this.runGenerator(this.denseZincBlock, world, random, chunkX, chunkZ, 100, 0, 96);

	    	               	                } 
	                
	                }
	                
		    	}
		        
		        
		        break;
			case -1: // Nether
				// Vanilla
                if (Config.enabledInternalWorldGen) {
	                if (Config.enabledBaseMetalsDenseOres){
		        this.runGenerator(this.denseAdamantineBlock, world, random, chunkX, chunkZ, 100, 0, 96);
		        this.runGenerator(this.denseColdironBlock, world, random, chunkX, chunkZ, 100, 0, 96);
	                }
                }

				break;
			case 1: // End
				
                if (Config.enabledInternalWorldGen) {
	                if (Config.enabledBaseMetalsDenseOres){
		        this.runGenerator(this.denseStarsteelBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                }
                }
				
				break;
			}
	}
}
