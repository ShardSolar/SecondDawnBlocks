package net.seconddawnblocks.groups;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
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

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import static net.fabricmc.loader.impl.util.StringUtil.capitalize;

public class PanelGroup {

    /*
     * Keep your full texture-name list here exactly like before.
     * I left only a few examples below so this message stays readable.
     * Paste your full existing list into this array.
     */
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
            "blackblockpanel",
            "lightgrayhalfpanels",
            "cut_lightgrayhalfpanels",
            "lightgrayfullpanel",
            "corridor1",
            "bluemetalplateblock",
            "darkgray_texturedpanel_block",
            "darkgray_texturedstripe",
            "darkgray_halfpanel_ventblock",
            "darkgray_metalpanel",
            "trimetalpanel",
            "polygratesurface",

            // paste the rest of your current list here
            //AstralBlocks

            "black_block",
            "black_chiseled_quartz",
            "black_conduit",
            "black_double_pipe",
            "black_hull",
            "black_large_bolted_pipe",
            "black_large_pipe",
            "black_metal",
            "black_modern_wool",
            "black_paving_stone",
            "black_pipe",
            "black_polished_block",
            "black_quartz",
            "black_quartz_bricks",
            "black_sandstone",
            "black_shingles",
            "black_stained_glass",
            "black_tiles",

            "blue_block",
            "blue_brushed_copper",
            "blue_chiseled_quartz",
            "blue_circuit",
            "blue_complex_circuit",
            "blue_conduit",
            "blue_double_neon_strip",
            "blue_double_pipe",
            "blue_dual_lamp",
            "blue_glowing_conduit",
            "blue_hex_glass",
            "blue_horizontal_glowing_conduit",
            "blue_hull",
            "blue_lamp",
            "blue_large_bolted_pipe",
            "blue_large_pipe",
            "blue_light_pillar",
            "blue_long_plasma_conduit",
            "blue_metal",
            "blue_modern_wool",
            "blue_neon_strip",
            "blue_padded_wool",
            "blue_pipe",
            "blue_plasma_conduit",
            "blue_polished_block",
            "blue_power_bank",
            "blue_quartz",
            "blue_quartz_bricks",
            "blue_sandstone",
            "blue_shingles",
            "blue_stained_glass",
            "blue_tiles",

            "brown_block",
            "brown_chiseled_quartz",
            "brown_metal",
            "brown_modern_wool",
            "brown_padded_wool",
            "brown_polished_block",
            "brown_quartz",
            "brown_quartz_bricks",
            "brown_sandstone",
            "brown_shingles",
            "brown_stained_glass",
            "brown_tiles",

            "brushed_copper",
            "cargo_lift_inner",
            "cargo_lift_side",
            "charcoal_gray_hull",
            "chevron_planks",
            "coal_black_hull",
            "coffee_gray_hull",
            "conduit",
            "conveyor_belt",
            "conveyor_belt_mirrored",
            "crimson_sandstone",

            "cut_black_sandstone",
            "cut_blue_sandstone",
            "cut_brown_sandstone",
            "cut_crimson_sandstone",
            "cut_cyan_sandstone",
            "cut_gray_sandstone",
            "cut_green_sandstone",
            "cut_light_blue_sandstone",
            "cut_light_gray_sandstone",
            "cut_lime_sandstone",
            "cut_magenta_sandstone",
            "cut_orange_sandstone",
            "cut_pink_sandstone",
            "cut_purple_sandstone",
            "cut_white_sandstone",
            "cut_yellow_sandstone",

            "cyan_big_light",
            "cyan_block",
            "cyan_chiseled_quartz",
            "cyan_double_neon_strip",
            "cyan_dual_lamp",
            "cyan_glowing_conduit",
            "cyan_hex_lamp",
            "cyan_horizontal_glowing_conduit",
            "cyan_lamp",
            "cyan_light_pillar",
            "cyan_modern_wool",
            "cyan_neon_strip",
            "cyan_polished_block",
            "cyan_power_bank",
            "cyan_quartz",
            "cyan_quartz_bricks",
            "cyan_sandstone",
            "cyan_server_rack",
            "cyan_shingles",
            "cyan_stained_glass",
            "cyan_tiles",

