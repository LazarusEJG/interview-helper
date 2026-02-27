package com.model;

import java.util.ArrayList;

public class UserList {
    private static UserList instance;
    private ArrayList<User> users;

    private UserList() {

    }
    
    public static UserList getInstance() {
        return instance; // temporary return statement
    }

    public User getUser(String username, String password) {
        return null; // temporary return statement
    }

    public boolean addUser(String eMail, String username, String password) {
        return true; // temporary return statement
    }

    public void removeUser(User user) {

    }

    public ArrayList<User> getUsers() {
        return null; // temporary return statement
    }

    public void save(String filename) {
        
    }
}