package com.mycompany.projectloginoverview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Data Access Object (DAO) for performing operations on the `users` table in
 * the database. This class includes methods for saving users, validating user
 * login, and retrieving all users.
 */
public class UsersDAO {

    /**
     * Saves a new user to the database. This method inserts a new record into
     * the `users` table using the provided User object.
     *
     * @param user User object containing the data to be saved
     */
    public void saveUser(Users user) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        // SQL statement for inserting a new user into the `users` table.
        String sql = "INSERT INTO users (username, password, sex) VALUES (?, ?, ?)";

        try {
            // Create a PreparedStatement for executing the SQL statement.
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());// Set the username parameter
            preparedStatement.setString(2, user.getUserPassword()); // Set the password parameter
            preparedStatement.setString(3, user.getUserSex());  // Set the sex parameter
            // Execute the update, which inserts the new user into the database.
            preparedStatement.executeUpdate();
            System.out.println("User saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Ensure the connection is closed after the operation is complete.
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Validates user credentials by checking if a user with the given username
     * and password exists in the database.
     *
     * @param userName Username to be validated
     * @param password Password to be validated
     * @return true if the user exists; false otherwise
     */
    public boolean validateUser(String userName, String password) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        // SQL statement for selecting a user with the given username and password.
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try {
            // Create a PreparedStatement for executing the SQL statement.
            // Only for username and password beacause we need it only for Login
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);// Set the username parameter
            preparedStatement.setString(2, password);// Set the password parameter
            // Execute the query and retrieve the result set.
            ResultSet resultSet = preparedStatement.executeQuery();
            // Return true if there is at least one result in the result set (user exists)
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;// Return false if no user was found
    }
    
     /**
     * Retrieves all users from the database. We need this for OverviewController to display on listView and PieChart.
     * @return ObservableList of Users objects representing all users in the database
     */

    public ObservableList<Users> getAllUsers() {
        ObservableList<Users> users = FXCollections.observableArrayList();// Create an ObservableList to store users
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        // SQL statement for selecting all users from the `users` table.
        String sql = "SELECT * FROM users";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
             // Execute the query and retrieve the result set.
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                 // Retrieve user details from the result set
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String sex = resultSet.getString("sex");
                
                // Create a new User object and add it to the ObservableList
                Users user = new Users(username, password, sex);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users; // Return the list of users
    }

}
