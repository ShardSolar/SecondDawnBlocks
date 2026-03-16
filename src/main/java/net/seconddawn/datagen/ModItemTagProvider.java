package net.seconddawn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.seconddawn.SecondDawn;
import net.seconddawn.block.shelvesdir.ShelvesBlockTag;
import net.seconddawn.block.shelvesdir.ShelvesItemTag;

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
                .addOptional(ShipyardsMod.id("crimson_shelf"))
                .addOptional(ShipyardsMod.id("warped_shelf"));
    }
}