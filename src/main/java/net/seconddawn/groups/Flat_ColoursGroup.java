package net.seconddawn.groups;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.seconddawn.SecondDawn;
import net.seconddawn.block.HorizontalQuarterBlock;
import net.seconddawn.block.VerticalQuarterBlock;
import net.seconddawn.block.VerticalLayersBlock;
import net.seconddawn.block.cornerstairs.CornerStairs;
import net.seconddawn.block.verticalslabsdir.VerticalSlabs;

import java.util.Vector;

import static net.fabricmc.loader.impl.util.StringUtil.capitalize;

public class Flat_ColoursGroup {

    public static final String[] COLOURS = {
            "beige_0",
            "beige_1",
            "beige_2",
            "beige_3",
            "beige_4",
            "beige_5",
            "beige_6",
            "beige_7",
            "beige_8",
            "black_0",
            "black_1",
            "black_2",
            "black_3",
            "blue_0",
            "blue_1",
            "blue_2",
            "blue_3",
            "blue_4",
            "blue_5",
            "blue_6",
            "blue_7",
            "blue_8",
            "bluegrey_0",
            "bluegrey_1",
            "bluegrey_2",
            "bluegrey_3",
            "bluegrey_4",
            "bluegrey_5",
            "bluegrey_6",
            "bluegrey_7",
            "bluegrey_8",
            "brown_0",
            "brown_1",
            "brown_2",
            "brown_3",
            "brown_4",
            "brown_5",
            "brown_6",
            "brown_7",
            "brown_8",
            "darkaquamarine_0",
            "darkaquamarine_1",
            "darkaquamarine_2",
            "darkaquamarine_3",
            "darkaquamarine_4",
            "darkaquamarine_5",
            "darkaquamarine_6",
            "darkaquamarine_7",
            "darkaquamarine_8",
            "darkblue_0",
            "darkblue_1",
            "darkblue_2",
            "darkblue_3",
            "darkblue_4",
            "darkblue_5",
            "darkblue_6",
            "darkblue_7",
            "darkblue_8",
            "darkgrey_0",
            "darkgrey_1",
            "darkgrey_2",
            "darkteal_0",
            "darkteal_1",
            "darkteal_2",
            "darkteal_3",
            "darkteal_4",
            "darkteal_5",
            "darkteal_6",
            "darkteal_7",
            "darkteal_8",
            "ferngreen_0",
            "ferngreen_1",
            "ferngreen_2",
            "ferngreen_3",
            "ferngreen_4",
            "ferngreen_5",
            "ferngreen_6",
            "ferngreen_7",
            "ferngreen_8",
            "gold_0",
            "gold_1",
            "gold_2",
            "gold_3",
            "gold_4",
            "gold_5",
            "gold_6",
            "gold_7",
            "gold_8",
            "gray_0",
            "gray_1",
            "gray_2",
            "green_0",
            "green_1",
            "green_2",
            "green_3",
            "green_4",
            "green_5",
            "green_6",
            "green_7",
            "green_8",
            "greygreenblue_0",
            "greygreenblue_1",
            "greygreenblue_2",
            "greygreenblue_3",
            "greygreenblue_4",
            "greygreenblue_5",
            "greygreenblue_6",
            "greygreenblue_7",
            "greygreenblue_8",
            "hull_bluegrey_0",
            "hull_bluegrey_1",
            "hull_bluegrey_2",
            "hull_pale_0",
            "hull_pale_1",
            "hull_pale_2",
            "hull_whiteblue_0",
            "hull_whiteblue_1",
            "hull_whiteblue_2",
            "lightblue_0",
            "lightblue_1",
            "lightblue_2",
            "lightblue_3",
            "lightblue_4",
            "lightblue_5",
            "lightblue_6",
            "lightblue_7",
            "lightblue_8",
            "lightbrown_0",
            "lightbrown_1",
            "lightbrown_2",
            "lightbrown_3",
            "lightbrown_4",
            "lightbrown_5",
            "lightbrown_6",
            "lightbrown_7",
            "lightbrown_8",
            "lightmaroon_0",
            "lightmaroon_1",
            "lightmaroon_2",
            "lightmaroon_3",
            "lightmaroon_4",
            "lightmaroon_5",
            "lightmaroon_6",
            "lightmaroon_7",
            "lightmaroon_8",
            "neonpink_0",
            "neonpink_1",
            "neonpink_2",
            "neonpink_3",
            "neonpink_4",
            "neonpink_5",
            "neonpink_6",
            "neonpink_7",
            "neonpink_8",
            "neonred_0",
            "neonred_1",
            "neonred_2",
            "neonred_3",
            "neonred_4",
            "neonred_5",
            "neonred_6",
            "neonred_7",
            "neonred_8",
            "orange_0",
            "orange_1",
            "orange_2",
            "orange_3",
            "orange_4",
            "orange_5",
            "orange_6",
            "orange_7",
            "orange_8",
            "paleorange_0",
            "paleorange_1",
            "paleorange_2",
            "paleorange_3",
            "paleorange_4",
            "paleorange_5",
            "paleorange_6",
            "paleorange_7",
            "paleorange_8",
            "purple_0",
            "purple_1",
            "purple_2",
            "purple_3",
            "purple_4",
            "purple_5",
            "purple_6",
            "purple_7",
            "purple_8",
            "red_0",
            "red_1",
            "red_2",
            "red_3",
            "red_4",
            "red_5",
            "red_6",
            "red_7",
            "red_8",
            "teal_0",
            "teal_1",
            "teal_2",
            "tealgrey_0",
            "tealgrey_1",
            "tealgrey_2",
            "tealgrey_3",
            "tealgrey_4",
            "tealgrey_5",
            "tealgrey_6",
            "tealgrey_7",
            "tealgrey_8",
            "white_0",
            "white_1",
            "white_2",
            "yellow_0",
            "yellow_1",
            "yellow_2",
            "yellow_3",
            "yellow_4",
            "yellow_5",
            "yellow_6",
            "yellow_7",
            "yellow_8"
    };

