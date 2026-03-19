package net.seconddawnblocks.groups;

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
import net.seconddawnblocks.SecondDawnBlocks;
import net.seconddawnblocks.block.HorizontalQuarterBlock;
import net.seconddawnblocks.block.VerticalLayersBlock;
import net.seconddawnblocks.block.VerticalQuarterBlock;
import net.seconddawnblocks.block.cornerstairs.CornerStairs;
import net.seconddawnblocks.block.verticalslabsdir.VerticalSlabs;

import java.util.Vector;

import static net.fabricmc.loader.impl.util.StringUtil.capitalize;

public class PanelGroup {

    public static final String[] PANELS = {
            "surfacegray_orangelight",
            "alt_oak_planks",
            "surfacelightgray_redstripe",
            "surfacegray_light",
            "surfacewhite_bluelight",
            "surfaceblack_redlight",
            "surfacelightgray_bluelight",
            "strippedgraypanel",
            "grayborder_redpanel",
            "techblock1",
            "ventblock",
            "grayfabric_lowblackline",
            "blackblockpanel"

    };

    public static final int NUM_PANELS = PANELS.length;

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

    public static final Vector<Block> PANEL_BLOCKS = new Vector<>();

    public static ItemGroup PANELS_GROUP;

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(SecondDawnBlocks.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(
                Registries.ITEM,
                Identifier.of(SecondDawnBlocks.MOD_ID, name),
                new BlockItem(block, new Item.Settings())
        );
    }

    public static void registerModBlocks() {
        SecondDawnBlocks.LOGGER.info("Registering Panel Group blocks for " + SecondDawnBlocks.MOD_ID);

        for (int panelIndex = 0; panelIndex < NUM_PANELS; panelIndex++) {
            String panelName = PANELS[panelIndex];

            Block baseBlock = registerBlock(
                    panelName,
                    new Block(
                            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
                                    .sounds(BlockSoundGroup.STONE)
                    )
            );
            BASE_BLOCKS.add(baseBlock);
            PANEL_BLOCKS.add(baseBlock);

            Block stairsBlock = registerBlock(
                    panelName + "_stairs",
                    new StairsBlock(
                            baseBlock.getDefaultState(),
                            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
                                    .sounds(BlockSoundGroup.STONE)
                    )
            );
            STAIRS_BLOCKS.add(stairsBlock);
            PANEL_BLOCKS.add(stairsBlock);

            Block slabBlock = registerBlock(
                    panelName + "_slab",
                    new SlabBlock(
                            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
                                    .sounds(BlockSoundGroup.STONE)
                    )
            );
            SLAB_BLOCKS.add(slabBlock);
            PANEL_BLOCKS.add(slabBlock);

            Block wallBlock = registerBlock(
                    panelName + "_wall",
                    new WallBlock(
                            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
                                    .sounds(BlockSoundGroup.STONE)
                    )
            );
            WALL_BLOCKS.add(wallBlock);
            PANEL_BLOCKS.add(wallBlock);

            Block trapdoorBlock = registerBlock(
                    panelName + "_trapdoor",
                    new TrapdoorBlock(
                            BlockSetType.OAK,
                            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
                                    .sounds(BlockSoundGroup.STONE)
                                    .nonOpaque()
                    )
            );
            TRAPDOOR_BLOCKS.add(trapdoorBlock);
            PANEL_BLOCKS.add(trapdoorBlock);

            Block layerBlock = registerBlock(
                    panelName + "_layer",
                    new VerticalLayersBlock(
                            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
                                    .sounds(BlockSoundGroup.STONE)
                                    .nonOpaque()
                    )
            );
            LAYER_BLOCKS.add(layerBlock);
            PANEL_BLOCKS.add(layerBlock);

            Block cornerStairsBlock = registerBlock(
                    panelName + "_corner_stairs",
                    new CornerStairs(
                            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
                                    .sounds(BlockSoundGroup.STONE)
                                    .nonOpaque()
                    )
            );
            CORNER_STAIRS_BLOCKS.add(cornerStairsBlock);
            PANEL_BLOCKS.add(cornerStairsBlock);

            Block verticalSlabBlock = registerBlock(
                    panelName + "_vertical_slab",
                    new VerticalSlabs(
                            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
                                    .sounds(BlockSoundGroup.STONE)
                                    .nonOpaque()
                    )
            );
            VERTICAL_SLAB_BLOCKS.add(verticalSlabBlock);
            PANEL_BLOCKS.add(verticalSlabBlock);

            Block verticalQuarterBlock = registerBlock(
                    panelName + "_quarter_vertical",
                    new VerticalQuarterBlock(
                            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
                                    .sounds(BlockSoundGroup.STONE)
                                    .nonOpaque()
                    )
            );
            VERTICAL_QUARTER_BLOCKS.add(verticalQuarterBlock);
            PANEL_BLOCKS.add(verticalQuarterBlock);

            Block horizontalQuarterBlock = registerBlock(
                    panelName + "_quarter_horizontal",
                    new HorizontalQuarterBlock(
                            AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
                                    .sounds(BlockSoundGroup.STONE)
                                    .nonOpaque()
                    )
            );
            HORIZONTAL_QUARTER_BLOCKS.add(horizontalQuarterBlock);
            PANEL_BLOCKS.add(horizontalQuarterBlock);
        }

        PANELS_GROUP = Registry.register(
                Registries.ITEM_GROUP,
                Identifier.of(SecondDawnBlocks.MOD_ID, "08panels"),
                FabricItemGroup.builder()
                        .displayName(Text.literal("Panels").formatted(Formatting.GOLD))
                        .icon(() -> new ItemStack(Items.IRON_BLOCK))
                        .entries((context, entries) -> {
                            for (Block block : PANEL_BLOCKS) {
                                entries.add(block.asItem());
                            }
                        })
                        .build()
        );

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            for (Block block : BASE_BLOCKS) {
                entries.add(block);
            }
        });
    }

    public static ItemGroup registerItemGroup(String name) {
        return Registry.register(
                Registries.ITEM_GROUP,
                Identifier.of(SecondDawnBlocks.MOD_ID, name),
                FabricItemGroup.builder()
                        .displayName(Text.literal(capitalize(name)).formatted(Formatting.GOLD))
                        .icon(() -> new ItemStack(Items.IRON_BLOCK))
                        .entries((context, entries) -> {
                        })
                        .build()
        );
    }
}