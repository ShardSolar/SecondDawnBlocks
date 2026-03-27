package net.seconddawnblocks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.seconddawnblocks.groups.PanelGroup;

import java.util.concurrent.CompletableFuture;

public class ModEnglishLangProvider extends FabricLanguageProvider {

    public ModEnglishLangProvider(
            FabricDataOutput dataOutput,
            CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        super(dataOutput, registriesFuture);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registries, TranslationBuilder translationBuilder) {
        for (String panel : PanelGroup.PANELS) {
            String nice = prettify(panel);

            translationBuilder.add(blockKey(panel), nice);
            translationBuilder.add(blockKey(panel + "_stairs"), nice + " Stairs");
            translationBuilder.add(blockKey(panel + "_slab"), nice + " Slab");
            translationBuilder.add(blockKey(panel + "_wall"), nice + " Wall");
            translationBuilder.add(blockKey(panel + "_trapdoor"), nice + " Trapdoor");
            translationBuilder.add(blockKey(panel + "_layer"), nice + " Layer");
            translationBuilder.add(blockKey(panel + "_corner_stairs"), nice + " Corner Stairs");
            translationBuilder.add(blockKey(panel + "_vertical_slab"), nice + " Vertical Slab");
            translationBuilder.add(blockKey(panel + "_quarter_vertical"), nice + " Vertical Quarter Block");
            translationBuilder.add(blockKey(panel + "_quarter_horizontal"), nice + " Horizontal Quarter Block");
        }

        translationBuilder.add("itemGroup.seconddawnblocks.08panels", "Panels");
    }

    private static String blockKey(String path) {
        return "block.seconddawnblocks." + path;
    }

    private static String prettify(String raw) {
        String[] parts = raw.split("_");
        StringBuilder out = new StringBuilder();

        for (String part : parts) {
            if (part.isEmpty()) continue;
            if (!out.isEmpty()) out.append(" ");
            out.append(capitalizeSmart(part));
        }

        return out.toString();
    }

    private static String capitalizeSmart(String word) {
        return switch (word) {
            case "oak" -> "Oak";
            case "spruce" -> "Spruce";
            case "birch" -> "Birch";
            case "jungle" -> "Jungle";
            case "acacia" -> "Acacia";
            case "cherry" -> "Cherry";
            case "bamboo" -> "Bamboo";
            case "crimson" -> "Crimson";
            case "warped" -> "Warped";
            case "dark" -> "Dark";
            case "light" -> "Light";
            case "gray" -> "Gray";
            case "grey" -> "Grey";
            case "blue" -> "Blue";
            case "red" -> "Red";
            case "green" -> "Green";
            case "yellow" -> "Yellow";
            case "orange" -> "Orange";
            case "purple" -> "Purple";
            case "pink" -> "Pink";
            case "cyan" -> "Cyan";
            case "lime" -> "Lime";
            case "magenta" -> "Magenta";
            case "white" -> "White";
            case "black" -> "Black";
            case "brown" -> "Brown";
            case "alt" -> "Alt";
            default -> Character.toUpperCase(word.charAt(0)) + word.substring(1);
        };
    }
}