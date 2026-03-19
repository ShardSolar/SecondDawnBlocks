package net.seconddawn.block.shelvesdir;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.seconddawn.SecondDawnBlocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Shelves {

    private Shelves() {}

    public static final Logger LOGGER = LoggerFactory.getLogger(SecondDawnBlocks.MOD_ID);

    // IMPORTANT: use your main modid so everything is assets/shipyardsmod and data/shipyardsmod
    public static Identifier id(String path) {
        return SecondDawnBlocks.id(path);
    }

    /** Call this from ShipyardsMod.onInitialize() */
    public static void register() {
        ShelvesBlock.initialize();
        ShelvesBlockEntities.initialize();
        ShelvesItems.initialize();

        // Flammables
        FlammableBlockRegistry flammables = FlammableBlockRegistry.getDefaultInstance();
        flammables.add(ShelvesBlock.OAK_SHELF, 5, 5);
        flammables.add(ShelvesBlock.SPRUCE_SHELF, 5, 5);
        flammables.add(ShelvesBlock.BIRCH_SHELF, 5, 5);
        flammables.add(ShelvesBlock.JUNGLE_SHELF, 5, 5);
        flammables.add(ShelvesBlock.ACACIA_SHELF, 5, 5);
        flammables.add(ShelvesBlock.CHERRY_SHELF, 5, 5);
        flammables.add(ShelvesBlock.DARK_OAK_SHELF, 5, 5);
        flammables.add(ShelvesBlock.MANGROVE_SHELF, 5, 5);
        flammables.add(ShelvesBlock.BAMBOO_SHELF, 5, 5);

        // Fuel tag (ticks)
        FuelRegistry.INSTANCE.add(ShelvesItemTag.SHELVES, 450);

        // Creative tab insertion
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries ->
                entries.addAfter(Items.CHISELED_BOOKSHELF,
                        ShelvesItems.OAK_SHELF,
                        ShelvesItems.SPRUCE_SHELF,
                        ShelvesItems.BIRCH_SHELF,
                        ShelvesItems.JUNGLE_SHELF,
                        ShelvesItems.ACACIA_SHELF,
                        ShelvesItems.CHERRY_SHELF,
                        ShelvesItems.DARK_OAK_SHELF,
                        ShelvesItems.MANGROVE_SHELF,
                        ShelvesItems.BAMBOO_SHELF,
                        ShelvesItems.CRIMSON_SHELF,
                        ShelvesItems.WARPED_SHELF
                )
        );
    }
}