            "dark_black_hull",
            "dark_black_metal",
            "dark_blue_geo",
            "dark_blue_glowing_conduit",
            "dark_blue_hull",
            "dark_blue_neon_tile",
            "dark_blue_tron",
            "dark_brushed_copper",
            "dark_brushed_metal",
            "dark_corrugated_metal",
            "dark_cyan_geo",
            "dark_cyan_glowing_conduit",
            "dark_cyan_neon_tile",
            "dark_cyan_tron",
            "dark_diamond_metal",
            "dark_gray_hull",
            "dark_gray_metal",
            "dark_green_geo",
            "dark_green_glowing_conduit",
            "dark_green_hull",
            "dark_green_neon_tile",
            "dark_green_tron",
            "dark_indigo_geo",
            "dark_indigo_glowing_conduit",
            "dark_indigo_neon_tile",
            "dark_indigo_tron",
            "dark_lighted_pillar",
            "dark_metal_panel",
            "dark_metal_vent",
            "dark_metallic_hull",
            "dark_modern_bookshelf",
            "dark_modern_drawers",
            "dark_oak_modern_cabinet",
            "dark_orange_geo",
            "dark_orange_glowing_conduit",
            "dark_orange_neon_tile",
            "dark_orange_tron",
            "dark_pink_geo",
            "dark_pink_glowing_conduit",
            "dark_pink_neon_tile",
            "dark_pink_tron",
            "dark_purple_geo",
            "dark_purple_glowing_conduit",
            "dark_purple_neon_tile",
            "dark_purple_tron",
            "dark_radiator",
            "dark_radiator_panel",
            "dark_red_geo",
            "dark_red_glowing_conduit",
            "dark_red_hull",
            "dark_red_neon_tile",
            "dark_red_tron",
            "dark_silver_hull",
            "dark_tile",
            "dark_tool_cabinet",
            "dark_white_geo",
            "dark_white_glowing_conduit",
            "dark_white_hull",
            "dark_white_neon_tile",
            "dark_white_tron",
            "dark_yellow_geo",
            "dark_yellow_glowing_conduit",
            "dark_yellow_hull",
            "dark_yellow_neon_tile",
            "dark_yellow_tron",

            "deflector_blue",
            "deflector_cyan",
            "diamond_decorated_sandstone",
            "dove_gray_hull",
            "dune_gray_hull",
            "emerald_decorated_sandstone",

            "glass",

            "gray_block",
            "gray_blue_hull",
            "gray_blue_neon_tile",
            "gray_brushed_metal",
            "gray_chiseled_quartz",
            "gray_conduit",
            "gray_cyan_neon_tile",
            "gray_double_pipe",
            "gray_green_neon_tile",
            "gray_hex_glass",
            "gray_hull",
            "gray_indigo_neon_tile",
            "gray_large_bolted_pipe",
            "gray_large_pipe",
            "gray_metal",
            "gray_modern_drawers",
            "gray_modern_wool",
            "gray_orange_neon_tile",
            "gray_padded_wool",
            "gray_paving_stone",
            "gray_pink_neon_tile",
            "gray_pipe",
            "gray_polished_block",
            "gray_purple_neon_tile",
            "gray_quartz",
            "gray_quartz_bricks",
            "gray_red_neon_tile",
            "gray_sandstone",
            "gray_shingles",
            "gray_stained_glass",
            "gray_tile",
            "gray_tiles",
            "gray_tool_cabinet",
            "gray_white_neon_tile",
            "gray_yellow_neon_tile",

            "green_big_light",
            "green_block",
            "green_chiseled_quartz",
            "green_circuit",
            "green_complex_circuit",
            "green_conduit",
            "green_double_neon_strip",
            "green_double_pipe",
            "green_dual_lamp",
            "green_glowing_conduit",
            "green_hex_lamp",
            "green_horizontal_glowing_conduit",
            "green_hull",
            "green_large_bolted_pipe",
            "green_large_pipe",
            "green_light_pillar",
            "green_long_plasma_conduit",
            "green_modern_wool",
            "green_neon_strip",
            "green_pipe",
            "green_plasma_conduit",
            "green_polished_block",
            "green_power_bank",
            "green_quartz",
            "green_quartz_bricks",
            "green_sandstone",
            "green_server_rack",
            "green_shingles",
            "green_stained_glass",
            "green_tiles",

