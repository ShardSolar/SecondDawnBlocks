package net.seconddawnblocks.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class VerticalQuarterBlock extends HorizontalFacingBlock {

    public static final MapCodec<VerticalQuarterBlock> CODEC =
            Block.createCodec(VerticalQuarterBlock::new);

    private static final VoxelShape NORTH_WEST = Block.createCuboidShape(0, 0, 0, 8, 16, 8);
    private static final VoxelShape NORTH_EAST = Block.createCuboidShape(8, 0, 0, 16, 16, 8);
    private static final VoxelShape SOUTH_WEST = Block.createCuboidShape(0, 0, 8, 8, 16, 16);
    private static final VoxelShape SOUTH_EAST = Block.createCuboidShape(8, 0, 8, 16, 16, 16);

    public VerticalQuarterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends VerticalQuarterBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShapeForState(state);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShapeForState(state);
    }

    private static VoxelShape getShapeForState(BlockState state) {
        return switch (state.get(FACING)) {
            case NORTH -> NORTH_WEST;
            case EAST -> NORTH_EAST;
            case SOUTH -> SOUTH_EAST;
            case WEST -> SOUTH_WEST;
            default -> NORTH_WEST;
        };
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        Direction side = ctx.getSide();

        double hitX = ctx.getHitPos().x - pos.getX();
        double hitZ = ctx.getHitPos().z - pos.getZ();

        boolean west;
        boolean north;

        switch (side) {
            case UP, DOWN -> {
                // Top/bottom placement: use actual click position in the block
                west = hitX < 0.5D;
                north = hitZ < 0.5D;
            }

            case WEST -> {
                // West face fixes west-half; Z decides north/south
                west = true;
                north = hitZ < 0.5D;
            }

            case EAST -> {
                // East face fixes east-half; Z decides north/south
                west = false;
                north = hitZ < 0.5D;
            }

            case NORTH -> {
                // North face fixes north-half; X decides west/east
                north = true;
                west = hitX < 0.5D;
            }

            case SOUTH -> {
                // South face fixes south-half; X decides west/east
                north = false;
                west = hitX < 0.5D;
            }

            default -> {
                west = true;
                north = true;
            }
        }

        return this.getDefaultState().with(FACING, getFacingForQuadrant(west, north));
    }

    private static Direction getFacingForQuadrant(boolean west, boolean north) {
        if (north && west) return Direction.NORTH; // NORTH_WEST
        if (north) return Direction.EAST;          // NORTH_EAST
        if (!west) return Direction.SOUTH;         // SOUTH_EAST
        return Direction.WEST;                     // SOUTH_WEST
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }
}