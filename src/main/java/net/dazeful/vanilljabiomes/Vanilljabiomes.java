package net.dazeful.vanilljabiomes;

import net.dazeful.vanilljabiomes.block.ModBlocks;
import net.dazeful.vanilljabiomes.entity.ModEntities;
import net.dazeful.vanilljabiomes.item.ModItemGroups;
import net.dazeful.vanilljabiomes.item.ModItems;
import net.dazeful.vanilljabiomes.util.ModRegistries;
import net.dazeful.vanilljabiomes.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vanilljabiomes implements ModInitializer {
	public static final String MOD_ID = "vanilljabiomes";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		ModRegistries.registerModStuffs();
		ModWorldGeneration.generateModWorldGeneration();
		ModEntities.registerModEntities();
	}
}