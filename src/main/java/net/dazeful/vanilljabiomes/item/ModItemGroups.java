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
                        entries.add(ModBlocks.RAW_ROSE_QUARTZ_BLOCK);
                        entries.add(ModItems.POLISHED_ROSE_QUARTZ);

                    }).build());


    public static void registerItemGroups() {
        Vanilljabiomes.LOGGER.info("Registering Item Groups for " + Vanilljabiomes.MOD_ID);
    }
}