    public static final int NUM_COLOURS = COLOURS.length;

    public static final Vector<Block> BASE_BLOCKS = new Vector<>();
    public static final Vector<Block> STAIRS_BLOCKS = new Vector<>();
    public static final Vector<Block> SLAB_BLOCKS = new Vector<>();
    public static final Vector<Block> WALL_BLOCKS = new Vector<>();
    public static final Vector<Block> TRAPDOOR_BLOCKS = new Vector<>();
    public static final Vector<Block> LAYER_BLOCKS = new Vector<>();
    public static final Vector<Block> CORNER_STAIRS_BLOCKS = new Vector<>();
    public static final Vector<Block> VERTICAL_SLAB_BLOCKS = new Vector<>();
    public static final Vector<Block> VERTICAL_QUARTER_BLOCKS = new Vector<>();
    public static final Vector<Block> HORIZONTAL_QUARTER_BLOCKS = new Vector<>();

    public static final Vector<Block> COLOUR_BLOCKS = new Vector<>();

    public static ItemGroup FLAT_COLOURS;

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(SecondDawn.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(
                Registries.ITEM,
                Identifier.of(SecondDawn.MOD_ID, name),
                new BlockItem(block, new Item.Settings())
        );
    }

    public static void registerModBlocks() {
        SecondDawn.LOGGER.info("Registering Flat Colours blocks for " + SecondDawn.MOD_ID);

        for (int colorIndex = 0; colorIndex < NUM_COLOURS; colorIndex++) {
            String colourName = COLOURS[colorIndex];

            Block baseBlock = registerBlock(
                    colourName,
                    new Block(
                            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.STONE)
                    )
            );
            BASE_BLOCKS.add(baseBlock);
            COLOUR_BLOCKS.add(baseBlock);

            Block stairsBlock = registerBlock(
                    colourName + "_stairs",
                    new StairsBlock(
                            baseBlock.getDefaultState(),
                            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.STONE)
                    )
            );
            STAIRS_BLOCKS.add(stairsBlock);
            COLOUR_BLOCKS.add(stairsBlock);

            Block slabBlock = registerBlock(
                    colourName + "_slab",
                    new SlabBlock(
                            AbstractBlock.Settings.create()
                                    .strength(2f)
                                    .requiresTool()
                                    .sounds(BlockSoundGroup.STONE)
                    )
            );
            SLAB_BLOCKS.add(slabBlock);
            COLOUR_BLOCKS.add(slabBlock);

