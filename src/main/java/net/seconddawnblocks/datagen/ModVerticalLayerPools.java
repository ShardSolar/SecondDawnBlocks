package net.seconddawnblocks.datagen;

import net.minecraft.block.Block;
import net.minecraft.data.client.TextureMap;
import net.seconddawnblocks.block.ModBlocks;
import net.seconddawnblocks.block.verticalslabsdir.VerticalSlabPool;
import net.seconddawnblocks.groups.Flat_ColoursGroup;
import net.seconddawnblocks.groups.PanelGroup;

public final class ModVerticalLayerPools {
    private ModVerticalLayerPools() {}

    // Base folder is textures/block/
    public static final VerticalLayerTexturePool BLOCK =
            VerticalLayerTexturePool.of("block");

    // Corner stairs pool
    public static final VerticalLayerTexturePool CORNER_STAIRS =
            VerticalLayerTexturePool.of("block");

    // Vertical slabs pool
    public static final VerticalSlabPool VERTICAL_SLABS = new VerticalSlabPool();

    public static void register() {

        // ---- Manual / test vertical layer blocks ----
        BLOCK.layer(ModBlocks.TEST_CORRIDOR_LAYER);
        BLOCK.layer(ModBlocks.TEST_CORRIDOR_LAYER2, "gal_corridor_btm");

        // ---- Generate every flat-colour layer block ----
        for (int i = 0; i < Flat_ColoursGroup.NUM_COLOURS; i++) {
            BLOCK.layer(
                    Flat_ColoursGroup.LAYER_BLOCKS.get(i),
                    Flat_ColoursGroup.COLOURS[i]
            );
        }

        // ---- Manual / test corner stairs ----
        CORNER_STAIRS.layer(ModBlocks.TEST_CORNER_STAIRS);

        // ---- Generate every flat-colour corner stairs block ----
        for (int i = 0; i < Flat_ColoursGroup.NUM_COLOURS; i++) {
            CORNER_STAIRS.layer(
                    Flat_ColoursGroup.CORNER_STAIRS_BLOCKS.get(i),
                    Flat_ColoursGroup.COLOURS[i]
            );
        }

        // ---- Manual / test vertical slab ----
        VERTICAL_SLABS.layer(ModBlocks.VERTICAL_TEST_SLAB, "beige_0");

        // ---- Generate every flat-colour vertical slab block ----
        for (int i = 0; i < Flat_ColoursGroup.NUM_COLOURS; i++) {
            VERTICAL_SLABS.layer(
                    Flat_ColoursGroup.VERTICAL_SLAB_BLOCKS.get(i),
                    Flat_ColoursGroup.COLOURS[i]
            );
        }

        // ---- Generate every panel custom-shape block ----
        for (int i = 0; i < PanelGroup.NUM_PANELS; i++) {
            Block baseBlock = PanelGroup.BASE_BLOCKS.get(i);

            String textureName = net.minecraft.registry.Registries.BLOCK
                    .getId(baseBlock)
                    .getPath();

            BLOCK.layer(
                    PanelGroup.LAYER_BLOCKS.get(i),
                    textureName
            );

            CORNER_STAIRS.layer(
                    PanelGroup.CORNER_STAIRS_BLOCKS.get(i),
                    textureName
            );

            VERTICAL_SLABS.layer(
                    PanelGroup.VERTICAL_SLAB_BLOCKS.get(i),
                    textureName
            );
        }

        /*
         Option B: override the HALF texture name (re-use another texture)
         expects textures/block/gray_fabric_wall.png for the HALF model,
         but STILL generates models/block/vertical_test_slab_double.json

         VERTICAL_SLABS.addWithTexture(ModBlocks.VERTICAL_TEST_SLAB, "gray_fabric_wall");
         */
    }
}