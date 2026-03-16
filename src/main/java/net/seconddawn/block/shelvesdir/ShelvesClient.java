package net.seconddawn.block.shelvesdir;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class ShelvesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(ShelvesBlockEntities.SHELF, ShelfRenderer::new);
    }
}
