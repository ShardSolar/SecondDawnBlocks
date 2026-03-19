package net.seconddawnblocks.datagen;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.seconddawnblocks.SecondDawnBlocks;

import java.util.LinkedHashMap;
import java.util.Map;

public class VerticalLayerTexturePool {
    private final Identifier baseTexture;
    private final Map<Block, Identifier> layers = new LinkedHashMap<>();

    private VerticalLayerTexturePool(Identifier baseTexture) {
        this.baseTexture = baseTexture;
    }

    public static VerticalLayerTexturePool of(String baseTexturePath) {
        return new VerticalLayerTexturePool(Identifier.of(SecondDawnBlocks.MOD_ID, baseTexturePath));
    }

    public VerticalLayerTexturePool layer(Block block) {
        String blockPath = net.minecraft.registry.Registries.BLOCK.getId(block).getPath();
        Identifier tex = Identifier.of(baseTexture.getNamespace(), baseTexture.getPath() + "/" + blockPath);
        layers.put(block, tex);
        return this;
    }

    public VerticalLayerTexturePool layer(Block block, String textureName) {
        Identifier tex = Identifier.of(baseTexture.getNamespace(), baseTexture.getPath() + "/" + textureName);
        layers.put(block, tex);
        return this;
    }

    public Map<Block, Identifier> getLayers() {
        return layers;
    }
}