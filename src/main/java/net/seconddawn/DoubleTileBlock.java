package net.seconddawn;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.block.enums.DoubleBlockHalf;

public class DoubleTileBlock extends Block {
    public static final MapCodec<DoubleTileBlock> CODEC = createCodec(DoubleTileBlock::new);

    public static final int MAX_LAYERS = 2;

    // Custom layers property so blockstates are ONLY 1..2 (no missing variants)
    public static final IntProperty LAYERS = IntProperty.of("layers", 1, MAX_LAYERS);
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    // Which face the "sheet" is attached to (UP/DOWN/NORTH/SOUTH/EAST/WEST)
    public static final DirectionProperty FACING = Properties.FACING;

    public DoubleTileBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.stateManager.getDefaultState()
                        .with(LAYERS, 1)
                        .with(FACING, Direction.UP)
                        .with(HALF, DoubleBlockHalf.LOWER)
        );
    }

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    public static VoxelShape getShape(BlockState state) {
        int t = state.get(LAYERS);
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
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, net.minecraft.block.ShapeContext ctx) {
        Direction face = state.get(FACING);
        if (face == Direction.UP || face == Direction.DOWN) {
            return Block.createCuboidShape(0, 0, 0, 16, 16, 16);
        }
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
            BlockState state, Direction direction, BlockState neighborState,
            WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        DoubleBlockHalf half = state.get(HALF);

        // If our counterpart half disappeared, remove this one too
        if (direction == Direction.UP && half == DoubleBlockHalf.LOWER) {
            if (!neighborState.isOf(this) || neighborState.get(HALF) != DoubleBlockHalf.UPPER) {
                return Blocks.AIR.getDefaultState();
            }
        }
        if (direction == Direction.DOWN && half == DoubleBlockHalf.UPPER) {
            if (!neighborState.isOf(this) || neighborState.get(HALF) != DoubleBlockHalf.LOWER) {
                return Blocks.AIR.getDefaultState();
            }
        }

        // If you rely on canPlaceAt for support checks, keep this:
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
        return layers == 1;
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockState existing = world.getBlockState(pos);

        // 1) If clicking an existing double tile, stack layers on BOTH halves and cancel placement
        if (existing.isOf(this)) {
            BlockPos basePos = existing.get(HALF) == DoubleBlockHalf.LOWER ? pos : pos.down();
            BlockState baseState = world.getBlockState(basePos);

            int newLayers = Math.min(MAX_LAYERS, baseState.get(LAYERS) + 1);

            BlockState newBase = baseState.with(LAYERS, newLayers).with(HALF, DoubleBlockHalf.LOWER);
            BlockState newTop  = baseState.with(LAYERS, newLayers).with(HALF, DoubleBlockHalf.UPPER);

            if (!world.isClient) {
                world.setBlockState(basePos, newBase, Block.NOTIFY_ALL);
                world.setBlockState(basePos.up(), newTop, Block.NOTIFY_ALL);
            }
            return null; // cancel normal placement; we already updated
        }

        // 2) New placement: must have room for upper half
        if (!world.getBlockState(pos.up()).canReplace(ctx)) return null;

        Direction face = ctx.getSide();

        return this.getDefaultState()
                .with(LAYERS, 1)
                .with(FACING, face)
                .with(HALF, DoubleBlockHalf.LOWER);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state,
                         net.minecraft.entity.LivingEntity placer,
                         net.minecraft.item.ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);

        if (!world.isClient && state.get(HALF) == DoubleBlockHalf.LOWER) {
            world.setBlockState(pos.up(), state.with(HALF, DoubleBlockHalf.UPPER), Block.NOTIFY_ALL);
        }
    }

    @Override
    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        Direction face = state.get(FACING);

        // Make floor/ceiling variants easy to click so stacking hits the existing block,
        // not the air above it.
        if (face == Direction.UP || face == Direction.DOWN) {
            return Block.createCuboidShape(0, 0, 0, 16, 16, 16); // full block for raycast only
        }

        // Walls are already easy to hit with your normal shape
        return getShape(state);
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        DoubleBlockHalf half = state.get(HALF);
        BlockPos otherPos = (half == DoubleBlockHalf.LOWER) ? pos.up() : pos.down();
        BlockState other = world.getBlockState(otherPos);

        if (other.isOf(this) && other.get(HALF) != half) {
            world.breakBlock(otherPos, false, player);
        }

        super.onBreak(world, pos, state, player);
        return other;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LAYERS, FACING, HALF);
    }
}