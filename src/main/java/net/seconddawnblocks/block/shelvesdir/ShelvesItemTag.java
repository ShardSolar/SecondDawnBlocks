package net.seconddawnblocks.block.shelvesdir;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ShelvesItemTag {

    public static final TagKey<Item> SHELVES =
            TagKey.of(RegistryKeys.ITEM, Shelves.id("shelves"));
}
