/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.projectloginoverview;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
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
    }

    public void setUsers(ObservableList<Users> users) {
        myListView.setItems(users);
        //In OverviewController, you create a setUsers method that takes the user list and sets it in the ListView:
        updatePieChart(users);
    }
    
   

    public void updatePieChart(ObservableList<Users> users) {
        Register_viewController registerController = new Register_viewController();
        Map<String, Integer> count = registerController.sexCount(users);
        int countMen = count.get("Male");
        int countFemale = count.get("Female");

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Male", countMen),
                new PieChart.Data("Female", countFemale)
        );
        myPieChart.setData(pieChartData);
        System.out.println("male"+countMen + "Female"+countFemale);
    }

}
