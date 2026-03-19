package net.seconddawn.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.seconddawn.SecondDawnBlocks;
import net.seconddawn.block.VerticalLayersBlock;
import net.seconddawn.block.verticalslabsdir.VerticalSlabPool;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class VerticalLayerProvider implements DataProvider {

    private final FabricDataOutput output;

    public VerticalLayerProvider(FabricDataOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        ModVerticalLayerPools.register();

        // Vertical layers
        futures.addAll(generateVerticalLayersFromPool(writer, ModVerticalLayerPools.BLOCK));

        // Corner stairs
        futures.addAll(generateCornerStairsFromPool(writer, ModVerticalLayerPools.CORNER_STAIRS));

        // Vertical slabs
        futures.addAll(generateVerticalSlabsFromPool(writer, ModVerticalLayerPools.VERTICAL_SLABS));

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    @Override
    public String getName() {
        return "Shipyards Custom Block Models (Layers / Corner Stairs / Vertical Slabs)";
    }

    // =========================================================
    // Vertical layers
    // =========================================================

    private List<CompletableFuture<?>> generateVerticalLayersFromPool(DataWriter writer, VerticalLayerTexturePool pool) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (Map.Entry<Block, Identifier> entry : pool.getLayers().entrySet()) {
            futures.addAll(generateVerticalLayers(writer, entry.getKey(), entry.getValue()));
        }
        return futures;
    }

    private List<CompletableFuture<?>> generateVerticalLayers(DataWriter writer, Block block, Identifier texture) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        String name = Registries.BLOCK.getId(block).getPath();
        String textureId = normalizeTextureId(texture);
        Path root = output.getPath();

        for (int layers = 1; layers <= VerticalLayersBlock.MAX_LAYERS; layers++) {
            int t = layers * 2;
            String modelName = name + "_height" + t;

            JsonObject modelJson = makeUpFacingLayerModel(textureId, t);

            Path modelPath = root.resolve("assets")
                    .resolve(SecondDawnBlocks.MOD_ID)
                    .resolve("models")
                    .resolve("block")
                    .resolve(modelName + ".json");

            futures.add(DataProvider.writeToPath(writer, modelJson, modelPath));
        }

        JsonObject blockstate = new JsonObject();
        JsonObject variants = new JsonObject();

        for (int layers = 1; layers <= VerticalLayersBlock.MAX_LAYERS; layers++) {
            int t = layers * 2;
            String modelId = SecondDawnBlocks.MOD_ID + ":block/" + name + "_height" + t;

            variants.add("facing=up,layers=" + layers, variantJson(modelId, null, null));
            variants.add("facing=down,layers=" + layers, variantJson(modelId, 180, null));
            variants.add("facing=north,layers=" + layers, variantJson(modelId, 90, null));
            variants.add("facing=south,layers=" + layers, variantJson(modelId, 90, 180));
            variants.add("facing=east,layers=" + layers, variantJson(modelId, 90, 90));
            variants.add("facing=west,layers=" + layers, variantJson(modelId, 90, 270));
        }

        blockstate.add("variants", variants);

        Path blockstatePath = root.resolve("assets")
                .resolve(SecondDawnBlocks.MOD_ID)
                .resolve("blockstates")
                .resolve(name + ".json");

        futures.add(DataProvider.writeToPath(writer, blockstate, blockstatePath));

        JsonObject itemModel = new JsonObject();
        itemModel.addProperty("parent", SecondDawnBlocks.MOD_ID + ":block/" + name + "_height2");

        Path itemPath = root.resolve("assets")
                .resolve(SecondDawnBlocks.MOD_ID)
                .resolve("models")
                .resolve("item")
                .resolve(name + ".json");

        futures.add(DataProvider.writeToPath(writer, itemModel, itemPath));

        return futures;
    }

    // =========================================================
    // Corner stairs
    // =========================================================

    private List<CompletableFuture<?>> generateCornerStairsFromPool(DataWriter writer, VerticalLayerTexturePool pool) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (Map.Entry<Block, Identifier> entry : pool.getLayers().entrySet()) {
            futures.addAll(generateCornerStairs(writer, entry.getKey(), entry.getValue()));
        }
        return futures;
    }

    private List<CompletableFuture<?>> generateCornerStairs(DataWriter writer, Block block, Identifier texture) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        String name = Registries.BLOCK.getId(block).getPath();
        String textureId = normalizeTextureId(texture);
        Path root = output.getPath();

        String blockModelId = SecondDawnBlocks.MOD_ID + ":block/" + name;

        JsonObject modelJson = makeCornerStairsModel(textureId);

        Path modelPath = root.resolve("assets")
                .resolve(SecondDawnBlocks.MOD_ID)
                .resolve("models")
                .resolve("block")
                .resolve(name + ".json");

        futures.add(DataProvider.writeToPath(writer, modelJson, modelPath));

        JsonObject blockstate = new JsonObject();
        JsonObject variants = new JsonObject();

        variants.add("facing=north", variantJson(blockModelId, null, null));
        variants.add("facing=east", variantJson(blockModelId, null, 90));
        variants.add("facing=south", variantJson(blockModelId, null, 180));
        variants.add("facing=west", variantJson(blockModelId, null, 270));

        blockstate.add("variants", variants);

        Path blockstatePath = root.resolve("assets")
                .resolve(SecondDawnBlocks.MOD_ID)
                .resolve("blockstates")
                .resolve(name + ".json");

        futures.add(DataProvider.writeToPath(writer, blockstate, blockstatePath));

        JsonObject itemModel = new JsonObject();
        itemModel.addProperty("parent", blockModelId);

        Path itemPath = root.resolve("assets")
                .resolve(SecondDawnBlocks.MOD_ID)
                .resolve("models")
                .resolve("item")
                .resolve(name + ".json");

        futures.add(DataProvider.writeToPath(writer, itemModel, itemPath));

        return futures;
    }

    // =========================================================
    // Vertical slabs
    // =========================================================

    private List<CompletableFuture<?>> generateVerticalSlabsFromPool(DataWriter writer, VerticalSlabPool pool) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (Map.Entry<Block, VerticalSlabPool.Spec> entry : pool.entries().entrySet()) {
            futures.addAll(generateVerticalSlabs(writer, entry.getKey(), entry.getValue().texture()));
        }
        return futures;
    }

    private List<CompletableFuture<?>> generateVerticalSlabs(DataWriter writer, Block block, Identifier texture) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        String name = Registries.BLOCK.getId(block).getPath();
        String textureId = normalizeTextureId(texture);
        Path root = output.getPath();

        String halfModelId = SecondDawnBlocks.MOD_ID + ":block/" + name;
        String doubleModelId = SecondDawnBlocks.MOD_ID + ":block/" + name + "_double";

        JsonObject halfModel = makeVerticalSlabHalfModel(textureId);
        JsonObject doubleModel = makeFullCubeModel(textureId);

        Path halfModelPath = root.resolve("assets")
                .resolve(SecondDawnBlocks.MOD_ID)
                .resolve("models")
                .resolve("block")
                .resolve(name + ".json");

        Path doubleModelPath = root.resolve("assets")
                .resolve(SecondDawnBlocks.MOD_ID)
                .resolve("models")
                .resolve("block")
                .resolve(name + "_double.json");

        futures.add(DataProvider.writeToPath(writer, halfModel, halfModelPath));
        futures.add(DataProvider.writeToPath(writer, doubleModel, doubleModelPath));

        JsonObject blockstate = new JsonObject();
        JsonObject variants = new JsonObject();

        // HALF
        variants.add("facing=north,type=half,waterlogged=false", variantJson(halfModelId, null, 0));
        variants.add("facing=east,type=half,waterlogged=false", variantJson(halfModelId, null, 90));
        variants.add("facing=south,type=half,waterlogged=false", variantJson(halfModelId, null, 180));
        variants.add("facing=west,type=half,waterlogged=false", variantJson(halfModelId, null, 270));

        variants.add("facing=north,type=half,waterlogged=true", variantJson(halfModelId, null, 0));
        variants.add("facing=east,type=half,waterlogged=true", variantJson(halfModelId, null, 90));
        variants.add("facing=south,type=half,waterlogged=true", variantJson(halfModelId, null, 180));
        variants.add("facing=west,type=half,waterlogged=true", variantJson(halfModelId, null, 270));

        // DOUBLE
        variants.add("facing=north,type=double,waterlogged=false", variantJson(doubleModelId, null, null));
        variants.add("facing=east,type=double,waterlogged=false", variantJson(doubleModelId, null, null));
        variants.add("facing=south,type=double,waterlogged=false", variantJson(doubleModelId, null, null));
        variants.add("facing=west,type=double,waterlogged=false", variantJson(doubleModelId, null, null));

        variants.add("facing=north,type=double,waterlogged=true", variantJson(doubleModelId, null, null));
        variants.add("facing=east,type=double,waterlogged=true", variantJson(doubleModelId, null, null));
        variants.add("facing=south,type=double,waterlogged=true", variantJson(doubleModelId, null, null));
        variants.add("facing=west,type=double,waterlogged=true", variantJson(doubleModelId, null, null));

        blockstate.add("variants", variants);

        Path blockstatePath = root.resolve("assets")
                .resolve(SecondDawnBlocks.MOD_ID)
                .resolve("blockstates")
                .resolve(name + ".json");

        futures.add(DataProvider.writeToPath(writer, blockstate, blockstatePath));

        JsonObject itemModel = new JsonObject();
        itemModel.addProperty("parent", halfModelId);

        Path itemPath = root.resolve("assets")
                .resolve(SecondDawnBlocks.MOD_ID)
                .resolve("models")
                .resolve("item")
                .resolve(name + ".json");

        futures.add(DataProvider.writeToPath(writer, itemModel, itemPath));

        return futures;
    }

    // =========================================================
    // Model builders
    // =========================================================

    private static String normalizeTextureId(Identifier id) {
        String path = id.getPath();

        while (path.startsWith("block/block/")) {
            path = path.substring("block/".length());
        }

        if (!path.startsWith("block/")) {
            path = "block/" + path;
        }

        return id.getNamespace() + ":" + path;
    }

    private JsonObject variantJson(String model, Integer x, Integer y) {
        JsonObject v = new JsonObject();
        v.addProperty("model", model);
        if (x != null) v.addProperty("x", x);
        if (y != null) v.addProperty("y", y);
        return v;
    }

    private JsonObject makeUpFacingLayerModel(String textureId, int t) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "block/thin_block");

        JsonObject textures = new JsonObject();
        textures.addProperty("texture", textureId);
        textures.addProperty("particle", "#texture");
        root.add("textures", textures);

        JsonObject element = new JsonObject();

        JsonArray from = new JsonArray();
        from.add(0); from.add(0); from.add(0);

        JsonArray to = new JsonArray();
        to.add(16); to.add(t); to.add(16);

        element.add("from", from);
        element.add("to", to);

        JsonObject faces = new JsonObject();
        faces.add("down", faceWithUvCull("#texture", 0, 0, 16, 16, "down"));
        faces.add("up", faceWithUv("#texture", 0, 0, 16, 16));

        int v0 = 16 - t;
        faces.add("north", faceWithUvCull("#texture", 0, v0, 16, 16, "north"));
        faces.add("south", faceWithUvCull("#texture", 0, v0, 16, 16, "south"));
        faces.add("west", faceWithUvCull("#texture", 0, v0, 16, 16, "west"));
        faces.add("east", faceWithUvCull("#texture", 0, v0, 16, 16, "east"));

        element.add("faces", faces);

        JsonArray elements = new JsonArray();
        elements.add(element);
        root.add("elements", elements);

        return root;
    }

    private JsonObject makeCornerStairsModel(String textureId) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "minecraft:block/block");

        JsonObject textures = new JsonObject();
        textures.addProperty("texture", textureId);
        textures.addProperty("particle", "#texture");
        root.add("textures", textures);

        JsonArray elements = new JsonArray();

        // North-facing base shape:
        // back half
        elements.add(cuboid(0, 0, 8, 16, 16, 16, "#texture"));

        // right half
        elements.add(cuboid(8, 0, 0, 16, 16, 16, "#texture"));

        root.add("elements", elements);
        return root;
    }

    private JsonObject makeVerticalSlabHalfModel(String textureId) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "minecraft:block/block");

        JsonObject textures = new JsonObject();
        textures.addProperty("texture", textureId);
        textures.addProperty("particle", "#texture");
        root.add("textures", textures);

        JsonArray elements = new JsonArray();
        elements.add(cuboid(0, 0, 8, 16, 16, 16, "#texture"));
        root.add("elements", elements);

        return root;
    }

    private JsonObject makeFullCubeModel(String textureId) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "minecraft:block/cube_all");

        JsonObject textures = new JsonObject();
        textures.addProperty("all", textureId);
        root.add("textures", textures);

        return root;
    }

    private JsonObject cuboid(int x1, int y1, int z1, int x2, int y2, int z2, String textureRef) {
        JsonObject element = new JsonObject();

        JsonArray from = new JsonArray();
        from.add(x1); from.add(y1); from.add(z1);

        JsonArray to = new JsonArray();
        to.add(x2); to.add(y2); to.add(z2);

        element.add("from", from);
        element.add("to", to);

        JsonObject faces = new JsonObject();
        faces.add("down", face(textureRef));
        faces.add("up", face(textureRef));
        faces.add("north", face(textureRef));
        faces.add("south", face(textureRef));
        faces.add("west", face(textureRef));
        faces.add("east", face(textureRef));

        element.add("faces", faces);
        return element;
    }

    private JsonObject face(String textureRef) {
        JsonObject f = new JsonObject();
        f.addProperty("texture", textureRef);
        return f;
    }

    private JsonObject faceWithUv(String textureRef, int u0, int v0, int u1, int v1) {
        JsonObject f = new JsonObject();
        f.addProperty("texture", textureRef);

        JsonArray uv = new JsonArray();
        uv.add(u0); uv.add(v0); uv.add(u1); uv.add(v1);
        f.add("uv", uv);

        return f;
    }

    private JsonObject faceWithUvCull(String textureRef, int u0, int v0, int u1, int v1, String cullface) {
        JsonObject f = faceWithUv(textureRef, u0, v0, u1, v1);
        f.addProperty("cullface", cullface);
        return f;
    }
}