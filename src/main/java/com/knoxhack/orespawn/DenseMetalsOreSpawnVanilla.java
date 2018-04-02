package com.knoxhack.orespawn;

import com.mcmoddev.orespawn.api.os3.OS3API;
import com.mcmoddev.orespawn.api.plugin.IOreSpawnPlugin;
import com.mcmoddev.orespawn.api.plugin.OreSpawnPlugin;
import com.mcmoddev.lib.data.SharedStrings;


@OreSpawnPlugin(modid = "densemetals_vanilla", resourcePath = SharedStrings.ORESPAWN_MODID)
public class DenseMetalsOreSpawnVanilla implements IOreSpawnPlugin {

    @Override
    public void register(final OS3API apiInterface) {
        // nothing for us to do - all of our ores are in the
        // jar and the code handles that
    }
}