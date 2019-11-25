package com.knoxhack.densemetals;

import com.knoxhack.densemetals.init.ModBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabMain extends CreativeTabs {

	public CreativeTabMain() {
		super("densemetals");
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModBlocks.DENSE_ORES.get(0));
	}
}