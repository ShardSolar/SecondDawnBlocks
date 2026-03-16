package net.seconddawn.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import net.seconddawn.block.shelvesdir.ShelvesBlock;

import java.util.concurrent.CompletableFuture;

public class ShelvesLootProvider extends FabricBlockLootTableProvider {

    public ShelvesLootProvider(
            FabricDataOutput output,
            CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    public void generate() {
        // Drops the block + drops inventory contents (and keeps custom name if applicable)
        addDrop(ShelvesBlock.OAK_SHELF, this::nameableContainerDrops);
        addDrop(ShelvesBlock.SPRUCE_SHELF, this::nameableContainerDrops);
        addDrop(ShelvesBlock.BIRCH_SHELF, this::nameableContainerDrops);
        addDrop(ShelvesBlock.JUNGLE_SHELF, this::nameableContainerDrops);
        addDrop(ShelvesBlock.ACACIA_SHELF, this::nameableContainerDrops);
        addDrop(ShelvesBlock.CHERRY_SHELF, this::nameableContainerDrops);
        addDrop(ShelvesBlock.DARK_OAK_SHELF, this::nameableContainerDrops);
        addDrop(ShelvesBlock.MANGROVE_SHELF, this::nameableContainerDrops);
        addDrop(ShelvesBlock.BAMBOO_SHELF, this::nameableContainerDrops);
        addDrop(ShelvesBlock.CRIMSON_SHELF, this::nameableContainerDrops);
        addDrop(ShelvesBlock.WARPED_SHELF, this::nameableContainerDrops);
    }
}