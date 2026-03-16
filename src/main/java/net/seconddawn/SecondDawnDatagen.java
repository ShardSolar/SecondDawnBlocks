package net.seconddawn;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.seconddawn.datagen.ModBlockTagProvider;
import net.seconddawn.datagen.ModItemTagProvider;
import net.seconddawn.datagen.ModModelProvider;
import net.seconddawn.datagen.ShelvesLootProvider;
import net.seconddawn.datagen.ShelvesRecipeProvider;
import net.seconddawn.datagen.VerticalLayerProvider;

public class SecondDawnDatagen implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		FabricDataGenerator.Pack pack = dataGenerator.createPack();

		// Block tags provider
		FabricTagProvider.BlockTagProvider blockTags = pack.addProvider(ModBlockTagProvider::new);

		// Model provider(s)
		pack.addProvider(ModModelProvider::new);

		// Your vertical layers provider (the one that uses CachedOutput/DataProvider.writeToPath)
		pack.addProvider(VerticalLayerProvider::new);

		// Loot + recipes
		pack.addProvider(ShelvesLootProvider::new);
		pack.addProvider(ShelvesRecipeProvider::new);

		// Item tags provider (depends on blockTags)
		pack.addProvider((output, registriesFuture) ->
				new ModItemTagProvider(output, registriesFuture, blockTags)
		);
	}
}