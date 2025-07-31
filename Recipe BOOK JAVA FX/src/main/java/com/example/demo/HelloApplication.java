package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HelloApplication extends Application {

    private RecipeManager recipeManager = new RecipeManager();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Recipe Book");
        Image icon = new Image(getClass().getResourceAsStream("/images/recipe-book.png"));
        primaryStage.getIcons().add(icon);

        // Load existinAg recipes
        try {
            List<Recipe> loadedRecipes = FileHandler.loadRecipes();
            if (loadedRecipes != null) {
                recipeManager.getAllRecipes().addAll(loadedRecipes);
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load recipes.");
        }

        // Main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(10, 10, 10, 10));

        // Top menu
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #adb0e6;");
        Menu menu = new Menu("Options");
        MenuItem addRecipeMenuItem = new MenuItem("Add Recipe");
        MenuItem viewAllRecipesMenuItem = new MenuItem("View All Recipes");
        MenuItem searchRecipeMenuItem = new MenuItem("Search Recipe");
        MenuItem editRecipeMenuItem = new MenuItem("Edit Recipe");
        MenuItem deleteRecipeMenuItem = new MenuItem("Delete Recipe");
        menu.getItems().addAll(addRecipeMenuItem, viewAllRecipesMenuItem, searchRecipeMenuItem, editRecipeMenuItem, deleteRecipeMenuItem);
        menuBar.getMenus().add(menu);
        mainLayout.setTop(menuBar);

        // Center content
        TextArea textArea = new TextArea();
        textArea.setStyle("-fx-control-inner-background: lightblue;");
        textArea.setEditable(false);
        mainLayout.setCenter(textArea);

        // Welcome text
        Text welcomeText = new Text("Welcome!");
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        welcomeText.setFill(Color.DARKBLUE);

        // Sub-text
        Text subText = new Text("Have a Look At the Top left Corner");
        subText.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        subText.setFill(Color.DARKBLUE);

        // Positioning in the layout
        VBox centerContent = new VBox(welcomeText, subText);
        centerContent.setSpacing(20);
        centerContent.setPadding(new Insets(20));
        centerContent.setStyle("-fx-background-color: #e6f7ff;");
        centerContent.setAlignment(Pos.CENTER);
        mainLayout.setCenter(centerContent);

        // Main scene
        Scene mainScene = new Scene(mainLayout, 600, 400);
        primaryStage.setScene(mainScene);
        primaryStage.show();

        // Create a new scene for viewing all recipes
        BorderPane viewAllRecipesLayout = new BorderPane();
        TextArea recipesTextArea = new TextArea();
        recipesTextArea.setEditable(false);
        recipesTextArea.setStyle("-fx-control-inner-background: lightblue;");
        viewAllRecipesLayout.setCenter(recipesTextArea);

        Scene viewAllRecipesScene = new Scene(viewAllRecipesLayout, 800, 600);

        // Add back button to return to the main scene
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(mainScene));
        HBox bottomPane = new HBox(backButton);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(10));
        viewAllRecipesLayout.setBottom(bottomPane);

        // Populate recipesTextArea with all recipes
        viewAllRecipesMenuItem.setOnAction(e -> {
            StringBuilder recipesContent = new StringBuilder();
            for (Recipe recipe : recipeManager.getAllRecipes()) {
                recipesContent.append(recipe.toString()).append("\n\n");
            }
            recipesTextArea.setText(recipesContent.toString());
            primaryStage.setScene(viewAllRecipesScene);
        });

        // Event handlers
        addRecipeMenuItem.setOnAction(e -> showAddRecipeDialog());
        searchRecipeMenuItem.setOnAction(e -> showSearchRecipeDialog());
        editRecipeMenuItem.setOnAction(e -> showEditRecipeDialog());
        deleteRecipeMenuItem.setOnAction(e -> showDeleteRecipeDialog());

        // Save recipes on exit
        primaryStage.setOnCloseRequest(event -> {
            try {
                FileHandler.saveRecipes(recipeManager.getAllRecipes());
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save recipes.");
            }
        });
    }
    private void displayAllRecipes(TextArea textArea) {
        List<Recipe> recipes = recipeManager.getAllRecipes();
        StringBuilder sb = new StringBuilder();
        for (Recipe recipe : recipes) {
            sb.append(recipe).append("\n\n");
        }
        textArea.setText(sb.toString());
    }

    private void showAddRecipeDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Add Recipe");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        TextField nameField = new TextField();
        nameField.setPromptText("Recipe Name");

        TextArea ingredientsArea = new TextArea();
        ingredientsArea.setPromptText("Ingredients (comma-separated)");

        TextArea instructionsArea = new TextArea();
        instructionsArea.setPromptText("Instructions");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            List<String> ingredients = Arrays.asList(ingredientsArea.getText().split(","));
            String instructions = instructionsArea.getText();

            Recipe recipe = new Recipe(name, ingredients, instructions);
            recipeManager.addRecipe(recipe);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Recipe added successfully!");
            dialog.close();
        });

        vbox.getChildren().addAll(new Label("Name:"), nameField, new Label("Ingredients:"), ingredientsArea, new Label("Instructions:"), instructionsArea, addButton);
        vbox.setStyle("-fx-background-color: #e6adbc;");
        Scene scene = new Scene(vbox, 300, 400);
        dialog.setScene(scene);
        dialog.show();
    }

    private void showSearchRecipeDialog() {
        // Create search input and button
        TextField searchField = new TextField();
        searchField.setPromptText("Enter recipe name...");
        Button searchButton = new Button("Search");

        // Layout for dialog
        VBox layout = new VBox(10, searchField, searchButton);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        // Create and show dialog window
        Scene scene = new Scene(layout, 300, 150);
        Stage dialog = new Stage();
        dialog.setTitle("Search Recipe by Name");
        dialog.setScene(scene);
        dialog.initModality(Modality.APPLICATION_MODAL); // optional
        dialog.show();

        // Event inside dialog: what happens on Search
        searchButton.setOnAction(ev -> {
            String recipeName = searchField.getText().trim();
            List<Recipe> recipes = recipeManager.searchRecipesByName(recipeName);

            if (recipes.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "No Results", "No recipes found with the given name.");
            } else {
                showSearchResultsDialog(recipes);
            }

            dialog.close();
        });
    }

    private void showSearchResultsDialog(List<Recipe> recipes) {
        Stage dialog = new Stage();
        dialog.setTitle("Search Results");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        TextArea resultsArea = new TextArea();
        resultsArea.setEditable(false);
        resultsArea.setStyle("-fx-control-inner-background: lightblue;");

        StringBuilder sb = new StringBuilder();
        for (Recipe recipe : recipes) {
            sb.append(recipe).append("\n\n");
        }
        resultsArea.setText(sb.toString());

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> dialog.close());

        vbox.getChildren().addAll(resultsArea, closeButton);
        vbox.setStyle("-fx-background-color: #e6f2ff;");
        Scene scene = new Scene(vbox, 400, 300);
        dialog.setScene(scene);
        dialog.show();
    }

    private void showEditRecipeDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Edit Recipe");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);
        vbox.setStyle("-fx-background-color: #e6adad;");

        TextField oldNameField = new TextField();
        oldNameField.setPromptText("Current Recipe Name");

        TextField newNameField = new TextField();
        newNameField.setPromptText("New Recipe Name");

        TextArea newIngredientsArea = new TextArea();
        newIngredientsArea.setPromptText("New Ingredients (comma-separated)");

        TextArea newInstructionsArea = new TextArea();
        newInstructionsArea.setPromptText("New Instructions");

        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> {
            String oldName = oldNameField.getText();
            String newName = newNameField.getText();
            List<String> newIngredients = Arrays.asList(newIngredientsArea.getText().split(","));
            String newInstructions = newInstructionsArea.getText();

            Recipe newRecipe = new Recipe(newName, newIngredients, newInstructions);
            boolean success = recipeManager.editRecipe(oldName, newRecipe);

            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Recipe edited successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Recipe not found!");
            }
            dialog.close();
        });

        vbox.getChildren().addAll(new Label("Current Name:"), oldNameField, new Label("New Name:"), newNameField, new Label("New Ingredients:"), newIngredientsArea, new Label("New Instructions:"), newInstructionsArea, editButton);
        Scene scene = new Scene(vbox, 300, 400);
        dialog.setScene(scene);
        dialog.show();
    }

    private void showDeleteRecipeDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Delete Recipe");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);
        vbox.setStyle("-fx-background-color: #b9e6ad;");

        TextField nameField = new TextField();
        nameField.setPromptText("Recipe Name");

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            String name = nameField.getText();
            boolean success = recipeManager.deleteRecipe(name);

            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Recipe deleted successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Recipe not found!");
            }
            dialog.close();
        });

        vbox.getChildren().addAll(new Label("Recipe Name:"), nameField, deleteButton);
        Scene scene = new Scene(vbox, 300, 200);
        dialog.setScene(scene);
        dialog.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #e8e7df;");
        alert.showAndWait();
    }
}