            "hazard_cargo_lift",
            "herringbone_planks",

            "indigo_big_light",
            "indigo_conduit",
            "indigo_double_neon_strip",
            "indigo_dual_lamp",
            "indigo_glowing_conduit",
            "indigo_horizontal_glowing_conduit",
            "indigo_lamp",
            "indigo_light_pillar",
            "indigo_neon_strip",
            "indigo_power_bank",
            "indigo_server_rack",

            "knight_gray_hull",
            "lead_hull",
            "light_black_hull",

            "light_blue_block",
            "light_blue_chiseled_quartz",
            "light_blue_double_pipe",
            "light_blue_geo",
            "light_blue_hull",
            "light_blue_large_bolted_pipe",
            "light_blue_large_pipe",
            "light_blue_modern_wool",
            "light_blue_neon_tile",
            "light_blue_pipe",
            "light_blue_polished_block",
            "light_blue_quartz",
            "light_blue_quartz_bricks",
            "light_blue_sandstone",
            "light_blue_shingles",
            "light_blue_stained_glass",
            "light_blue_tiles",
            "light_blue_tron",

            "light_cyan_geo",
            "light_cyan_neon_tile",
            "light_cyan_tron",

            "light_gray_block",
            "light_gray_brushed_metal",
            "light_gray_chiseled_quartz",
            "light_gray_hex_glass",
            "light_gray_hull",
            "light_gray_metal",
            "light_gray_modern_wool",
            "light_gray_padded_wool",
            "light_gray_paving_stone",
            "light_gray_polished_block",
            "light_gray_quartz",
            "light_gray_quartz_bricks",
            "light_gray_sandstone",
            "light_gray_shingles",
            "light_gray_stained_glass",
            "light_gray_tiles",

            "light_green_geo",
            "light_green_neon_tile",
            "light_green_tron",
            "light_indigo_geo",
            "light_indigo_neon_tile",
            "light_indigo_tron",
            "light_metallic_hull",
            "light_modern_bookshelf",
            "light_modern_drawers",
            "light_orange_geo",
            "light_orange_neon_tile",
            "light_orange_tron",
            "light_pink_geo",
            "light_pink_neon_tile",
            "light_pink_tron",
            "light_purple_geo",
            "light_purple_neon_tile",
            "light_purple_tron",
            "light_radiator_panel",
            "light_red_geo",
            "light_red_hull",
            "light_red_neon_tile",
            "light_red_tron",
            "light_silver_hull",
            "light_tile",
            "light_tool_cabinet",
            "light_white_geo",
            "light_white_hull",
            "light_white_neon_tile",
            "light_white_tron",
            "light_yellow_geo",
            "light_yellow_hull",
            "light_yellow_neon_tile",
            "light_yellow_tron",

            "lime_block",
            "lime_chiseled_quartz",
            "lime_modern_wool",
            "lime_polished_block",
            "lime_quartz",
            "lime_quartz_bricks",
            "lime_sandstone",
            "lime_shingles",
            "lime_stained_glass",
            "lime_tiles",

            "magenta_block",
            "magenta_chiseled_quartz",
            "magenta_modern_wool",
            "magenta_polished_block",
            "magenta_quartz",
            "magenta_quartz_bricks",
            "magenta_sandstone",
            "magenta_shingles",
            "magenta_stained_glass",
            "magenta_tiles",

            "metallic_hull",
            "modern_birch_planks",
            "modern_gray_stone_bricks",
            "modern_gray_wooden_boards",
            "modern_large_stone_bricks",
            "modern_marble",
            "modern_marble_bricks",
            "modern_marble_tiles",
            "modern_oak_planks",
            "modern_sandstone_bricks",
            "modern_spruce_planks",
            "modern_stone_bricks",
            "modern_wooden_boards",

