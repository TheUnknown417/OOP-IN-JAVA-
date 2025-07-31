package com.example.demo3;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SalaryCal extends Application {
    private final int OFF_ALLOWED = 4 ;
    @Override
    public void start(Stage stage) throws IOException {
        GridPane grid = new GridPane();
        grid.setOnMouseClicked(e->{

            grid.requestFocus();
        });
        grid.setPadding(new Insets(20));
        grid.setHgap(20);
        grid.setVgap(35);
        grid.setBackground(new Background(new BackgroundFill(Color.DARKGRAY , CornerRadii.EMPTY , Insets.EMPTY)));

    //name
        Label name = new Label("Name : ");
        name.setFont(Font.font("Roberto" , FontWeight.BOLD , null , 20));
        GridPane.setConstraints(name , 0 , 0);
        TextField name_textfield = new TextField();
        name_textfield.setPromptText("ENTER YOUR FULL NAME HERE");
        name_textfield.setFocusTraversable(false);
        name_textfield.setPrefSize(200 , 30);
        GridPane.setConstraints(name_textfield , 1 , 0);



        //Gender
        Label gender = new Label("Gender : ");

        gender.setFont(Font.font("Roberto" , FontWeight.BOLD , null , 20));

        RadioButton male = new RadioButton("MALE");
        RadioButton female = new RadioButton("FEMALE");
        GridPane.setConstraints(gender , 0 , 1);
        GridPane.setConstraints(male , 1 , 1);
        GridPane.setConstraints(female , 2 , 1);

        ToggleGroup toggleGroup = new ToggleGroup() ;
        male.setToggleGroup(toggleGroup);
        female.setToggleGroup(toggleGroup);

        //month
        Label month = new Label("Month : ");
        month.setFont(Font.font("Roberto" , FontWeight.BOLD , null , 20));
        ComboBox<String> month_list = new ComboBox<>();
        ObservableList<String> list = month_list.getItems();
        list.addAll("January" , "February" , "March", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        month_list.setVisibleRowCount(6);
        GridPane.setConstraints(month , 0 , 3);
        GridPane.setConstraints(month_list , 1 , 3);





        //Shift
        Label shift = new Label("Select Shift : ");
        shift.setFont(Font.font("Roberto" , FontWeight.BOLD , null , 20));
        ComboBox<String> shift_list = new ComboBox<>();
        ObservableList<String> list1 = shift_list.getItems();
        list1.addAll("Morning " , "Evening" , "Night");
        GridPane.setConstraints(shift , 0 , 4);
        GridPane.setConstraints(shift_list , 1 , 4);


        // basic Salary
        Label sal = new Label("Basic Salary : ");
        sal.setFont(Font.font("Roberto" , FontWeight.BOLD , null , 20));
        GridPane.setConstraints(sal , 0 , 5);
        TextField sal_textfield = new TextField();
        sal_textfield.setPromptText("ENTER YOUR BASIC SALARY HERE");
        sal_textfield.setFocusTraversable(false);
        sal_textfield.setPrefSize(200 , 30);
        GridPane.setConstraints(sal_textfield , 1 , 5);



        //OFFS
        Label offs = new Label("OFFS      : ");
        offs.setFont(Font.font("Roberto" , FontWeight.BOLD , null , 20));

        Spinner<Double> offs_spin = new Spinner<>();
        SpinnerValueFactory<Double> valueFactory =
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, 0, 1.0);
        offs_spin.setValueFactory(valueFactory);

        GridPane.setConstraints(offs , 0 , 6);
        GridPane.setConstraints(offs_spin , 1 , 6);

        //fine
        Label fine = new Label("Fine : ");
        fine.setFont(Font.font("Roberto" , FontWeight.BOLD , null , 20));
        GridPane.setConstraints(fine , 0 , 7);
        TextField fine_textfield = new TextField();
        fine_textfield.setPromptText("ENTER FINE IF ANY");
        fine_textfield.setFocusTraversable(false);
        fine_textfield.setPrefSize(200 , 30);
        GridPane.setConstraints(fine_textfield , 1 , 7);




        // Calculate
        Button button = new Button("CALCULATE");
        GridPane.setConstraints(button , 2 , 8);

        button.setOnAction(e->{
            //CHECKING FOR EMPTY FIELDS
            if (name_textfield.getText().isEmpty() || (!male.isSelected() && !female.isSelected()) ||  shift_list.getSelectionModel().getSelectedItem() == null || sal_textfield.getText().isEmpty() || offs_spin.getValue()==null) {
                showalert(Alert.AlertType.ERROR, "Error", "No empty field is allowed");
                return;
            }


            String s = name_textfield.getText();
            String g = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
            String sh = shift_list.getValue();
            String mon = month_list.getValue();
            double sl = Double.parseDouble(sal_textfield.getText());
            double of = offs_spin.getValue();
            double f = Double.parseDouble(fine_textfield.getText());

            double final_sal ;

            if(of>OFF_ALLOWED) {
                double deduction = ( (sl / 30) * ( of - OFF_ALLOWED) ) ;
                 final_sal = sl - deduction - f ;
            }
            else final_sal = sl - f;




            try {
                save_info_tofile(s,g,sh,mon,sl,of,f,final_sal);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });










        grid.getChildren().addAll(name, name_textfield , gender ,male , female , shift , shift_list,sal, sal_textfield ,offs , offs_spin ,fine , fine_textfield , button , month , month_list);



        stage.setTitle("Salary Calculator");
        Image icon = new Image(getClass().getResourceAsStream("/images/salary.png"));
        stage.getIcons().add(icon);
        Scene scene = new Scene(grid , 550 , 550);
        stage.setScene(scene);
        stage.show();
    }

    private void showalert(Alert.AlertType alertType , String title , String message)
    {
        Alert alertType1 = new Alert(alertType);
        alertType1.setTitle(title);
        alertType1.setContentText(message);
        alertType1.showAndWait();


    }
    private void save_info_tofile(String name , String gender , String shift , String month , double basic , double offs , double fine , double total_sal) throws IOException {
        String filename = "MemberInfo.txt";
        String complete_Info = name + "," + gender + ","  + shift + "," + month + "," + basic + "," + offs + "," + fine + "," + total_sal ;

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

    public static void main(String[] args) {
        launch();
    }
}