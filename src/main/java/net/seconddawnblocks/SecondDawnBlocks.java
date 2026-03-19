package net.seconddawnblocks;

import net.fabricmc.api.ModInitializer;

import net.seconddawnblocks.groups.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.util.Identifier;
import net.seconddawnblocks.groups.PanelGroup;

public class SecondDawnBlocks implements ModInitializer {
	public static final String MOD_ID = "seconddawnblocks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		net.seconddawnblocks.block.ModBlocks.registerModBlocks();

		Flat_ColoursGroup.registerModBlocks();
		PanelGroup.registerModBlocks();
		net.seconddawnblocks.block.shelvesdir.ShelvesBlock.initialize();
		net.seconddawnblocks.block.shelvesdir.ShelvesItems.initialize();
		net.seconddawnblocks.block.shelvesdir.Shelves.register();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);  // 1.21+
	}
}