            "oak_modern_cabinet",
            "onyx_hull",

            "orange_big_light",
            "orange_block",
            "orange_chiseled_quartz",
            "orange_conduit",
            "orange_double_neon_strip",
            "orange_double_pipe",
            "orange_dual_lamp",
            "orange_glowing_conduit",
            "orange_horizontal_glowing_conduit",
            "orange_large_bolted_pipe",
            "orange_large_pipe",
            "orange_light_pillar",
            "orange_metal",
            "orange_modern_wool",
            "orange_neon_strip",
            "orange_pipe",
            "orange_polished_block",
            "orange_power_bank",
            "orange_quartz",
            "orange_quartz_bricks",
            "orange_sandstone",
            "orange_shingles",
            "orange_stained_glass",
            "orange_tiles",

            "pink_big_light",
            "pink_block",
            "pink_chiseled_quartz",
            "pink_double_neon_strip",
            "pink_dual_lamp",
            "pink_glowing_conduit",
            "pink_hex_lamp",
            "pink_horizontal_glowing_conduit",
            "pink_lamp",
            "pink_light_pillar",
            "pink_modern_wool",
            "pink_neon_strip",
            "pink_polished_block",
            "pink_power_bank",
            "pink_quartz",
            "pink_quartz_bricks",
            "pink_sandstone",
            "pink_server_rack",
            "pink_shingles",
            "pink_stained_glass",
            "pink_tiles",

            "pipe_track",

            "purple_big_light",
            "purple_block",
            "purple_chiseled_quartz",
            "purple_circuit",
            "purple_complex_circuit",
            "purple_conduit",
            "purple_double_neon_strip",
            "purple_double_pipe",
            "purple_dual_lamp",
            "purple_glowing_conduit",
            "purple_hex_lamp",
            "purple_horizontal_glowing_conduit",
            "purple_lamp",
            "purple_large_bolted_pipe",
            "purple_large_pipe",
            "purple_light_pillar",
            "purple_long_plasma_conduit",
            "purple_modern_wool",
            "purple_neon_strip",
            "purple_pipe",
            "purple_plasma_conduit",
            "purple_polished_block",
            "purple_power_bank",
            "purple_quartz",
            "purple_quartz_bricks",
            "purple_sandstone",
            "purple_shingles",
            "purple_stained_glass",
            "purple_tiles",

            "red_block",
            "red_brushed_copper",
            "red_chiseled_quartz",
            "red_circuit",
            "red_complex_circuit",
            "red_conduit",
            "red_double_neon_strip",
            "red_double_pipe",
            "red_dual_lamp",
            "red_glowing_conduit",
            "red_hex_glass",
            "red_horizontal_glowing_conduit",
            "red_hull",
            "red_large_bolted_pipe",
            "red_large_pipe",
            "red_light_pillar",
            "red_long_plasma_conduit",
            "red_metal",
            "red_modern_wool",
            "red_neon_strip",
            "red_padded_wool",
            "red_pipe",
            "red_plasma_conduit",
            "red_polished_block",
            "red_power_bank",
            "red_quartz",
            "red_quartz_bricks",
            "red_server_rack",
            "red_shingles",
            "red_stained_glass",
            "red_tiles",

            "rose_red_hull",
            "shingles",
            "silver_brushed_metal",
            "silver_hull",
            "silver_metal",
            "silver_metallic_hull",
            "sky_blue_hull",
            "slate_bricks",
            "slate_tiles",
            "small_slate_bricks",
            "smooth_modern_marble",
            "stone_gray_hull",
            "tan_gray_hull",
            "tapa_gray_hull",
            "tundora_gray_hull",

            "warm_gray_hull",

