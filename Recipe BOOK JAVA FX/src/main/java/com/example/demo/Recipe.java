package com.example.demo;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    private String name;
    private List<String> ingredients;
    private String instructions;


    public Recipe(String name, List<String> ingredients, String instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {

        // Join the ingredients list into a single line separated by commas
        String ingredientsLine = String.join(", ", ingredients);

        // Split instructions into lines and format as bullet points
        String[] instructionsLines = instructions.split("\\r?\\n");
        StringBuilder formattedInstructions = new StringBuilder();
        for (String line : instructionsLines) {
            formattedInstructions.append("• ").append(line).append("\n");
        }

        return "Recipe Name: " + name + "\n" +
                "Ingredients: " + "\n" + ingredientsLine + "\n" +
                "Instructions:\n" + formattedInstructions.toString();
    }
}

