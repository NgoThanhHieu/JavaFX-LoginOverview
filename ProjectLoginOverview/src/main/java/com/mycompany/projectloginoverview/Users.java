/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectloginoverview;

/**
 *
 * @author ngo
 */
public class Users{
    
    private String userName;
    private String userPassword;
    private String userSex;
    

    public Users(String userName, String userPassword, String userSex) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userSex = userSex;
    }

    @Override
    public String toString() {
        return "User: name = " + userName + ", Password = " + userPassword + ", sex = " + userSex;
    }
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
    
      
    
}
