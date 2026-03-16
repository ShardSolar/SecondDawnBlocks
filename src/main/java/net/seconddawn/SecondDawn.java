package net.seconddawn;

import net.fabricmc.api.ModInitializer;

import net.seconddawn.block.ModBlocks;
import net.seconddawn.block.shelvesdir.ShelvesBlock;
import net.seconddawn.groups.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.util.Identifier;

public class SecondDawn implements ModInitializer {
	public static final String MOD_ID = "seconddawn";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {


		Flat_ColoursGroup.registerModBlocks();

		

		net.seconddawn.block.shelvesdir.ShelvesBlock.initialize();
		net.seconddawn.block.shelvesdir.ShelvesItems.initialize();
		net.seconddawn.block.shelvesdir.Shelves.register();

	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);  // 1.21+
	}
}