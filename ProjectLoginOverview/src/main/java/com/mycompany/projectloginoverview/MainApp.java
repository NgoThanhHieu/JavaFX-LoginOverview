package com.mycompany.projectloginoverview;

import java.io.IOException;
import java.sql.Connection;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ngo
 */
public class MainApp extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login_view"));
        stage.setTitle("Users Overview program");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        /*
        Purpose: Creates an instance of the DatabaseConnection class.
        Reason: This class is responsible for handling the details of connecting to the database, including managing connection strings, credentials, 
        and  the JDBC driver.
        */
        Connection connection = dbConnection.getConnection();
        /*
        Purpose: Retrieves a Connection object from the DatabaseConnection instance.
        Reason: This Connection object represents an active connection to the database. 
        It allows the application to perform SQL operations such as queries and updates.
        */
        dbConnection.closeConnection(connection);
        //Closes the database connection that was previously opened.
        launch();
    }
}