            "white_block",
            "white_brushed_metal",
            "white_chiseled_quartz",
            "white_conduit",
            "white_double_neon_strip",
            "white_double_pipe",
            "white_dual_lamp",
            "white_glowing_conduit",
            "white_horizontal_glowing_conduit",
            "white_hull",
            "white_lamp",
            "white_large_bolted_pipe",
            "white_large_pipe",
            "white_light_pillar",
            "white_metal",
            "white_modern_wool",
            "white_neon_strip",
            "white_paving_stone",
            "white_pipe",
            "white_polished_block",
            "white_power_bank",
            "white_quartz",
            "white_quartz_bricks",
            "white_sandstone",
            "white_server_rack",
            "white_shingles",
            "white_silver_hull",
            "white_stained_glass",
            "white_tiles",

            "yellow_block",
            "yellow_chiseled_quartz",
            "yellow_circuit",
            "yellow_complex_circuit",
            "yellow_conduit",
            "yellow_double_neon_strip",
            "yellow_double_pipe",
            "yellow_dual_lamp",
            "yellow_glowing_conduit",
            "yellow_hex_glass",
            "yellow_horizontal_glowing_conduit",
            "yellow_hull",
            "yellow_lamp",
            "yellow_large_bolted_pipe",
            "yellow_large_pipe",
            "yellow_light_pillar",
            "yellow_long_plasma_conduit",
            "yellow_metal",
            "yellow_modern_wool",
            "yellow_neon_strip",
            "yellow_pipe",
            "yellow_plasma_conduit",
            "yellow_polished_block",
            "yellow_power_bank",
            "yellow_quartz",
            "yellow_quartz_bricks",
            "yellow_sandstone",
            "yellow_server_rack",
            "yellow_shingles",
            "yellow_stained_glass",
            "yellow_tiles"
    };

    /*
     * Any texture name placed here will generate its normal family
     * with luminance added to block settings.
     */
    private static final Set<String> EMISSIVE_PANELS = new HashSet<>(Set.of(
            "surfacegray_orangelight",
            "surfacegray_light",
            "surfacewhite_bluelight",
            "surfaceblack_redlight",
            "surfacelightgray_bluelight"
            // add more names here whenever you want them to glow
    ));

    /*
     * Any texture name placed here will also get a door block generated.
     * You can keep this very small and only use it for true door textures.
     */
    private static final Set<String> DOOR_PANELS = new HashSet<>(Set.of(
            "corridor1"
            // add more names here if you want a door version generated
    ));

    /*
     * Default emissive light level for any texture in EMISSIVE_PANELS.
     * Change this if you want brighter or dimmer glowing blocks.
     */
    private static final int DEFAULT_EMISSIVE_LIGHT = 12;

    public static final int NUM_PANELS = PANELS.length;

    public static final Vector<Block> BASE_BLOCKS = new Vector<>();
    public static final Vector<StairsBlock> STAIRS_BLOCKS = new Vector<>();
    public static final Vector<SlabBlock> SLAB_BLOCKS = new Vector<>();
    public static final Vector<WallBlock> WALL_BLOCKS = new Vector<>();
    public static final Vector<TrapdoorBlock> TRAPDOOR_BLOCKS = new Vector<>();

    public static final Vector<Block> LAYER_BLOCKS = new Vector<>();
    public static final Vector<Block> CORNER_STAIRS_BLOCKS = new Vector<>();
    public static final Vector<Block> VERTICAL_SLAB_BLOCKS = new Vector<>();
    public static final Vector<Block> VERTICAL_QUARTER_BLOCKS = new Vector<>();
    public static final Vector<Block> HORIZONTAL_QUARTER_BLOCKS = new Vector<>();

    public static final Vector<DoorBlock> DOOR_BLOCKS = new Vector<>();

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

    private static boolean isEmissive(String panelName) {
        return EMISSIVE_PANELS.contains(panelName);
    }

    private static boolean shouldGenerateDoor(String panelName) {
        return DOOR_PANELS.contains(panelName);
    }

    private static AbstractBlock.Settings createPanelSettings(String panelName) {
        AbstractBlock.Settings settings = AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
                .sounds(BlockSoundGroup.STONE);

        if (isEmissive(panelName)) {
            settings = settings.luminance(state -> DEFAULT_EMISSIVE_LIGHT);
        }

        return settings;
    }

    private static AbstractBlock.Settings createNonOpaquePanelSettings(String panelName) {
        return createPanelSettings(panelName).nonOpaque();
    }

    private static void registerBaseFamily(String panelName) {
        AbstractBlock.Settings solidSettings = createPanelSettings(panelName);
        AbstractBlock.Settings nonOpaqueSettings = createNonOpaquePanelSettings(panelName);

        Block baseBlock = registerBlock(
                panelName,
                new Block(solidSettings)
        );
        BASE_BLOCKS.add(baseBlock);
        PANEL_BLOCKS.add(baseBlock);

        StairsBlock stairsBlock = (StairsBlock) registerBlock(
                panelName + "_stairs",
                new StairsBlock(
                        baseBlock.getDefaultState(),
                        createPanelSettings(panelName)
                )
        );
        STAIRS_BLOCKS.add(stairsBlock);
        PANEL_BLOCKS.add(stairsBlock);

        SlabBlock slabBlock = (SlabBlock) registerBlock(
                panelName + "_slab",
                new SlabBlock(createPanelSettings(panelName))
        );
        SLAB_BLOCKS.add(slabBlock);
        PANEL_BLOCKS.add(slabBlock);

        WallBlock wallBlock = (WallBlock) registerBlock(
                panelName + "_wall",
                new WallBlock(createPanelSettings(panelName))
        );
        WALL_BLOCKS.add(wallBlock);
        PANEL_BLOCKS.add(wallBlock);

        TrapdoorBlock trapdoorBlock = (TrapdoorBlock) registerBlock(
                panelName + "_trapdoor",
                new TrapdoorBlock(
                        BlockSetType.OAK,
                        createNonOpaquePanelSettings(panelName)
                )
        );
        TRAPDOOR_BLOCKS.add(trapdoorBlock);
        PANEL_BLOCKS.add(trapdoorBlock);

        Block layerBlock = registerBlock(
                panelName + "_layer",
                new VerticalLayersBlock(nonOpaqueSettings)
        );
        LAYER_BLOCKS.add(layerBlock);
        PANEL_BLOCKS.add(layerBlock);

        Block cornerStairsBlock = registerBlock(
                panelName + "_corner_stairs",
                new CornerStairs(nonOpaqueSettings)
        );
        CORNER_STAIRS_BLOCKS.add(cornerStairsBlock);
        PANEL_BLOCKS.add(cornerStairsBlock);

        Block verticalSlabBlock = registerBlock(
                panelName + "_vertical_slab",
                new VerticalSlabs(nonOpaqueSettings)
        );
        VERTICAL_SLAB_BLOCKS.add(verticalSlabBlock);
        PANEL_BLOCKS.add(verticalSlabBlock);

        Block verticalQuarterBlock = registerBlock(
                panelName + "_quarter_vertical",
                new VerticalQuarterBlock(nonOpaqueSettings)
        );
        VERTICAL_QUARTER_BLOCKS.add(verticalQuarterBlock);
        PANEL_BLOCKS.add(verticalQuarterBlock);

        Block horizontalQuarterBlock = registerBlock(
                panelName + "_quarter_horizontal",
                new HorizontalQuarterBlock(nonOpaqueSettings)
        );
        HORIZONTAL_QUARTER_BLOCKS.add(horizontalQuarterBlock);
        PANEL_BLOCKS.add(horizontalQuarterBlock);
    }

    private static void registerDoorFamily(String panelName) {
        DoorBlock doorBlock = (DoorBlock) registerBlock(
                panelName + "_door",
                new DoorBlock(
                        BlockSetType.OAK,
                        createNonOpaquePanelSettings(panelName)
                )
        );
        DOOR_BLOCKS.add(doorBlock);
        PANEL_BLOCKS.add(doorBlock);
    }

    public static void registerModBlocks() {
        SecondDawnBlocks.LOGGER.info("Registering Panel Group blocks for " + SecondDawnBlocks.MOD_ID);

        for (String panelName : PANELS) {
            registerBaseFamily(panelName);

            if (shouldGenerateDoor(panelName)) {
                registerDoorFamily(panelName);
            }
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