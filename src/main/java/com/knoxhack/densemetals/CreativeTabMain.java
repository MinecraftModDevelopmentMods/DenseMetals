package com.knoxhack.densemetals;

import com.knoxhack.densemetals.init.ModBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabMain extends CreativeTabs {
    public CreativeTabMain() {
        super("densemetals");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack createIcon() {
        return new ItemStack(ModBlocks.denseDiamonBlock);
    }
}