            Block wallBlock = registerBlock(
                    colourName + "_wall",
                    new WallBlock(
                            AbstractBlock.Settings.create()
                                    .strength(2f)
                                    .requiresTool()
                                    .sounds(BlockSoundGroup.STONE)
                    )
            );
            WALL_BLOCKS.add(wallBlock);
            COLOUR_BLOCKS.add(wallBlock);

            Block trapdoorBlock = registerBlock(
                    colourName + "_trapdoor",
                    new TrapdoorBlock(
                            BlockSetType.OAK,
                            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
                                    .strength(2f)
                                    .requiresTool()
                                    .sounds(BlockSoundGroup.STONE)
                                    .nonOpaque()
                    )
            );
            TRAPDOOR_BLOCKS.add(trapdoorBlock);
            COLOUR_BLOCKS.add(trapdoorBlock);

            Block layerBlock = registerBlock(
                    colourName + "_layer",
                    new VerticalLayersBlock(
                            AbstractBlock.Settings.create()
                                    .strength(2f)
                                    .requiresTool()
                                    .sounds(BlockSoundGroup.STONE)
                    )
            );
            LAYER_BLOCKS.add(layerBlock);
            COLOUR_BLOCKS.add(layerBlock);

            Block cornerStairsBlock = registerBlock(
                    colourName + "_corner_stairs",
                    new CornerStairs(
                            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
                                    .sounds(BlockSoundGroup.STONE)
                    )
            );
            CORNER_STAIRS_BLOCKS.add(cornerStairsBlock);
            COLOUR_BLOCKS.add(cornerStairsBlock);

            Block verticalSlabBlock = registerBlock(
                    colourName + "_vertical_slab",
                    new VerticalSlabs(
                            AbstractBlock.Settings.create()
                                    .strength(2f)
                                    .requiresTool()
                                    .sounds(BlockSoundGroup.STONE)
                                    .nonOpaque()
                    )
            );
            VERTICAL_SLAB_BLOCKS.add(verticalSlabBlock);
            COLOUR_BLOCKS.add(verticalSlabBlock);

            Block verticalQuarterBlock = registerBlock(
                    colourName + "_quarter_vertical",
                    new VerticalQuarterBlock(
                            AbstractBlock.Settings.create()
                                    .strength(2f)
                                    .requiresTool()
                                    .sounds(BlockSoundGroup.STONE)
                                    .nonOpaque()
                    )
            );
            VERTICAL_QUARTER_BLOCKS.add(verticalQuarterBlock);
            COLOUR_BLOCKS.add(verticalQuarterBlock);

            Block horizontalQuarterBlock = registerBlock(
                    colourName + "_quarter_horizontal",
                    new HorizontalQuarterBlock(
                            AbstractBlock.Settings.create()
                                    .strength(2f)
                                    .requiresTool()
                                    .sounds(BlockSoundGroup.STONE)
                                    .nonOpaque()
                    )
            );
            HORIZONTAL_QUARTER_BLOCKS.add(horizontalQuarterBlock);
            COLOUR_BLOCKS.add(horizontalQuarterBlock);
        }

        FLAT_COLOURS = Registry.register(
                Registries.ITEM_GROUP,
                Identifier.of(SecondDawn.MOD_ID, "07flatcolours"),
                FabricItemGroup.builder()
                        .displayName(Text.literal("Flat Colours").formatted(Formatting.GOLD))
                        .icon(() -> new ItemStack(Blocks.BLUE_WOOL))
                        .entries((context, entries) -> {
                            for (Block block : COLOUR_BLOCKS) {
                                entries.add(block.asItem());
                            }
                        })
                        .build()
        );

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
            for (Block block : BASE_BLOCKS) {
                fabricItemGroupEntries.add(block);
            }
        });
    }

    public static ItemGroup registerItemGroup(String name) {
        return Registry.register(
                Registries.ITEM_GROUP,
                Identifier.of(SecondDawn.MOD_ID, name),
                FabricItemGroup.builder()
                        .displayName(Text.literal(capitalize(name)).formatted(Formatting.GOLD))
                        .icon(() -> new ItemStack(Items.OAK_BOAT))
                        .entries((context, entries) -> {
                        })
                        .build()
        );
    }
}