package com.knoxhack.densemetals.init;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.knoxhack.densemetals.DenseMetals;
import com.knoxhack.densemetals.DenseMetalsConfig;
import com.knoxhack.densemetals.blocks.BlockDenseOre;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

@EventBusSubscriber(modid = DenseMetals.MODID)
public class ModBlocks {

	public static final List<BlockDenseOre> DENSE_ORES = new ArrayList<>();

	@SubscribeEvent
	public static void register(RegistryEvent.Register<Block> e) {
		//Formatter::off
		e.getRegistry().registerAll(
			createDenseOre(Blocks.IRON_ORE).stats(0, 96, 10),
			createDenseOre(Blocks.COAL_ORE).stats(0, 96, 15),
			createDenseOre(Blocks.GOLD_ORE).stats(0, 35, 10),
			createDenseOre(Blocks.DIAMOND_ORE).stats(0, 30, 10),
			createDenseOre(Blocks.EMERALD_ORE).stats(0, 96, 10),
			createDenseOre(Blocks.LAPIS_ORE).stats(0, 35, 10),
			createDenseOre(Blocks.REDSTONE_ORE).stats(0, 35, 10),
			createDenseOre(Blocks.QUARTZ_ORE).stats(0, 255, 10).dim(-1),
			createDenseOre("nether_iron_ore", "oreNetherIron").stats(0, 255, 10).dim(-1),
			createDenseOre("nether_coal_ore", "oreNetherCoal").stats(0, 255, 10).dim(-1),
			createDenseOre("nether_gold_ore", "oreNetherGold").stats(0, 255, 10).dim(-1),
			createDenseOre("nether_diamond_ore", "oreNetherDiamond").stats(0, 255, 10).dim(-1),
			createDenseOre("nether_emerald_ore", "oreNetherEmerald").stats(0, 255, 10).dim(-1),
			createDenseOre("nether_lapis_ore", "oreNetherLapis").stats(0, 255, 10).dim(-1),
			createDenseOre("nether_redstone_ore", "oreNetherRedstone").stats(0, 255, 10).dim(-1),
			createDenseOre("aluminum_ore", "oreAluminum").stats(0, 0, 0),
			createDenseOre("copper_ore", "oreCopper").stats(20, 96, 10),
			createDenseOre("iridium_ore", "oreIridium").stats(0, 0, 0),
			createDenseOre("lead_ore", "oreLead").stats(0, 35, 10),
			createDenseOre("mithril_ore", "oreMithril").stats(0, 0, 0),
			createDenseOre("nickel_ore", "oreNickel").stats(0, 20, 10),
			createDenseOre("platinum_ore", "orePlatinum").stats(0, 0, 0),
			createDenseOre("silver_ore", "oreSilver").stats(0, 35, 10),
			createDenseOre("tin_ore", "oreTin").stats(20, 55, 10),
			createDenseOre("nether_aluminum_ore", "oreNetherAluminum").stats(0, 255, 0).dim(-1),
			createDenseOre("nether_copper_ore", "oreNetherCopper").stats(0, 255, 10).dim(-1),
			createDenseOre("nether_iridium_ore", "oreNetherIridium").stats(0, 255, 0).dim(-1),
			createDenseOre("nether_lead_ore", "oreNetherLead").stats(0, 255, 10).dim(-1),
			createDenseOre("nether_mithril_ore", "oreNetherMithril").stats(0, 255, 0).dim(-1),
			createDenseOre("nether_nickel_ore", "oreNetherNickel").stats(0, 255, 10).dim(-1),
			createDenseOre("nether_platinum_ore", "oreNetherPlatinum").stats(0, 255, 0).dim(-1),
			createDenseOre("nether_silver_ore", "oreNetherSilver").stats(0, 255, 10).dim(-1),
			createDenseOre("nether_tin_ore", "oreNetherTin").stats(0, 255, 10).dim(-1)
		);
		//Formatter::on
	}

	@SubscribeEvent
	public static void registerI(Register<Item> e) {
		DENSE_ORES.forEach(b -> e.getRegistry().register(new ItemBlock(b).setRegistryName(b.getRegistryName())));
	}

	private static BlockDenseOre createDenseOre(Block block) {
		return createDenseOre(block.getRegistryName().getPath(), block.getDefaultState());
	}

	private static BlockDenseOre createDenseOre(String name, IBlockState state) {
		BlockDenseOre bde = new BlockDenseOre(state);
		bde.setRegistryName("dense_" + name);
		bde.setTranslationKey("densemetals." + bde.getRegistryName().getPath());
		DENSE_ORES.add(bde);
		return bde;
	}

	@SuppressWarnings("deprecation")
	private static BlockDenseOre createDenseOre(String name, String oreName) {
		return createDenseOre(name, () -> {
			ItemStack stack = getStackFromDictWithPreference(DenseMetalsConfig.prefModid, oreName);
			if (stack.getItem() instanceof ItemBlock) {
				Block block = ((ItemBlock) stack.getItem()).getBlock();
				IBlockState state = null;
				try {
					state = block.getStateForPlacement(null, BlockPos.ORIGIN, EnumFacing.DOWN, 0, 0, 0, stack.getMetadata(), null, EnumHand.MAIN_HAND);
				} catch (Exception e) {
					state = block.getStateFromMeta(stack.getMetadata());
				}
				return state;
			}
			return null;
		});
	}

	private static BlockDenseOre createDenseOre(String name, Supplier<IBlockState> state) {
		BlockDenseOre bde = new BlockDenseOre(state);
		bde.setRegistryName("dense_" + name);
		bde.setTranslationKey("densemetals." + bde.getRegistryName().getPath());
		DENSE_ORES.add(bde);
		return bde;
	}

	public static ItemStack getStackFromDictWithPreference(String domain, String ore) {
		ItemStack stack = ItemStack.EMPTY;
		for (ItemStack is : OreDictionary.getOres(ore, false)) {
			stack = is;
			if (is.getItem().getRegistryName().getNamespace().equals(domain)) {
				break;
			}
		}
		return stack;
	}

}