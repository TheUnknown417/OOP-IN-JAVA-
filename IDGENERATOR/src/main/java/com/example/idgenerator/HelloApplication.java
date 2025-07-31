package com.example.idgenerator;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

import static javafx.application.Application.launch;

public class
HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        VBox vbox = new VBox(50);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(new Background(new BackgroundFill(Color.CADETBLUE , CornerRadii.EMPTY , Insets.EMPTY)));

        TextField name = new TextField();
        name.setFont(Font.font("Magenta" , FontWeight.BOLD , null , 15));
        name.setPrefHeight(35);
        name.setPromptText("Enter The User Name");
        name.setFocusTraversable(false);

        HBox hbox = new HBox(10);


        Label label_game = new Label("Chose Game :");
        label_game.setFont(Font.font("Roberto" , FontWeight.BOLD , null , 18));

        ComboBox<String> game_com_box = new ComboBox<>();
        ObservableList<String> list = game_com_box.getItems();
        list.addAll("OS","FK","MW","PM","VB","GT","JU","GV","UP","EG");
        game_com_box.setVisibleRowCount(5);

        hbox.getChildren().addAll(label_game , game_com_box);



        Button b1 = new Button("Generate");
        b1.setAlignment(Pos.CENTER);
        b1.setFont(Font.font("New Times Roman" , FontWeight.BOLD , null , 18));
        b1.setBorder(new Border(new BorderStroke(Color.GRAY , BorderStrokeStyle.SOLID , CornerRadii.EMPTY ,BorderStroke.THICK)));
        b1.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW , CornerRadii.EMPTY , Insets.EMPTY)));

        TextArea textArea = new TextArea();

        b1.setOnAction(e->{

            String first_name = name.getText();
            String textWithoutSpaces = first_name.replaceAll("\\s+", "");
            String game = game_com_box.getSelectionModel().getSelectedItem().toLowerCase();
            String pass = "Password : " +generateRandomPassword(6);

            Random random = new Random();
            int num = random.nextInt(900)+100;

            String id ="Name      : " + textWithoutSpaces + num+game+"\n";

            //link
            String link = "";
            switch(game)
            {
                case "os" -> link = "http://start.orionstars.vip:8580/index.html";


                case "fk"-> link = "http://start.firekirin.xyz:8580/index.html";

                case "eg"-> link = "https://www.egame99.club";

                case "mw"-> link = "https://milkywayapp.xyz/";

                case "pm"-> link = "https://pandamaster.vip:8888/index.html";

                case "up"-> link = "www.ultrapanda.mobi/";

                case "vb"-> link = "http://www.vblink777.club/";

                case "gt"-> link = "https://www.goldentreasure.mobi";

                case "ju"-> link = "https://dl.juwa777.com/";

                case "gv"-> link = "http://download.gamevault999.com1\n" +
                            "Note: Kindly remove 1 from the end of the link and then use it.";

            }

            String info = id+pass+"\n"+link;



            textArea.setText(info);







        });


        vbox.getChildren().addAll(name , hbox , b1 ,textArea);



        Scene scene = new Scene(vbox, 500, 440);
        scene.setOnMouseClicked(mouseEvent -> {

            vbox.requestFocus();
        });
        stage.setTitle("ID Generator");
        Image icon = new Image(getClass().getResourceAsStream("/images/jackpot-machine.png"));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    // Method to generate a random password of specified length
    public static String generateRandomPassword(int length) {
        // Define all possible characters for the password
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";

        // Create an instance of Random class
        Random random = new Random();

        // Initialize a StringBuilder to build the password
        StringBuilder passwordBuilder = new StringBuilder();

        // Generate random lowercase letters and append them to the password
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(lowerCaseLetters.length());
            char randomChar = lowerCaseLetters.charAt(randomIndex);
            passwordBuilder.append(randomChar);
        }

        // Convert StringBuilder to String and return the generated password
        return passwordBuilder.toString();
    }





    public static void main(String[] args) {
        launch();
    }
}