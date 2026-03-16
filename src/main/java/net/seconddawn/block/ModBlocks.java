package net.seconddawn.block;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.seconddawn.block.cornerstairs.CornerStairs;
import net.seconddawn.SecondDawn;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.seconddawn.block.shelvesdir.ShelvesBlock;
import net.seconddawn.block.verticalslabsdir.VerticalSlabs;
import net.seconddawn.groups.*;

import static net.fabricmc.loader.impl.util.StringUtil.capitalize;


// Below is all registry for every block in the Shipyards mod


public class ModBlocks {

// Stairs

    public static final Block TEST_STAIRS = registerBlock("test_stairs",
            new StairsBlock(TNGGroup.GRAY_FABRIC_WALL.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.WOOL)));

    // Slabs

    public static final Block TEST_SLAB = registerBlock("test_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(2f).requiresTool().sounds(BlockSoundGroup.WOOL)));

    // Walls

    public static final Block TEST_WALL = registerBlock("test_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(2f).requiresTool().sounds(BlockSoundGroup.WOOL)));

    // Fences

    public static final Block TEST_FENCE = registerBlock("test_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(2f).requiresTool().sounds(BlockSoundGroup.WOOL)));

    // Fence Gates

    public static final Block TEST_FENCE_GATE = registerBlock("test_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(2f).requiresTool().sounds(BlockSoundGroup.WOOL)));

    // Doors

    public static final Block TEST_DOOR = registerBlock("test_door",
            new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create().strength(2f).requiresTool().sounds(BlockSoundGroup.WOOL).nonOpaque()));

    // Trapdoors

    public static final Block TEST_TRAPDOOR = registerBlock("test_trapdoor",
            new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.GLOWSTONE).strength(2f).requiresTool().sounds(BlockSoundGroup.WOOL).nonOpaque()));

    // Buttons

    public static final Block TEST_BUTTON = registerBlock("test_button",
            new ButtonBlock(BlockSetType.IRON, 10, AbstractBlock.Settings.create().strength(2f).requiresTool().sounds(BlockSoundGroup.WOOL).noCollision()));

    // Pressure Plate

    public static final Block TEST_PRESSURE_PLATE = registerBlock("test_pressure_plate",
            new PressurePlateBlock(BlockSetType.IRON, AbstractBlock.Settings.create().strength(2f).requiresTool().sounds(BlockSoundGroup.WOOL)));

    // Carpet


    // Layer

    public static final Block TEST_CORRIDOR_LAYER = registerBlock("test_corridor_layer",
            new VerticalLayersBlock(AbstractBlock.Settings.create().strength(2f).requiresTool().sounds(BlockSoundGroup.WOOL)));

    public static final Block TEST_CORRIDOR_LAYER2 = registerBlock("test_corridor_layer2",
            new VerticalLayersBlock(AbstractBlock.Settings.create().strength(2f).requiresTool().sounds(BlockSoundGroup.WOOL)));


    // Vertical Slabs

    public static final Block VERTICAL_TEST_SLAB = registerBlock("vertical_test_slab",
            new VerticalSlabs(AbstractBlock.Settings.copy(Blocks.OAK_SLAB).sounds(BlockSoundGroup.WOOD).strength(2f)) // use strength(), not hardness()
    );

    public static final Block TEST_CORNER_STAIRS = registerBlock("test_corner_stairs",
            new CornerStairs(AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)));

    public static final Block TEST_TILE = registerBlock("test_tile",
            new TileBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));

    public static final Block HORIZONTAL_QUARTER_BLOCK = registerBlock("horizontal_quarter_block",
            new HorizontalQuarterBlock(AbstractBlock.Settings.create().strength(2.0f).requiresTool().nonOpaque())
    );

    public static final Block VERTICAL_QUARTER_BLOCK = registerBlock("vertical_quarter_block",
            new VerticalQuarterBlock(AbstractBlock.Settings.create().strength(2.0f).requiresTool().nonOpaque())
    );


    // Below is all registry for Item Groups in the mod
    // Copy and paste within the Class to make a new group.
    public static final ItemGroup SHIPYARD = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(SecondDawn.MOD_ID, "01shipyard"), //name
            FabricItemGroup.builder()
                    .displayName(Text.literal("Shipyard").formatted(Formatting.GOLD)) //Display in the Creative Menu
                    .icon(() -> new ItemStack(Items.OAK_BOAT))
                    .entries((context, entries) -> {



                        //TEST

                        entries.add(ModBlocks.TEST_CORRIDOR_LAYER.asItem());
                        entries.add(ModBlocks.VERTICAL_TEST_SLAB.asItem());
                        entries.add(ModBlocks.TEST_CORNER_STAIRS.asItem());
                        entries.add(ModBlocks.TEST_TILE.asItem());
                        entries.add(ModBlocks.TEST_STAIRS.asItem());
                        entries.add(ModBlocks.TEST_SLAB.asItem());
                        entries.add(ModBlocks.TEST_DOOR.asItem());
                        entries.add(ModBlocks.TEST_BUTTON.asItem());
                        entries.add(ModBlocks.TEST_TRAPDOOR.asItem());
                        entries.add(ModBlocks.TEST_PRESSURE_PLATE.asItem());
                        entries.add(ModBlocks.TEST_FENCE.asItem());
                        entries.add(ModBlocks.TEST_FENCE_GATE.asItem());
                        entries.add(ModBlocks.TEST_WALL.asItem());

                        //SHELF.

                        entries.add(ShelvesBlock.OAK_SHELF.asItem());
                        entries.add(ShelvesBlock.SPRUCE_SHELF.asItem());
                        entries.add(ShelvesBlock.BIRCH_SHELF.asItem());
                        entries.add(ShelvesBlock.CRIMSON_SHELF.asItem());
                        entries.add(ShelvesBlock.CHERRY_SHELF.asItem());
                        entries.add(ShelvesBlock.JUNGLE_SHELF.asItem());
                        entries.add(ShelvesBlock.ACACIA_SHELF.asItem());
                        entries.add(ShelvesBlock.BAMBOO_SHELF.asItem());
                        entries.add(ShelvesBlock.MANGROVE_SHELF.asItem());
                        entries.add(ShelvesBlock.DARK_OAK_SHELF.asItem());
                        entries.add(ShelvesBlock.WARPED_SHELF.asItem());


                        for (int colourBlockIndex = 0; colourBlockIndex < Flat_ColoursGroup.COLOUR_BLOCKS.size(); colourBlockIndex++) {

                            entries.add(Flat_ColoursGroup.COLOUR_BLOCKS.get(colourBlockIndex).asItem());

                        }


                    })
                    .build()
    );

    //This is base block register format -Note by Shard
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(SecondDawn.MOD_ID, name), block);
    }

    public static void registerBlockItem(String name, Block block) {
             Registry.register(Registries.ITEM, Identifier.of(SecondDawn.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        SecondDawn.LOGGER.info("Registering ModBlocks for " + SecondDawn.MOD_ID);






    }

    public static ItemGroup registerItemGroup(String name) {
        return Registry.register(
                Registries.ITEM_GROUP,
                Identifier.of(SecondDawn.MOD_ID, name),
                FabricItemGroup.builder()
                        .displayName(Text.literal(capitalize(name).formatted(Formatting.GOLD)))
                        .icon(() -> new ItemStack(Items.OAK_BOAT))
                        .entries((context, entries) -> {
                            // Add your mod items here:
                            // entries.add(ModItems.MY_ITEM);
                            // entries.add(ModBlocks.MY_BLOCK_ITEM);
                        })
                        .build()
        );
    }
}


