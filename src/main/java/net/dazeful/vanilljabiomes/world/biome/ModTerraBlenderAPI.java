package net.dazeful.vanilljabiomes.world.biome;

import net.dazeful.vanilljabiomes.Vanilljabiomes;
import net.dazeful.vanilljabiomes.world.biome.surface.ModMaterialRules;
import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerraBlenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(new Identifier(Vanilljabiomes.MOD_ID, "overworld"), 3));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Vanilljabiomes.MOD_ID, ModMaterialRules.makeRules());
    }
}
