package net.seconddawnblocks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.seconddawnblocks.SecondDawnBlocks;
import net.seconddawnblocks.block.shelvesdir.ShelvesBlockTag;
import net.seconddawnblocks.groups.Flat_ColoursGroup;
import net.seconddawnblocks.groups.PanelGroup;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(
            FabricDataOutput output,
            CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries) {

        // ===== Flat Colours tags =====
        for (int i = 0; i < Flat_ColoursGroup.NUM_COLOURS; i++) {
            getOrCreateTagBuilder(BlockTags.STAIRS).add(Flat_ColoursGroup.STAIRS_BLOCKS.get(i));
            getOrCreateTagBuilder(BlockTags.SLABS).add(Flat_ColoursGroup.SLAB_BLOCKS.get(i));
            getOrCreateTagBuilder(BlockTags.WALLS).add(Flat_ColoursGroup.WALL_BLOCKS.get(i));
            getOrCreateTagBuilder(BlockTags.TRAPDOORS).add(Flat_ColoursGroup.TRAPDOOR_BLOCKS.get(i));
        }

        // ===== Panel Group tags =====
        for (int i = 0; i < PanelGroup.NUM_PANELS; i++) {
            getOrCreateTagBuilder(BlockTags.STAIRS).add(PanelGroup.STAIRS_BLOCKS.get(i));
            getOrCreateTagBuilder(BlockTags.SLABS).add(PanelGroup.SLAB_BLOCKS.get(i));
            getOrCreateTagBuilder(BlockTags.WALLS).add(PanelGroup.WALL_BLOCKS.get(i));
            getOrCreateTagBuilder(BlockTags.TRAPDOORS).add(PanelGroup.TRAPDOOR_BLOCKS.get(i));
        }

        // ===== Shelves =====
        getOrCreateTagBuilder(ShelvesBlockTag.SHELVES)
                .addOptional(SecondDawnBlocks.id("oak_shelf"))
                .addOptional(SecondDawnBlocks.id("spruce_shelf"))
                .addOptional(SecondDawnBlocks.id("birch_shelf"))
                .addOptional(SecondDawnBlocks.id("jungle_shelf"))
                .addOptional(SecondDawnBlocks.id("acacia_shelf"))
                .addOptional(SecondDawnBlocks.id("cherry_shelf"))
                .addOptional(SecondDawnBlocks.id("dark_oak_shelf"))
                .addOptional(SecondDawnBlocks.id("mangrove_shelf"))
                .addOptional(SecondDawnBlocks.id("bamboo_shelf"))
                .addOptional(SecondDawnBlocks.id("crimson_shelf"))
                .addOptional(SecondDawnBlocks.id("warped_shelf"));

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .addTag(ShelvesBlockTag.SHELVES);
    }
}