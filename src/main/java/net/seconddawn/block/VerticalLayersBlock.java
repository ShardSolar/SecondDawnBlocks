package net.seconddawn.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class VerticalLayersBlock extends Block {
    public static final MapCodec<VerticalLayersBlock> CODEC = createCodec(VerticalLayersBlock::new);

    public static final int MAX_LAYERS = 8;

    // Layer count (1..8)
    public static final IntProperty LAYERS = Properties.LAYERS;

    // Which face the "sheet" is attached to (UP/DOWN/NORTH/SOUTH/EAST/WEST)
    public static final DirectionProperty FACING = Properties.FACING;

    public VerticalLayersBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(
                this.stateManager.getDefaultState()
                        .with(LAYERS, 1)
                        .with(FACING, Direction.UP)
        );
    }


    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }


    public static VoxelShape getShape(BlockState state) {
        int t = state.get(LAYERS) * 2;
        Direction face = state.get(FACING);

        return switch (face) {
            case UP -> Block.createCuboidShape(0, 0, 0, 16, t, 16);
            case DOWN -> Block.createCuboidShape(0, 16 - t, 0, 16, 16, 16);

            case EAST -> Block.createCuboidShape(0, 0, 0, t, 16, 16);
            case WEST -> Block.createCuboidShape(16 - t, 0, 0, 16, 16, 16);

            case SOUTH -> Block.createCuboidShape(0, 0, 0, 16, 16, t);
            case NORTH -> Block.createCuboidShape(0, 0, 16 - t, 16, 16, 16);
        };
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, net.minecraft.block.ShapeContext context) {
        return getShape(state);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, net.minecraft.block.ShapeContext context) {
        return getShape(state);
    }

    @Override
    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return getShape(state);
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, net.minecraft.block.ShapeContext context) {
        return getShape(state);
    }

    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return state.get(LAYERS) == MAX_LAYERS ? 0.2F : 1.0F;
    }

    @Override
    public BlockState getStateForNeighborUpdate(
            BlockState state,
            Direction direction,
            BlockState neighborState,
            WorldAccess world,
            BlockPos pos,
            BlockPos neighborPos
    ) {
        return !state.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }


    @Override
    protected boolean canReplace(BlockState state, ItemPlacementContext context) {
        int layers = state.get(LAYERS);

        if (context.getStack().isOf(this.asItem()) && layers < MAX_LAYERS) {
            if (!context.canReplaceExisting()) {
                return true;
            }
            // Only stack when clicking the sheet's attachment face
            return context.getSide() == state.get(FACING);
        }

        // Snow-like fallback: allow replacing a single layer with another block.
        return false;
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState existing = ctx.getWorld().getBlockState(ctx.getBlockPos());

        // If placing onto itself, increase thickness
        if (existing.isOf(this)) {
            int layers = existing.get(LAYERS);
            return existing.with(LAYERS, Math.min(MAX_LAYERS, layers + 1));
        }

        // Attach to the face you clicked
        Direction face = ctx.getSide();

        return this.getDefaultState()
                .with(LAYERS, 1)
                .with(FACING, face);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LAYERS, FACING);
    }
}