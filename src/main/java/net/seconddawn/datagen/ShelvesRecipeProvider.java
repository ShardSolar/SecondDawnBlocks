package net.seconddawn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import net.seconddawn.block.shelvesdir.ShelvesItems; // <-- change if needed

import java.util.concurrent.CompletableFuture;

public class ShelvesRecipeProvider extends FabricRecipeProvider {

    public ShelvesRecipeProvider(
            FabricDataOutput output,
            CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        shelf(exporter, ShelvesItems.OAK_SHELF,     Items.STRIPPED_OAK_LOG);
        shelf(exporter, ShelvesItems.SPRUCE_SHELF,  Items.STRIPPED_SPRUCE_LOG);
        shelf(exporter, ShelvesItems.BIRCH_SHELF,   Items.STRIPPED_BIRCH_LOG);
        shelf(exporter, ShelvesItems.JUNGLE_SHELF,  Items.STRIPPED_JUNGLE_LOG);
        shelf(exporter, ShelvesItems.ACACIA_SHELF,  Items.STRIPPED_ACACIA_LOG);
        shelf(exporter, ShelvesItems.CHERRY_SHELF,  Items.STRIPPED_CHERRY_LOG);
        shelf(exporter, ShelvesItems.DARK_OAK_SHELF,Items.STRIPPED_DARK_OAK_LOG);
        shelf(exporter, ShelvesItems.MANGROVE_SHELF,Items.STRIPPED_MANGROVE_LOG);
        shelf(exporter, ShelvesItems.BAMBOO_SHELF,  Items.STRIPPED_BAMBOO_BLOCK);
        shelf(exporter, ShelvesItems.CRIMSON_SHELF, Items.STRIPPED_CRIMSON_STEM);
        shelf(exporter, ShelvesItems.WARPED_SHELF,  Items.STRIPPED_WARPED_STEM);

        // If you actually have PALE_OAK in your game version + Yarn mappings:
        // shelf(exporter, ShelvesItems.PALE_OAK_SHELF, Items.STRIPPED_PALE_OAK_LOG);
    }

    private static void shelf(RecipeExporter exporter, net.minecraft.item.ItemConvertible shelf, net.minecraft.item.ItemConvertible material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, shelf, 6)
                .group("shelf")
                .input('#', material)
                .pattern("###")
                .pattern("   ")
                .pattern("###")
                .criterion("has_material", FabricRecipeProvider.conditionsFromItem(material))
                .offerTo(exporter);
    }
}
