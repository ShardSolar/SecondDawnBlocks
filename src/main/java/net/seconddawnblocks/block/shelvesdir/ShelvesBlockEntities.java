package net.seconddawnblocks.block.shelvesdir;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ShelvesBlockEntities {

    public static final BlockEntityType<ShelfBlockEntity> SHELF = register(
            "shelf",
            ShelfBlockEntity::new,
            ShelvesBlock.OAK_SHELF,
            ShelvesBlock.SPRUCE_SHELF,
            ShelvesBlock.BIRCH_SHELF,
            ShelvesBlock.JUNGLE_SHELF,
            ShelvesBlock.ACACIA_SHELF,
            ShelvesBlock.CHERRY_SHELF,
            ShelvesBlock.DARK_OAK_SHELF,
            ShelvesBlock.MANGROVE_SHELF,
            ShelvesBlock.BAMBOO_SHELF,
            ShelvesBlock.CRIMSON_SHELF,
            ShelvesBlock.WARPED_SHELF
    );

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String path,
            FabricBlockEntityTypeBuilder.Factory<T> factory,
            Block... blocks
    ) {
        return Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Shelves.id(path),
                FabricBlockEntityTypeBuilder.create(factory, blocks).build()
        );
    }

    public static void initialize() {
        // intentionally empty - forces class load from ModInitializer
    }
}