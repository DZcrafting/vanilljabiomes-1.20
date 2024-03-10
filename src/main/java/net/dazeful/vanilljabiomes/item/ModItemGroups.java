package net.dazeful.vanilljabiomes.item;

import net.dazeful.vanilljabiomes.Vanilljabiomes;
import net.dazeful.vanilljabiomes.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup VANILLJA_ITEMGROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Vanilljabiomes.MOD_ID, "vanillja_item_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.vanillja_item_group"))
                    .icon(() -> new ItemStack(ModItems.RAW_ROSE_QUARTZ)).entries((displayContext, entries) -> {

                        entries.add(ModItems.RAW_ROSE_QUARTZ);
                        entries.add(ModItems.POLISHED_ROSE_QUARTZ);
                        entries.add(ModBlocks.ROSE_QUARTZ_SPIRE);
                        entries.add(ModBlocks.RAW_ROSE_QUARTZ_BLOCK);
                        entries.add(ModBlocks.POLISHED_ROSE_QUARTZ_BLOCK);
                        entries.add(ModBlocks.POLISHED_ROSE_QUARTZ_STAIRS);
                        entries.add(ModBlocks.POLISHED_ROSE_QUARTZ_SLAB);
                        entries.add(ModBlocks.ROSE_QUARTZ_BRICKS);

                        entries.add(ModItems.RAW_CARMINE_ROSE_QUARTZ);
                        entries.add(ModItems.POLISHED_CARMINE_ROSE_QUARTZ);
                        entries.add(ModBlocks.CARMINE_ROSE_QUARTZ_SPIRE);
                        entries.add(ModBlocks.RAW_CARMINE_ROSE_QUARTZ_BLOCK);
                        entries.add(ModBlocks.POLISHED_CARMINE_ROSE_QUARTZ_BLOCK);
                        entries.add(ModBlocks.POLISHED_CARMINE_ROSE_QUARTZ_STAIRS);
                        entries.add(ModBlocks.POLISHED_CARMINE_ROSE_QUARTZ_SLAB);
                        entries.add(ModBlocks.CARMINE_ROSE_QUARTZ_BRICKS);

                        entries.add(ModItems.RAW_PALE_ROSE_QUARTZ);
                        entries.add(ModItems.POLISHED_PALE_ROSE_QUARTZ);
                        entries.add(ModBlocks.PALE_ROSE_QUARTZ_SPIRE);
                        entries.add(ModBlocks.RAW_PALE_ROSE_QUARTZ_BLOCK);
                        entries.add(ModBlocks.POLISHED_PALE_ROSE_QUARTZ_BLOCK);
                        entries.add(ModBlocks.POLISHED_PALE_ROSE_QUARTZ_STAIRS);
                        entries.add(ModBlocks.POLISHED_PALE_ROSE_QUARTZ_SLAB);
                        entries.add(ModBlocks.PALE_ROSE_QUARTZ_BRICKS);

                        entries.add(ModBlocks.QUARTZITE);
                        entries.add(ModBlocks.TRAVERTINE);
                        entries.add(ModItems.ROSE_BETTA_BUCKET);
                        entries.add(ModItems.ROSE_BETTA_SPAWN_EGG);

                        entries.add(ModBlocks.SCHIST);
                        entries.add(ModBlocks.SULFUR_BLOCK);
                        entries.add(ModBlocks.SULFUR_VENT);

                    }).build());


    public static void registerItemGroups() {
        Vanilljabiomes.LOGGER.info("Registering Item Groups for " + Vanilljabiomes.MOD_ID);
    }
}
