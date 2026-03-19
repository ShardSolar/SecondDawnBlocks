package net.seconddawn.datagen;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.seconddawn.SecondDawnBlocks;

import java.util.LinkedHashMap;
import java.util.Map;

public class QuarterBlockTexturePool {

    private final Identifier baseTexture;
    private final Map<Block, Identifier> quarterBlocks = new LinkedHashMap<>();

    private QuarterBlockTexturePool(Identifier baseTexture) {
        this.baseTexture = baseTexture;
    }

    public static QuarterBlockTexturePool of(String baseTexturePath) {
        return new QuarterBlockTexturePool(
                Identifier.of(SecondDawnBlocks.MOD_ID, baseTexturePath)
        );
    }

    public QuarterBlockTexturePool quarter(Block block) {
        String blockPath = Registries.BLOCK.getId(block).getPath();
        Identifier textureId = Identifier.of(
                baseTexture.getNamespace(),
                baseTexture.getPath() + "/" + blockPath
        );
        quarterBlocks.put(block, textureId);
        return this;
    }

    public QuarterBlockTexturePool quarter(Block block, String textureName) {
        Identifier textureId = Identifier.of(
                baseTexture.getNamespace(),
                baseTexture.getPath() + "/" + textureName
        );
        quarterBlocks.put(block, textureId);
        return this;
    }

    public Map<Block, Identifier> getQuarterBlocks() {
        return quarterBlocks;
    }
}