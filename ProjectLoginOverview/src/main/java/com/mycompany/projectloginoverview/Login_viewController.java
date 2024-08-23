/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.projectloginoverview;

import java.net.URL;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ngo
 */
/**
 * Controller for the login view. Handles user login and navigation to other
 * views.
 */
public class Login_viewController implements Initializable {

    @FXML
    private TextField loginName;
    @FXML
    private PasswordField loginPassword;

    private Parent root;
    private Scene scene;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Handles the sign-in action. Validates the user's credentials and
     * navigates to the overview view if successful.
     *
     * @param event Action event triggered by the sign-in button
     */
    @FXML
    private void signIn(ActionEvent event) {
        String userName = loginName.getText();
        String password = loginPassword.getText();

        UsersDAO usersDAO = new UsersDAO();// Create an instance of UsersDAO to interact with the database
        boolean isValidUser = usersDAO.validateUser(userName, password); // Validate the user's credentials

        if (userName.isBlank() || password.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter both username and password");
            alert.showAndWait();
        } else if (isValidUser) {
            try {
                // Load the overview view if the user is valid
                FXMLLoader loader = new FXMLLoader(getClass().getResource("overview.fxml"));
                root = loader.load();

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Unable to load the overview view");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setContentText("Invalid username or password");
            alert.showAndWait();
        }
    }

    /**
     * Handles the sign-up action. Navigates to the registration view.
     *
     * @param event Action event triggered by the sign-up button
     */
    @FXML
    private void signUp(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register_view.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Unable to load the registration view");
            alert.showAndWait();
        }
    }
}
