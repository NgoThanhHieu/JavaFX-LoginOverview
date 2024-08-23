/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.projectloginoverview;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author ngo
 */
/**
 * Controller for the overview view. Displays user data in a pie chart and a
 * list view.
 */
public class OverviewController implements Initializable {

    @FXML
    private PieChart myPieChart;
    @FXML
    private ListView<Users> myListView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updatePieChart();
        updateListView();
    }

        /**
     * Sets the list of users to be displayed in the list view and pie chart.
     * @param users ObservableList of Users objects
     */
    public void setUsers(ObservableList<Users> users) {
        myListView.setItems(users);
        //In OverviewController, you create a setUsers method that takes the user list and sets it in the ListView:
    }

    private void updatePieChart() {
        UsersDAO usersDAO = new UsersDAO();
        ObservableList<Users> users = usersDAO.getAllUsers();

        int maleCount = 0;
        int femaleCount = 0;

        for (Users user : users) {
            if (user.getUserSex().equals("male")) {
                maleCount++;
            } else if (user.getUserSex().equals("female")) {
                femaleCount++;
            }
        }

        PieChart.Data maleData = new PieChart.Data("Male", maleCount);
        PieChart.Data femaleData = new PieChart.Data("Female", femaleCount);

        myPieChart.getData().setAll(maleData, femaleData);
    }

    private void updateListView() {
        UsersDAO usersDAO = new UsersDAO();
        ObservableList<Users> users = usersDAO.getAllUsers();
        myListView.setItems(users);
    }

}
