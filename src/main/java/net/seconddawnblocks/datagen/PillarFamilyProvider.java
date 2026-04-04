package net.seconddawnblocks.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.seconddawnblocks.SecondDawnBlocks;
import net.seconddawnblocks.groups.PanelGroup;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PillarFamilyProvider implements DataProvider {

    private final FabricDataOutput output;

    public PillarFamilyProvider(FabricDataOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        Path root = output.getPath();

        for (String panelName : PanelGroup.PILLAR_BASE_PANELS) {
            String textureId = SecondDawnBlocks.MOD_ID + ":block/" + panelName;

            futures.addAll(generateStairs(writer, root, panelName, textureId));
            futures.addAll(generateSlab(writer, root, panelName, textureId));
            futures.addAll(generateWall(writer, root, panelName, textureId));
        }

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    @Override
    public String getName() {
        return "Second Dawn Blocks Pillar Family Models";
    }

    private List<CompletableFuture<?>> generateStairs(DataWriter writer, Path root, String panelName, String textureId) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        String blockName = panelName + "_stairs";
        String modelBase = SecondDawnBlocks.MOD_ID + ":block/" + blockName;
        String innerModel = modelBase + "_inner";
        String outerModel = modelBase + "_outer";

        futures.add(writeJson(writer, root, "models/block/" + blockName + ".json",
                stairsModel("minecraft:block/stairs", textureId)));
        futures.add(writeJson(writer, root, "models/block/" + blockName + "_inner.json",
                stairsModel("minecraft:block/inner_stairs", textureId)));
        futures.add(writeJson(writer, root, "models/block/" + blockName + "_outer.json",
                stairsModel("minecraft:block/outer_stairs", textureId)));

        JsonObject blockstate = new JsonObject();
        JsonObject variants = new JsonObject();

        for (String waterlogged : new String[]{"false", "true"}) {
            addStairsVariant(variants, "north", "bottom", "straight", waterlogged, modelBase, null, 0);
            addStairsVariant(variants, "south", "bottom", "straight", waterlogged, modelBase, null, 180);
            addStairsVariant(variants, "west", "bottom", "straight", waterlogged, modelBase, null, 270);
            addStairsVariant(variants, "east", "bottom", "straight", waterlogged, modelBase, null, 90);

            addStairsVariant(variants, "north", "top", "straight", waterlogged, modelBase, 180, 0);
            addStairsVariant(variants, "south", "top", "straight", waterlogged, modelBase, 180, 180);
            addStairsVariant(variants, "west", "top", "straight", waterlogged, modelBase, 180, 270);
            addStairsVariant(variants, "east", "top", "straight", waterlogged, modelBase, 180, 90);

            addStairsVariant(variants, "north", "bottom", "inner_left", waterlogged, innerModel, null, 270);
            addStairsVariant(variants, "north", "bottom", "inner_right", waterlogged, innerModel, null, 0);
            addStairsVariant(variants, "south", "bottom", "inner_left", waterlogged, innerModel, null, 180);
            addStairsVariant(variants, "south", "bottom", "inner_right", waterlogged, innerModel, null, 90);
            addStairsVariant(variants, "west", "bottom", "inner_left", waterlogged, innerModel, null, 180);
            addStairsVariant(variants, "west", "bottom", "inner_right", waterlogged, innerModel, null, 270);
            addStairsVariant(variants, "east", "bottom", "inner_left", waterlogged, innerModel, null, 0);
            addStairsVariant(variants, "east", "bottom", "inner_right", waterlogged, innerModel, null, 90);

            addStairsVariant(variants, "north", "top", "inner_left", waterlogged, innerModel, 180, 270);
            addStairsVariant(variants, "north", "top", "inner_right", waterlogged, innerModel, 180, 0);
            addStairsVariant(variants, "south", "top", "inner_left", waterlogged, innerModel, 180, 180);
            addStairsVariant(variants, "south", "top", "inner_right", waterlogged, innerModel, 180, 90);
            addStairsVariant(variants, "west", "top", "inner_left", waterlogged, innerModel, 180, 180);
            addStairsVariant(variants, "west", "top", "inner_right", waterlogged, innerModel, 180, 270);
            addStairsVariant(variants, "east", "top", "inner_left", waterlogged, innerModel, 180, 0);
            addStairsVariant(variants, "east", "top", "inner_right", waterlogged, innerModel, 180, 90);

            addStairsVariant(variants, "north", "bottom", "outer_left", waterlogged, outerModel, null, 270);
            addStairsVariant(variants, "north", "bottom", "outer_right", waterlogged, outerModel, null, 0);
            addStairsVariant(variants, "south", "bottom", "outer_left", waterlogged, outerModel, null, 180);
            addStairsVariant(variants, "south", "bottom", "outer_right", waterlogged, outerModel, null, 90);
            addStairsVariant(variants, "west", "bottom", "outer_left", waterlogged, outerModel, null, 180);
            addStairsVariant(variants, "west", "bottom", "outer_right", waterlogged, outerModel, null, 270);
            addStairsVariant(variants, "east", "bottom", "outer_left", waterlogged, outerModel, null, 0);
            addStairsVariant(variants, "east", "bottom", "outer_right", waterlogged, outerModel, null, 90);

            addStairsVariant(variants, "north", "top", "outer_left", waterlogged, outerModel, 180, 270);
            addStairsVariant(variants, "north", "top", "outer_right", waterlogged, outerModel, 180, 0);
            addStairsVariant(variants, "south", "top", "outer_left", waterlogged, outerModel, 180, 180);
            addStairsVariant(variants, "south", "top", "outer_right", waterlogged, outerModel, 180, 90);
            addStairsVariant(variants, "west", "top", "outer_left", waterlogged, outerModel, 180, 180);
            addStairsVariant(variants, "west", "top", "outer_right", waterlogged, outerModel, 180, 270);
            addStairsVariant(variants, "east", "top", "outer_left", waterlogged, outerModel, 180, 0);
            addStairsVariant(variants, "east", "top", "outer_right", waterlogged, outerModel, 180, 90);
        }

        blockstate.add("variants", variants);
        futures.add(writeJson(writer, root, "blockstates/" + blockName + ".json", blockstate));

        JsonObject itemModel = new JsonObject();
        itemModel.addProperty("parent", modelBase);
        futures.add(writeJson(writer, root, "models/item/" + blockName + ".json", itemModel));

        return futures;
    }

    private List<CompletableFuture<?>> generateSlab(DataWriter writer, Path root, String panelName, String textureId) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        String blockName = panelName + "_slab";
        String modelBase = SecondDawnBlocks.MOD_ID + ":block/" + blockName;
        String modelTop = modelBase + "_top";
        String modelDouble = modelBase + "_double";

        futures.add(writeJson(writer, root, "models/block/" + blockName + ".json",
                slabModel("minecraft:block/slab", textureId)));
        futures.add(writeJson(writer, root, "models/block/" + blockName + "_top.json",
                slabModel("minecraft:block/slab_top", textureId)));
        futures.add(writeJson(writer, root, "models/block/" + blockName + "_double.json",
                cubeAllModel(textureId)));

        JsonObject blockstate = new JsonObject();
        JsonObject variants = new JsonObject();

        variants.add("type=bottom,waterlogged=false", variantJson(modelBase, null, null));
        variants.add("type=bottom,waterlogged=true", variantJson(modelBase, null, null));
        variants.add("type=top,waterlogged=false", variantJson(modelTop, null, null));
        variants.add("type=top,waterlogged=true", variantJson(modelTop, null, null));
        variants.add("type=double,waterlogged=false", variantJson(modelDouble, null, null));
        variants.add("type=double,waterlogged=true", variantJson(modelDouble, null, null));

        blockstate.add("variants", variants);
        futures.add(writeJson(writer, root, "blockstates/" + blockName + ".json", blockstate));

        JsonObject itemModel = new JsonObject();
        itemModel.addProperty("parent", modelBase);
        futures.add(writeJson(writer, root, "models/item/" + blockName + ".json", itemModel));

        return futures;
    }

    private List<CompletableFuture<?>> generateWall(DataWriter writer, Path root, String panelName, String textureId) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        String blockName = panelName + "_wall";
        String postModel = SecondDawnBlocks.MOD_ID + ":block/" + blockName + "_post";
        String sideModel = SecondDawnBlocks.MOD_ID + ":block/" + blockName + "_side";
        String sideTallModel = SecondDawnBlocks.MOD_ID + ":block/" + blockName + "_side_tall";
        String inventoryModel = SecondDawnBlocks.MOD_ID + ":block/" + blockName + "_inventory";

        futures.add(writeJson(writer, root, "models/block/" + blockName + "_post.json",
                wallModel("minecraft:block/template_wall_post", textureId)));
        futures.add(writeJson(writer, root, "models/block/" + blockName + "_side.json",
                wallModel("minecraft:block/template_wall_side", textureId)));
        futures.add(writeJson(writer, root, "models/block/" + blockName + "_side_tall.json",
                wallModel("minecraft:block/template_wall_side_tall", textureId)));
        futures.add(writeJson(writer, root, "models/block/" + blockName + "_inventory.json",
                wallModel("minecraft:block/wall_inventory", textureId)));

        JsonObject blockstate = new JsonObject();
        JsonArray multipart = new JsonArray();

        JsonObject postPart = new JsonObject();
        JsonObject whenPost = new JsonObject();
        whenPost.addProperty("up", "true");
        postPart.add("when", whenPost);
        postPart.add("apply", variantJson(postModel, null, null));
        multipart.add(postPart);

        addWallSide(multipart, "north", "low", sideModel, 0);
        addWallSide(multipart, "east", "low", sideModel, 90);
        addWallSide(multipart, "south", "low", sideModel, 180);
        addWallSide(multipart, "west", "low", sideModel, 270);

        addWallSide(multipart, "north", "tall", sideTallModel, 0);
        addWallSide(multipart, "east", "tall", sideTallModel, 90);
        addWallSide(multipart, "south", "tall", sideTallModel, 180);
        addWallSide(multipart, "west", "tall", sideTallModel, 270);

        blockstate.add("multipart", multipart);
        futures.add(writeJson(writer, root, "blockstates/" + blockName + ".json", blockstate));

        JsonObject itemModel = new JsonObject();
        itemModel.addProperty("parent", inventoryModel);
        futures.add(writeJson(writer, root, "models/item/" + blockName + ".json", itemModel));

        return futures;
    }

    private JsonObject stairsModel(String parent, String textureId) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", parent);

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", textureId);
        textures.addProperty("top", textureId);
        textures.addProperty("side", textureId);
        root.add("textures", textures);

        return root;
    }

    private JsonObject slabModel(String parent, String textureId) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", parent);

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", textureId);
        textures.addProperty("top", textureId);
        textures.addProperty("side", textureId);
        root.add("textures", textures);

        return root;
    }

    private JsonObject cubeAllModel(String textureId) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "minecraft:block/cube_all");

        JsonObject textures = new JsonObject();
        textures.addProperty("all", textureId);
        root.add("textures", textures);

        return root;
    }

    private JsonObject wallModel(String parent, String textureId) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", parent);

        JsonObject textures = new JsonObject();
        textures.addProperty("wall", textureId);
        root.add("textures", textures);

        return root;
    }

    private void addStairsVariant(JsonObject variants, String facing, String half, String shape,
                                  String waterlogged, String model, Integer x, Integer y) {
        variants.add(
                "facing=" + facing + ",half=" + half + ",shape=" + shape + ",waterlogged=" + waterlogged,
                variantJson(model, x, y)
        );
    }

    private void addWallSide(JsonArray multipart, String side, String height, String model, int y) {
        JsonObject part = new JsonObject();

        JsonObject when = new JsonObject();
        when.addProperty(side, height);
        part.add("when", when);

        JsonObject apply = variantJson(model, null, y);
        part.add("apply", apply);

        multipart.add(part);
    }

    private JsonObject variantJson(String model, Integer x, Integer y) {
        JsonObject v = new JsonObject();
        v.addProperty("model", model);

        if (x != null) v.addProperty("x", x);
        if (y != null) v.addProperty("y", y);

        v.addProperty("uvlock", true);

        return v;
    }

    private CompletableFuture<?> writeJson(DataWriter writer, Path root, String relativePath, JsonObject json) {
        Path path = root.resolve("assets")
                .resolve(SecondDawnBlocks.MOD_ID)
                .resolve(relativePath);
        return DataProvider.writeToPath(writer, json, path);
    }
}