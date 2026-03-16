package net.seconddawn.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.seconddawn.SecondDawn;
import net.seconddawn.block.HorizontalQuarterBlock;
import net.seconddawn.block.ModBlocks;
import net.seconddawn.block.VerticalQuarterBlock;
import net.seconddawn.block.shelvesdir.ShelfBlock;
import net.seconddawn.block.shelvesdir.ShelfType;
import net.seconddawn.block.shelvesdir.ShelvesBlock;
import net.seconddawn.groups.Flat_ColoursGroup;

import java.util.Map;
import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {

    public static final Model BLOCK_SHELF = new Model(
            Optional.of(SecondDawn.id("block/shelf")),
            Optional.empty(),
            TextureKey.FRONT, TextureKey.BACK
    );

    public static final Model ITEM_SHELF = new Model(
            Optional.of(SecondDawn.id("item/shelf")),
            Optional.empty(),
            TextureKey.FRONT, TextureKey.BACK
    );

    private static final Model QUARTER_BLOCK_Y_MODEL = new Model(
            Optional.of(SecondDawn.id("block/template/quarter_block_y")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    private static final Model QUARTER_BLOCK_X_NORTH_BOTTOM = new Model(
            Optional.of(SecondDawn.id("block/template/quarter_block_x_north_bottom")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    private static final Model QUARTER_BLOCK_X_NORTH_TOP = new Model(
            Optional.of(SecondDawn.id("block/template/quarter_block_x_north_top")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    private static final Model QUARTER_BLOCK_X_SOUTH_BOTTOM = new Model(
            Optional.of(SecondDawn.id("block/template/quarter_block_x_south_bottom")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    private static final Model QUARTER_BLOCK_X_SOUTH_TOP = new Model(
            Optional.of(SecondDawn.id("block/template/quarter_block_x_south_top")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    private static final Model QUARTER_BLOCK_Z_WEST_BOTTOM = new Model(
            Optional.of(SecondDawn.id("block/template/quarter_block_z_west_bottom")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    private static final Model QUARTER_BLOCK_Z_WEST_TOP = new Model(
            Optional.of(SecondDawn.id("block/template/quarter_block_z_west_top")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    private static final Model QUARTER_BLOCK_Z_EAST_BOTTOM = new Model(
            Optional.of(SecondDawn.id("block/template/quarter_block_z_east_bottom")),
            Optional.empty(),
            TextureKey.ALL, TextureKey.PARTICLE
    );

    private static final Model QUARTER_BLOCK_Z_EAST_TOP = new Model(
            Optional.of(SecondDawn.id("block/template/quarter_block_z_east_top")),
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

        gen.registerSimpleCubeAll(TNGGroup.GAL_WALL_BEIGE);
        gen.registerSimpleCubeAll(TNGGroup.FOAM_WALL_ANGLED);
        gen.registerSimpleCubeAll(TNGGroup.GAL_CORRIDOR_BTM);
        gen.registerSimpleCubeAll(TNGGroup.FOAM_WALL_GRID);


        // ------------- Test blocks and texture pool -------------

        BlockStateModelGenerator.BlockTexturePool grayfabricpool =
                gen.registerCubeAllModelTexturePool(TNGGroup.GRAY_FABRIC_WALL);



        grayfabricpool.stairs(ModBlocks.TEST_STAIRS);
        grayfabricpool.slab(ModBlocks.TEST_SLAB);
        grayfabricpool.pressurePlate(ModBlocks.TEST_PRESSURE_PLATE);
        grayfabricpool.button(ModBlocks.TEST_BUTTON);
        grayfabricpool.fence(ModBlocks.TEST_FENCE);
        grayfabricpool.fenceGate(ModBlocks.TEST_FENCE_GATE);
        grayfabricpool.wall(ModBlocks.TEST_WALL);
        gen.registerDoor(ModBlocks.TEST_DOOR);
        gen.registerTrapdoor(ModBlocks.TEST_TRAPDOOR);

        //-----------------------------------------------------------------

        // ------------- Texture Pools -------------


        //gal Pale Red Carpet

        BlockStateModelGenerator.BlockTexturePool gal_pale_red_carpet_pool =
                gen.registerCubeAllModelTexturePool(TNGGroup.GAL_PALE_RED_CARPET);



        gal_pale_red_carpet_pool.stairs(TNGGroup.GAL_PALE_RED_CARPET_STAIRS);
        gal_pale_red_carpet_pool.slab(TNGGroup.GAL_PALE_RED_CARPET_SLAB);

        //gal Pale Blue Carpet

        BlockStateModelGenerator.BlockTexturePool gal_pale_blue_carpet_pool =
                gen.registerCubeAllModelTexturePool(TNGGroup.GAL_PALE_BLUE_CARPET);



        gal_pale_blue_carpet_pool.stairs(TNGGroup.GAL_PALE_BLUE_CARPET_STAIRS);
        gal_pale_blue_carpet_pool.slab(TNGGroup.GAL_PALE_BLUE_CARPET_SLAB);

        //gal Red Carpet

        BlockStateModelGenerator.BlockTexturePool gal_red_carpet_pool =
                gen.registerCubeAllModelTexturePool(TNGGroup.GAL_RED_CARPET);



        gal_red_carpet_pool.stairs(TNGGroup.GAL_RED_CARPET_STAIRS);
        gal_red_carpet_pool.slab(TNGGroup.GAL_RED_CARPET_SLAB);

        //gal Blue Carpet

        BlockStateModelGenerator.BlockTexturePool gal_blue_carpet_pool =
                gen.registerCubeAllModelTexturePool(TNGGroup.GAL_BLUE_CARPET);



        gal_blue_carpet_pool.stairs(TNGGroup.GAL_BLUE_CARPET_STAIRS);
        gal_blue_carpet_pool.slab(TNGGroup.GAL_BLUE_CARPET_SLAB);


        // ------------- Block Datagen -------------

        //TNG

        gen.registerSimpleCubeAll(TNGGroup.GAL_CORRIDOR_BTM_ALT);
        gen.registerSimpleCubeAll(TNGGroup.GAL_CORRIDOR_MID);
        gen.registerSimpleCubeAll(TNGGroup.GAL_CORRIDOR_PANEL);
        gen.registerSimpleCubeAll(TNGGroup.GAL_CORRIDOR_TOP);
        gen.registerSimpleCubeAll(TNGGroup.GAL_BRIDGE_CONSOLES_MID);
        gen.registerSimpleCubeAll(TNGGroup.GAL_BRIDGE_CONSOLES_TOP_SIDE);
        gen.registerSimpleCubeAll(TNGGroup.GAL_BRIDGE_CONSOLES_TOP);
        gen.registerSimpleCubeAll(TNGGroup.GAL_WALL_GREY);
        gen.registerSimpleCubeAll(TNGGroup.GAL_WALL_GREY_PILLAR);
        gen.registerSimpleCubeAll(TNGGroup.ISOLINEAR_PANEL);
        gen.registerSimpleCubeAll(TNGGroup.TNG_HOLODECK_GRID);
        gen.registerSimpleCubeAll(TNGGroup.GAL_JT);
        gen.registerSimpleCubeAll(TNGGroup.GAL_WALL_BEIGE_PILLAR);
        gen.registerSimpleCubeAll(TNGGroup.GAL_CONSOLE_BEIGE);

        gen.registerDoor(TNGGroup.TENFORWARD_DOOR);


        //TMP

        gen.registerCubeAllModelTexturePool(TMPGroup.TMP_CORRIDOR_BTM);
        gen.registerSimpleCubeAll(TMPGroup.TMP_CORRIDOR_BTM_ALT);
        gen.registerSimpleCubeAll(TMPGroup.TMP_CORRIDOR_PANEL);
        gen.registerSimpleCubeAll(TMPGroup.TMP_CORRIDOR_MID_ALT);
        gen.registerSimpleCubeAll(TMPGroup.TMP_CORRIDOR_TOP_ALT);
        gen.registerSimpleCubeAll(TMPGroup.TMP_BEIGE_FABRIC_CORRIDOR_MID_ALT);
        gen.registerSimpleCubeAll(TMPGroup.TMP_BEIGE_FABRIC_CORRIDOR_PANEL);
        gen.registerSimpleCubeAll(TMPGroup.TMP_BEIGE_FABRIC_CORRIDOR_TOP_ALT);
        gen.registerSimpleCubeAll(TMPGroup.TMP_BLUE_FABRIC_CORRIDOR_MID_ALT);
        gen.registerSimpleCubeAll(TMPGroup.TMP_BLUE_FABRIC_CORRIDOR_PANEL);
        gen.registerSimpleCubeAll(TMPGroup.TMP_BLUE_FABRIC_CORRIDOR_TOP_ALT);
        gen.registerSimpleCubeAll(TMPGroup.TMP_ORANGE_FABRIC_CORRIDOR_MID_ALT);
        gen.registerSimpleCubeAll(TMPGroup.TMP_ORANGE_FABRIC_CORRIDOR_PANEL);
        gen.registerSimpleCubeAll(TMPGroup.TMP_ORANGE_FABRIC_CORRIDOR_TOP_ALT);
        gen.registerSimpleCubeAll(TMPGroup.TMP_WHITE_FABRIC_CORRIDOR_MID_ALT);
        gen.registerSimpleCubeAll(TMPGroup.TMP_WHITE_FABRIC_CORRIDOR_PANEL);
        gen.registerSimpleCubeAll(TMPGroup.TMP_WHITE_FABRIC_CORRIDOR_TOP_ALT);
        gen.registerSimpleCubeAll(TMPGroup.TMP_BLACK_FABRIC_CORRIDOR_MID_ALT);
        gen.registerSimpleCubeAll(TMPGroup.TMP_BLACK_FABRIC_CORRIDOR_PANEL);
        gen.registerSimpleCubeAll(TMPGroup.TMP_BLACK_FABRIC_CORRIDOR_TOP_ALT);
        gen.registerSimpleCubeAll(TMPGroup.TMP_GOLD_CORRIDOR_MID_ALT);
        gen.registerSimpleCubeAll(TMPGroup.TMP_GOLD_CORRIDOR_PANEL);
        gen.registerSimpleCubeAll(TMPGroup.TMP_GOLD_CORRIDOR_TOP_ALT);

        gen.registerSimpleCubeAll(TNGGroup.BEIGE_FABRIC_WALL);
        gen.registerSimpleCubeAll(TNGGroup.BLACK_FABRIC_WALL);
        gen.registerSimpleCubeAll(TNGGroup.BLUE_FABRIC_WALL);
        gen.registerSimpleCubeAll(TNGGroup.BROWN_FABRIC_WALL);
        gen.registerSimpleCubeAll(TNGGroup.CYAN_FABRIC_WALL);
        gen.registerSimpleCubeAll(TNGGroup.DARK_GRAY_FABRIC_WALL);
        gen.registerSimpleCubeAll(TNGGroup.GREEN_FABRIC_WALL);
        gen.registerSimpleCubeAll(TNGGroup.LIGHT_BLUE_FABRIC_WALL);
        gen.registerSimpleCubeAll(TNGGroup.LIGHT_GRAY_FABRIC_WALL);
        gen.registerSimpleCubeAll(TNGGroup.LIGHT_RED_FABRIC_WALL);
        gen.registerSimpleCubeAll(TNGGroup.ORANGE_FABRIC_WALL);
        gen.registerSimpleCubeAll(TNGGroup.PURPLE_FABRIC_WALL);
        gen.registerSimpleCubeAll(TNGGroup.RED_FABRIC_WALL);
        gen.registerSimpleCubeAll(TNGGroup.WHITE_FABRIC_WALL);

        gen.registerSimpleCubeAll(TNGGroup.TNG_TRANSPORTER_WALL_TOP);
        gen.registerSimpleCubeAll(TNGGroup.TNG_TRANSPORTER_WALL_MID);
        gen.registerSimpleCubeAll(TNGGroup.TNG_TRANSPORTER_WALL_BTM);

        gen.registerSimpleCubeAll(TNGGroup.TNG_METAL_FLOOR);
        gen.registerSimpleCubeAll(TNGGroup.GRATING_LIGHT_BLOCK);
        gen.registerSimpleCubeAll(TNGGroup.GAL_BEIGE_CARPET);
        gen.registerSimpleCubeAll(TNGGroup.FOAM_WALL_CUBED);



        //NEM

        gen.registerSimpleCubeAll(NEMGroup.SOV_FLOOR);
        gen.registerSimpleCubeAll(NEMGroup.SOV_NAVY_CARPET);
        gen.registerSimpleCubeAll(NEMGroup.SOV_WALL_BROWN);
        gen.registerSimpleCubeAll(NEMGroup.SOV_WALL_BEIGE);
        gen.registerSimpleCubeAll(NEMGroup.SOV_WALL_BEIGE_DIVOT);
        gen.registerSimpleCubeAll(NEMGroup.SOV_PALE_BLUE_CARPET);
        gen.registerSimpleCubeAll(NEMGroup.BEIGE_FABRIC_PANEL);
        gen.registerSimpleCubeAll(NEMGroup.PROM_FLAT_CORRIDOR_MID);
        gen.registerSimpleCubeAll(NEMGroup.PROM_CORRIDOR_PANEL);
        gen.registerSimpleCubeAll(NEMGroup.PROM_CORRIDOR_BTM_ALT);
        gen.registerSimpleCubeAll(NEMGroup.PROM_CORRIDOR_MID);
        gen.registerSimpleCubeAll(NEMGroup.PROM_CORRIDOR_TOP);
        gen.registerSimpleCubeAll(NEMGroup.PROM_FLAT_CORRIDOR_BTM);


        //Memes


        gen.registerSimpleCubeAll(MemesGroup.SANNIC);
        gen.registerSimpleCubeAll(MemesGroup.STEVEN_OF_NINE);
        gen.registerSimpleCubeAll(MemesGroup.FOCUSED_DATA);
        gen.registerSimpleCubeAll(MemesGroup.IPAN_TRAITOR);
        gen.registerSimpleCubeAll(MemesGroup.MEMECATTHING);
        gen.registerSimpleCubeAll(MemesGroup.LONG_CRUSHER);
        gen.registerSimpleCubeAll(MemesGroup.PINK_DATA);
        gen.registerSimpleCubeAll(MemesGroup.MINION);
        gen.registerSimpleCubeAll(MemesGroup.NEELIXBTM);
        gen.registerSimpleCubeAll(MemesGroup.PANNYNYYTKFSEF);
        gen.registerSimpleCubeAll(MemesGroup.PANNYLAND_MEME);
        gen.registerSimpleCubeAll(MemesGroup.ODO_BLOCK);
        gen.registerSimpleCubeAll(MemesGroup.NEELIX_BLOCK);
        gen.registerSimpleCubeAll(MemesGroup.OIOIOI);
        gen.registerSimpleCubeAll(MemesGroup.CARS_ENTA);
        gen.registerSimpleCubeAll(MemesGroup.PAN_BLOCK);
        gen.registerSimpleCubeAll(MemesGroup.CAPTAINSLOG);
        gen.registerSimpleCubeAll(MemesGroup.ABSOLUTECINEMA);
        gen.registerSimpleCubeAll(MemesGroup.CARS_ENTC);
        gen.registerSimpleCubeAll(ModBlocks.NINE_FISH);


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

            pool.stairs(stairsBlock);
            pool.slab(slabBlock);
            pool.wall(wallBlock);
            gen.registerTrapdoor(trapdoorBlock);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemGen) {
        itemGen.register(Shipyards_ItemsGroup.TNG_COM_BADGE.asItem(), Models.GENERATED);
        itemGen.register(Shipyards_ItemsGroup.TMP_COM_BADGE.asItem(), Models.GENERATED);
        itemGen.register(Shipyards_ItemsGroup.NEM_COM_BADGE.asItem(), Models.GENERATED);

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