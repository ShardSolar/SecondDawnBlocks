package net.seconddawn.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class HorizontalQuarterBlock extends Block {

    public static final MapCodec<HorizontalQuarterBlock> CODEC =
            Block.createCodec(HorizontalQuarterBlock::new);

    public static final EnumProperty<Direction.Axis> AXIS = Properties.HORIZONTAL_AXIS;
    public static final EnumProperty<QuarterPart> PART = EnumProperty.of("part", QuarterPart.class);

    public enum QuarterPart implements StringIdentifiable {
        BOTTOM_LEFT("bottom_left"),
        BOTTOM_RIGHT("bottom_right"),
        TOP_RIGHT("top_right"),
        TOP_LEFT("top_left");

        private final String name;

        QuarterPart(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return this.name;
        }

        public QuarterPart rotateClockwise() {
            return switch (this) {
                case BOTTOM_LEFT -> BOTTOM_RIGHT;
                case BOTTOM_RIGHT -> TOP_RIGHT;
                case TOP_RIGHT -> TOP_LEFT;
                case TOP_LEFT -> BOTTOM_LEFT;
            };
        }

        public QuarterPart rotateCounterclockwise() {
            return switch (this) {
                case BOTTOM_LEFT -> TOP_LEFT;
                case TOP_LEFT -> TOP_RIGHT;
                case TOP_RIGHT -> BOTTOM_RIGHT;
                case BOTTOM_RIGHT -> BOTTOM_LEFT;
            };
        }

        public QuarterPart rotate180() {
            return switch (this) {
                case BOTTOM_LEFT -> TOP_RIGHT;
                case BOTTOM_RIGHT -> TOP_LEFT;
                case TOP_RIGHT -> BOTTOM_LEFT;
                case TOP_LEFT -> BOTTOM_RIGHT;
            };
        }

        public QuarterPart mirrorLeftRight() {
            return switch (this) {
                case BOTTOM_LEFT -> BOTTOM_RIGHT;
                case BOTTOM_RIGHT -> BOTTOM_LEFT;
                case TOP_LEFT -> TOP_RIGHT;
                case TOP_RIGHT -> TOP_LEFT;
            };
        }
    }

    // AXIS = X -> full X, split by Z and Y
    private static final VoxelShape X_NORTH_BOTTOM = Block.createCuboidShape(0, 0, 0, 16, 8, 8);
    private static final VoxelShape X_SOUTH_BOTTOM = Block.createCuboidShape(0, 0, 8, 16, 8, 16);
    private static final VoxelShape X_SOUTH_TOP    = Block.createCuboidShape(0, 8, 8, 16, 16, 16);
    private static final VoxelShape X_NORTH_TOP    = Block.createCuboidShape(0, 8, 0, 16, 16, 8);

    // AXIS = Z -> full Z, split by X and Y
    private static final VoxelShape Z_WEST_BOTTOM  = Block.createCuboidShape(0, 0, 0, 8, 8, 16);
    private static final VoxelShape Z_EAST_BOTTOM  = Block.createCuboidShape(8, 0, 0, 16, 8, 16);
    private static final VoxelShape Z_EAST_TOP     = Block.createCuboidShape(8, 8, 0, 16, 16, 16);
    private static final VoxelShape Z_WEST_TOP     = Block.createCuboidShape(0, 8, 0, 8, 16, 16);

    public HorizontalQuarterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(AXIS, Direction.Axis.X)
                .with(PART, QuarterPart.BOTTOM_LEFT));
    }

    @Override
    protected MapCodec<? extends HorizontalQuarterBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS, PART);
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
        Direction.Axis axis = state.get(AXIS);
        QuarterPart part = state.get(PART);

        return switch (axis) {
            case X -> switch (part) {
                case BOTTOM_LEFT -> X_NORTH_BOTTOM;
                case BOTTOM_RIGHT -> X_SOUTH_BOTTOM;
                case TOP_RIGHT -> X_SOUTH_TOP;
                case TOP_LEFT -> X_NORTH_TOP;
            };
            case Z -> switch (part) {
                case BOTTOM_LEFT -> Z_WEST_BOTTOM;
                case BOTTOM_RIGHT -> Z_EAST_BOTTOM;
                case TOP_RIGHT -> Z_EAST_TOP;
                case TOP_LEFT -> Z_WEST_TOP;
            };
            default -> X_NORTH_BOTTOM;
        };
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        Direction side = ctx.getSide();

        double hitX = ctx.getHitPos().x - pos.getX();
        double hitY = ctx.getHitPos().y - pos.getY();
        double hitZ = ctx.getHitPos().z - pos.getZ();

        Direction.Axis axis;
        QuarterPart part;

        switch (side) {
            case WEST, EAST -> {
                axis = Direction.Axis.X;

                boolean left = hitZ < 0.5D; // north half
                boolean top = hitY >= 0.5D;

                part = getPart(left, top);
            }

            case NORTH, SOUTH -> {
                axis = Direction.Axis.Z;

                boolean left = hitX < 0.5D; // west half
                boolean top = hitY >= 0.5D;

                part = getPart(left, top);
            }

            case UP, DOWN -> {
                boolean top = side == Direction.DOWN;

                double distX = Math.abs(hitX - 0.5D);
                double distZ = Math.abs(hitZ - 0.5D);

                if (distZ >= distX) {
                    axis = Direction.Axis.X;
                    boolean left = hitZ < 0.5D; // north half
                    part = getPart(left, top);
                } else {
                    axis = Direction.Axis.Z;
                    boolean left = hitX < 0.5D; // west half
                    part = getPart(left, top);
                }
            }

            default -> {
                axis = Direction.Axis.X;
                part = QuarterPart.BOTTOM_LEFT;
            }
        }

        return this.getDefaultState()
                .with(AXIS, axis)
                .with(PART, part);
    }

    private static QuarterPart getPart(boolean left, boolean top) {
        if (!top && left) return QuarterPart.BOTTOM_LEFT;
        if (!top) return QuarterPart.BOTTOM_RIGHT;
        if (left) return QuarterPart.TOP_LEFT;
        return QuarterPart.TOP_RIGHT;
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        Direction.Axis axis = state.get(AXIS);
        QuarterPart part = state.get(PART);

        return switch (rotation) {
            case NONE -> state;
            case CLOCKWISE_180 -> state
                    .with(PART, part.rotate180());

            case CLOCKWISE_90 -> state
                    .with(AXIS, axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X)
                    .with(PART, part.rotateClockwise());

            case COUNTERCLOCKWISE_90 -> state
                    .with(AXIS, axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X)
                    .with(PART, part.rotateCounterclockwise());
        };
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        Direction.Axis axis = state.get(AXIS);
        QuarterPart part = state.get(PART);

        QuarterPart mirroredPart = switch (mirror) {
            case NONE -> part;

            case FRONT_BACK -> {
                // Mirror north<->south
                if (axis == Direction.Axis.X) {
                    yield part.mirrorLeftRight();
                }
                yield part;
            }

            case LEFT_RIGHT -> {
                // Mirror west<->east
                if (axis == Direction.Axis.Z) {
                    yield part.mirrorLeftRight();
                }
                yield part;
            }
        };

        return state.with(PART, mirroredPart);
    }
}