/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.projectloginoverview;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ngo
 */
public class Register_viewController implements Initializable {

    @FXML
    private TextField registerName;
    @FXML
    private TextField registerPassword;
    @FXML
    private ChoiceBox<String> registerSex;

    /**
     * Initializes the controller class.
     */
    private Parent root;
    private Scene scene;
    private Stage stage;

    private int countMen;
    private int countFemale;

    private String[] sex = {"male", "female"};
    /*
    ObservableList is a special list that automatically updates any graphical element
    (e.g., ListView) when you add, remove, or modify items in it.
     */
    private ObservableList<Users> listOfUsers;

    /*
    users is the list that will contain all the users (objects of the User class).
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registerSex.getItems().addAll(sex);
        listOfUsers = FXCollections.observableArrayList();

    }

    /*
    When you want to display the users in another controller (OverviewController), this controller needs to somehow get access to the users list. 
    This can be done by passing this list Register_viewController to OverviewController.
     */
    @FXML
    private void signUp(ActionEvent event) {
        try {
            if (registerName.getText().isBlank() || registerPassword.getText().isBlank() || registerSex.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error someting went wrong");
                alert.setContentText("Type in name and password and choose sex... Please");
                alert.showAndWait();
            } else {
                String name1 = "John Doe";  // Například jméno
                String password1 = "password123";  // Například heslo
                String sex1 = "male";  // Pohlaví, např. "male" nebo "female"

                /*
                When you switch from one part of the application to another (e.g., from Register_view to OverviewView), you use FXMLLoader, which loads the new scene. 
                During this process, you get access to the controller of the new scene (OverviewView), allowing you to pass the user list to it.
                 */
                String name = registerName.getText();
                String password = registerPassword.getText();
                String sex = registerSex.getValue();

                Users newUser = new Users(name, password, sex);
                Users newUser1 = new Users(name1, password1, sex1);
                listOfUsers.add(newUser);
                listOfUsers.add(newUser1);

//                int sexCount = Integer.parseInt(newUser.getUserSex());
//                System.out.println(sexCount);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("overview.fxml"));
                root = loader.load();

                OverviewController overView = loader.getController(); //loader.getController() lets you get the instance of OverviewController.
                overView.setUsers(getUsers());  // Passing the user list to OverviewController.
                //calls the setUsers method in OverviewController to pass the user list to it.

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setX(300);
                stage.setY(100);
                stage.show();

            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Can't load");
            alert.showAndWait();
        }
    }

    public ObservableList<Users> getUsers() {
        return listOfUsers;
    }

    @FXML
    private void signIn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login_view.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Someting went wrong");
            alert.setContentText("Can't load a second Calculator");
            alert.showAndWait();
        }
    }

    public Map<String, Integer> sexCount(ObservableList<Users> users) {
        int countMen = 0;
        int countFemale = 0;
        for (Users user : users) {
            if (user.getUserSex().equals("male")) {
                countMen++;
            } else if (user.getUserSex().equals("female")) {
                countFemale++;
            }
        }
        Map<String, Integer> result = new HashMap<>();
        result.put("Male", countMen);
        result.put("Female", countFemale);
        return result;
    }

}
