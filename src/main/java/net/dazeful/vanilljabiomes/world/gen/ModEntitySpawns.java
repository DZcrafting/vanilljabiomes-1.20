package net.dazeful.vanilljabiomes.world.gen;

import net.dazeful.vanilljabiomes.entity.ModEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawns {
    public static void addSpawns() {
        SpawnRestriction.register(ModEntities.ROSEBETTA, SpawnRestriction.Location.IN_WATER,
                Heightmap.Type.OCEAN_FLOOR, FishEntity::canSpawn);
    }
}
