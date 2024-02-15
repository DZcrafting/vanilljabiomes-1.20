package net.dazeful.vanilljabiomes.datagen;

import net.dazeful.vanilljabiomes.block.ModBlocks;
import net.dazeful.vanilljabiomes.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.tag.ItemTags;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.RAW_ROSE_QUARTZ_BLOCK);
        addDrop(ModBlocks.RAW_CARMINE_ROSE_QUARTZ_BLOCK);
        addDrop(ModBlocks.RAW_PALE_ROSE_QUARTZ_BLOCK);
        addDrop(ModBlocks.POLISHED_ROSE_QUARTZ_BLOCK);
        addDrop(ModBlocks.POLISHED_CARMINE_ROSE_QUARTZ_BLOCK);
        addDrop(ModBlocks.POLISHED_PALE_ROSE_QUARTZ_BLOCK);
        addDrop(ModBlocks.ROSE_QUARTZ_BRICKS);
        addDrop(ModBlocks.CARMINE_ROSE_QUARTZ_BRICKS);
        addDrop(ModBlocks.PALE_ROSE_QUARTZ_BRICKS);
        addDrop(ModBlocks.TRAVERTINE);




        addDrop(ModBlocks.ROSE_QUARTZ_SPIRE,
                dropsWithSilkTouch(ModBlocks.ROSE_QUARTZ_SPIRE,
                        ItemEntry.builder(ModItems.RAW_ROSE_QUARTZ)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f)))
                                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
                                .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create()
                                        .tag(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                                .alternatively(
                                        this.applyExplosionDecay(ModBlocks.ROSE_QUARTZ_SPIRE, ItemEntry.builder(ModItems.RAW_ROSE_QUARTZ)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f)))))));
        addDrop(ModBlocks.CARMINE_ROSE_QUARTZ_SPIRE,
                dropsWithSilkTouch(ModBlocks.CARMINE_ROSE_QUARTZ_SPIRE,
                        ItemEntry.builder(ModItems.RAW_CARMINE_ROSE_QUARTZ)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f)))
                                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
                                .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create()
                                        .tag(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                                .alternatively(
                                        this.applyExplosionDecay(ModBlocks.CARMINE_ROSE_QUARTZ_SPIRE, ItemEntry.builder(ModItems.RAW_CARMINE_ROSE_QUARTZ)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f)))))));
        addDrop(ModBlocks.PALE_ROSE_QUARTZ_SPIRE,
                dropsWithSilkTouch(ModBlocks.PALE_ROSE_QUARTZ_SPIRE,
                        ItemEntry.builder(ModItems.RAW_PALE_ROSE_QUARTZ)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f)))
                                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
                                .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create()
                                        .tag(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                                .alternatively(
                                        this.applyExplosionDecay(ModBlocks.POLISHED_CARMINE_ROSE_QUARTZ_BLOCK, ItemEntry.builder(ModItems.RAW_PALE_ROSE_QUARTZ)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f)))))));


    }
}
