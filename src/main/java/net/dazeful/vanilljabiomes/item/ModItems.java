package net.dazeful.vanilljabiomes.item;

import net.dazeful.vanilljabiomes.Vanilljabiomes;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item RAW_ROSE_QUARTZ = registerItem("raw_rose_quartz", new Item(new FabricItemSettings()));
    public static final Item POLISHED_ROSE_QUARTZ = registerItem("polished_rose_quartz", new Item(new FabricItemSettings()));
    public static final Item RAW_CARMINE_ROSE_QUARTZ = registerItem("raw_carmine_rose_quartz", new Item(new FabricItemSettings()));
    public static final Item POLISHED_CARMINE_ROSE_QUARTZ = registerItem("polished_carmine_rose_quartz", new Item(new FabricItemSettings()));







    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(RAW_ROSE_QUARTZ);
    }
    private static Item registerItem(String name,Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Vanilljabiomes.MOD_ID, name), item);
    }

    public static void registerModItems () {
        Vanilljabiomes.LOGGER.info("Registering Mod Items for " + Vanilljabiomes.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
