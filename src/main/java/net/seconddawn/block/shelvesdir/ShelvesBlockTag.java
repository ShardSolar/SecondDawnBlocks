package net.seconddawn.block.shelvesdir;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;

public class ShelvesBlockTag {

    public static final TagKey<Block> SHELVES = bind("shelves");

    private static TagKey<Block> bind(String path) {
        return TagKey.of(Registries.BLOCK.getKey(), Shelves.id(path));
    }
}
