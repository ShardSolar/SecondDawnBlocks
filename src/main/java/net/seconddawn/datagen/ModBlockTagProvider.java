package net.seconddawn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.seconddawn.SecondDawn;
import net.seconddawn.block.ModBlocks;
import net.seconddawn.block.shelvesdir.ShelvesBlockTag;
import net.seconddawn.groups.Flat_ColoursGroup;

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
                .addOptional(SecondDawn.id("oak_shelf"))
                .addOptional(SecondDawn.id("spruce_shelf"))
                .addOptional(SecondDawn.id("birch_shelf"))
                .addOptional(SecondDawn.id("jungle_shelf"))
                .addOptional(SecondDawn.id("acacia_shelf"))
                .addOptional(SecondDawn.id("cherry_shelf"))
                .addOptional(SecondDawn.id("dark_oak_shelf"))
                .addOptional(SecondDawn.id("mangrove_shelf"))
                .addOptional(SecondDawn.id("bamboo_shelf"))
                .addOptional(SecondDawn.id("crimson_shelf"))
                .addOptional(SecondDawn.id("warped_shelf"));

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .addTag(ShelvesBlockTag.SHELVES);
    }
}