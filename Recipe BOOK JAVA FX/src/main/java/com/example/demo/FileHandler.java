package com.example.demo;

import java.io.*;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "recipes.dat";

    public static void saveRecipes(List<Recipe> recipes) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(recipes);
        }
    }

    public static List<Recipe> loadRecipes() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Recipe>) ois.readObject();
        }
    }
}

