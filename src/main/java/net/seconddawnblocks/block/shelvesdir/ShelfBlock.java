package net.seconddawnblocks.block.shelvesdir;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.OptionalInt;

public class ShelfBlock extends BlockWithEntity {

    public static final EnumProperty<ShelfType> TYPE = EnumProperty.of("type", ShelfType.class);
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    private static final MapCodec<ShelfBlock> CODEC = createCodec(ShelfBlock::new);

    private static final float HALF_HITBOX_SIZE = 0.375f * 0.5f;

    // 2px thick collision (2/16 of a block). Make it obvious while testing.
    // You can change "2" to "5" later if you want it thicker.
    private static final int THICKNESS = 2;

    private static final VoxelShape EAST_SHAPE  = Block.createCuboidShape(0, 0, 0, THICKNESS, 16, 16);
    private static final VoxelShape WEST_SHAPE  = Block.createCuboidShape(16 - THICKNESS, 0, 0, 16, 16, 16);
    private static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, THICKNESS);
    private static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0, 0, 16 - THICKNESS, 16, 16, 16);

    public ShelfBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(TYPE, ShelfType.UNPOWERED)
                .with(FACING, Direction.NORTH));
    }

    @Override
    protected @NotNull ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world,
                                                      BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    // Fix "missing ground" by preventing neighbor face culling
    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    // ---- SHAPES (this is what controls collision!) ----

    @Override
    public @NotNull VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShelfShape(state);
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShelfShape(state);
    }

    @Override
    public @NotNull VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        return getShelfShape(state);
    }

    private static VoxelShape getShelfShape(BlockState state) {
        return switch (state.get(FACING)) {
            case WEST -> WEST_SHAPE;
            case EAST -> EAST_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case NORTH -> NORTH_SHAPE;
            default -> NORTH_SHAPE;
        };
    }

    // ---- State / BE ----

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, FACING);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ShelfBlockEntity(pos, state);
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction facing = ctx.getHorizontalPlayerFacing().getOpposite();
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();

        return this.getDefaultState()
                .with(TYPE, getType(facing, world, pos))
                .with(FACING, facing);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
                                                WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        ShelfType type = state.get(TYPE);
        if (type == ShelfType.UNPOWERED) return state;

        Direction facing = state.get(FACING);
        Direction leftDirection = getLeft(facing);
        if (direction.getAxis() != leftDirection.getAxis()) return state;

        boolean left = direction == leftDirection;

        if (!shouldConnect(world, neighborPos, neighborState, facing, direction, left)) {
            if (type == ShelfType.SINGLE) return state;
            if (type == ShelfType.MIDDLE) return state.with(TYPE, left ? ShelfType.RIGHT : ShelfType.LEFT);
            if (left == (type == ShelfType.LEFT)) return state.with(TYPE, ShelfType.SINGLE);
            return state;
        } else {
            if (type == ShelfType.SINGLE) return state.with(TYPE, left ? ShelfType.LEFT : ShelfType.RIGHT);
            if (type == ShelfType.MIDDLE) return state;
            if (left == (type == ShelfType.LEFT)) return state;

            int otherLength = getLength(world, pos, state, facing, direction.getOpposite(), !left);
            if (otherLength == 3) return state;
            return state.with(TYPE, ShelfType.MIDDLE);
        }
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.isClient) return;

        boolean powered = world.isReceivingRedstonePower(pos);
        boolean currentlyUnpowered = (state.get(TYPE) == ShelfType.UNPOWERED);

        if (powered == currentlyUnpowered) {
            ShelfType newType = powered ? getPoweredType(state.get(FACING), world, pos) : ShelfType.UNPOWERED;
            world.setBlockState(pos, state.with(TYPE, newType), Block.NOTIFY_LISTENERS);
        }
    }

    // ---- helpers (unchanged) ----

    private OptionalInt getHitSlot(BlockHitResult hit, BlockState state, BlockPos pos) {
        var hitPos = hit.getPos();
        double localX = hitPos.x - pos.getX();
        double localY = hitPos.y - pos.getY();
        double localZ = hitPos.z - pos.getZ();

        Direction facing = state.get(FACING);

        double xOnFace;
        switch (facing) {
            case NORTH -> xOnFace = 1.0 - localX;
            case SOUTH -> xOnFace = localX;
            case WEST  -> xOnFace = localZ;
            case EAST  -> xOnFace = 1.0 - localZ;
            default -> { return OptionalInt.empty(); }
        }

        double yOnFace = localY;

        if (Math.abs(0.5 - yOnFace) > HALF_HITBOX_SIZE) return OptionalInt.empty();
        if (Math.abs(0.5 - xOnFace) < HALF_HITBOX_SIZE) return OptionalInt.of(ShelfBlockEntity.MIDDLE_SLOT);
        if (xOnFace < 0.5) return OptionalInt.of(ShelfBlockEntity.LEFT_SLOT);
        return OptionalInt.of(ShelfBlockEntity.RIGHT_SLOT);
    }

    public boolean shouldConnect(WorldView world, BlockPos pos, BlockState state, Direction facing, Direction connection, boolean left) {
        if (!state.isOf(this)) return false;
        if (state.get(FACING) != facing) return false;

        ShelfType type = state.get(TYPE);
        if (type == ShelfType.UNPOWERED) return false;
        if (type == ShelfType.SINGLE) return false;
        if (type == (left ? ShelfType.LEFT : ShelfType.RIGHT)) return false;

        return getLength(world, pos, state, facing, connection, left) < 3;
    }

    public ShelfType getType(Direction facing, World world, BlockPos pos) {
        if (!world.isReceivingRedstonePower(pos)) return ShelfType.UNPOWERED;
        return getPoweredType(facing, world, pos);
    }

    public ShelfType getPoweredType(Direction facing, WorldView world, BlockPos pos) {
        int left = Math.min(3, Math.max(0, getNeighborLength(world, pos, facing, true)));
        int right = Math.min(3, Math.max(0, getNeighborLength(world, pos, facing, false)));

        if (left == 0 || left == 3) {
            if (right == 0 || right == 3) return ShelfType.SINGLE;
            return ShelfType.RIGHT;
        } else if (right == 0 || right == 3) return ShelfType.LEFT;

        if (left == 1 && right == 1) return ShelfType.MIDDLE;
        return ShelfType.RIGHT;
    }

    public int getNeighborLength(WorldView world, BlockPos pos, Direction facing, boolean left) {
        Direction connection = getDirection(facing, left);
        BlockPos next = pos.offset(connection);
        BlockState state = world.getBlockState(next);
        return getLength(world, next, state, facing, connection, left);
    }

    public int getLength(WorldView world, BlockPos pos, BlockState state, Direction facing, Direction connection, boolean left) {
        if (!state.isOf(this)) return 0;
        if (state.get(FACING) != facing) return 0;

        ShelfType type = state.get(TYPE);
        if (type == ShelfType.UNPOWERED) return 0;

        if (type == ShelfType.SINGLE) return 1;
        if (left == (type == ShelfType.RIGHT)) return 1;

        BlockState neighbor = world.getBlockState(pos.offset(connection));
        if (!neighbor.isOf(this)) return 1;
        if (neighbor.get(FACING) != facing) return 1;

        ShelfType neighborType = neighbor.get(TYPE);
        if (neighborType == ShelfType.MIDDLE || neighborType == (left ? ShelfType.LEFT : ShelfType.RIGHT)) return 3;

        return 2;
    }

    private static Direction getLeft(Direction facing) {
        return facing.rotateYCounterclockwise();
    }

    private static Direction getRight(Direction facing) {
        return facing.rotateYClockwise();
    }

    private static Direction getDirection(Direction facing, boolean left) {
        return left ? getLeft(facing) : getRight(facing);
    }
}