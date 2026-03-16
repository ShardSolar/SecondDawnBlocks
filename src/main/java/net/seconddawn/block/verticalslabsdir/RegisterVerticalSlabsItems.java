package net.seconddawn.block.verticalslabsdir;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.seconddawn.SecondDawn;

import static net.seconddawn.block.ModBlocks.VERTICAL_TEST_SLAB;
import static net.seconddawn.block.ModBlocks.registerBlockItem;

public class RegisterVerticalSlabsItems implements ModInitializer {
    public static final String MOD_ID = "verticalslabs";

    @Override
    public void onInitialize() {
        registerBlockItem("vertical_oak_slab", VERTICAL_TEST_SLAB);

    }


    private static Item register(String path, Block block) {
        return Registry.register(
                Registries.ITEM,
                SecondDawn.id(path),
                new BlockItem(block, new Item.Settings())
        );

    }
}