package net.seconddawnblocks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.seconddawnblocks.SecondDawnBlocks;
import net.seconddawnblocks.block.shelvesdir.ShelvesBlockTag;
import net.seconddawnblocks.block.shelvesdir.ShelvesItemTag;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(
            FabricDataOutput output,
            CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture,
            FabricTagProvider.BlockTagProvider blockTags
    ) {
        super(output, registriesFuture, blockTags);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries) {
        copy(ShelvesBlockTag.SHELVES, ShelvesItemTag.SHELVES);

        getOrCreateTagBuilder(ItemTags.NON_FLAMMABLE_WOOD)
                .addOptional(SecondDawnBlocks.id("crimson_shelf"))
                .addOptional(SecondDawnBlocks.id("warped_shelf"));
    }
}