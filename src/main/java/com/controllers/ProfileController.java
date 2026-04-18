package com.controllers;

import java.io.IOException;

import com.interview.App;
import com.model.InterviewApp;
import com.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ProfileController {

    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize() {
        InterviewApp library = App.getInterviewApp();
        User user = library.getCurrentUser();
        welcomeLabel.setText("Welcome in! " + user.getUsername());

    }

    @FXML
    private void goToLogin() throws IOException {
        App.setRoot("Login");
    }

    @FXML
    void backToHome(MouseEvent event) throws IOException {
        App.setRoot("Home");
    }

}
