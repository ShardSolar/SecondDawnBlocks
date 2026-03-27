package net.seconddawnblocks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.seconddawnblocks.SecondDawnBlocks;
import net.seconddawnblocks.block.HorizontalQuarterBlock;
import net.seconddawnblocks.block.ModBlocks;
import net.seconddawnblocks.block.VerticalQuarterBlock;
import net.seconddawnblocks.block.shelvesdir.ShelfBlock;
import net.seconddawnblocks.block.shelvesdir.ShelfType;
import net.seconddawnblocks.block.shelvesdir.ShelvesBlock;
import net.seconddawnblocks.groups.Flat_ColoursGroup;
import net.seconddawnblocks.groups.PanelGroup;

import java.util.Map;
import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {

    public static final Model BLOCK_SHELF = new Model(
            Optional.of(SecondDawnBlocks.id("block/shelf")),
            Optional.empty(),
            TextureKey.FRONT, TextureKey.BACK
    );

    public static final Model ITEM_SHELF = new Model(
            Optional.of(SecondDawnBlocks.id("item/shelf")),
            Optional.empty(),
            TextureKey.FRONT, TextureKey.BACK
    );

    private static final Model QUARTER_BLOCK_Y_MODEL = new Model(
            Optional.of(SecondDawnBlocks.id("block/template/quarter_block_y")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    private static final Model QUARTER_BLOCK_X_NORTH_BOTTOM = new Model(
            Optional.of(SecondDawnBlocks.id("block/template/quarter_block_x_north_bottom")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    private static final Model QUARTER_BLOCK_X_NORTH_TOP = new Model(
            Optional.of(SecondDawnBlocks.id("block/template/quarter_block_x_north_top")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    private static final Model QUARTER_BLOCK_X_SOUTH_BOTTOM = new Model(
            Optional.of(SecondDawnBlocks.id("block/template/quarter_block_x_south_bottom")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    private static final Model QUARTER_BLOCK_X_SOUTH_TOP = new Model(
            Optional.of(SecondDawnBlocks.id("block/template/quarter_block_x_south_top")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    private static final Model QUARTER_BLOCK_Z_WEST_BOTTOM = new Model(
            Optional.of(SecondDawnBlocks.id("block/template/quarter_block_z_west_bottom")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    private static final Model QUARTER_BLOCK_Z_WEST_TOP = new Model(
            Optional.of(SecondDawnBlocks.id("block/template/quarter_block_z_west_top")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    private static final Model QUARTER_BLOCK_Z_EAST_BOTTOM = new Model(
            Optional.of(SecondDawnBlocks.id("block/template/quarter_block_z_east_bottom")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    private static final Model QUARTER_BLOCK_Z_EAST_TOP = new Model(
            Optional.of(SecondDawnBlocks.id("block/template/quarter_block_z_east_top")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator gen) {

        registerVerticalQuarterBlock(gen, ModBlocks.VERTICAL_QUARTER_BLOCK, TextureMap.getId(ModBlocks.VERTICAL_QUARTER_BLOCK));
        registerHorizontalQuarterBlock(gen, ModBlocks.HORIZONTAL_QUARTER_BLOCK, TextureMap.getId(ModBlocks.HORIZONTAL_QUARTER_BLOCK));

        QuarterBlockTexturePool quarterPool = QuarterBlockTexturePool.of("block");
        for (int i = 0; i < Flat_ColoursGroup.NUM_COLOURS; i++) {
            quarterPool.quarter(Flat_ColoursGroup.VERTICAL_QUARTER_BLOCKS.get(i), Flat_ColoursGroup.COLOURS[i]);
            quarterPool.quarter(Flat_ColoursGroup.HORIZONTAL_QUARTER_BLOCKS.get(i), Flat_ColoursGroup.COLOURS[i]);
        }
        registerQuarterPool(gen, quarterPool);

        //gen.registerSimpleCubeAll(TNGGroup.GAL_WALL_BEIGE);



        // ------------- Test blocks and texture pool -------------

        /*BlockStateModelGenerator.BlockTexturePool grayfabricpool =
                gen.registerCubeAllModelTexturePool(ModBlocks.CONDUIT);



        //grayfabricpool.stairs(ModBlocks.TEST_STAIRS);
        grayfabricpool.slab(ModBlocks.TEST_SLAB);
        grayfabricpool.pressurePlate(ModBlocks.TEST_PRESSURE_PLATE);
        grayfabricpool.button(ModBlocks.TEST_BUTTON);
        grayfabricpool.fence(ModBlocks.TEST_FENCE);
        grayfabricpool.fenceGate(ModBlocks.TEST_FENCE_GATE);
        grayfabricpool.wall(ModBlocks.TEST_WALL);
        gen.registerDoor(ModBlocks.TEST_DOOR);
        gen.registerTrapdoor(ModBlocks.TEST_TRAPDOOR);*/

        //-----------------------------------------------------------------

        // ------------- Texture Pools -------------








        // ------------- Block Datagen -------------



        //gen.registerSimpleCubeAll(TNGGroup.GAL_CORRIDOR_BTM_ALT);


        //gen.registerDoor(TNGGroup.TENFORWARD_DOOR);







        //Shelves

        createShelf(gen, ShelvesBlock.OAK_SHELF);
        createShelf(gen, ShelvesBlock.SPRUCE_SHELF);
        createShelf(gen, ShelvesBlock.BIRCH_SHELF);
        createShelf(gen, ShelvesBlock.JUNGLE_SHELF);
        createShelf(gen, ShelvesBlock.ACACIA_SHELF);
        createShelf(gen, ShelvesBlock.CHERRY_SHELF);
        createShelf(gen, ShelvesBlock.DARK_OAK_SHELF);
        createShelf(gen, ShelvesBlock.MANGROVE_SHELF);
        createShelf(gen, ShelvesBlock.BAMBOO_SHELF);
        createShelf(gen, ShelvesBlock.CRIMSON_SHELF);
        createShelf(gen, ShelvesBlock.WARPED_SHELF);

        //Flat Colour Generation loop

        for (int i = 0; i < Flat_ColoursGroup.NUM_COLOURS; i++) {
            Block baseBlock = Flat_ColoursGroup.BASE_BLOCKS.get(i);
            Block stairsBlock = Flat_ColoursGroup.STAIRS_BLOCKS.get(i);
            Block slabBlock = Flat_ColoursGroup.SLAB_BLOCKS.get(i);
            Block wallBlock = Flat_ColoursGroup.WALL_BLOCKS.get(i);
            Block trapdoorBlock = Flat_ColoursGroup.TRAPDOOR_BLOCKS.get(i);

            BlockStateModelGenerator.BlockTexturePool pool =
                    gen.registerCubeAllModelTexturePool(baseBlock);

            gen.registerParentedItemModel(baseBlock, ModelIds.getBlockModelId(baseBlock));

            pool.stairs(stairsBlock);
            pool.slab(slabBlock);
            pool.wall(wallBlock);
            gen.registerTrapdoor(trapdoorBlock);
        }

        for (int i = 0; i < PanelGroup.NUM_PANELS; i++) {
            Block baseBlock = PanelGroup.BASE_BLOCKS.get(i);
            Block stairsBlock = PanelGroup.STAIRS_BLOCKS.get(i);
            Block slabBlock = PanelGroup.SLAB_BLOCKS.get(i);
            Block wallBlock = PanelGroup.WALL_BLOCKS.get(i);
            Block trapdoorBlock = PanelGroup.TRAPDOOR_BLOCKS.get(i);
            Block verticalQuarterBlock = PanelGroup.VERTICAL_QUARTER_BLOCKS.get(i);
            Block horizontalQuarterBlock = PanelGroup.HORIZONTAL_QUARTER_BLOCKS.get(i);

            BlockStateModelGenerator.BlockTexturePool pool =
                    gen.registerCubeAllModelTexturePool(baseBlock);

            gen.registerParentedItemModel(baseBlock, ModelIds.getBlockModelId(baseBlock));

            pool.stairs(stairsBlock);
            pool.slab(slabBlock);
            pool.wall(wallBlock);
            gen.registerTrapdoor(trapdoorBlock);

            registerVerticalQuarterBlock(gen, verticalQuarterBlock, TextureMap.getId(baseBlock));
            registerHorizontalQuarterBlock(gen, horizontalQuarterBlock, TextureMap.getId(baseBlock));
        }

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemGen) {

        createShelfItem(itemGen, ShelvesBlock.OAK_SHELF);
        createShelfItem(itemGen, ShelvesBlock.SPRUCE_SHELF);
        createShelfItem(itemGen, ShelvesBlock.BIRCH_SHELF);
        createShelfItem(itemGen, ShelvesBlock.JUNGLE_SHELF);
        createShelfItem(itemGen, ShelvesBlock.ACACIA_SHELF);
        createShelfItem(itemGen, ShelvesBlock.CHERRY_SHELF);
        createShelfItem(itemGen, ShelvesBlock.DARK_OAK_SHELF);
        createShelfItem(itemGen, ShelvesBlock.MANGROVE_SHELF);
        createShelfItem(itemGen, ShelvesBlock.BAMBOO_SHELF);
        createShelfItem(itemGen, ShelvesBlock.CRIMSON_SHELF);
        createShelfItem(itemGen, ShelvesBlock.WARPED_SHELF);
    }

    private void registerQuarterPool(BlockStateModelGenerator gen, QuarterBlockTexturePool pool) {
        for (Map.Entry<Block, Identifier> entry : pool.getQuarterBlocks().entrySet()) {
            Block block = entry.getKey();

            if (block instanceof VerticalQuarterBlock) {
                registerVerticalQuarterBlock(gen, block, entry.getValue());
            }

            if (block instanceof HorizontalQuarterBlock) {
                registerHorizontalQuarterBlock(gen, block, entry.getValue());
            }
        }
    }

    private void registerVerticalQuarterBlock(BlockStateModelGenerator gen, Block block, Identifier texture) {
        TextureMap textures = new TextureMap()
                .put(TextureKey.ALL, texture)
                .put(TextureKey.PARTICLE, texture);

        Identifier model = QUARTER_BLOCK_Y_MODEL.upload(
                ModelIds.getBlockModelId(block),
                textures,
                gen.modelCollector
        );

        VariantsBlockStateSupplier supplier = VariantsBlockStateSupplier.create(block)
                .coordinate(
                        BlockStateVariantMap.create(VerticalQuarterBlock.FACING)
                                .register(Direction.NORTH,
                                        BlockStateVariant.create().put(VariantSettings.MODEL, model))
                                .register(Direction.EAST,
                                        BlockStateVariant.create()
                                                .put(VariantSettings.MODEL, model)
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                                .register(Direction.SOUTH,
                                        BlockStateVariant.create()
                                                .put(VariantSettings.MODEL, model)
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                                .register(Direction.WEST,
                                        BlockStateVariant.create()
                                                .put(VariantSettings.MODEL, model)
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                );

        gen.blockStateCollector.accept(supplier);
        gen.registerParentedItemModel(block, model);
    }

    private void registerHorizontalQuarterBlock(BlockStateModelGenerator gen, Block block, Identifier texture) {
        TextureMap textures = new TextureMap()
                .put(TextureKey.ALL, texture)
                .put(TextureKey.PARTICLE, texture);

        Identifier xNorthBottom = QUARTER_BLOCK_X_NORTH_BOTTOM.upload(
                ModelIds.getBlockSubModelId(block, "_x_north_bottom"),
                textures,
                gen.modelCollector
        );
        Identifier xNorthTop = QUARTER_BLOCK_X_NORTH_TOP.upload(
                ModelIds.getBlockSubModelId(block, "_x_north_top"),
                textures,
                gen.modelCollector
        );
        Identifier xSouthBottom = QUARTER_BLOCK_X_SOUTH_BOTTOM.upload(
                ModelIds.getBlockSubModelId(block, "_x_south_bottom"),
                textures,
                gen.modelCollector
        );
        Identifier xSouthTop = QUARTER_BLOCK_X_SOUTH_TOP.upload(
                ModelIds.getBlockSubModelId(block, "_x_south_top"),
                textures,
                gen.modelCollector
        );

        Identifier zWestBottom = QUARTER_BLOCK_Z_WEST_BOTTOM.upload(
                ModelIds.getBlockSubModelId(block, "_z_west_bottom"),
                textures,
                gen.modelCollector
        );
        Identifier zWestTop = QUARTER_BLOCK_Z_WEST_TOP.upload(
                ModelIds.getBlockSubModelId(block, "_z_west_top"),
                textures,
                gen.modelCollector
        );
        Identifier zEastBottom = QUARTER_BLOCK_Z_EAST_BOTTOM.upload(
                ModelIds.getBlockSubModelId(block, "_z_east_bottom"),
                textures,
                gen.modelCollector
        );
        Identifier zEastTop = QUARTER_BLOCK_Z_EAST_TOP.upload(
                ModelIds.getBlockSubModelId(block, "_z_east_top"),
                textures,
                gen.modelCollector
        );

        VariantsBlockStateSupplier supplier = VariantsBlockStateSupplier.create(block)
                .coordinate(
                        BlockStateVariantMap.create(HorizontalQuarterBlock.AXIS, HorizontalQuarterBlock.PART)
                                .register(Direction.Axis.X, HorizontalQuarterBlock.QuarterPart.BOTTOM_LEFT,
                                        BlockStateVariant.create().put(VariantSettings.MODEL, xNorthBottom))
                                .register(Direction.Axis.X, HorizontalQuarterBlock.QuarterPart.BOTTOM_RIGHT,
                                        BlockStateVariant.create().put(VariantSettings.MODEL, xSouthBottom))
                                .register(Direction.Axis.X, HorizontalQuarterBlock.QuarterPart.TOP_RIGHT,
                                        BlockStateVariant.create().put(VariantSettings.MODEL, xSouthTop))
                                .register(Direction.Axis.X, HorizontalQuarterBlock.QuarterPart.TOP_LEFT,
                                        BlockStateVariant.create().put(VariantSettings.MODEL, xNorthTop))

                                .register(Direction.Axis.Z, HorizontalQuarterBlock.QuarterPart.BOTTOM_LEFT,
                                        BlockStateVariant.create().put(VariantSettings.MODEL, zWestBottom))
                                .register(Direction.Axis.Z, HorizontalQuarterBlock.QuarterPart.BOTTOM_RIGHT,
                                        BlockStateVariant.create().put(VariantSettings.MODEL, zEastBottom))
                                .register(Direction.Axis.Z, HorizontalQuarterBlock.QuarterPart.TOP_RIGHT,
                                        BlockStateVariant.create().put(VariantSettings.MODEL, zEastTop))
                                .register(Direction.Axis.Z, HorizontalQuarterBlock.QuarterPart.TOP_LEFT,
                                        BlockStateVariant.create().put(VariantSettings.MODEL, zWestTop))
                );

        gen.blockStateCollector.accept(supplier);
        gen.registerParentedItemModel(block, xNorthBottom);
    }

    private static void createShelf(BlockStateModelGenerator gen, Block block) {
        Identifier unpoweredModel = BLOCK_SHELF.upload(
                ModelIds.getBlockModelId(block),
                shelfTextures(TextureMap.getId(block)),
                gen.modelCollector
        );

        Identifier singleModel = BLOCK_SHELF.upload(
                ModelIds.getBlockSubModelId(block, "_single"),
                shelfTextures(TextureMap.getSubId(block, "_single")),
                gen.modelCollector
        );

        Identifier leftModel = BLOCK_SHELF.upload(
                ModelIds.getBlockSubModelId(block, "_left"),
                shelfTextures(TextureMap.getSubId(block, "_left")),
                gen.modelCollector
        );

        Identifier middleModel = BLOCK_SHELF.upload(
                ModelIds.getBlockSubModelId(block, "_middle"),
                shelfTextures(TextureMap.getSubId(block, "_middle")),
                gen.modelCollector
        );

        Identifier rightModel = BLOCK_SHELF.upload(
                ModelIds.getBlockSubModelId(block, "_right"),
                shelfTextures(TextureMap.getSubId(block, "_right")),
                gen.modelCollector
        );

        VariantsBlockStateSupplier supplier = VariantsBlockStateSupplier.create(block)
                .coordinate(
                        BlockStateVariantMap.create(ShelfBlock.TYPE)
                                .register(ShelfType.UNPOWERED, variantModel(unpoweredModel))
                                .register(ShelfType.SINGLE, variantModel(singleModel))
                                .register(ShelfType.LEFT, variantModel(leftModel))
                                .register(ShelfType.MIDDLE, variantModel(middleModel))
                                .register(ShelfType.RIGHT, variantModel(rightModel))
                )
                .coordinate(
                        BlockStateVariantMap.create(ShelfBlock.FACING)
                                .register(Direction.NORTH,
                                        BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R0))
                                .register(Direction.EAST,
                                        BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R90))
                                .register(Direction.SOUTH,
                                        BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180))
                                .register(Direction.WEST,
                                        BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R270))
                );

        gen.blockStateCollector.accept(supplier);
    }

    private static void createShelfItem(ItemModelGenerator itemGen, Block block) {
        TextureMap textures = shelfTextures(TextureMap.getId(block));
        ITEM_SHELF.upload(ModelIds.getItemModelId(block.asItem()), textures, itemGen.writer);
    }

    private static TextureMap shelfTextures(Identifier front) {
        return new TextureMap()
                .put(TextureKey.FRONT, front)
                .put(TextureKey.BACK, front);
    }

    private static BlockStateVariant variantModel(Identifier modelId) {
        return BlockStateVariant.create().put(VariantSettings.MODEL, modelId);
    }
}