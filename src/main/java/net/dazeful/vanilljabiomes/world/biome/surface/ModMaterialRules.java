package net.dazeful.vanilljabiomes.world.biome.surface;

import net.dazeful.vanilljabiomes.block.ModBlocks;
import net.dazeful.vanilljabiomes.world.biome.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule STONE = makeStateRule(Blocks.STONE);
    private static final MaterialRules.MaterialRule SMOOTH_BASALT = makeStateRule(Blocks.SMOOTH_BASALT);
    private static final MaterialRules.MaterialRule PALE_ROSE_QUARTZ = makeStateRule(ModBlocks.RAW_PALE_ROSE_QUARTZ_BLOCK);
    private static final MaterialRules.MaterialRule CARMINE_ROSE_QUARTZ = makeStateRule(ModBlocks.RAW_CARMINE_ROSE_QUARTZ_BLOCK);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);

        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, SMOOTH_BASALT), STONE);

        return MaterialRules.sequence(
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.ROSE_SPIRE_CAVERN),
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, CARMINE_ROSE_QUARTZ)),
                        MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, PALE_ROSE_QUARTZ)),


                // Default to a grass and dirt surface
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface)
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}