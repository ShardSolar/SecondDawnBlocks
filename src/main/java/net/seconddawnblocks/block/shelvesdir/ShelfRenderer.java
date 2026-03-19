package net.seconddawnblocks.block.shelvesdir;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class ShelfRenderer implements BlockEntityRenderer<ShelfBlockEntity> {
    private static final float SIZE = 0.375f;

    private final ItemRenderer itemRenderer;

    public ShelfRenderer(BlockEntityRendererFactory.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(ShelfBlockEntity blockEntity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {

        // Prefer your own block's facing property
        Direction facing = blockEntity.getCachedState().get(ShelfBlock.FACING);

        // Used for deterministic item rendering randomness
        int seed = (int) blockEntity.getPos().asLong();

        for (int slot = 0; slot < ShelfBlockEntity.SIZE; slot++) {
            ItemStack stack = blockEntity.getStack(slot);
            if (stack.isEmpty()) continue;

            matrices.push();

            // Center of block
            matrices.translate(0.5f, 0.5f, 0.5f);

            // Rotate so "front" matches facing
            float yaw = -facing.asRotation() + 180.0f;
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yaw));

            // Slot offset left / middle / right
            float x = switch (slot) {
                case ShelfBlockEntity.LEFT_SLOT -> 0.3125f;
                case ShelfBlockEntity.RIGHT_SLOT -> -0.3125f;
                default -> 0.0f;
            };

            // Push items slightly forward on the shelf face
            matrices.translate(x, 0.0f, 0.3f);

            // Scale down item
            matrices.scale(SIZE, SIZE, SIZE);

            // Render the item
            itemRenderer.renderItem(
                    stack,
                    ModelTransformationMode.FIXED,
                    light,
                    overlay,
                    matrices,
                    vertexConsumers,
                    blockEntity.getWorld(),
                    seed + slot
            );

            matrices.pop();
        }
    }
}
