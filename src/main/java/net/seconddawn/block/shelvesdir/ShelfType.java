package net.seconddawn.block.shelvesdir;

import net.minecraft.util.StringIdentifiable;
import org.jetbrains.annotations.NotNull;

public enum ShelfType implements StringIdentifiable {
    UNPOWERED("unpowered"),
    SINGLE("single"),
    LEFT("left"),
    MIDDLE("middle"),
    RIGHT("right");

    private final String name;

    ShelfType(String name) {
        this.name = name;
    }

    @Override
    @NotNull
    public String asString() {
        return this.name;
    }
}
