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
/**
 * Controller for the registration view. Handles user registration and
 * navigation to other views.
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
    // Array of sex options for the ChoiceBox
    private String[] sex = {"male", "female"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registerSex.getItems().addAll(sex);
    }

    /*
    When you want to display the users in another controller (OverviewController), this controller needs to somehow get access to the users list. 
    This can be done by passing this list Register_viewController to OverviewController.
     */
    @FXML
    private void signUp(ActionEvent event) {
        try {
            // Validate input fields
            if (registerName.getText().isBlank() || registerPassword.getText().isBlank() || registerSex.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Chyba");
                alert.setContentText("Zadejte jméno, heslo a vyberte pohlaví.");
                alert.showAndWait();
            } else {
                String name = registerName.getText();
                String password = registerPassword.getText();
                String sex = registerSex.getValue();

                Users newUser = new Users(name, password, sex);

                UsersDAO usersDAO = new UsersDAO();// Create an instance of UsersDAO
                usersDAO.saveUser(newUser); // Save the new user to the database

                /*
                When you switch from one part of the application to another (e.g., from Register_view to OverviewView), you use FXMLLoader, which loads the new scene. 
                During this process, you get access to the controller of the new scene (OverviewView), allowing you to pass the user list to it.
                 */
                FXMLLoader loader = new FXMLLoader(getClass().getResource("overview.fxml"));
                root = loader.load();

                OverviewController overView = loader.getController(); //This line retrieves the controller instance for the FXML file that was loaded by FXMLLoader.
                overView.setUsers(usersDAO.getAllUsers());  //  Retrieve all users from the database
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

    /**
     * Handles the sign-in action. Navigates to the login view.
     *
     * @param event Action event triggered by the sign-in button
     */
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

    /*
         * Counts the number of male and female users in the provided list.
     * @param users ObservableList of Users objects
     * @return Map containing counts of male and female users
     */
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
        result.put("Male", countMen); // Add count of male users to the map
        result.put("Female", countFemale); // Add count of female users to the map
        return result;
    }

}
