package com.example.myfinalpractice;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ViewMember extends Application {
    //Creating Table
    TableView<Member> table = new TableView<>();



    @Override
    public void start(Stage stage) throws Exception {

        //Creating table columns (Headings) and setting their width
        TableColumn<Member, String> name = new TableColumn<>("Name");
        name.setPrefWidth(150);
        TableColumn<Member, String> gender = new TableColumn<>("Gender");
        gender.setPrefWidth(150);
        TableColumn<Member, String>  dob= new TableColumn<>("Date of Birth");
        dob.setPrefWidth(200);
        TableColumn<Member, String> mem_type = new TableColumn<>("Member Type");
        mem_type.setPrefWidth(180);


        //setting values for columns ("jo bhi hai" , wo Member class k instance variable hain)

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        mem_type.setCellValueFactory(new PropertyValueFactory<>("mem_type"));
        dob.setCellValueFactory(new PropertyValueFactory<>("dob"));


        table.getColumns().addAll(name , gender  , dob ,mem_type );
        table.setItems(loaddata());

        //root , scene , stage

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(table);

        Scene scene = new Scene(vBox , 680 , 400);
        stage.setTitle("View Members");
        stage.setScene(scene);
        stage.show();



    }

    private ObservableList<Member> loaddata()
    {
        ObservableList<Member> members= FXCollections.observableArrayList();

        try(BufferedReader reader = new BufferedReader(new FileReader("MemberInfo.txt")))
        {
            String line ;
            while((line = reader.readLine()) != null)
            {
                String info[] = line.split(",");
                String name=info[0];
                String gender=info[1];
                String dob= info[2];
                String memType=info[3];

                members.add(new Member(name , gender , memType , dob));


            }

        }
        catch (IOException e)
        {
            showAlert(Alert.AlertType.ERROR , "Errot" , "Failed to Read from the file");
        }
            return members;

    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
