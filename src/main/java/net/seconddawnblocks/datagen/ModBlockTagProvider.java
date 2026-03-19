package net.seconddawnblocks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.seconddawnblocks.SecondDawnBlocks;
import net.seconddawnblocks.block.ModBlocks;
import net.seconddawnblocks.block.shelvesdir.ShelvesBlockTag;
import net.seconddawnblocks.groups.Flat_ColoursGroup;

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

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(ModBlocks.TEST_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(ModBlocks.TEST_FENCE_GATE);
        getOrCreateTagBuilder(BlockTags.WALLS).add(ModBlocks.TEST_WALL);




        //Flat Colour Walls


        for (int colourBlockIdx = 0; colourBlockIdx < Flat_ColoursGroup.NUM_COLOURS; colourBlockIdx++)
        {
            getOrCreateTagBuilder(BlockTags.WALLS).add(Flat_ColoursGroup.WALL_BLOCKS.get(colourBlockIdx
            ));
        }


        // shelves:shelves (use IDs so ShelvesBlock class doesn't initialize during datagen)
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