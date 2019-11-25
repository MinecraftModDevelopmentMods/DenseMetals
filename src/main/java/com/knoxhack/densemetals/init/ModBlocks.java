package com.knoxhack.densemetals.init;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.knoxhack.densemetals.DenseMetals;
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
			createDenseOre(Blocks.IRON_ORE),
			createDenseOre(Blocks.COAL_ORE),
			createDenseOre(Blocks.GOLD_ORE),
			createDenseOre(Blocks.DIAMOND_ORE),
			createDenseOre(Blocks.EMERALD_ORE),
			createDenseOre(Blocks.LAPIS_ORE),
			createDenseOre(Blocks.REDSTONE_ORE),
			createDenseOre(Blocks.QUARTZ_ORE),
			createDenseOre("adamantine_ore", "oreAdamantine"),
			createDenseOre("antimony_ore", "oreAntimony"),
			createDenseOre("bismuth_ore", "oreBismuth"),
			createDenseOre("coldiron_ore", "oreColdiron"),
			createDenseOre("copper_ore", "oreCopper"),
			createDenseOre("lead_ore", "oreLead"),
			createDenseOre("mercury_ore", "oreMercury"),
			createDenseOre("nether_coal_ore", "oreNetherCoal"),
			createDenseOre("nether_diamond_ore", "oreNetherDiamond"),
			createDenseOre("nether_emerald_ore", "oreNetherEmerald"),
			createDenseOre("nether_gold_ore", "oreNetherGold"),
			createDenseOre("nether_iron_ore", "oreNetherIron"),
			createDenseOre("nether_lapis_ore", "oreNetherLapis"),
			createDenseOre("nether_redstone_ore", "oreNetherRedstone"),
			createDenseOre("nickel_ore", "oreNickel"),
			createDenseOre("starsteel_ore", "oreStarsteel"),
			createDenseOre("tin_ore", "oreTin"),
			createDenseOre("zinc_ore", "oreZinc")
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
			ItemStack stack = getStackFromDictWithPreference("basemetals", oreName);
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