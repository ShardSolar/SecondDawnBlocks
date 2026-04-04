package net.seconddawnblocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.seconddawnblocks.groups.PanelGroup;

public class SecondDawnClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerTransparentPanelRenderLayers();
    }

    private static void registerTransparentPanelRenderLayers() {
        for (Block block : PanelGroup.TRANSPARENT_RENDER_BLOCKS) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
        }
    }
}