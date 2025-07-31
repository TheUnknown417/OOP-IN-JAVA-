package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class RecipeManager {
    private List<Recipe> recipes;

    public RecipeManager() {
        recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipes;
    }

    public Recipe searchRecipeByName(String name) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                return recipe;
            }
        }
        return null;
    }

    public List<Recipe> searchRecipesByName(String searchQuery) {
        List<Recipe> result = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                result.add(recipe);
            }
        }
        return result;
    }

    public boolean deleteRecipe(String name) {
        return recipes.removeIf(recipe -> recipe.getName().equalsIgnoreCase(name));
    }

    public boolean editRecipe(String oldName, Recipe newRecipe) {
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getName().equalsIgnoreCase(oldName)) {
                recipes.set(i, newRecipe);
                return true;
            }
        }
        return false;
    }
}
