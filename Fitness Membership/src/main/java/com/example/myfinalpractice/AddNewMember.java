package com.example.myfinalpractice;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class AddNewMember extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {



        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(20);
        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN , CornerRadii.EMPTY , Insets.EMPTY)));

        // Name
        Label name_label = new Label("Name                  :");
        name_label.setFont(Font.font("TIMES NEW ROMAN" , FontWeight.BOLD , 20));
        GridPane.setConstraints(name_label,0,0);

        TextField name_text_field = new TextField();
        name_text_field.setFont(Font.font("Calibre" , 15));
        name_text_field.setPromptText("Enter your name here");
        name_text_field.setFocusTraversable(false);
        GridPane.setConstraints(name_text_field,1,0);


        //Gender
        Label gender_label = new Label("Gender                :");
        GridPane.setConstraints(gender_label ,0,1);
        gender_label.setFont(Font.font("TIMES NEW ROMAN" , FontWeight.BOLD , 20));

        RadioButton male_radio_button = new RadioButton("MALE");
        GridPane.setConstraints(male_radio_button ,1,1);
        RadioButton female_radio_button = new RadioButton("FEMALE");
        GridPane.setConstraints(female_radio_button ,2,1);

        // giving toggle property to Radio Buttons (EIk TIME PR EIK HE SELECT HO SKAY GA )
        ToggleGroup toggleGroup = new ToggleGroup();
        male_radio_button.setToggleGroup(toggleGroup);
        female_radio_button.setToggleGroup(toggleGroup);


        //Membership Type
        Label Membership_label = new Label("Membership       :");
        GridPane.setConstraints(Membership_label ,0,2);
        Membership_label.setFont(Font.font("TIMES NEW ROMAN" , FontWeight.BOLD , 20));

        ComboBox<String> Membership_comboBox = new ComboBox<>();
        ObservableList<String> list = Membership_comboBox.getItems();
        list.addAll("Standard", "Premium" , "VIP");
        GridPane.setConstraints(Membership_comboBox ,1,2);


        //DatePicker
        Label Date_label = new Label("Date of Birth  :");
        GridPane.setConstraints(Date_label ,0,3);
        Date_label.setFont(Font.font("TIMES NEW ROMAN" , FontWeight.BOLD , 20));

        DatePicker datePicker = new DatePicker();
        GridPane.setConstraints(datePicker ,1,3);



        // Save Button
        Button save_button = new Button("SAVE");
        save_button.setFont(Font.font("MAGENTO" , FontWeight.BOLD , 20));
        save_button.setBorder(Border.stroke(Color.BLACK));
        GridPane.setConstraints(save_button ,1,5);

       save_button.setOnAction(e->{
            //CHECKING FOR EMPTY FIELDS
           if (name_text_field.getText().isEmpty() || (!male_radio_button.isSelected() && !female_radio_button.isSelected()) || datePicker.getValue() == null || Membership_comboBox.getSelectionModel().getSelectedItem() == null) {
               showalert(Alert.AlertType.ERROR, "Error", "No empty field is allowed");
               return;
           }


           String name = name_text_field.getText();
            String gender = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
            String ship_type = Membership_comboBox.getValue();
            String dob = datePicker.getValue().toString();


           try {
               save_info_tofile(name , gender  , dob , ship_type);
           } catch (IOException ex) {
               throw new RuntimeException(ex);
           }


       });




        pane.getChildren().addAll(name_label , name_text_field , gender_label , male_radio_button , female_radio_button , Membership_label , Membership_comboBox , Date_label ,datePicker , save_button);

        primaryStage.setTitle("ADD MEMBER");
        Scene scene = new Scene(pane ,500 ,500 );
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    private void save_info_tofile(String name , String gender , String membership_type , String dob) throws IOException {
        String filename = "MemberInfo.txt";
        String complete_Info = name + "," + gender + ","  + membership_type + "," + dob ;

        try( BufferedWriter writer = new BufferedWriter(new FileWriter(filename , true)))
        {
            writer.write(complete_Info);
            writer.newLine();
            showalert(Alert.AlertType.INFORMATION , "Success" , "Information Written to file Successfully");



        }

        catch (IOException exception)
        {

            showalert(Alert.AlertType.ERROR , "Error" , "Failed to Store the information to the file");

        }



    }




    // ALERT Message prompts

    private void showalert(Alert.AlertType alertType , String title , String message)
    {
        Alert alertType1 = new Alert(alertType);
        alertType1.setTitle(title);
        alertType1.setContentText(message);
        alertType1.showAndWait();


    }











    public static void main(String[] args)
    { launch(args);}

}
