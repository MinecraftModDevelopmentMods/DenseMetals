package com.knoxhack.densemetals.world;

import java.util.Random;
import com.knoxhack.densemetals.Config;
import com.knoxhack.densemetals.init.ModBlocks;
import com.mcmoddev.lib.init.Materials;

import net.minecraft.block.Block;
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

    private WorldGenerator
        denseIronOre,
        denseDiamonBlock,
        denseRedstoneBlock,
        denseEmeraldBlock,
        denseCoalBlock,
        denseLapisBlock,
        denseGoldBlock,
        denseAdamantineBlock,
        denseAntimonyBlock,
        denseBismuthBlock,
        denseCopperBlock,
        denseColdironBlock,
        denseLeadBlock,
        denseNickelBlock,
        denseMercuryBlock,
        denseStarsteelBlock,
        denseTinBlock,
        denseZincBlock;

    public WorldGen() {
        denseIronOre = getWGM(ModBlocks.denseIronBlock, Blocks.IRON_ORE);
        denseDiamonBlock = getWGM(ModBlocks.denseDiamonBlock, Blocks.DIAMOND_ORE);
        denseRedstoneBlock = getWGM(ModBlocks.denseRedstoneBlock, Blocks.REDSTONE_ORE);
        denseEmeraldBlock = getWGM(ModBlocks.denseEmeraldBlock, Blocks.EMERALD_ORE);
        denseCoalBlock = getWGM(ModBlocks.denseCoalBlock, 30, Blocks.COAL_ORE);
        denseLapisBlock = getWGM(ModBlocks.denseLapisBlock, Blocks.LAPIS_ORE);
        denseGoldBlock = getWGM(ModBlocks.denseGoldBlock, Blocks.GOLD_ORE);

        if (Loader.isModLoaded("basemetals"))
            if (Config.enabledBaseMetalsDenseOres) {
                denseAdamantineBlock = getWGM(ModBlocks.denseAdamantineBlock, "adamntine");
                denseAntimonyBlock = getWGM(ModBlocks.denseAntimonyBlock, "antimony");
                denseBismuthBlock = getWGM(ModBlocks.denseBismuthBlock, "bismuth");
                denseCopperBlock = getWGM(ModBlocks.denseCopperBlock, "copper");
                denseColdironBlock = getWGM(ModBlocks.denseColdironBlock, "coldiron");
                denseLeadBlock = getWGM(ModBlocks.denseLeadBlock, "lead");
                denseNickelBlock = getWGM(ModBlocks.denseNickelBlock, "nickel");
                denseMercuryBlock = getWGM(ModBlocks.denseMercuryBlock, "mercury");
                denseStarsteelBlock = getWGM(ModBlocks.denseStarsteelBlock, "starsteel");
                denseTinBlock = getWGM(ModBlocks.denseTinBlock, "tin");
                denseZincBlock = getWGM(ModBlocks.denseZincBlock, "zinc");
            }
    }

    private WorldGenMinable getWGM(Block block, int count, Block blockType) {
        return new WorldGenMinable(block.getDefaultState(), count, BlockMatcher.forBlock(blockType));
    }

    private WorldGenMinable getWGM(Block block, Block blockType) {
        return getWGM(block, 35, blockType);
    }

    private WorldGenMinable getWGM(Block block, String name) {
        return getWGM(block, 35, Materials.getMaterialByName(name).getBlock("ore"));
    }

    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z,
            int chancesToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i++) {
            int x = chunk_X * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunk_Z * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
        case 0: //

            if (Config.enabledInternalWorldGen) {
                if (Config.enabledVanillaDenseOres) {
                    if (Config.enabledDenseIronOre)
                        runGenerator(denseIronOre, world, random, chunkX, chunkZ, 20, 0, 96);
                    if (Config.enabledDenseDiamondOre)
                        runGenerator(denseDiamonBlock, world, random, chunkX, chunkZ, 30, 0, 30);
                    if (Config.enabledDenseRedstoneOre)
                        runGenerator(denseRedstoneBlock, world, random, chunkX, chunkZ, 30, 0, 35);
                    if (Config.enabledDenseEmeraldOre)
                        runGenerator(denseEmeraldBlock, world, random, chunkX, chunkZ, 30, 0, 96);
                    if (Config.enabledDenseCoalOre)
                        runGenerator(denseCoalBlock, world, random, chunkX, chunkZ, 20, 0, 96);
                    if (Config.enabledDenseLapisOre)
                        runGenerator(denseLapisBlock, world, random, chunkX, chunkZ, 30, 0, 35);
                    if (Config.enabledDenseGoldOre)
                        runGenerator(denseGoldBlock, world, random, chunkX, chunkZ, 30, 0, 35);
                }
                if (Config.enabledInternalWorldGen)
                    if (Loader.isModLoaded("basemetals"))
                        if (Config.enabledBaseMetalsDenseOres) {
                            runGenerator(denseAntimonyBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseBismuthBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseCopperBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseLeadBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseNickelBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseMercuryBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseTinBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseZincBlock, world, random, chunkX, chunkZ, 100, 0, 96);

                        }
            }
            break;
        case 6: //

            if (Config.enabledInternalWorldGen) {
                if (Config.enabledVanillaDenseOres) {
                    if (Config.enabledDenseIronOre)
                        runGenerator(denseIronOre, world, random, chunkX, chunkZ, 20, 0, 120);
                    if (Config.enabledDenseDiamondOre)
                        runGenerator(denseDiamonBlock, world, random, chunkX, chunkZ, 30, 0, 30);
                    if (Config.enabledDenseRedstoneOre)
                        runGenerator(denseRedstoneBlock, world, random, chunkX, chunkZ, 30, 0, 35);
                    if (Config.enabledDenseEmeraldOre)
                        runGenerator(denseEmeraldBlock, world, random, chunkX, chunkZ, 30, 0, 100);
                    if (Config.enabledDenseCoalOre)
                        runGenerator(denseCoalBlock, world, random, chunkX, chunkZ, 20, 0, 120);
                    if (Config.enabledDenseLapisOre)
                        runGenerator(denseLapisBlock, world, random, chunkX, chunkZ, 30, 0, 35);
                    if (Config.enabledDenseGoldOre)
                        runGenerator(denseGoldBlock, world, random, chunkX, chunkZ, 30, 0, 35);
                }
                if (Config.enabledInternalWorldGen)
                    if (Loader.isModLoaded("basemetals"))
                        if (Config.enabledBaseMetalsDenseOres) {
                            runGenerator(denseAntimonyBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseBismuthBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseCopperBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseLeadBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseNickelBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseMercuryBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseTinBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseZincBlock, world, random, chunkX, chunkZ, 100, 0, 96);

                        }
            }
            break;
        case 69: //

            if (Config.enabledInternalWorldGen) {
                if (Config.enabledVanillaDenseOres) {
                    if (Config.enabledDenseIronOre)
                        runGenerator(denseIronOre, world, random, chunkX, chunkZ, 20, 0, 120);
                    if (Config.enabledDenseDiamondOre)
                        runGenerator(denseDiamonBlock, world, random, chunkX, chunkZ, 30, 0, 30);
                    if (Config.enabledDenseRedstoneOre)
                        runGenerator(denseRedstoneBlock, world, random, chunkX, chunkZ, 30, 0, 35);
                    if (Config.enabledDenseEmeraldOre)
                        runGenerator(denseEmeraldBlock, world, random, chunkX, chunkZ, 30, 0, 100);
                    if (Config.enabledDenseCoalOre)
                        runGenerator(denseCoalBlock, world, random, chunkX, chunkZ, 20, 0, 120);
                    if (Config.enabledDenseLapisOre)
                        runGenerator(denseLapisBlock, world, random, chunkX, chunkZ, 30, 0, 35);
                    if (Config.enabledDenseGoldOre)
                        runGenerator(denseGoldBlock, world, random, chunkX, chunkZ, 30, 0, 35);
                }
                if (Config.enabledInternalWorldGen)
                    if (Loader.isModLoaded("basemetals"))
                        if (Config.enabledBaseMetalsDenseOres) {
                            runGenerator(denseAntimonyBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseBismuthBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseCopperBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseLeadBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseNickelBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseMercuryBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseTinBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                            runGenerator(denseZincBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                        }
            }
            break;
        case -1: // Nether
            // Vanilla
            if (Config.enabledInternalWorldGen)
                if (Loader.isModLoaded("basemetals"))
                    if (Config.enabledBaseMetalsDenseOres) {
                        runGenerator(denseAdamantineBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                        runGenerator(denseColdironBlock, world, random, chunkX, chunkZ, 100, 0, 96);
                    }
            break;
        case 1: // End
            if (Config.enabledInternalWorldGen)
                if (Loader.isModLoaded("basemetals"))
                    if (Config.enabledBaseMetalsDenseOres)
                        runGenerator(denseStarsteelBlock, world, random, chunkX, chunkZ, 100, 0, 96);
            break;
        }
    }
}