package com.knoxhack.densemetals.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;

public class ExplosiveBlock extends BlockOre {

    private boolean explode;

    public ExplosiveBlock() {
        super();
    }

    public Block explode() {
        explode = true;
        return this;
    }

    public boolean doesExplode() {
        return explode;
    }
}
