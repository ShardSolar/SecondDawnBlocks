package net.seconddawnblocks.block.shelvesdir;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.seconddawnblocks.SecondDawnBlocks;

public class ShelvesItems {

    public static final Item OAK_SHELF = register("oak_shelf", ShelvesBlock.OAK_SHELF);
    public static final Item SPRUCE_SHELF = register("spruce_shelf", ShelvesBlock.SPRUCE_SHELF);
    public static final Item BIRCH_SHELF = register("birch_shelf", ShelvesBlock.BIRCH_SHELF);
    public static final Item JUNGLE_SHELF = register("jungle_shelf", ShelvesBlock.JUNGLE_SHELF);
    public static final Item ACACIA_SHELF = register("acacia_shelf", ShelvesBlock.ACACIA_SHELF);
    public static final Item CHERRY_SHELF = register("cherry_shelf", ShelvesBlock.CHERRY_SHELF);
    public static final Item DARK_OAK_SHELF = register("dark_oak_shelf", ShelvesBlock.DARK_OAK_SHELF);
    public static final Item MANGROVE_SHELF = register("mangrove_shelf", ShelvesBlock.MANGROVE_SHELF);
    public static final Item BAMBOO_SHELF = register("bamboo_shelf", ShelvesBlock.BAMBOO_SHELF);
    public static final Item CRIMSON_SHELF = register("crimson_shelf", ShelvesBlock.CRIMSON_SHELF);
    public static final Item WARPED_SHELF = register("warped_shelf", ShelvesBlock.WARPED_SHELF);

    private static Item register(String path, Block block) {
        return Registry.register(
                Registries.ITEM,
                SecondDawnBlocks.id(path),
                new BlockItem(block, new Item.Settings())
        );
    }

    public static void initialize() {
        // Intentionally empty - forces class loading
    }
}
