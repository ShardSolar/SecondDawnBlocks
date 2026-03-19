package net.seconddawnblocks.block.shelvesdir;


import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.Function;

public class ShelvesBlock {

    public static final Block OAK_SHELF = register("oak_shelf", ShelfBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque());
    public static final Block SPRUCE_SHELF = register("spruce_shelf", ShelfBlock::new, AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS));
    public static final Block BIRCH_SHELF = register("birch_shelf", ShelfBlock::new, AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS));
    public static final Block JUNGLE_SHELF = register("jungle_shelf", ShelfBlock::new, AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS));
    public static final Block ACACIA_SHELF = register("acacia_shelf", ShelfBlock::new, AbstractBlock.Settings.copy(Blocks.ACACIA_PLANKS));
    public static final Block CHERRY_SHELF = register("cherry_shelf", ShelfBlock::new, AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS));
    public static final Block DARK_OAK_SHELF = register("dark_oak_shelf", ShelfBlock::new, AbstractBlock.Settings.copy(Blocks.DARK_OAK_PLANKS));
    public static final Block MANGROVE_SHELF = register("mangrove_shelf", ShelfBlock::new, AbstractBlock.Settings.copy(Blocks.MANGROVE_PLANKS));
    public static final Block BAMBOO_SHELF = register("bamboo_shelf", ShelfBlock::new, AbstractBlock.Settings.copy(Blocks.BAMBOO_PLANKS));
    public static final Block CRIMSON_SHELF = register("crimson_shelf", ShelfBlock::new, AbstractBlock.Settings.copy(Blocks.CRIMSON_PLANKS));
    public static final Block WARPED_SHELF = register("warped_shelf", ShelfBlock::new, AbstractBlock.Settings.copy(Blocks.WARPED_PLANKS));

    private static Block register(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return Registry.register(Registries.BLOCK, Shelves.id(path), factory.apply(settings));
    }

    public static void initialize() {
        // intentionally empty
    }
}