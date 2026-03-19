package net.seconddawnblocks.block.verticalslabsdir;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.seconddawnblocks.SecondDawnBlocks;

import java.util.LinkedHashMap;
import java.util.Map;

public class VerticalSlabPool {

    public record Spec(Identifier texture) {}

    private final Map<Block, Spec> entries = new LinkedHashMap<>();

    // auto texture from block name: textures/block/<block>.png
    public VerticalSlabPool layer(Block block) {
        String blockPath = Registries.BLOCK.getId(block).getPath();
        Identifier tex = Identifier.of(SecondDawnBlocks.MOD_ID, "block/" + blockPath);
        entries.put(block, new Spec(tex));
        return this;
    }

    // custom texture: textures/block/<textureName>.png
    public VerticalSlabPool layer(Block block, String textureName) {
        Identifier tex = Identifier.of(SecondDawnBlocks.MOD_ID, "block/" + textureName);
        entries.put(block, new Spec(tex));
        return this;
    }

    public Map<Block, Spec> entries() {
        return entries;
    }
}