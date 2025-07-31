package com.example.myfinalpractice;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class Mainwindow extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        //WELCOME MESSAGE

        Label welcomne = new Label("WELCOME TO FITNESS CLUB");
        welcomne.setFont(Font.font("Roboto" , FontWeight.BOLD , 20));
        welcomne.setTextFill(Color.LIGHTSEAGREEN);

        //ADD BUTTON
        Button add_mem = new Button("Add Member");
        add_mem.setFont(Font.font("Times New Roman", FontWeight.BOLD , null  , 20));
        add_mem.setBackground(new Background(new BackgroundFill(Color.rgb(112,128,144) , CornerRadii.EMPTY , Insets.EMPTY)));
        add_mem.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5) )));

        // ADD BUTTON EVENT
        add_mem.setOnAction(e-> {
            try {
                OpenAddNewMemberform();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });


        //VIEW BUTTON
        Button view_mem = new Button("View Members");
        view_mem.setFont(Font.font("Times New Roman", FontWeight.BOLD , null  , 20));
        view_mem.setBackground(new Background(new BackgroundFill(Color.rgb(112,128,144) , CornerRadii.EMPTY , Insets.EMPTY)));
        view_mem.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5) )));

        //VIEW BUTTON EVENT
        view_mem.setOnAction(event -> {
            try {
                OpenViewForm();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });




        //ROOT , SCENE , STAGE
        VBox vBox = new VBox(20);
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(25,25,112) , CornerRadii.EMPTY , Insets.EMPTY)));
        vBox.getChildren().addAll(welcomne ,add_mem, view_mem);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene( vBox ,320, 240);
        stage.setTitle("Fitness Reservation");
        Image icon = new Image(getClass().getResourceAsStream("/images/exercise-routine.png"));
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();
    }



    //ADD Button click honay pr yeah call hoga
    private void OpenAddNewMemberform() throws Exception {
        AddNewMember form = new AddNewMember();
            form.start(new Stage());

    }



    //View Button click honay pr yeah call hoga
    private void OpenViewForm() throws Exception {
        ViewMember form = new ViewMember();
        form.start(new Stage());


    }



    public static void main(String[] args) {
        launch();
    }
}