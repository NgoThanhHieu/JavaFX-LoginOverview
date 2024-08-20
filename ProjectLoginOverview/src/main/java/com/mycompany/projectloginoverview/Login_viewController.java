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

    @FXML
    private void signIn(ActionEvent event) {
        try {
            if (loginName.getText().isBlank() && loginPassword.getText().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error someting went wrong");
                alert.setContentText("Type in name and password please");
                alert.showAndWait();
            } else {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("overview.fxml"));
                root = loader.load();

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Can't load");
            alert.showAndWait();
        }
    }

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
            alert.setTitle("Someting went wrong");
            alert.setContentText("Can't load a second Calculator");
            alert.showAndWait();
        }
    }

}
